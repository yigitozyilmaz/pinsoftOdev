package com.example.odev.business.responses;

import com.example.odev.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginResponse {

    private String username;
    private String email;
    private String token;
    private Long role_id;

    public LoginResponse(String username, String token ,String email, Long role_id) {
        this.username = username;
        this.token = token;
        this.email=email;
        this.role_id=role_id;
    }
}
