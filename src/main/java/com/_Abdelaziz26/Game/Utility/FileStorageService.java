package com._Abdelaziz26.Game.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service

public class FileStorageService {

    @Value("${storage.dir}")
    private static String PARENT_DIR;

    public String SaveImgAndGetUrl(MultipartFile file, String subfolder) throws IOException {

        if(file == null || file.isEmpty() ) {
            throw new NullPointerException("File is null or empty");
        }

        File targetFile = new File(PARENT_DIR + File.separator +
                subfolder + File.separator + file.getOriginalFilename());

        if(!Objects.equals(targetFile.getParentFile().getAbsolutePath(), PARENT_DIR))
            throw new SecurityException("Unsupported file name");

        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return targetFile.getAbsolutePath();

    }
}
