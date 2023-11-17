package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.ManagerDTO;
import yc.mhkif.marjaneapi.DTOs.Requests.ManagerRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.ManagerResponse;
import yc.mhkif.marjaneapi.Entities.Manager;

import java.util.List;
import java.util.Optional;

public interface IManagerService {
    Optional<Manager> findByCIN(String cin);

    Optional<Manager> findByEmail(String email);

    List<ManagerResponse> findAll();

    Optional<Manager>  save(ManagerRequest t);

    void delete(String cin);

    Optional<Manager> login(String email, String password);
}
