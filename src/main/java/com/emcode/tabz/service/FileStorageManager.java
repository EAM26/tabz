package com.emcode.tabz.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageManager {

    String storeFile(Long tabId, Long shopId, MultipartFile file);
}
