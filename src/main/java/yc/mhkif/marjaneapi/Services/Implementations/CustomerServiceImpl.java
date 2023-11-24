package yc.mhkif.marjaneapi.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.mhkif.marjaneapi.DTOs.CustomerPointDTO;
import yc.mhkif.marjaneapi.DTOs.PurchaseDTO;
import yc.mhkif.marjaneapi.DTOs.Requests.CustomerRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.CustomerResponse;

import yc.mhkif.marjaneapi.Entities.Customer;
import yc.mhkif.marjaneapi.Repositories.CustomerRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.ICustomerService;
import yc.mhkif.marjaneapi.Services.Interfaces.IEmailService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository repository;
    private final PurchaseServiceImpl purchasesService;
    private final CustomerPointServiceImpl customerPointService;
    private final IEmailService emailService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository , CustomerPointServiceImpl customerPointService,
                               PurchaseServiceImpl purchasesService, IEmailService emailService) {
        this.repository = repository;
        this.purchasesService = purchasesService;
        this.customerPointService = customerPointService;
        this.emailService = emailService;
    }

    @Override
    public Optional<Customer> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<CustomerResponse> findAll() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public Optional<Customer> save(CustomerRequest request) {

        if (this.findByCIN(request.getCin()).isPresent()) {
            throw new IllegalStateException("Customer Already Exist with same CIN : " + request.getCin());
        } else if (this.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Customer Already Exist with same Email : " + request.getEmail());
        }else{

            Customer customer = mapToEntity(request);
            Optional<Customer> customerOptional = Optional.of(repository.save(customer));
            CustomerPointDTO customerPoints = new CustomerPointDTO();
            customerPoints.setCode(customerOptional.get().getCin());
            customerPoints.setCustomer(customerOptional.get());
            customerPoints.setPoints(BigDecimal.valueOf(0));
            customerPoints.setCreatedAt(LocalDateTime.now());
            customerPoints.setUpdatedAt(null);
            customerPointService.save(customerPoints);
            // # TODO Send Email to Customer About his Card Code

            emailService.sendSimpleMailMessage(customer.getFirstName()+" "+customer.getLastName(), customer.getEmail(), customerPoints.getCode());
            return customerOptional;
        }
    }

    public Optional<PurchaseDTO> purchase(CustomerRequest request){
        /*
        Customer customer = mapToEntity(request);

        repository.save(customer);

        List<PurchaseDTO> purchases = request.getProducts().stream()
                .map(product -> PurchaseDTO.builder()
                        .cashier(request.getCashier())
                        .product(product)
                        .customer(customer)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(null)
                        .build()).toList();

        purchases.forEach(purchasesService::save);

        return Optional.of(repository.save(customer));

         */
        return Optional.empty();
    }
    public CustomerResponse mapToDTO(Customer customer){
        return CustomerResponse.builder()
                .cin(customer.getCin())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .build();
    }

    public Customer mapToEntity(CustomerRequest customerRequest){
        Customer customer =  new Customer();
        customer.setCin(customerRequest.getCin());
        customer.setCashier(customerRequest.getCashier());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhone(customerRequest.getPhone());

        return customer;
    }
}
