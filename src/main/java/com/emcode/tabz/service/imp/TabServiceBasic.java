package com.emcode.tabz.service.imp;

import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.repository.ShopRepo;
import com.emcode.tabz.repository.TabRepo;
import com.emcode.tabz.service.FileStorageManager;
import com.emcode.tabz.service.TabService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        Shop shop = shopRepo.findById(shopId).orElseThrow(()
                -> new NoSuchElementException("No shop found with id: " + shopId));
        Tab tab = new Tab();
        tab.setShop(shop);
        Tab savedTab = tabRepo.save(tab);

        return storageManager.storeFile(shopId, savedTab.getId(), file);
    }




}
