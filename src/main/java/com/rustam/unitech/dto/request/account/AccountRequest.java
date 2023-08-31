package com.rustam.unitech.dto.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccountRequest {
    @NotNull
    private final Integer pin;

    public AccountRequest(Integer pin) {
        this.pin = pin;
    }
}
