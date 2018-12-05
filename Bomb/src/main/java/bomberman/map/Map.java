package bomberman.map;


import bomberman.entities.character.Character;
import bomberman.entities.character.Monster;
import bomberman.entities.character.Player;
import bomberman.entities.item.Item;
import bomberman.entities.tiles.Brick;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Tiles;
import bomberman.entities.tiles.Wall;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
    public ArrayList<Tiles> tiles;
    public ArrayList<Item> items;
    public Map(String path) {
        items = new ArrayList<Item>();
        tiles  = new ArrayList<Tiles>();
        getData(path);
    }
    public void getData(String path){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
            String[] temp = br.readLine().split(" ", 2);
            Integer nRows = Integer.parseInt(temp[0]);
            Integer nCols = Integer.parseInt(temp[1]);
            for (int i = 0; i < nRows; i++) {
                String str = br.readLine();
                for (int j = 0; j < nCols; j++) {
                    switch (str.charAt(j)) {
                        case '#':
                            Wall wall = new Wall(j * 32, i * 32);
                            tiles.add(wall);
                            break;
                        case ' ':
                            Grass grass = new Grass(j * 32, i * 32);
                            tiles.add(grass);
                            break;
                        case '*':
                            Brick brick = new Brick(j * 32, i * 32);
                            tiles.add(brick);
                            break;
                        default:
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeBricktoGrass(int x, int y){
        int j = x/32;
        int i = y/32;
        Tiles tile = tiles.get(31*i+j);
        if(!(tile instanceof Brick)) return;
        if (((Brick) tile).hasItem()) items.add(((Brick) tile).getItem());
        tiles.remove(31*i+j);
        tiles.add(31*i+j,new Grass(x,y));
    }
    public Tiles getTileAt(int x,int y){
        int j = x/32;
        int i = y/32;
        return tiles.get(31*i+j);
    }
    public void render(Graphics2D g2d){
        for (Tiles tiles: tiles){
            tiles.render(g2d);
        }
        for (Item item: items){
            item.render(g2d);
        }
    }
    public void update(){

    }
    }
