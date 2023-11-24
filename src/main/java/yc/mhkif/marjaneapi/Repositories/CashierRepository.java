package yc.mhkif.marjaneapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.mhkif.marjaneapi.Entities.Cashier;
import yc.mhkif.marjaneapi.Entities.Manager;

import java.util.Optional;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, String> {
    Optional<Cashier> findByEmailAndPassword(String email, String password);
    Optional<Cashier> findByEmail(String email);
}
