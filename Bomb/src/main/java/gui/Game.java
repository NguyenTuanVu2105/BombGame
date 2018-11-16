package gui;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    Game(){
        initCompenents();
    }

    private void initCompenents() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.red);
        Image img = ImageLoader.loadImage("/image/background_hightscore.png");
        g2d.drawImage(img,220,220, null);
    }
}
