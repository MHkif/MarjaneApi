package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.Requests.CashierRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.CashierResponse;
import yc.mhkif.marjaneapi.Entities.Cashier;

import java.util.List;
import java.util.Optional;

public interface ICashierService {
    Optional<Cashier> findByCIN(String cin);

    Optional<Cashier> findByEmail(String email);

    List<CashierResponse> findAll();

    Optional<Cashier>  save(CashierRequest t);

}
