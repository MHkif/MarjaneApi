package yc.mhkif.marjaneapi.DTOs.Requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private  String password;
}
