package yc.mhkif.marjaneapi.Repositories;

import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProxyAdminRepository extends JpaRepository<ProxyAdmin, String> {
    Optional<ProxyAdmin> findByEmailAndPassword(String email, String password);
    Optional<ProxyAdmin> findByEmail(String email);
}
