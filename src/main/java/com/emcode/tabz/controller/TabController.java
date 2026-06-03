package com.emcode.tabz.controller;

import com.emcode.tabz.service.TabService;
import com.emcode.tabz.util.QRGenerator;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/tab")
public class TabController {

    private final TabService tabService;

    public TabController(TabService tabService) {
        this.tabService = tabService;
    }

    @PostMapping(value = "/shop/{shopId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createTab(@RequestParam("file") MultipartFile file, @PathVariable Long shopId) {
        return ResponseEntity.ok(tabService.createTab(file, shopId));
    }

    @PostMapping(value = "/claim/{tabId}/{userId}")
    public String claimTabByUser(@PathVariable Long tabId, @PathVariable Long userId) {
        return tabService.claim(userId, tabId);
    }

    @PostMapping
    public ResponseEntity<byte[]> createTabAndQR(@RequestBody String url) throws IOException, WriterException {
        // todo refactor to service
        QRGenerator generator = new QRGenerator();
        URI location = URI.create("fake-location");
        byte[] qrImage =  generator.generateQRCode(url, 300, 300);
        return ResponseEntity
                .created(location)
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }



}
