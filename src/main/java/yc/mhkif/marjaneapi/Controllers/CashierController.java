package yc.mhkif.marjaneapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yc.mhkif.marjaneapi.DTOs.Requests.CashierRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.CashierResponse;
import yc.mhkif.marjaneapi.Entities.Cashier;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import yc.mhkif.marjaneapi.Services.Implementations.CashierServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.ProxyAdminServiceImpl;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "marjane/api/v1")
public class CashierController {


    private CashierServiceImpl service;
    private ProxyAdminServiceImpl proxyAdminService;

    @Autowired
    public CashierController(CashierServiceImpl service, ProxyAdminServiceImpl proxyAdminService) {
        this.service = service;
        this.proxyAdminService = proxyAdminService;
    }

    @PostMapping(value = "/cashiers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CashierResponse> save(@RequestBody CashierRequest request, @RequestHeader Map<String, String> headers){
        if(headers.get("token") != null) {
            Optional<Cashier> cashierByCin = this.service.findByCIN(request.getCin());
            Optional<Cashier> cashierByEmail = this.service.findByEmail(request.getEmail());
            if (cashierByCin.isPresent()) {
                throw new IllegalStateException("Cashier Already Exist with same CIN : " + request.getCin());
            } else if (cashierByEmail.isPresent()) {
                throw new IllegalStateException("Cashier Already Exist with same Email : " + request.getEmail());
            }

            ProxyAdmin proxyAdmin = proxyAdminService.findByCIN(headers.get("token")).orElseThrow(() -> new IllegalStateException("Proxy Admin Not Found [400]."));
            request.setAdmin(proxyAdmin);
            Optional<Cashier> cashier = service.save(request);
            return cashier.map(cashierEntity -> new ResponseEntity<>(service.mapToDTO(cashierEntity), HttpStatus.CREATED)).orElseThrow(() -> new IllegalStateException("Cashier Could not be created due to some internal problems [500]."));
        }else {
            throw new IllegalStateException("Token Authentication for Proxy Admin Not Found ");
        }

    }
}
