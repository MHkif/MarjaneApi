package yc.mhkif.marjaneapi.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.mhkif.marjaneapi.DTOs.CustomerPointDTO;
import yc.mhkif.marjaneapi.Entities.CustomerPoints;
import yc.mhkif.marjaneapi.Repositories.CustomerPointsRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.ICustomerPointService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerPointServiceImpl implements ICustomerPointService {

    private final CustomerPointsRepository repository;

    @Autowired
    public CustomerPointServiceImpl(CustomerPointsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CustomerPoints> findByCIN(String cin) {
        return Optional.empty();
    }

    @Override
    public List<CustomerPointDTO> findAll() {
        return null;
    }

    @Override
    public Optional<CustomerPoints> save(CustomerPointDTO request) {
        CustomerPoints customerPoint = mapToEntity(request);
        return Optional.of(repository.save(customerPoint));
    }

    public CustomerPointDTO mapToDTO(CustomerPoints customerPoints){
        if(customerPoints == null){
            return null;
        }
        return CustomerPointDTO.builder()
                .code(customerPoints.getCode())
                .customer(customerPoints.getCustomer())
                .points(customerPoints.getPoints())
                .createdAt(customerPoints.getCreatedAt())
                .updatedAt(customerPoints.getUpdatedAt())
                .build();
    }

    public CustomerPoints mapToEntity(CustomerPointDTO request){
        if(request == null){
            return null;
        }
        CustomerPoints customerPoints =  new CustomerPoints();
        customerPoints.setCode(request.getCode());
        customerPoints.setCustomer(request.getCustomer());
        customerPoints.setPoints(request.getPoints());
        customerPoints.setCreatedAt(request.getCreatedAt());
        customerPoints.setUpdatedAt(request.getUpdatedAt());
        return customerPoints;
    }
}
