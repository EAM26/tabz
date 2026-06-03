package com.emcode.tabz.service.imp;

import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.repository.TabRepo;
import com.emcode.tabz.service.FileStorageManager;
import com.emcode.tabz.service.TabService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TabServiceBasic implements TabService {

    private final FileStorageManager storageManager;
    private final TabRepo tabRepo;
    private final ShopRepo shopRepo;

    public TabServiceBasic(FileStorageManager storageManager, TabRepo tabRepo, ShopRepo shopRepo) {
        this.storageManager = storageManager;
        this.tabRepo = tabRepo;
        this.shopRepo = shopRepo;
    }

    @Override
    public String createTab(MultipartFile file, Long shopId) {
        validateFile(file);
        Shop shop = findShop(shopId);

        String fileName = storageManager.storeFile(file);

        return saveTab(shop, fileName).getFileName();
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




}
