package com.emcode.tabz.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserTabResponse(
        Long id,
        Long shopId,
        String shopName,
        BigDecimal totalAmount,
        LocalDateTime createdAt

) {
}
