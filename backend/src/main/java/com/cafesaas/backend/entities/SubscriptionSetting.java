package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.SubscriptionPlanType;
import jakarta.persistence.*;

@Entity
@Table(name = "subscription_settings")
public class SubscriptionSetting {

    //burayı biz planların fiyatlarını değiştirmek istediğimizde kaynak koda dönmeyelim kendimize bir panel yaptığımızda
    //kullanırız diye yapıyorum
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type")
    private SubscriptionPlanType planType;

    @Column(name = "price")
    private Double price;
}
