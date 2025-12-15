package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "cafes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cafe extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cafe_name", nullable = false)
    private String cafeName; // görünecek ad

    @Column(name = "cafe_username", unique = true, nullable = false)//sistemsel ad
    private String cafeUsername;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.CAFE_TERMINAL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
}
