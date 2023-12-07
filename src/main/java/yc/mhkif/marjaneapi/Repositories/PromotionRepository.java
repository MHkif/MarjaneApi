package yc.mhkif.marjaneapi.Repositories;

import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;

import java.util.List;


@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findAllByAdmin(ProxyAdmin proxyAdmin);

}
