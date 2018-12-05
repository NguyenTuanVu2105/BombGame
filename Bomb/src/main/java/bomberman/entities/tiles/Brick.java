package bomberman.entities.tiles;

import bomberman.entities.item.Item;
import bomberman.entities.item.ItemManagement;
import bomberman.gui.Assets;

import java.awt.*;

public class Brick extends Tiles{
    private Item item ;
    public Brick(int x,int y) {
        super(x,y);
        img = Assets.brick;
        ItemManagement itemManagement = new ItemManagement(x,y);
        item = itemManagement.getRandomItem();
    }
    public boolean hasItem(){
        return item!=null;
    }

    public Item getItem() {
        return item;
    }
}
