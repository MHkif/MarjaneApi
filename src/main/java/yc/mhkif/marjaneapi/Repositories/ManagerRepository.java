package yc.mhkif.marjaneapi.Repositories;

import yc.mhkif.marjaneapi.Entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
    Optional<Manager> findByEmailAndPassword(String email, String password);
    Optional<Manager> findByEmail(String email);
}
