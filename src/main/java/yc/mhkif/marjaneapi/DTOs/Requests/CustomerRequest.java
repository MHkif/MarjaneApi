package yc.mhkif.marjaneapi.DTOs.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.mhkif.marjaneapi.Entities.Cashier;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import yc.mhkif.marjaneapi.Entities.Purchase;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {
    private Cashier cashier;
    private String cin;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Product> products;
}
