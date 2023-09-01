package com.rustam.unitech.dto.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class AccountRequest {
    @NotNull
    private final Integer pin;
}
