package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.service.ShopService;
import com.emcode.tabz.util.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Primary
public class ShopServiceBasic implements ShopService {

    private final ShopRepo shopRepo;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public ShopServiceBasic(ShopRepo shopRepo, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.shopRepo = shopRepo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ShopResponse getShopById(Long id) {
        Shop shop =  shopRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No shop found with id: " + id));
        return mapper.createShopResponse(shop);

    }

    @Override
    public ShopResponse createShop(ShopRequest shopRequest) {
        Shop shop = mapper.createShopEntity(shopRequest);

        String rawToken = generateToken();
        shop.setTokenHash(hashToken(rawToken));

        Shop savedShop = shopRepo.save(shop);
        return mapper.createShopResponse(savedShop, rawToken);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private String hashToken(String token) {
        return passwordEncoder.encode(token);
    }


}
