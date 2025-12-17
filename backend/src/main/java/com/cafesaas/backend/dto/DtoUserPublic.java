package com.cafesaas.backend.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoUserPublic {
    private UUID id;

    private String firstName;

    private String lastName;
}
