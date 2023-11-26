package yc.mhkif.marjaneapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.mhkif.marjaneapi.Entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
