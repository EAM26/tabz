package com.emcode.tabz.service.imp;

import com.emcode.tabz.service.FileStorageManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileStorage implements FileStorageManager {

    private static final Path STORAGE_LOCATION = Path.of("C:/tabz-data/storage");
    @Override
    public String storeFile(Long tabId, Long merchantId, MultipartFile file) {

        try {
            Files.createDirectories(STORAGE_LOCATION);
            String fileName = merchantId + "_" + tabId + ".pdf";
            Path targetLocation = STORAGE_LOCATION.resolve(fileName);

            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toString();

        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + e.getMessage());
        }
    }
}
