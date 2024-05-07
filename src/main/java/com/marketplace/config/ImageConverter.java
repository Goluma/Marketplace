package com.marketplace.config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static void byteArrayToImage(byte[] byteArray, String outputFilePath) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        BufferedImage bufferedImage = ImageIO.read(bis);
        bis.close();

        File outputFile = new File(outputFilePath);
        ImageIO.write(bufferedImage, "png", outputFile);
    }
}

