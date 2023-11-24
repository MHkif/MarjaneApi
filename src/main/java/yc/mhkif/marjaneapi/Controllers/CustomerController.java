package yc.mhkif.marjaneapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yc.mhkif.marjaneapi.DTOs.Requests.CustomerRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.CustomerResponse;
import yc.mhkif.marjaneapi.Entities.Cashier;
import yc.mhkif.marjaneapi.Entities.Customer;
import yc.mhkif.marjaneapi.Services.Implementations.CashierServiceImpl;
import yc.mhkif.marjaneapi.Services.Implementations.CustomerServiceImpl;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("marjane/api/v1")
public class CustomerController {

    private final CustomerServiceImpl service;
    private final CashierServiceImpl cashierService;


    @Autowired
    public CustomerController(CustomerServiceImpl service, CashierServiceImpl cashierService) {
        this.cashierService = cashierService;
        this.service = service;
    }

    @PostMapping(value = "/customers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CustomerResponse> save(@RequestBody CustomerRequest request, @RequestHeader Map<String, String> headers) {
        if (headers.get("token") != null) {
            Cashier cashier = cashierService.findByCIN(headers.get("token"))
                    .orElseThrow(() -> new IllegalStateException("Cashier Not Found ."));

            request.setCashier(cashier);
            Optional<Customer> customer = service.save(request);
            return customer.map(customerEntity -> new ResponseEntity<>(service.mapToDTO(customerEntity), HttpStatus.CREATED))
                    .orElseThrow(() -> new IllegalStateException("Customer Could not be created due to some internal problems."));
        } else {
            throw new IllegalStateException("Token Authentication for Cashier Not Found ");
        }

    }


}
