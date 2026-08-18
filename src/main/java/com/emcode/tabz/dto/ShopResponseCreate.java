package com.emcode.tabz.dto;

public record ShopResponseCreate(
        Long id,
        String name,
        String email,
        String rawToken,
        boolean active
) {
}
