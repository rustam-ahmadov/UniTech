package com.rustam.unitech.controller;


import com.rustam.unitech.dto.request.account.AccountCreationRequest;
import com.rustam.unitech.dto.request.account.AccountRequest;
import com.rustam.unitech.dto.response.account.AccountResponse;
import com.rustam.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unitech/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> get(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.find(request));
    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> create(@RequestBody AccountCreationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.create(request));
    }

}
