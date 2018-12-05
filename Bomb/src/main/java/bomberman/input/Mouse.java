package bomberman.input;

import bomberman.Game;
import bomberman.states.GameState;
import bomberman.states.MenuState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener , MouseMotionListener {
    MenuState menu;
    private boolean play;
    private boolean exit;
    private boolean sound;
    private boolean playHover;
    private boolean exitHover;
    private boolean soundHover;
    public Mouse(MenuState menu) {
        this.menu = menu;
        play = false;
        exit = false;
        sound = true;
        playHover = false;
        exitHover = false;
        soundHover = false;
    }

    public boolean isPlay() {
        return play;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isPlayHover() {
        return playHover;
    }

    public boolean isExitHover() {
        return exitHover;
    }

    public boolean isSound() {
        return sound;
    }

    public boolean isSoundHover() {
        return soundHover;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(menu.getPlayButton().contains(e.getPoint())) play = true;
        else if (menu.getSoundButton().contains(e.getPoint())) sound=!sound;
        else if (menu.getExitButton().contains(e.getPoint())) exit = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(menu.getPlayButton().contains(e.getPoint())) playHover = true;
        else playHover = false;
        if (menu.getSoundButton().contains(e.getPoint())) soundHover = true;
        else soundHover = false;
        if (menu.getExitButton().contains(e.getPoint())) exitHover = true;
        else exitHover = false;
    }
}
