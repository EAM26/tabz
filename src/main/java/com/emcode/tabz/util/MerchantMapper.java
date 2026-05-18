package com.emcode.tabz.util;

import com.emcode.tabz.dto.MerchantRequest;
import com.emcode.tabz.dto.MerchantResponse;
import com.emcode.tabz.model.Merchant;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {

    public MerchantResponse mapToResponse(Merchant merchant) {
        return new MerchantResponse(
                merchant.getId(),
                merchant.getName(),
                merchant.getEmail(),
                merchant.getToken()
        );
    }

    public Merchant mapToEntity(MerchantRequest request) {
        return Merchant.builder()
                .name(request.name())
                .email(request.email())
                .token(request.token())
                .build();
    }
}
