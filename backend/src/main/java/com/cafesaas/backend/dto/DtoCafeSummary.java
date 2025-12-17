package com.cafesaas.backend.dto;

import com.cafesaas.backend.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DtoCafeSummary {
    private UUID id;
    private String cafeName;
    private String cafeUsername;
    private Role role;
}
