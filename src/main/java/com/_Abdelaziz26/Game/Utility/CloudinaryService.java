package com._Abdelaziz26.Game.Utility;

import com._Abdelaziz26.Game.AOP.CloudinaryValidatorProxy;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @CloudinaryValidatorProxy
    public String upload(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(),
                Map.of(
                        "folder", "___GamesStore__Api",
                        "resource_type", "auto",
                        "public_id", file.getOriginalFilename(),
                        "overwrite", true
                )
        ).get("url").toString();

    }

    public void delete(String id) throws IOException {
        cloudinary.uploader().destroy(id, new HashMap<>());
    }
}
