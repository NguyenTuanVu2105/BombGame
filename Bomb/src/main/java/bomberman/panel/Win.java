package bomberman.panel;

import javax.swing.*;
import java.awt.*;

public class Win extends JPanel{
    private JLabel background;

    public Win() {
        setLayout(new CardLayout());
        background = new JLabel(new ImageIcon(getClass().getResource("/img/win.png")));
        add(background);
    }
}
