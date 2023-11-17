package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.Requests.ProxyAdminRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.ProxyAdminResponse;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;

import java.util.List;
import java.util.Optional;

public interface IProxyAdminService {
    Optional<ProxyAdmin> findByCIN(String cin);

    Optional<ProxyAdmin> findByEmail(String email);

    List<ProxyAdminResponse> findAll();

    void delete(String cin);

    Optional<ProxyAdmin> save(ProxyAdminRequest proxyAdminRequest);

    Optional<ProxyAdmin> login(String email, String password);
}
