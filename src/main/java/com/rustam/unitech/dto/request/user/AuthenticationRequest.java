package com.rustam.unitech.dto.request.user;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
