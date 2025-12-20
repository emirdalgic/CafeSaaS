package com.cafesaas.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCafeRegisterIU {
    @NotBlank
    private String cafeName;
    @NotBlank
    private String cafeUsername;
    @NotBlank
    private String password;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
}
