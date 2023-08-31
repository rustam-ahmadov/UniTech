package com.rustam.unitech.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class CurrencyRateRequest {
    private final Double amount;
    @NotBlank
    private final String source;
    private final String target;

    public Optional<String> getTarget() {
        return Optional.ofNullable(target);
    }
}
