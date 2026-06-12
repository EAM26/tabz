package com.emcode.tabz.dto;

public record ShopResponse(
        Long id,
        String name,
        String email,
        String tokenHash,
        boolean active
) {
}
