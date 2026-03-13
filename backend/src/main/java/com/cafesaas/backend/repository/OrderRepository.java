package com.cafesaas.backend.repository;

import com.cafesaas.backend.dto.ChartRawData;
import com.cafesaas.backend.dto.DtoDashboardSummary;
import com.cafesaas.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT new com.cafesaas.backend.dto.DtoDashboardSummary(" +
            "COUNT(o.id), " +
            "COALESCE(SUM(o.totalAmount), 0), " + // Hiç satış yoksa null dönmesin, 0 dönsün diye COALESCE kullanıyoruz
            "COUNT(o.id)) " +
            "FROM Order o " +
            "WHERE o.cafe.id = :cafeId " +
            "AND o.createdAt >= :startDate AND o.createdAt < :endDate")
    DtoDashboardSummary getDashboardSummaryData(@Param("cafeId") UUID cafeId,
                                                @Param("startDate") LocalDateTime startDate,
                                                @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new com.cafesaas.backend.dto.ChartRawData(o.createdAt, o.totalAmount) " +
            "FROM Order o " +
            "WHERE o.cafe.id = :cafeId " +
            "AND o.createdAt >= :startDate AND o.createdAt < :endDate")
    List<ChartRawData> getRawChartData(@Param("cafeId") UUID cafeId,
                                       @Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);
}
