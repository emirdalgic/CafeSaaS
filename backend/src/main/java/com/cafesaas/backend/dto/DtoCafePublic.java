package com.cafesaas.backend.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCafePublic {//frontta headerda çekmelik

    private UUID id;

    private String cafeName;//görünen ad

    private String slug;

    private String address;

    private String phone;
}
