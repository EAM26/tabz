package com.emcode.tabz.dto;

import com.emcode.tabz.model.Role;

import java.util.List;

public record UserResponse(
        Long id,
        String username,
        String email,
        List<UserTabResponse> tabs,
        Role userRole
) {
}
