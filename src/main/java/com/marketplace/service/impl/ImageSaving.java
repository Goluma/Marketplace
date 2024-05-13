package com.marketplace.service.impl;

import lombok.extern.java.Log;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Log
public final class ImageSaving {

    private ImageSaving(){}

    private static final String PATH_TO_IMAGE = "src/main/resources/static";

    public static String saveImage(MultipartFile file){

        String fileName = UUID.randomUUID().toString() + ".png";
        Path filePath = Paths.get(PATH_TO_IMAGE, fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
            log.info("File successfully saved");
        } catch (IOException e) {
            log.info("Error file saving");
            e.printStackTrace();
            return "";
        }

        return PATH_TO_IMAGE + fileName;
    }
}
