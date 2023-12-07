package yc.mhkif.marjaneapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Services.Implementations.ProductServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "marjane/api/v1")
@CrossOrigin("*")
public class ProductController {

    private final ProductServiceImp service;

    @Autowired
    public ProductController(ProductServiceImp service){
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> productList = service.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/products/pages")
    public ResponseEntity<Page<Product>> getByPages(@RequestParam int page, @RequestParam int size){
        Page<Product> productList = service.findPages(page, size);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Long id){
        Optional<Product> product = service.findById(id);

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category){

        List<Product> products = service.findByCategory(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/products/save", produces  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Product> save(@RequestBody Product productReq){
        Optional<Product> product = service.save(productReq);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
