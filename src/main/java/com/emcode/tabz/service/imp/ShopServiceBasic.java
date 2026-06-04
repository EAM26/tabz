package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.service.ShopService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Primary
public class ShopServiceBasic implements ShopService {

    private final ShopRepo shopRepo;

    public ShopServiceBasic(ShopRepo shopRepo) {
        this.shopRepo = shopRepo;
    }

    @Override
    public ShopResponse getShopById(Long id) {
        Shop shop =  shopRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No shop found with id: " + id));
        return createResponse(shop);

    }

    @Override
    public ShopResponse createShop(ShopRequest shopRequest) {
        Shop savedShop = shopRepo.save(createShopEntity(shopRequest));
        return createResponse(savedShop);
    }

    private ShopResponse createResponse(Shop shop) {
        return new ShopResponse(
                shop.getId(),
                shop.getName(),
                shop.getEmail(),
                shop.getToken(),
                shop.isActive()
        );
    }

    private Shop createShopEntity(ShopRequest request) {
        return Shop.builder()
                .name(request.name())
                .email(request.email())
                .token(request.token())
                .active(request.active())
                .build();
    }

}
