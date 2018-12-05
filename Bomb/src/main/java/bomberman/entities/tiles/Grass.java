package bomberman.entities.tiles;

import bomberman.gui.Assets;

public class Grass extends Tiles{
    public Grass(int x,int y) {
        super(x,y);
        img = Assets.grass;
    }
}
