package yc.mhkif.marjaneapi.DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponse {
    private String cin;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
