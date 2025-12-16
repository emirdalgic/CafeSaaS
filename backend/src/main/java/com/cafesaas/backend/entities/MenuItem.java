package com.cafesaas.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl; //ilerde cnd linki eklenicek

    //qrda ödeme alıp almamıza göre burası silincek. bir de bunun düzgün çalışması için
    //adamın stoğunu tutmalıyız ki stok dediğin her zaman son ürün olmayabilir
    //bu durumda işletme kısmında her sabah dükkanı açarken gidip el ile tek tek bugun 20 hambuger yaparz vs
    //falan demesi gerekçek o yüzden pek gerçekçi değil
    //dersen eğer qr menüde ödeme alınsın ve avaible kısmı takibi zor olduğu için kaldırılsnı
    //bu durumda payment kısmında ürünün varlığı ya da yokluğu hakkında bir doğrulama sağlayamayız ve
    //müşteri olmayan bir şeye para ödemiş olabilir.
    //şimdilik bunu böyle bırakıyorum kararı netleştirince bakarız
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MenuCategory category;
}
