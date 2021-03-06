package bomberman.entities.tiles;

import java.awt.*;

public abstract class Tiles {
    protected int x,y;
    protected Image img;
    public Rectangle bound;
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;

    public Tiles(int x, int y) {
        this.x = x;
        this.y = y;
        bound = new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(img,x,y,null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
