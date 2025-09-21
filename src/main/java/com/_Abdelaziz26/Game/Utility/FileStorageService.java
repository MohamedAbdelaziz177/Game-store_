package com._Abdelaziz26.Game.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service

public class FileStorageService {

    @Value("${STORAGE_DIR}")
    private String PARENT_DIR;

    public String SaveImgAndGetUrl(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }


        Path parentDir = Paths.get(PARENT_DIR).normalize().toAbsolutePath();
        String filename = file.getOriginalFilename();


        if (filename == null || filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
            throw new SecurityException("Invalid filename");
        }

        Path targetPath = parentDir.resolve(filename).normalize();


        if (!targetPath.startsWith(parentDir)) {
            throw new SecurityException("Path traversal attempt blocked");
        }


        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }


        Files.copy(
                file.getInputStream(),
                targetPath,
                StandardCopyOption.REPLACE_EXISTING
        );


        return "/images/" + parentDir.relativize(targetPath).toString();
    }
}
