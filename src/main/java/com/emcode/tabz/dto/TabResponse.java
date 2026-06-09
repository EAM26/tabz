package com.emcode.tabz.dto;

import java.time.LocalDateTime;

public record TabResponse(
        Long id,
        Long shopId,
        String name,
        LocalDateTime createdAt

) {
}
