package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.Requests.CustomerRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.CustomerResponse;
import yc.mhkif.marjaneapi.Entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> findByCIN(String cin);

    Optional<Customer> findByEmail(String email);

    List<CustomerResponse> findAll();

    Optional<Customer>  save(CustomerRequest t);
}
