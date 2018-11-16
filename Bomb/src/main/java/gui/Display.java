package gui;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame{
    public static final String TITLE = "BombGame";
    public Display() {
        initCompenent();
    }

    private void initCompenent() {
        setSize(520,520);
        setVisible(true);
        setLayout(new CardLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new Game());
    }

}
