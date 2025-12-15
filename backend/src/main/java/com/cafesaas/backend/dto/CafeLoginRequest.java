package com.cafesaas.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record CafeLoginRequest (
    @NotBlank(message = "Kafe adı boş bırakılamaz")
    String cafeUserName,

    @NotBlank(message = "Şifre alanı boş bırakılamaz.")
    String password

){}
