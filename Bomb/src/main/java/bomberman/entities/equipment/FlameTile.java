package bomberman.entities.equipment;

import bomberman.entities.character.Monster;
import bomberman.entities.character.Player;
import bomberman.entities.tiles.Tiles;
import bomberman.gui.Assets;

import java.awt.*;

public class FlameTile {
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;
    private int x,y;
    private int status;
    private boolean head;
    private Rectangle bound;
    public FlameTile(int x, int y, int status,boolean head ) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.head = head;
        bound = new Rectangle(x,y,Tiles.WIDTH,Tiles.HEIGHT);
    }
    public void render(Graphics2D g2d){
        switch (status){
            case UP:
                if (head) g2d.drawImage(Assets.flame_up1,x,y, Tiles.WIDTH,Tiles.HEIGHT,null);
                else g2d.drawImage(Assets.flame_up2,x,y, Tiles.WIDTH,Tiles.HEIGHT,null);
                break;
            case DOWN:
                if (head) g2d.drawImage(Assets.flame_down1,x,y, Tiles.WIDTH,Tiles.HEIGHT,null);
                else g2d.drawImage(Assets.flame_down2,x,y,Tiles.WIDTH,Tiles.HEIGHT,null);
                break;
            case LEFT:
                if (head) g2d.drawImage(Assets.flame_left1,x,y, Tiles.WIDTH,Tiles.HEIGHT,null);
                else g2d.drawImage(Assets.flame_left2,x,y,Tiles.WIDTH,Tiles.HEIGHT,null);
                break;
            case RIGHT:
                if (head) g2d.drawImage(Assets.flame_right1,x,y, Tiles.WIDTH,Tiles.HEIGHT,null);
                else g2d.drawImage(Assets.flame_right2,x,y,Tiles.WIDTH,Tiles.HEIGHT,null);
                break;
        }
    }
    public boolean isCollisionVsPlayer(Player player){
        Rectangle boundPlayer = new Rectangle((int)(player.getX()+10),(int) (player.getY()+36),16,12);
        Rectangle boundFlameTile = new Rectangle(x,y,Tiles.WIDTH, Tiles.HEIGHT);
        return boundPlayer.intersects(boundFlameTile);
    }
    public boolean isCollisionVsMonster(Monster monster){
        Rectangle boundMonster = new Rectangle((int)(monster.getX()+10),(int)(monster.getY()+36),16,16);
        Rectangle boundFlameTile = new Rectangle(x,y,Tiles.WIDTH, Tiles.HEIGHT);
        return boundMonster.intersects(boundFlameTile);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBound() {
        return bound;
    }
}
