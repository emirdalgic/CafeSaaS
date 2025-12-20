package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.DtoCafeRegisterIU;
import com.cafesaas.backend.dto.DtoCafeSummary;
import com.cafesaas.backend.dto.DtoCafeUpdateIU;

import java.util.UUID;

public interface ICafeService {
    public DtoCafeSummary createCafe(DtoCafeRegisterIU dtoCafeRegisterIU, UUID userId);
    public DtoCafeSummary updateCafe(DtoCafeUpdateIU dtoCafeUpdateIU, UUID userId, UUID cafeId);
    public void deleteCafe(UUID cafeId, UUID userId);
}
