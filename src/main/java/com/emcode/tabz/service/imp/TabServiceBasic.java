package com.emcode.tabz.service.imp;

import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.model.User;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.repository.TabRepo;
import com.emcode.tabz.repository.UserRepo;
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
    private final UserRepo userRepo;

    public TabServiceBasic(FileStorageManager storageManager, TabRepo tabRepo, ShopRepo shopRepo, UserRepo userRepo) {
        this.storageManager = storageManager;
        this.tabRepo = tabRepo;
        this.shopRepo = shopRepo;
        this.userRepo = userRepo;
    }

    @Override
    public String createTab(MultipartFile file, Long shopId) {
        validateFile(file);
        Shop shop = findShop(shopId);

        String fileName = storageManager.storeFile(file);

        Tab savedTab =  saveTab(shop, fileName);
        return createEndpointForClaim(savedTab.getId());
    }

    @Override
    public String claim(Long userId, Long tabId) {
        Tab tab = findTab(tabId);
        User user = findUser(userId);
        tab.setUser(user);
        tabRepo.save(tab);
        return "ok, user claimed tab";
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
        return  "http://localhost:8080/api/tab/claim/" + tabId;
    }




}
