package com.emcode.tabz.service.imp;

import com.emcode.tabz.model.Merchant;
import com.emcode.tabz.model.Tab;
import com.emcode.tabz.repository.MerchantRepo;
import com.emcode.tabz.repository.TabRepo;
import com.emcode.tabz.service.FileStorageManager;
import com.emcode.tabz.service.MerchantService;
import com.emcode.tabz.service.TabService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TabServiceBasic implements TabService {

    private final FileStorageManager storageManager;
    private final TabRepo tabRepo;
    private final MerchantRepo merchantRepo;

    public TabServiceBasic(FileStorageManager storageManager, TabRepo tabRepo, MerchantRepo merchantRepo) {
        this.storageManager = storageManager;
        this.tabRepo = tabRepo;
        this.merchantRepo = merchantRepo;
    }

    @Override
    public String createTab(MultipartFile file, Long merchantId) {
        Merchant merchant = merchantRepo.findById(merchantId).orElseThrow(()
                -> new NoSuchElementException("No merchant found with id: " + merchantId));
        Tab tab = new Tab();
        tab.setMerchant(merchant);
        Tab savedTab = tabRepo.save(tab);

        return storageManager.storeFile(merchantId, savedTab.getId(), file);
    }




}
