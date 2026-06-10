package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.service.ShopService;
import com.emcode.tabz.util.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Primary
public class ShopServiceBasic implements ShopService {

    private final ShopRepo shopRepo;
    private final ModelMapper mapper;

    public ShopServiceBasic(ShopRepo shopRepo, ModelMapper mapper) {
        this.shopRepo = shopRepo;
        this.mapper = mapper;
    }

    @Override
    public ShopResponse getShopById(Long id) {
        Shop shop =  shopRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No shop found with id: " + id));
        return mapper.createShopResponse(shop);

    }

    @Override
    public ShopResponse createShop(ShopRequest shopRequest) {
        Shop savedShop = shopRepo.save(mapper.createShopEntity(shopRequest));
        return mapper.createShopResponse(savedShop);
    }





}
