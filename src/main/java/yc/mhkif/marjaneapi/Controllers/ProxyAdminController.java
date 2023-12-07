package yc.mhkif.marjaneapi.Controllers;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import yc.mhkif.marjaneapi.DTOs.Requests.LoginRequest;
import yc.mhkif.marjaneapi.DTOs.Requests.ProxyAdminRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.ProxyAdminResponse;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import yc.mhkif.marjaneapi.Entities.SuperAdmin;
import yc.mhkif.marjaneapi.Services.Implementations.PromotionCenterServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.ProxyAdminServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.SuperAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "marjane/api/v1")
@CrossOrigin("*")
public class ProxyAdminController {

    private ProxyAdminServiceImpl service;
    private SuperAdminServiceImpl superAdminService;
    private PromotionCenterServiceImpl promoCenterService;

    @Autowired
    public ProxyAdminController(ProxyAdminServiceImpl proxyAdminService,
                                SuperAdminServiceImpl superAdminService,
                                PromotionCenterServiceImpl promotionCenterService) {
        this.service = proxyAdminService;
        this.superAdminService = superAdminService;
        this.promoCenterService = promotionCenterService;

    }

    @PostMapping(value = "/proxies.admin/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProxyAdminResponse> login(@RequestBody LoginRequest request){
        Optional<ProxyAdmin> proxyAdmin = service.login(request.getEmail(), request.getPassword());
        return proxyAdmin.map(proxyAdminEnt -> new ResponseEntity<>(service.mapToDTO(proxyAdminEnt), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping(value = "/proxies.admin/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProxyAdminResponse> save(@RequestBody ProxyAdminRequest request, @RequestHeader Map<String,String> headers) {

        if(headers.get("token") != null){
            Optional<ProxyAdmin> proxyAdminByCin = this.service.findByCIN(request.getCin());
            Optional<ProxyAdmin> proxyAdminByEmail = this.service.findByEmail(request.getEmail());
            if(proxyAdminByCin.isPresent()){
                throw new IllegalStateException("Proxy Admin Already Exist with same CIN : "+ request.getCin());
            } else if (proxyAdminByEmail.isPresent()) {
                throw new IllegalStateException("Proxy Admin Already Exist with same Email : "+ request.getEmail());
            }
            Optional<SuperAdmin> superAdmin= this.superAdminService.findByCIN("SQ456089");
            superAdmin.ifPresent(request::setSuperAdmin);
            Optional<ProxyAdmin> proxyAdmin = this.service.save(request);
            return proxyAdmin.map(proxyAdminEnt -> new ResponseEntity<>(this.service.mapToDTO(proxyAdminEnt), HttpStatus.CREATED)).orElseGet(
                    () -> {
                        throw new IllegalStateException("Proxy Admin Could not be created due to some internal problems [500].");
                    }
            );
        }else{
            throw new IllegalStateException("Token Authentication for Super Admin Not Found ");
        }

    }


    @GetMapping(value = "/proxies_admin/promotions/pages")
    @ResponseBody
    public ResponseEntity<Page<PromotionCenter>> getPagesPromotionsProxyAdmin(@RequestParam int page, @RequestParam int size) {

        Optional<ProxyAdmin> proxyAdmin = this.service.findByCIN("LK103216");

        if(proxyAdmin.isEmpty()){
            throw new IllegalStateException("Could Not Find ProxyAdmin With This Cin : LK103216" );
        }
/*
        List<PromotionCenter> promotions = promoCenterService.findAll().stream()
                .filter(promotionCenter -> promotionCenter.getPromotion().getAdmin().equals(proxyAdmin.get())).toList();

 */
        Page<PromotionCenter> promotions = promoCenterService.getPagesByAdmin(proxyAdmin.get(), page, size);


        return ResponseEntity.ok(promotions);

    }

}
