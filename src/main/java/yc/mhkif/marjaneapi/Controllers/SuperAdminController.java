package yc.mhkif.marjaneapi.Controllers;


import org.springframework.web.bind.annotation.*;
import yc.mhkif.marjaneapi.DTOs.Requests.LoginRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.SuperAdminResponse;
import yc.mhkif.marjaneapi.Entities.SuperAdmin;
import yc.mhkif.marjaneapi.Services.Implementations.SuperAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@RestController
@RequestMapping(path = "marjane/api/v1")
public class SuperAdminController  {

    private SuperAdminServiceImpl service;

    @Autowired
    public SuperAdminController(SuperAdminServiceImpl superAdminService) {
        this.service = superAdminService;
    }


    @PostMapping(value = "/super-admin/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuperAdminResponse> login(@RequestBody LoginRequest request){
        Optional<SuperAdmin> superAdmin = service.login(request.getEmail(), request.getPassword());
        return superAdmin.map(superAdminEntity -> new ResponseEntity<>(service.mapToDTO(superAdminEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
