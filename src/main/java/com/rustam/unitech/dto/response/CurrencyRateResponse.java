package com.rustam.unitech.dto.response;

import lombok.Getter;

@Getter
public class CurrencyRateResponse {
    private final double rate;
    private final String currency;

    public CurrencyRateResponse(double rate, String currency) {
        this.rate = rate;
        this.currency = currency;
    }
}
