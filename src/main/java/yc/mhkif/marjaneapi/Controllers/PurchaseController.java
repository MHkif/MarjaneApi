package yc.mhkif.marjaneapi.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yc.mhkif.marjaneapi.DTOs.PurchaseDTO;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.Purchase;
import yc.mhkif.marjaneapi.Services.Implementations.PurchaseServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "marjane/api/v1")
public class PurchaseController {

    private final PurchaseServiceImpl service;


    @Autowired
    public PurchaseController(PurchaseServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getAll(){
        List<Purchase> purchases = service.findAll();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }
    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getOne(@PathVariable Long id){
        Optional<Purchase> product = service.findById(id);

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
    /*
    @GetMapping("/products/customer/{cin}")
    public ResponseEntity<List<Purchase>> getByCustomer(@PathVariable String cin){

        List<Purchase> products = service.findByCustomer(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

     */

    @PostMapping(value = "/purchases/save", produces  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Purchase> save(@RequestBody PurchaseDTO request){
        Optional<Purchase> product = service.save(request);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
