package com.emcode.tabz.dto;

public record UserRequest(
        String username,
        String email,
        String password
) {
}
