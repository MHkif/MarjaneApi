package yc.mhkif.marjaneapi.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.mhkif.marjaneapi.Entities.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
