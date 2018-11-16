package gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
           return ImageIO.read(ImageLoader.class.getResource(path));
        }catch (IOException e){
            System.out.println("image fall");
        }
        return null;
    }
}
