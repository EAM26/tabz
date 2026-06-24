package com.emcode.tabz.controller;

import com.emcode.tabz.dto.ClaimRequest;
import com.emcode.tabz.dto.ShopTabResponse;
import com.emcode.tabz.dto.UserTabResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.User;
import com.emcode.tabz.service.TabService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tab")
public class TabController {

    private final TabService tabService;

    public TabController(TabService tabService) {
        this.tabService = tabService;
    }

    @PostMapping(value = "/shop", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> createTab(@RequestParam("file") MultipartFile file,
                                            @RequestParam BigDecimal totalAmount,
                                            Authentication authentication) {
        Shop shop = (Shop) authentication.getPrincipal();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(tabService.createTab(file, totalAmount, shop));
    }

    @PostMapping(value = "/claim/{tabId}")
    public ResponseEntity<String> claimTabByUser(@PathVariable Long tabId, Authentication authentication, @RequestBody ClaimRequest request) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(tabService.claim(tabId, user, request));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserTabResponse>> getAllTabsByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(tabService.getTabsByUserId(user.getId()));
    }

    @GetMapping(value = "/shop")
    public ResponseEntity<List<ShopTabResponse>> createTab(Authentication authentication) {
        Shop shop = (Shop) authentication.getPrincipal();
        return ResponseEntity.ok(tabService.getTabsByShopId(shop.getId()));
    }


}
