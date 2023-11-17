package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.Responses.SuperAdminResponse;
import yc.mhkif.marjaneapi.Entities.SuperAdmin;

import java.util.Optional;

public interface ISuperAdminService  {
    Optional<SuperAdmin> findByCIN(String cin);

    Optional<SuperAdmin> login(String email, String password);


    SuperAdminResponse mapToDTO(SuperAdmin superAdmin);
}
