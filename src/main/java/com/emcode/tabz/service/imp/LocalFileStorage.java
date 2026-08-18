package com.emcode.tabz.service.imp;

import com.emcode.tabz.exception.RecordNotFoundException;
import com.emcode.tabz.service.FileStorageManager;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = STORAGE_LOCATION.resolve(fileName).normalize();

            if (!filePath.startsWith(STORAGE_LOCATION)) {
                throw new AccessDeniedException("Invalid file path");
            }

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RecordNotFoundException("File not found");
            }

            return resource;
        } catch (MalformedURLException ex) {
            throw new IllegalStateException("Could not load file", ex);
        }
    }


}
