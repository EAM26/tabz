package com.emcode.tabz.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageManager {

    String storeFile(MultipartFile file);
}
