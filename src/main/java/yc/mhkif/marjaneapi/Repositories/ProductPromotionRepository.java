package yc.mhkif.marjaneapi.Repositories;

import yc.mhkif.marjaneapi.Entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductPromotionRepository extends JpaRepository<Promotion, Long> {
}
