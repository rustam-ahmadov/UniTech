package com.rustam.unitech.controller;

import com.rustam.unitech.dto.request.CurrencyRateRequest;
import com.rustam.unitech.dto.response.CurrencyRateResponse;
import com.rustam.unitech.service.CurrencyRatesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unitech/currency")
public class CurrencyRatesController {
    private final CurrencyRatesService service;

    @PostMapping
    public ResponseEntity<List<CurrencyRateResponse>> get(@RequestBody @Valid CurrencyRateRequest request) {
        return ResponseEntity.ok(List.of(service.get(request)));
    }
}
