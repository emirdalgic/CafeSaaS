package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IDashboardController;
import com.cafesaas.backend.dto.DtoDashboardSummary;
import com.cafesaas.backend.dto.DtoPopulerItem;
import com.cafesaas.backend.dto.DtoSalesChart;
import com.cafesaas.backend.services.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/dashboard")
@RequiredArgsConstructor
public class DashboardControllerImpl implements IDashboardController {
    private final IDashboardService dashboardService;

    @GetMapping("/{cafeId}/summary")
    @Override
    public ResponseEntity<DtoDashboardSummary> getDashBoardSummary(
            @PathVariable(name = "cafeId") UUID cafeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(dashboardService.getDashBoardSummary(cafeId, startDate, endDate));
    }

    @GetMapping("/{cafeId}/sales-chart")
    @Override
    // DİKKAT: Grafik çizeceğimiz için List dönmeliyiz (Örn: Son 12 ayın veya son 30 günün verisi)
    public ResponseEntity<List<DtoSalesChart>> getSalesChart(
            @PathVariable(name = "cafeId") UUID cafeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(dashboardService.getSalesChart(cafeId, startDate, endDate));
    }

    @GetMapping("/{cafeId}/popular-items")
    @Override
    public ResponseEntity<List<DtoPopulerItem>> getPopularItems(
            @PathVariable(name = "cafeId") UUID cafeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(dashboardService.getPopularItems(cafeId, startDate, endDate));
    }
}
