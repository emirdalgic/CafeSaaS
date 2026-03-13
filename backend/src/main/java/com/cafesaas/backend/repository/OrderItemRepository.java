package com.cafesaas.backend.repository;

import com.cafesaas.backend.dto.DtoPopulerItem;
import com.cafesaas.backend.entities.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem,UUID> {
    @Query("SELECT new com.cafesaas.backend.dto.DtoPopulerItem(" +
            "0, " + // rank (sıra) için şimdilik 0 veriyoruz, Java'da for döngüsüyle dolduracağız
            "oi.productName, " +
            "COALESCE(c.name, 'Kategorisiz'), " + // Ürün silindiyse null dönmesin
            "MAX(oi.priceAtPurchase), " + // Fiyatı göstermek için
            "SUM(oi.quantity)) " + // Toplam satılma adedi
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "LEFT JOIN oi.menuItem mi " +
            "LEFT JOIN mi.category c " +
            "WHERE o.cafe.id = :cafeId " +
            "AND o.createdAt >= :startDate AND o.createdAt < :endDate " +
            "GROUP BY oi.productName, c.name " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<DtoPopulerItem> findPopularItems(@Param("cafeId") UUID cafeId,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate,
                                          Pageable pageable);
}
