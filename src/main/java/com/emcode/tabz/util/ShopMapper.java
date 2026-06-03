package com.emcode.tabz.util;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.model.Shop;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {

    public ShopResponse mapToResponse(Shop shop) {
        return new ShopResponse(
                shop.getId(),
                shop.getName(),
                shop.getEmail(),
                shop.getToken(),
                shop.isActive()
        );
    }

    public Shop mapToEntity(ShopRequest request) {
        return Shop.builder()
                .name(request.name())
                .email(request.email())
                .token(request.token())
                .active(request.active())
                .build();
    }
}
