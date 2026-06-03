package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.service.ShopService;
import com.emcode.tabz.util.ShopMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Primary
public class ShopServiceBasic implements ShopService {

    private final ShopRepo shopRepo;

    private final ShopMapper shopMapper;

    public ShopServiceBasic(ShopRepo shopRepo, ShopMapper shopMapper) {
        this.shopRepo = shopRepo;
        this.shopMapper = shopMapper;
    }

    @Override
    public ShopResponse getShopById(Long id) {
        Optional<Shop> optionalShop = shopRepo.findById(id);
        return optionalShop.map(shopMapper::mapToResponse).orElseThrow(() ->
                new NoSuchElementException("No shop found with id: " + id));
        // todo: make custom exception and global handler
    }

    @Override
    public Long createShop(ShopRequest shopRequest) {
        Shop savedShop = shopRepo.save(shopMapper.mapToEntity(shopRequest));
        return savedShop.getId(); // todo: change returntype to response
    }

}
