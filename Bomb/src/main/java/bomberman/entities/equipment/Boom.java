package bomberman.entities.equipment;

import bomberman.Game;
import bomberman.entities.character.Player;
import bomberman.entities.tiles.Tiles;
import bomberman.gui.Assets;
import bomberman.sound.GameSound;
import bomberman.states.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Boom {
    protected final int DEFAULT_TIMELINE = 320;
    public static final int DEFAULT_TIMEBANG = 60;
    protected int x,y;
    protected int timeline;
    protected int timebang;
    private Flame flame_up;
    private Flame flame_down;
    private Flame flame_left;
    private Flame flame_right;
    private boolean isBang;
    private boolean isRemoved;
    private boolean isPlayerRunOut;
    private int size;
    private boolean soundBang;
    private GameState game;
    public Boom(GameState game, int x, int y, int size) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.size = size;
        timeline = DEFAULT_TIMELINE;
        timebang = DEFAULT_TIMEBANG;
        isBang = false;
        isRemoved = false;
        isPlayerRunOut = false;
        soundBang = false;
    }
    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }

    public void update(){
        checkPlayerRun();
        if (timeline>0) timeline--;
        else {
            if(!soundBang) {
                game.getGameSound().play(GameSound.BONG_BANG);
                soundBang = true;
            }
            isBang = true;
            flame_up = new Flame(game,x, y - Tiles.HEIGHT*size, Flame.UP,size,this);
            flame_down = new Flame(game,x, y + Tiles.HEIGHT*size, Flame.DOWN,size,this);
            flame_left = new Flame(game,x - Tiles.WIDTH*size, y, Flame.LEFT,size,this);
            flame_right = new Flame(game,x + Tiles.WIDTH*size, y, Flame.RIGHT,size,this);
            if (isCollisionVsPlayer()) game.getPlayer().kill();
            if (timebang>0) timebang--;
            else{
                flame_left.checkCollisionVsBrick();
                flame_right.checkCollisionVsBrick();
                flame_up.checkCollisionVsBrick();
                flame_down.checkCollisionVsBrick();
                isRemoved = true;
            }
        }
    }

    public void render(Graphics2D g2d){
        if (!isBang)
             g2d.drawImage(Assets.boom,x+4,y+4,null);
        else {
            g2d.drawImage(Assets.flame_center,x,y,32,32,null);
            flame_right.render(g2d);
            flame_up.render(g2d);
            flame_down.render(g2d);
            flame_left.render(g2d);
        }
    }
    public void checkPlayerRun(){
        if (isPlayerRunOut) return;
        if  (!isCollisionVsPlayer()) isPlayerRunOut = true;
    }
    public boolean playerRunOut(){
        return isPlayerRunOut;
    }
    public boolean isCollisionVsPlayer(){
        Player player = game.getPlayer();
        Rectangle boundPlayer = new Rectangle((int)player.getX()+10,(int) player.getY() +36,16,16);
        Rectangle boundBoom = new Rectangle(x,y,Tiles.WIDTH,Tiles.HEIGHT);
        return boundBoom.intersects(boundPlayer);
    }
    public boolean isBang() {
        return isBang;
    }

    public boolean canRemove(){
        return isRemoved;
    }
    public boolean same(Boom boom){
        return (this.x == boom.x) && (this.y == boom.y);
    };
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

    public int getTimebang() {
        return timebang;
    }
}
