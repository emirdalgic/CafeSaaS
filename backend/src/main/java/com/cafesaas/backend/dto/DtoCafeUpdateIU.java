package com.cafesaas.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCafeUpdateIU {
    private String cafeName;
    private String password;
    private String address;
    private String phone;
}
