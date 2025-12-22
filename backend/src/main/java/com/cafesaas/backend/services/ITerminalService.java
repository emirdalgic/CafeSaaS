package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.DtoOrderCreateIU;
import com.cafesaas.backend.dto.DtoOrderIU;

import java.util.UUID;

public interface ITerminalService {
    public void createOrderAtTerminal(DtoOrderCreateIU dtoOrderCreateIU, UUID cafeId);
}
