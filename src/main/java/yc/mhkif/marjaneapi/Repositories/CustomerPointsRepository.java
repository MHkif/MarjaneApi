package yc.mhkif.marjaneapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.mhkif.marjaneapi.Entities.Customer;
import yc.mhkif.marjaneapi.Entities.CustomerPoints;

@Repository
public interface CustomerPointsRepository extends JpaRepository<CustomerPoints, Long> {
}
