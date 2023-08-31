package com.rustam.unitech.controller;

import com.rustam.unitech.dto.request.user.AuthenticationRequest;
import com.rustam.unitech.dto.response.AuthenticationResponse;
import com.rustam.unitech.dto.request.user.RegisterRequest;
import com.rustam.unitech.enums.ResponseDetails;
import com.rustam.unitech.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unitech/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        ResponseDetails response = authenticationService.register(request);
        return ResponseEntity
                .status(response.getHttpStatus())
                .body(response.getMessage());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
