package com.rustam.unitech.controller;


import com.rustam.unitech.dto.request.account.AccountCreationRequest;
import com.rustam.unitech.dto.request.account.AccountRequest;
import com.rustam.unitech.dto.request.account.TransferRequest;
import com.rustam.unitech.dto.response.account.AccountResponse;
import com.rustam.unitech.enums.ResponseDetails;
import com.rustam.unitech.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unitech/account")
public class AccountController {
    private final AccountService service;

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountCreationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAll());
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid TransferRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.transfer(request));
    }
}
