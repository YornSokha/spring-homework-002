package com.hrd.springhomework.helper;

import com.hrd.springhomework.repository.model.Article;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class UploadImage {
    public static boolean upload(Article article, MultipartFile file) {
        String imageName = UUID.randomUUID().toString();
        String extension;
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            imageName += extension;
            try {
                Files.copy(file.getInputStream(), Paths.get("src/main/resources/static/image/" + imageName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            article.setImage(imageName);
            return true;
        }
        return false;
    }
}
