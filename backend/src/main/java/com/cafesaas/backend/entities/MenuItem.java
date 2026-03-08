package com.cafesaas.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem extends BaseEntity {
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
    private MenuCategory category;

    @ElementCollection
    @CollectionTable(name = "menu_item_variants", joinColumns = @JoinColumn(name = "menu_item_id"))
    @MapKeyColumn(name = "variant_name") // küçük orta büyük
    @Column(name = "price")
    private Map<String, BigDecimal> variants = new HashMap<>();
}
