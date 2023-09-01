package com.rustam.unitech.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrencyRateResponse {
    private final double amount;
    private final String target;
}
