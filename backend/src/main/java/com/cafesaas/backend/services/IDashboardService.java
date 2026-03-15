package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.DtoDashboardSummary;
import com.cafesaas.backend.dto.DtoOrderHistory;
import com.cafesaas.backend.dto.DtoPopulerItem;
import com.cafesaas.backend.dto.DtoSalesChart;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IDashboardService {
    public DtoDashboardSummary getDashBoardSummary(UUID cafeId, LocalDate startDate, LocalDate endDate);
    public List<DtoSalesChart> getSalesChart(UUID cafeId, LocalDate startDate, LocalDate endDate);
    public List<DtoPopulerItem> getPopularItems(UUID cafeId, LocalDate startDate, LocalDate endDate);
    public Page<DtoOrderHistory> getOrderHistory(UUID cafeId, int page, int size);
}
