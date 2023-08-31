package com.rustam.unitech.dto.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {
    @NotNull
    private Double amount;
    @NotNull
    private Integer fromPin;
    @NotNull
    private Integer toPin;
}
