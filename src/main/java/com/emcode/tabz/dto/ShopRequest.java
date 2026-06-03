package com.emcode.tabz.dto;

public record ShopRequest(
        String name,
        String email,
        String token,// todo: remove and auto generate token
        boolean active
) {
}
