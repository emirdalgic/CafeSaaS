package com.cafesaas.backend.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    //buranın paymenta herhangi bir etkisi yok
    //sadece terminal üzerinde avaible no denirse menüden o ürün avaible olarana kadar kaldırılıcak(delete yok)
    @Column(name = "is_available")
    @Builder.Default
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    @ToString.Exclude
    private MenuCategory menuCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id",nullable = false)
    @ToString.Exclude
    private Cafe cafe;
}
