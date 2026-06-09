package com.emcode.tabz.dto;

import com.emcode.tabz.model.Role;

public record UserRequest(
        String username,
        String email,
        String password,
        Role userRole
) {
}
