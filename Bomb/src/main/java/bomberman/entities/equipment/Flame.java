package bomberman.entities.equipment;

import bomberman.Game;
import bomberman.entities.character.Monster;
import bomberman.entities.character.Player;
import bomberman.entities.tiles.Brick;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Tiles;
import bomberman.entities.tiles.Wall;
import bomberman.gui.Assets;
import bomberman.states.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Flame {
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;
    private int x,y;
    private int status;
    private int size;
    private boolean collision_brick;
    private GameState game;
    private ArrayList<FlameTile> flames;
    public Flame(GameState game,int x, int y, int status, int size,Boom boom) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.size = size;
        this.status = status;
        collision_brick = false;
        flames = new ArrayList<FlameTile>();
        initFlame();
        checkCollisionVsTile();
        checkCollisionVsMonster();
        checkCollisionVsPlayer();
    }
    private void initFlame(){
        switch (status){
            case UP:
                FlameTile head_up = new FlameTile(x,y,FlameTile.UP,true);
                flames.add(head_up);
                for(int i=1;i<size;i++){
                    FlameTile flameTile = new FlameTile(x,y+Tiles.HEIGHT*i,FlameTile.UP,false);
                    flames.add(flameTile);
                }
                break;
            case DOWN:
                FlameTile head_down = new FlameTile(x,y,FlameTile.DOWN,true);
                flames.add(head_down);
                for(int i=1;i<size;i++){
                    FlameTile flameTile = new FlameTile(x,y-Tiles.HEIGHT*i,FlameTile.DOWN,false);
                    flames.add(flameTile);
                }
                break;
            case LEFT:
                FlameTile head_left = new FlameTile(x,y,FlameTile.LEFT,true);
                flames.add(head_left);
                for(int i=1;i<size;i++){
                    FlameTile flameTile = new FlameTile(x+Tiles.WIDTH*i,y,FlameTile.LEFT,false);
                    flames.add(flameTile);
                }
                break;
            case RIGHT:
                FlameTile head_right = new FlameTile(x,y,FlameTile.RIGHT,true);
                flames.add(head_right);
                for(int i=1;i<size;i++){
                    FlameTile flameTile = new FlameTile(x-Tiles.WIDTH*i,y,FlameTile.RIGHT,false);
                    flames.add(flameTile);
                }
                break;
        }
    }
    public void checkCollisionVsPlayer(){
        Player player = game.getPlayer();
        for (FlameTile flameTile: flames){
                if (flameTile.isCollisionVsPlayer(player)) {
                    player.kill();
                }
        }
    }
    public void checkCollisionVsMonster(){
        for (FlameTile flameTile: flames){
            for (Monster monster: game.getMonsters())
            if (flameTile.isCollisionVsMonster(monster)) {
                monster.kill();
            }
        }
    }
    public void checkCollisionVsTile(){
        int i = size-1;
        while (i>=0){
            Tiles tile = game.getMap().getTileAt(flames.get(i).getX(),flames.get(i).getY());
            if (tile instanceof Grass) i--;
            else if (tile instanceof Wall){
                for (int j=i;j>=0;j--) flames.remove(j);
                i=-1;
            }
            else if (tile instanceof Brick){
                collision_brick = true;
                for (int j=i-1;j>=0;j--) flames.remove(j);
                i=-1;
            }
        }
    }
    public void checkCollisionVsBrick(){
        if (collision_brick) {
            game.getMap().changeBricktoGrass(flames.get(0).getX(), flames.get(0).getY());
        }
    }
    public void render(Graphics2D g2d){
        for (FlameTile flameTile: flames){
            flameTile.render(g2d);
        }
    }
}
