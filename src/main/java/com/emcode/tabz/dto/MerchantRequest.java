package com.emcode.tabz.dto;

public record MerchantRequest(
        String name,
        String email,
        String token,// todo: remove and auto generate token
        boolean active
) {
}
