package bomberman.states;

import bomberman.Game;
import bomberman.entities.character.Monster;
import bomberman.entities.character.Player;
import bomberman.gui.Assets;
import bomberman.input.Key;
import bomberman.map.Map;
import bomberman.sound.GameSound;
import bomberman.utlis.Utlis;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State{
    private final int MAXLEVEL = 5;
    private Player player;
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private Map map;
    private Key key;
    private GameSound gameSoundAllGame;
    private GameSound gameSoundNormal;
    private int level;
    private int timeStart;
    private boolean sound;
    public GameState(Game game, boolean sound, int level){
        super(game);
        gameSoundAllGame = new GameSound(sound);
        gameSoundAllGame.loop(GameSound.PLAYGAME);
        gameSoundNormal = new GameSound(sound);
        key = new Key();
        game.getDisplay().getCanvas().addKeyListener(key);
        timeStart = 600;
        this.level = level;
        this.sound = sound;
        //init Game
        player = Utlis.getPlayer(level,this);
        monsters = Utlis.getMonsters(level,this);
        map = Utlis.getMap(level);
    }
    public boolean gameEnd(){
        if (player.dead()) {
            game.setStatus(game.LOSE);
            gameSoundNormal.play(GameSound.LOSE);
            return true;
        }
        else if (monsters.size() == 0) {
            if (level == MAXLEVEL) {
                game.setStatus(game.WIN);
                gameSoundNormal.play(GameSound.WIN);
                return true;
            }else
                game.changeState(new GameState(game,sound,level+1));
        }
        return false;
    }
    @Override
    public void update() {
        if (timeStart > 0) timeStart--;
        else {
            key.update();
            map.update();
            for (Monster monster : monsters)
                monster.update();
            player.update();
            removeMonster();
            if (gameEnd()) {
                game.running = false;
                gameSoundAllGame.stop();
            }
        }
    }
    private void removeMonster(){
        for (int i=0; i<monsters.size(); i++){
            if (monsters.get(i).dead()) monsters.remove(i);
        }
    }
    public Player getPlayer() {
        return player;
    }

    public GameSound getGameSound() {
        return gameSoundNormal;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Map getMap() {
        return map;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (timeStart > 0){
            renderStart(g2d);
        }
        else {
            map.render(g2d);
            for (Monster monster : monsters)
                monster.render(g2d);
            player.render(g2d);
        }
    }
    public void renderStart(Graphics2D g2d){
        g2d.setFont(new Font("Arial",Font.BOLD,100));
        g2d.setColor(Color.BLUE);
        g2d.drawString("ROUND " + Integer.toString(level),250,250);
    }
}
