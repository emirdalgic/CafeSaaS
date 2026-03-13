package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.ChartRawData;
import com.cafesaas.backend.dto.DtoDashboardSummary;
import com.cafesaas.backend.dto.DtoPopulerItem;
import com.cafesaas.backend.dto.DtoSalesChart;
import com.cafesaas.backend.repository.OrderItemRepository;
import com.cafesaas.backend.repository.OrderRepository;
import com.cafesaas.backend.services.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements IDashboardService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    //helper
    private record DateRange(LocalDateTime start, LocalDateTime end) {}

    private DateRange calculateDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        if (startDate == null) {
            startDate = endDate.minusDays(30);
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Başlangıç tarihi bitiş tarihinden sonra olamaz.");
        }
        if (ChronoUnit.DAYS.between(startDate, endDate) > 365) {
            throw new IllegalArgumentException("Maksimum 1 yıllık veri çekebilirsiniz.");
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();
        return new DateRange(startDateTime, endDateTime);
    }

    @Override
    public DtoDashboardSummary getDashBoardSummary(UUID cafeId, LocalDate startDate, LocalDate endDate) {
        DateRange range = calculateDateRange(startDate, endDate);
        return orderRepository.getDashboardSummaryData(cafeId, range.start(), range.end());
    }

    @Override
    public List<DtoSalesChart> getSalesChart(UUID cafeId, LocalDate startDate, LocalDate endDate) {
        DateRange range = calculateDateRange(startDate, endDate);

        List<ChartRawData> rawData = orderRepository.getRawChartData(
                cafeId, range.start(), range.end()
        );

        long daysBetween = ChronoUnit.DAYS.between(range.start().toLocalDate(), range.end().toLocalDate());
        boolean isDaily = daysBetween <= 31;

        DateTimeFormatter dailyFormatter = DateTimeFormatter.ofPattern("dd MMM");
        DateTimeFormatter monthlyFormatter = DateTimeFormatter.ofPattern("MMM yyyy");

        Map<String, DtoSalesChart> chartMap = new LinkedHashMap<>();

        for (ChartRawData data : rawData) {
            String label = isDaily
                    ? data.createdAt().format(dailyFormatter)
                    : data.createdAt().format(monthlyFormatter);

            DtoSalesChart chartEntry = chartMap.computeIfAbsent(label, k -> new DtoSalesChart(k, BigDecimal.ZERO, 0));

            chartEntry.setRevenue(chartEntry.getRevenue().add(data.amount()));
            chartEntry.setOrderCount(chartEntry.getOrderCount() + 1);
        }

        return new ArrayList<>(chartMap.values());
    }

    @Override
    public List<DtoPopulerItem> getPopularItems(UUID cafeId, LocalDate startDate, LocalDate endDate) {
        DateRange range = calculateDateRange(startDate, endDate);

        // OrderStatus.COMPLETED parametresi kaldırıldı
        List<DtoPopulerItem> popularItems = orderItemRepository.findPopularItems(
                cafeId, range.start(), range.end(), PageRequest.of(0, 5)
        );

        int rank = 1;
        for (DtoPopulerItem item : popularItems) {
            item.setRank(rank++);
        }

        return popularItems;
    }
}