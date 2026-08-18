package com.emcode.tabz.dto;

import java.util.List;

public record ShopResponse(
        Long id,
        String name,
        String email,
        String tokenHash,
        boolean active,
        List<ShopTabResponse> tabs
) {
}
