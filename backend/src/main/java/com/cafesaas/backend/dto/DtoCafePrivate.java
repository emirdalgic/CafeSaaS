package com.cafesaas.backend.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCafePrivate {//burası sadece admin panelindeki işletme sahibine dönülücek
    private UUID id;

    private String cafeName;

    private String cafeUsername;

    private String address;

    private String phone;

    private DtoUserPrivate owner;
}
