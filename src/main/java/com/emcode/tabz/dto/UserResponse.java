package com.emcode.tabz.dto;

import com.emcode.tabz.model.Tab;

import java.util.List;

public record UserResponse(
        Long id,
        String username,
        String email,
        List<Tab> tabs
) {
}
