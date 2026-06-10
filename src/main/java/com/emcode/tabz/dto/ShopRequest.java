package com.emcode.tabz.dto;

public record ShopRequest(
        String name,
        String email,
        String tokenHash
) {
}
