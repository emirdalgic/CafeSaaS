package com.cafesaas.backend.dto;

import com.cafesaas.backend.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderCreateIU {
    @NotEmpty(message = "sepet boş olamaz")
    private List<DtoOrderItemIU> items;
    @NotNull
    private PaymentMethod paymentMethod;
    private String tableName;
}
