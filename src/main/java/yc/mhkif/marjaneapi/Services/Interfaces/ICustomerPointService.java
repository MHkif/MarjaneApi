package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.CustomerPointDTO;
import yc.mhkif.marjaneapi.Entities.CustomerPoints;

import java.util.List;
import java.util.Optional;

public interface ICustomerPointService {
    Optional<CustomerPoints> findByCIN(String cin);

    List<CustomerPointDTO> findAll();

    Optional<CustomerPoints>  save(CustomerPointDTO t);
}
