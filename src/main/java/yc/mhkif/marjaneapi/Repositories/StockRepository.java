package yc.mhkif.marjaneapi.Repositories;

import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock , Long> {
    Stock findByProduct(Product product);
}
