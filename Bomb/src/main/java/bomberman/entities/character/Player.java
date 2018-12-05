package bomberman.entities.character;

import bomberman.Game;
import bomberman.entities.equipment.Boom;
import bomberman.entities.item.*;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Tiles;
import bomberman.gui.Animation;
import bomberman.gui.Assets;
import bomberman.map.Map;
import bomberman.sound.GameSound;
import bomberman.states.GameState;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Character{
    protected final int UP = 0;
    protected final int DOWN = 1;
    protected final int LEFT = 2;
    protected final int RIGHT = 3;
    protected final int DEAD = 4;
    private GameState game;
    private int status;
    private ArrayList<Boom> booms = new ArrayList<Boom>();
    private int nBoom;
    private int sizeBoom;
    private int time_dead;
    private Animation animation;
    public Player(GameState game, float x, float y) {
        super(x,y);
        this.game = game;
        speed = DEFAULT_SPEED;
        nBoom = 1;
        sizeBoom = 1;
        time_dead = 240;
        status = RIGHT;
        animation = new Animation(Assets.player_right2,Assets.player_right1);
    }
    public void kill(){
        status = DEAD;
        game.getGameSound().play(GameSound.BOMBER_DIE);
    }
    public boolean canMove(){
        return !isCollisionVsTile() && !isCollisionVsBoom();
    }
    public boolean isCollisionVsTile(){
        Rectangle bound = new Rectangle((int)(x+10+xMove),(int)(y+yMove+32),12,10);
        for (Tiles tile: game.getMap().tiles){
            if ((bound.intersects(tile.bound)) && !(tile instanceof Grass)) return true;
        }
        return false;
    }
    public boolean isCollisionVsMonster(){
        Rectangle boundPlayer = new Rectangle((int)x+10,(int) y +36,16,16);
        for (Monster monster: game.getMonsters()){
            Rectangle boundMonster = new Rectangle((int)monster.getX()+12,(int)monster.getY()+40,16,12);
            if(boundPlayer.intersects(boundMonster)) return true;
        }
        return false;
    }
    public boolean isCollisionVsBoom(){
        Rectangle boundPlayer = new Rectangle((int)(x+10+xMove),(int) (y +36 + yMove),16,16);
        for (Boom boom: booms){
            Rectangle boundBoom = new Rectangle(boom.getX(),boom.getY(),Tiles.WIDTH, Tiles.HEIGHT);
            if(boundPlayer.intersects(boundBoom) && boom.playerRunOut()) return true;
        }
        return false;
    }
    public void checkDead(){
        if (isCollisionVsMonster()) {
            status = DEAD;
        }
    }
    public boolean dead(){
        return time_dead==0;
    }

    public void removeBoom(){
        for (int i=0; i<booms.size(); i++){
            if (booms.get(i).canRemove()){
                booms.remove(i);
                nBoom++;
                return;
            }
        }
    }
    public void putBoom(){
        if(nBoom != 0) {
            game.getGameSound().play(GameSound.BOMB);
            Boom boom = new Boom(game,((int)(x+16))/32*32,((int)(y+38)/32*32),sizeBoom);
            if (booms.size()==0) {
                booms.add(boom);
                nBoom--;
            }
            else if (!booms.get(booms.size()-1).same(boom)) {
                booms.add(boom);
                nBoom--;
            }
        }
    }
    public void getInput(){
        xMove = 0;
        yMove = 0;
        if (game.getKey().up) {
            yMove = -speed;
                if (status!=UP){
                    status = UP;
                    animation = new Animation(Assets.player_up1,Assets.player_up2);
                }
        }
        if (game.getKey().down) {
            yMove = speed;
                if (status!=DOWN){
                    status = DOWN;
                    animation = new Animation(Assets.player_down1,Assets.player_down2);
                }
        }
        if (game.getKey().left) {
            xMove = -speed;
            if (status!=LEFT) {
                status = LEFT;
                animation = new Animation(Assets.player_left1, Assets.player_left2);
            }
        }
        if (game.getKey().right) {
            xMove = speed;
            if (status!=RIGHT) {
                status = RIGHT;
                animation = new Animation(Assets.player_right1, Assets.player_right2);
            }
        }
        if (game.getKey().space) {
            putBoom();
        }
    }
    @Override
    public void update() {
        //animation.update();
        if(status != DEAD) {
            getInput();
            if (canMove()) {
                move();
                if (xMove != 0 || yMove!=0) animation.update();
            }
            pickItem();
        }else time_dead--;
            removeBoom();
            if (booms.size() != 0) {
                for (Boom boom : booms) {
                    boom.update();
                }
            }
            checkDead();
    }
    @Override
    public void render(Graphics2D g2d) {
        if (booms.size()!=0) {
            for (Boom boom: booms) {
                boom.render(g2d);
            }
        }
        if (status == DEAD)
                g2d.drawImage(Assets.player_dead,(int)x,(int)y,null);
        else g2d.drawImage(animation.getAnimation(),(int)x,(int)y,null);

    }
    public void pickItem(){
        Rectangle boundPlayer = new Rectangle((int)x+10,(int) y +36,16,16);
        Map map = game.getMap();
        for(int i=0;i<map.items.size();i++){
            Item item = map.items.get(i);
            if (boundPlayer.intersects(item.bound)){
                game.getGameSound().play(GameSound.ITEM);
                if (item instanceof ItemSpeedDecrease) {
                    if (speed > 0.1) speed -= 0.3;
                }
                else if (item instanceof ItemSpeedIncrease){
                    speed+=0.6;
                }
                else if (item instanceof ItemBoom){
                    nBoom++;
                }
                else if (item instanceof ItemFlame){
                    sizeBoom++;
                }
               map.items.remove(i);
            }
        }
    }
    public int getnBoom() {
        return nBoom;
    }

    public void setnBoom(int nBoom) {
        this.nBoom = nBoom;
    }

    public int getSizeBoom() {
        return sizeBoom;
    }

    public void setSizeBoom(int sizeBoom) {
        this.sizeBoom = sizeBoom;
    }

    public ArrayList<Boom> getBooms() {
        return booms;
    }
}
