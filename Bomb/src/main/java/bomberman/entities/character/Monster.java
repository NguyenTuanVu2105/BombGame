package bomberman.entities.character;

import bomberman.Game;
import bomberman.entities.equipment.Boom;
import bomberman.entities.item.Item;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Tiles;
import bomberman.gui.Animation;
import bomberman.gui.Assets;
import bomberman.map.Map;
import bomberman.sound.GameSound;
import bomberman.states.GameState;

import java.awt.*;

public class Monster extends Character{
    protected final int UP = 0;
    protected final int DOWN = 1;
    protected final int LEFT = 2;
    protected final int RIGHT = 3;
    private boolean dead;
    private int time_dead;
    private GameState game;
    protected int side;
    private Animation animation;
    public Monster(GameState game,int x, int y) {
        super(x,y);
        speed = DEFAULT_SPEED/2;
        side = RIGHT;
        this.game = game;
        dead = false;
        time_dead = 60;
        animation = new Animation(Assets.monster_right2,Assets.monster_right1);
    }

    public void update() {
        moveBySide();
        if (time_dead>0) {
            if (dead) time_dead--;
        }
        checkCollisionVsItem();
    }
    public void checkCollisionVsItem(){
        Rectangle bound = new Rectangle((int)(x+xMove),(int)(y+yMove+32),32,32);
        Map map = game.getMap();
        for (int i=0;i < map.items.size(); i++){
            Item item = map.items.get(i);
            if (bound.intersects(item.bound)) map.items.remove(i);
        }
    }
    public boolean isCollisionVsBoom(){
        Rectangle boundMonster = new Rectangle((int)(x+12+xMove),(int) (y +40 + yMove),16,12);
        for (Boom boom: game.getPlayer().getBooms()){
            Rectangle boundBoom = new Rectangle(boom.getX(),boom.getY(),Tiles.WIDTH, Tiles.HEIGHT);
            if(boundMonster.intersects(boundBoom)) return true;
        }
        return false;
    }
    public boolean isCollisionVsTile(){
        Rectangle bound = new Rectangle((int)(x+xMove),(int)(y+yMove+32),32,32);
        for (Tiles tile: game.getMap().tiles){
            if (bound.intersects(tile.bound) && !(tile instanceof Grass)) return true;
        }
        return false;
    }
    public boolean canMove(){
        return !isCollisionVsBoom() && !isCollisionVsTile();
    }
    public void kill(){
        dead = true;
        game.getGameSound().stop();
        game.getGameSound().play(GameSound.MONSTER_DIE);
    }
    public boolean dead(){
        return dead && time_dead==0;
    }
    public void moveBySide(){
        if (!dead) {
            xMove = 0;
            yMove = 0;
            switch (side) {
                case UP:
                    yMove = -speed;
                    break;
                case DOWN:
                    yMove = speed;
                    break;
                case LEFT:
                    xMove = -speed;
                    break;
                case RIGHT:
                    xMove = speed;
            }
            if (canMove()){
                move();
                animation.update();
            }
            else changeSide();
        }
    }
    private void changeSide(){
        side = (side+1)%4;
        switch(side){
            case UP:
                animation = new Animation(Assets.monster_up1,Assets.monster_up2);
                break;
            case DOWN:
                animation = new Animation(Assets.monster_down1,Assets.monster_down2);
                break;
            case LEFT:
                animation = new Animation(Assets.monster_left1,Assets.monster_left2);
                break;
            case RIGHT:
                animation = new Animation(Assets.monster_right1,Assets.monster_right2);
                break;
        }
    }
    public void render(Graphics2D g2d) {
        if (!dead) {
            g2d.drawImage(animation.getAnimation(),(int)x,(int)y,null);
        }
        else g2d.drawImage(Assets.monster_dead,(int)x, (int) y,null);
    }
}
