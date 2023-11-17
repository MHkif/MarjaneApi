package yc.mhkif.marjaneapi.DTOs.Requests;

import yc.mhkif.marjaneapi.Entities.SuperAdmin;
import lombok.Data;

@Data
public class ProxyAdminRequest {

    private String cin;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private SuperAdmin superAdmin;
}
