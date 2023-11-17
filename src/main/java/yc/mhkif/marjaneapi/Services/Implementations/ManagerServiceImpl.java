package yc.mhkif.marjaneapi.Services.Implementations;

import yc.mhkif.marjaneapi.DTOs.ManagerDTO;
import yc.mhkif.marjaneapi.DTOs.Requests.ManagerRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.ManagerResponse;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Repositories.ManagerRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements IManagerService {

    private ManagerRepository repository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Manager> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public Optional<Manager> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<ManagerResponse> findAll() {

        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public Optional<Manager> save(ManagerRequest request) {
        Manager manager = mapToEntity(request);
        return Optional.of(repository.save(manager));
    }

    @Override
    public void delete(String cin) {

    }

    @Override
    public Optional<Manager> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public ManagerResponse mapToDTO(Manager manager){
        return ManagerResponse.builder()
                .cin(manager.getCin())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .phone(manager.getPhone())
                .build();
    }

    public Manager mapToEntity(ManagerRequest managerRequest){
        Manager manager =  new Manager();
        manager.setCin(managerRequest.getCin());
        manager.setAdmin(managerRequest.getAdmin());
        manager.setEmail(managerRequest.getEmail());
        manager.setPassword(managerRequest.getPassword());
        manager.setFirstName(managerRequest.getFirstName());
        manager.setLastName(managerRequest.getLastName());
        manager.setPhone(managerRequest.getPhone());

        return manager;
    }

    public  boolean isOutOfTime() {
        final LocalTime START_TIME = LocalTime.of(8, 0); // 8 AM
        final LocalTime END_TIME = LocalTime.of(12, 0); // 12 AM
        LocalTime currentTime = LocalTime.now();
        return currentTime.isBefore(START_TIME) || currentTime.isAfter(END_TIME);
    }
}
