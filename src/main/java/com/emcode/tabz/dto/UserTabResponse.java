package com.emcode.tabz.dto;

import java.time.LocalDateTime;

public record UserTabResponse(
        Long id,
        Long shopId,
        Long userId,
        String name,
        LocalDateTime createdAt

) {
}
