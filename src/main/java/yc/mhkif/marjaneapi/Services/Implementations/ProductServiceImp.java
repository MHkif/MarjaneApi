package yc.mhkif.marjaneapi.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import yc.mhkif.marjaneapi.Entities.Category;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.PromotionCenter;
import yc.mhkif.marjaneapi.Repositories.CategoryRepository;
import yc.mhkif.marjaneapi.Repositories.ProductRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements IProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImp(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()){
            return product;
        }
        throw new IllegalStateException("Product with Id : "+ id + " Not Found");
    }
    public Page<Product> findPages(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = this.repository.findAll(pageRequest);
        return products;
    }
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> save(Product product) {
        if(product == null){
            throw new IllegalStateException("Product in null");
        }

        return Optional.of(repository.save(product));
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.empty();
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        Optional<Category>  category = categoryRepository.findByName(categoryName);
        if(category.isEmpty()){
            throw new IllegalStateException("Category Not Found");
        }
        List<Product> products = repository.findByCategory(category.get());
        if (products.isEmpty()){
            throw new IllegalStateException("Product with Category : "+ categoryName + " Not Found");
        }
        return products;
    }

    @Override
    public void delete(Long id) {

    }



}
