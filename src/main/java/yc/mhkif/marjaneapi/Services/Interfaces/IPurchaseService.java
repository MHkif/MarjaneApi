package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.PurchaseDTO;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Purchase;

import java.util.List;
import java.util.Optional;

public interface IPurchaseService {
    Optional<Purchase> findById(Long id);

    List<Purchase> findAll();

    Optional<Purchase> save(PurchaseDTO purchaseDTO);

    void delete(Long id);
}
