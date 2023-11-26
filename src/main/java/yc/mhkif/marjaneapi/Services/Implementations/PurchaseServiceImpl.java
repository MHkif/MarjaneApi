package yc.mhkif.marjaneapi.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.mhkif.marjaneapi.DTOs.PurchaseDTO;
import yc.mhkif.marjaneapi.Entities.*;
import yc.mhkif.marjaneapi.Repositories.CustomerRepository;
import yc.mhkif.marjaneapi.Repositories.ProductRepository;
import yc.mhkif.marjaneapi.Repositories.PurchaseRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.IEmailService;
import yc.mhkif.marjaneapi.Services.Interfaces.IPurchaseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PurchaseServiceImpl implements IPurchaseService {

    private final PurchaseRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IEmailService emailService;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository repository, CustomerRepository customerRepository, ProductRepository productRepository, IEmailService emailService) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Purchase> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Purchase> save(PurchaseDTO purchaseDTO) {

        Purchase purchase = mapToEntity(purchaseDTO);
        Optional<Product> product =  productRepository.findById(purchase.getProduct().getId());
        Optional<Customer> customer = customerRepository.findById(purchase.getCustomer().getCin());

        if (customer.isPresent()) {
            Optional<Purchase> purchaseOptional = Optional.of(repository.save(purchase));
            // # TODO Send Email to Customer About his Purchase
            String name = customer.get().getFirstName()+" "+customer.get().getLastName();
            String sendingTo = customer.get().getEmail();
            String subject = "New Purchase";
            String body = "Hello "+ name +" we Hope you are doing well, Your Purchase  "+product.get().getName()+" has been made Successfully \nAt the date : "+purchaseOptional.get().getCreatedAt() ;
            emailService.sendSimpleMailMessage(name, sendingTo, subject, body);
            return purchaseOptional;

        } else {
            throw new IllegalStateException("Customer not found");
        }

    }

    @Override
    public void delete(Long id) {

    }

    public Purchase mapToEntity(PurchaseDTO dto){
        if (dto == null){
            return null;
        }
        return Purchase.builder()
                .id(dto.getId())
                .customer(dto.getCustomer())
                .product(dto.getProduct())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
