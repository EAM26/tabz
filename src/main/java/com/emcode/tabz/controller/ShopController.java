package com.emcode.tabz.controller;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.dto.ShopResponseCreate;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopResponse> getShop(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @GetMapping("/owner")
    public ResponseEntity<ShopResponse> getShop(Authentication authentication) {
        Shop shop = (Shop) authentication.getPrincipal();
        Long shopId = shop.getId();
        return ResponseEntity.ok(shopService.getShopById(shopId));
    }



    @PostMapping
    public ResponseEntity<ShopResponseCreate> createShop(@RequestBody ShopRequest shopRequest) {
        ShopResponseCreate response = shopService.createShop(shopRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
}
