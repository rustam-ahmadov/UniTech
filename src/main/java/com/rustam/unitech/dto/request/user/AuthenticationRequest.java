package com.rustam.unitech.dto.request.user;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private final String email;
    private final String password;
}
