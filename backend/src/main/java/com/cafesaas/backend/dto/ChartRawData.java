package com.cafesaas.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ChartRawData(LocalDateTime createdAt, BigDecimal amount) {}
