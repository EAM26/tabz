package com.emcode.tabz.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShopTabResponse(
        Long id,
        BigDecimal totalAmount,
        boolean claimed,
        LocalDateTime createdAt
) {
}
