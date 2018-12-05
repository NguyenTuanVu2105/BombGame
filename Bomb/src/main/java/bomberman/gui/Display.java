package bomberman.gui;

import bomberman.panel.GameOver;
import bomberman.panel.Win;

import javax.swing.*;
import java.awt.*;

public class Display{
    public static final String TITLE = "BombGame";
    private int width;
    private int height;
    private JFrame frame;
    private Canvas canvas;
    private GameOver gameOver;
    private Win win;
    public Display(int width, int height) {
        this.width = width;
        this.height = height;
        initCompenent();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public Win getWin() {
        return win;
    }

    private void initCompenent() {
        frame = new JFrame();
        frame.setSize(width,height);
        frame.setVisible(true);
        frame.setLayout(new CardLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        frame.add(canvas);

        gameOver = new GameOver();
        frame.add(gameOver);
        gameOver.setVisible(false);

        win = new Win();
        frame.add(win);
        win.setVisible(false);
        frame.pack();
    }

}
