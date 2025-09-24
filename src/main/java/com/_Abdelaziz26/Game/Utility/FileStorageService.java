package com._Abdelaziz26.Game.Utility;

import com.cloudinary.Cloudinary;
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
// سيبها عشان لو كنت ناسي اي كول ليها في اي داهيه

public class FileStorageService extends CloudinaryService{

    public FileStorageService(Cloudinary cloudinary) {
        super(cloudinary);
    }

    public String SaveImgAndGetUrl(MultipartFile file) throws IOException {
       return super.upload(file);
    }
}
