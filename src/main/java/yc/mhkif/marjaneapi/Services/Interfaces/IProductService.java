package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.Entities.Category;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Stock;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> findById(Long id);

    List<Product> findAll();

    Optional<Product> save(Product product);
    Optional<Product> update(Product product);
    List<Product> findByCategory(String category);

    void delete(Long id);


}
