package com.emcode.tabz.dto;

public record MerchantResponse(
        Long id,
        String name,
        String email,
        String token,  // todo remove token
        boolean active
) {
}
