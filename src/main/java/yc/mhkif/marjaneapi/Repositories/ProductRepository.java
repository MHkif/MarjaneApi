package yc.mhkif.marjaneapi.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.mhkif.marjaneapi.Entities.Category;
import yc.mhkif.marjaneapi.Entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
   // Page<Product> findAllPageable(PageRequest request);
}
