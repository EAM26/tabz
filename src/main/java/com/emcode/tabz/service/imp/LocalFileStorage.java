package com.emcode.tabz.service.imp;

import com.emcode.tabz.service.FileStorageManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalFileStorage implements FileStorageManager {

    private static final Path STORAGE_LOCATION = Path.of("C:/tabz-data/storage");

    @Override
    public String storeFile(MultipartFile file)  {

        try {
            Files.createDirectories(STORAGE_LOCATION);
            String fileName = UUID.randomUUID() + ".pdf";
            Path targetLocation = STORAGE_LOCATION.resolve(fileName);

            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + e.getMessage());
        }

    }


}
