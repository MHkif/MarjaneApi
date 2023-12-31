package yc.mhkif.marjaneapi.Services.Implementations;

import yc.mhkif.marjaneapi.DTOs.Requests.ProxyAdminRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.ProxyAdminResponse;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import yc.mhkif.marjaneapi.Repositories.ProxyAdminRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IProxyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProxyAdminServiceImpl implements IProxyAdminService {

    private ProxyAdminRepository repository;


    @Autowired
    public ProxyAdminServiceImpl(ProxyAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ProxyAdmin> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public Optional<ProxyAdmin> findByEmail(String email){
        return repository.findByEmail(email);
    }


    @Override
    public List<ProxyAdminResponse> findAll() {
         repository.findAll();
         return null;
    }

    @Override
    public Optional<ProxyAdmin> save(ProxyAdminRequest proxyAdminRequest) {
        ProxyAdmin proxyAdmin = mapToEntity(proxyAdminRequest);
        return Optional.of(repository.save(proxyAdmin));
    }

    @Override
    public Optional<ProxyAdmin> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public ProxyAdminResponse mapToDTO(ProxyAdmin proxyAdmin){
        return ProxyAdminResponse.builder()
                .cin(proxyAdmin.getCin())
                .email(proxyAdmin.getEmail())
                .password(proxyAdmin.getPassword())
                .firstName(proxyAdmin.getFirstName())
                .lastName(proxyAdmin.getLastName())
                .phone(proxyAdmin.getPhone())
                .build();
    }

    public ProxyAdmin mapToEntity(ProxyAdminRequest proxyAdminRequest){
        ProxyAdmin proxyAdmin =  new ProxyAdmin();
        proxyAdmin.setCin(proxyAdminRequest.getCin());
        proxyAdmin.setSuperAdmin(proxyAdminRequest.getSuperAdmin());
        proxyAdmin.setEmail(proxyAdminRequest.getEmail());
        proxyAdmin.setPassword(proxyAdminRequest.getPassword());
        proxyAdmin.setFirstName(proxyAdminRequest.getFirstName());
        proxyAdmin.setLastName(proxyAdminRequest.getLastName());
        proxyAdmin.setPhone(proxyAdminRequest.getPhone());

        return proxyAdmin;
    }

    @Override
    public void delete(String cin) {
        repository.deleteById(cin);
    }


}
