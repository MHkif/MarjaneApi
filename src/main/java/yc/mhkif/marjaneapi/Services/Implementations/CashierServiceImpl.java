package yc.mhkif.marjaneapi.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.mhkif.marjaneapi.DTOs.Requests.CashierRequest;
import yc.mhkif.marjaneapi.DTOs.Responses.CashierResponse;
import yc.mhkif.marjaneapi.Entities.Cashier;
import yc.mhkif.marjaneapi.Repositories.CashierRepository;
import yc.mhkif.marjaneapi.Services.Interfaces.ICashierService;

import java.util.List;
import java.util.Optional;

@Service
public class CashierServiceImpl implements ICashierService {

    private final CashierRepository repository;

    @Autowired
    public CashierServiceImpl(CashierRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cashier> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public Optional<Cashier> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<CashierResponse> findAll() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public Optional<Cashier> save(CashierRequest cashierRequest) {
        Cashier cashier = mapToEntity(cashierRequest);
        return Optional.of(repository.save(cashier));
    }


    public CashierResponse mapToDTO(Cashier cashier){
        return CashierResponse.builder()
                .cin(cashier.getCin())
                .email(cashier.getEmail())
                .password(cashier.getPassword())
                .firstName(cashier.getFirstName())
                .lastName(cashier.getLastName())
                .phone(cashier.getPhone())
                .build();
    }

    public Cashier mapToEntity(CashierRequest cashierRequest){
        Cashier cashier =  new Cashier();
        cashier.setCin(cashierRequest.getCin());
        cashier.setAdmin(cashierRequest.getAdmin());
        cashier.setEmail(cashierRequest.getEmail());
        cashier.setPassword(cashierRequest.getPassword());
        cashier.setFirstName(cashierRequest.getFirstName());
        cashier.setLastName(cashierRequest.getLastName());
        cashier.setPhone(cashierRequest.getPhone());

        return cashier;
    }

}
