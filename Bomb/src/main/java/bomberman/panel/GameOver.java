package bomberman.panel;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    private JLabel background;

    public GameOver() {
        setLayout(new CardLayout());
        background = new JLabel(new ImageIcon(getClass().getResource("/img/gameover.png")));
        add(background);
    }
}
