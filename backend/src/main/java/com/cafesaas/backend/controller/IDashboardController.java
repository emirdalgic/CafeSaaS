package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoDashboardSummary;
import com.cafesaas.backend.dto.DtoPopulerItem;
import com.cafesaas.backend.dto.DtoSalesChart;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IDashboardController {
    public ResponseEntity<DtoDashboardSummary> getDashBoardSummary(UUID cafeId, LocalDate startDate, LocalDate endDate);
    public ResponseEntity<List<DtoSalesChart>> getSalesChart(UUID cafeId, LocalDate startDate, LocalDate endDate);
    public ResponseEntity<List<DtoPopulerItem>> getPopularItems(UUID cafeId, LocalDate startDate,LocalDate endDate);
}
