package com.cafesaas.backend.dto;

import com.cafesaas.backend.model.enums.AccountStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoUserPrivate {//buraki userı sadece "profilim" gibi yerlere dönücem
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private AccountStatus status;

    List<DtoCafeSummary> cafes;
}
