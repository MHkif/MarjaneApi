package yc.mhkif.marjaneapi.Controllers;


import yc.mhkif.marjaneapi.DTOs.PromotionCenterDTO;
import yc.mhkif.marjaneapi.DTOs.Requests.LoginRequest;
import yc.mhkif.marjaneapi.DTOs.Requests.ManagerRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.ManagerResponse;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import yc.mhkif.marjaneapi.Services.Implementations.ManagerServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.PromotionCenterServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.ProxyAdminServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.SuperAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "marjane/api/v1")
public class ManagerController {

    private PromotionCenterServiceImpl promoCenterService;
    private ManagerServiceImpl service;
    private ProxyAdminServiceImpl proxyAdminService;



    @Autowired
    public ManagerController(PromotionCenterServiceImpl promoCenterService,
                             ManagerServiceImpl service, SuperAdminServiceImpl superAdminService,
                             ProxyAdminServiceImpl proxyAdminService) {
        this.promoCenterService = promoCenterService;
        this.service = service;
        this.proxyAdminService = proxyAdminService;
    }

    @PostMapping(value = "/managers/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ManagerResponse> login(@RequestBody LoginRequest request){
        Optional<Manager> manager = service.login(request.getEmail(), request.getPassword());
        return manager.map(managerEntity -> new ResponseEntity<>(service.mapToDTO(managerEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/managers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ManagerResponse> save(@RequestBody ManagerRequest request, @RequestHeader Map<String, String> headers){
        if(headers.get("token") != null) {
            Optional<Manager> managerByCin = this.service.findByCIN(request.getCin());
            Optional<Manager> managerByEmail = this.service.findByEmail(request.getEmail());
            if (managerByCin.isPresent()) {
                throw new IllegalStateException("Manager Already Exist with same CIN : " + request.getCin());
            } else if (managerByEmail.isPresent()) {
                throw new IllegalStateException("Manager Already Exist with same Email : " + request.getEmail());
            }

            ProxyAdmin proxyAdmin = proxyAdminService.findByCIN(headers.get("token")).orElseThrow(() -> new IllegalStateException("Proxy Admin Not Found [400]."));
            request.setAdmin(proxyAdmin);
            Optional<Manager> manager = service.save(request);
            return manager.map(managerEntity -> new ResponseEntity<>(service.mapToDTO(managerEntity), HttpStatus.CREATED)).orElseThrow(() -> new IllegalStateException("Manager Could not be created due to some internal problems [500]."));
        }else {
            throw new IllegalStateException("Token Authentication for Proxy Admin Not Found ");
        }

    }



    @GetMapping(value = "/managers/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PromotionCenterDTO>> getAllPromotionsByManager(@RequestHeader Map<String, String> headers) {

        if(headers.get("token") != null && headers.get("cin") != null) {

            if(this.service.isOutOfTime()){
                throw new IllegalStateException("En tant que manager, vous ne pouvez voir les promotions que de 8 à 12 heures .");
            }
            Optional<Manager> managerEntity =  this.service.findByCIN(headers.get("cin"));

            if(managerEntity.isEmpty()){
                throw new IllegalStateException("Could Not Find Manager With This Cin : " + headers.get("cin"));
            }

            List<PromotionCenterDTO> promotions = promoCenterService.findAllPromsByManager(managerEntity.get())
                    .stream()
                    .map(promoCenterService::mapToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(promotions);

        }else {
            throw new IllegalStateException("Token Authentication for Manager Not Found ");
        }
    }

}
