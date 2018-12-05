package bomberman.gui;

import java.awt.*;

public class Animation {
    private Image img1,img2,currentImage;
    private int speed;
    private int index;
    public Animation() {
    }

    public Animation(Image img1, Image img2) {
        this.img1 = img1;
        this.img2 = img2;
        currentImage = img1;
        speed = 30;
        index = 0;
    }
    public void update(){
        if (index>speed) {
            index = 0;
            nextImage();
        }
        else index++;
    }
    public Image getAnimation(){
        return currentImage;
    }
    private void nextImage() {
        if (currentImage.equals(img1)) currentImage = img2;
        else currentImage = img1;
    }

}
