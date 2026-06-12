package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.ClaimRequest;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.model.User;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.repository.TabRepo;
import com.emcode.tabz.repository.UserRepo;
import com.emcode.tabz.service.FileStorageManager;
import com.emcode.tabz.service.TabService;
import com.emcode.tabz.util.QRGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TabServiceBasic implements TabService {

    private final FileStorageManager storageManager;
    private final TabRepo tabRepo;
    private final ShopRepo shopRepo;
    private final UserRepo userRepo;
    private final QRGenerator qrGenerator;
    private final String frontBaseUrl;

    public TabServiceBasic(FileStorageManager storageManager, TabRepo tabRepo, ShopRepo shopRepo, UserRepo userRepo,
                           QRGenerator qrGenerator, @Value("${app.front-base-url}") String frontBaseUrl) {
        this.storageManager = storageManager;
        this.tabRepo = tabRepo;
        this.shopRepo = shopRepo;
        this.userRepo = userRepo;
        this.qrGenerator = qrGenerator;
        this.frontBaseUrl = frontBaseUrl;
    }

    @Override
    public byte[] createTab(MultipartFile file, Shop shop) {
        validateFile(file);
        String fileName = storageManager.storeFile(file);
        Tab savedTab =  saveTab(shop, fileName);
        String endpoint = createEndpointForClaim(savedTab.getId());

        try {
            return qrGenerator.generateQRCode(endpoint, 300, 300);
        } catch (IOException | WriterException e) {
            throw new RuntimeException("Could not generate QR code: " + e.getMessage(), e);
        }
    }

    @Override
    public String claim(Long tabId, User user, ClaimRequest request) {
        Tab tab = findTab(tabId);
        if(request.isClaimed()) {
            tab.setUser(user);
            tab.setClaimed(true);
        } else {
            tab.setUser(null);
            tab.setClaimed(false);
        }
        tabRepo.save(tab);
        return tab.isClaimed() ? "Tab claimed" : "Tab unclaimed";
    }

    private User findUser(Long userId) {
        return userRepo.findById(userId).orElseThrow(()
                -> new NoSuchElementException("No user found with id " + userId));
    }

    private Tab findTab(Long tabId) {
        return tabRepo.findById(tabId).orElseThrow(()
                -> new NoSuchElementException("No tab found with id: " + tabId));
    }

    private Shop findShop(Long shopId) {
        return shopRepo.findById(shopId).orElseThrow(()
                -> new NoSuchElementException("No shop found with id: " + shopId));
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        String contentType = file.getContentType();
        if (!"application/pdf".equals(contentType)) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }
        String originalName = file.getOriginalFilename();
        if (originalName == null || !originalName.endsWith(".pdf")) {
            throw new IllegalArgumentException("File must have a .pdf extension");
        }
    }

    private Tab saveTab(Shop shop, String fileName) {
        Tab tab = new Tab();
        tab.setShop(shop);
        tab.setFileName(fileName);
        LocalDateTime createdAt = LocalDateTime.now();
        tab.setCreatedAt(createdAt);
        return tabRepo.save(tab);
    }

    private String createEndpointForClaim(Long tabId) {
        return frontBaseUrl + "/api/tab/" + tabId + "/claim-page";
    }




}
