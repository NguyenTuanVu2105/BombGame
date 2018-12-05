package bomberman.entities.tiles;

import bomberman.gui.Assets;

public class Wall extends Tiles {
    public Wall(int x,int y) {
        super(x,y);
        img = Assets.wall;
    }
}
