package bomberman.entities.item;

import bomberman.gui.Assets;

public class ItemBoom extends Item {
    public ItemBoom(int x, int y) {
        super(x, y);
        img = Assets.item_boom;
    }
}
