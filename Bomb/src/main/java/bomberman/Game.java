package bomberman;

import bomberman.gui.Display;
import bomberman.input.Key;
import bomberman.sound.GameSound;
import bomberman.states.GameState;
import bomberman.states.MenuState;
import bomberman.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    public final int WIN = 0;
    public final int LOSE = 1;
    public final int NONE = 2;
    public final static int WIDTH = 992;
    public final static int HEIGHT = 416;
    private Display display;
    private Thread thread;
    public boolean running;
    private BufferStrategy bs;
    private Graphics g;
    private Graphics2D g2d;
    private State state;
    private int status;
    public Game() {
        start();
        status = NONE;
    }
    private void init(){
        display = new Display(WIDTH,HEIGHT);
        state = new MenuState(this);
    }
    private void update(){
        state.update();
    }
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        //Start Draw
        g = bs.getDrawGraphics();
        g2d = (Graphics2D) g;
        g2d.clearRect(0,0,WIDTH,HEIGHT);
        state.render(g2d);
        //End draw

        bs.show();
        g2d.dispose();
    }
    public void run(){
        init();
        while (running){
            update();
            render();
        }
        stop();
    }
    public void start(){
        if (running) return;
        running = true;
        run();
    }
    public void stop(){
        try {
            if (!running){
                display.getCanvas().setVisible(false);
                if (status == WIN) {
                    display.getWin().setVisible(true);
                }
                else display.getGameOver().setVisible(true);
                thread.sleep(5000);
                display.getCanvas().setVisible(true);
                start();
            }
        } catch (InterruptedException e){
            System.exit(-1);
        }
    }


    public Display getDisplay() {
        return display;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void changeState(State state){
        this.state = state;
    }
}
