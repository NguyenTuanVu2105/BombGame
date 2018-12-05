package bomberman.utlis;

import bomberman.Game;
import bomberman.entities.character.Monster;
import bomberman.entities.character.Player;
import bomberman.map.Map;
import bomberman.states.GameState;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utlis {
    public static final String MAP = "/map.txt";
    public static final String MONSTER = "/monster.txt";
    public static final String PLAYER = "/player.txt";
    public static final String LEVEL = "/levels/Level";
    public static Map getMap(int level){
        return new Map(LEVEL + Integer.toString(level) + MAP);
    }
    public static ArrayList<Monster> getMonsters(int level, GameState game){
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(Utlis.class.getResourceAsStream(LEVEL + Integer.toString(level) + MONSTER)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" ", 2);
                Integer x = Integer.parseInt(temp[0]) * 32;
                Integer y = (Integer.parseInt(temp[1])-1) * 32;
                monsters.add(new Monster(game,x,y));
            }
        }catch (Exception e){
            System.exit(-1);
        }
        return monsters;
    }
    public static Player getPlayer(int level,GameState game){
        Player player = null;
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(Utlis.class.getResourceAsStream(LEVEL + Integer.toString(level) + PLAYER)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" ", 2);
                Integer x = Integer.parseInt(temp[0]) * 32;
                Integer y = (Integer.parseInt(temp[1])-1) * 32;
                player = new Player(game,x,y);
            }
        }catch (Exception e){
            System.exit(-1);
        }
        return player;
    }
}
