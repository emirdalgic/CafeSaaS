package com.cafesaas.backend.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCafePublic {

    private UUID id;

    private String cafeName;

    private String address;

    private String phone;
}
