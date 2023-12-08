package yc.mhkif.marjaneapi.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.Promotion;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;

import java.util.List;

@Repository
public interface PromotionCenterRepository extends JpaRepository<PromotionCenter, PromotionCenterId> {
    List<PromotionCenter> findAllByManager(Manager manager);
    Page<PromotionCenter> findPageableByManager(Manager manager, PageRequest pageRequest);

    @Query("SELECT pc FROM PromotionCenter pc " +
            "INNER JOIN pc.promotion p " +
            "WHERE p.admin.cin = :proxyAdmin order by p.createdAt desc ")

    Page<PromotionCenter> findPageableByAdmin(String proxyAdmin, PageRequest pageRequest);


}
