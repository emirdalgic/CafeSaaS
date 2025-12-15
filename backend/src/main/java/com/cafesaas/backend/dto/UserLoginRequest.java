package com.cafesaas.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(

        @NotBlank(message = "Email alanı boş bırakılamaz.")
        @Email(message = "Lütfen geçerli bir email adresi giriniz.")
        String email,

        @NotBlank(message = "Şifre alanı boş bırakılamaz.")
        String password
) {}