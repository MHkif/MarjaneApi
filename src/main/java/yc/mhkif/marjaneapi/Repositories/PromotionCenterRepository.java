package yc.mhkif.marjaneapi.Repositories;

import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionCenterRepository extends JpaRepository<PromotionCenter, PromotionCenterId> {
    List<PromotionCenter> findAllByManager(Manager manager);
}
