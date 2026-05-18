package com.emcode.tabz.controller;

import com.emcode.tabz.dto.MerchantRequest;
import com.emcode.tabz.dto.MerchantResponse;
import com.emcode.tabz.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponse> getMerchant(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @PostMapping
    public Long createMerchant(@RequestBody MerchantRequest merchantRequest) {
        return merchantService.createMerchant(merchantRequest);
        // todo change return type to ResponseEntity <response>
    }
}
