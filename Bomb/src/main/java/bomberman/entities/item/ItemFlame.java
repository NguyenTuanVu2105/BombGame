package bomberman.entities.item;

import bomberman.gui.Assets;

public class ItemFlame extends Item{
    public ItemFlame(int x, int y) {
        super(x, y);
        img = Assets.item_flame;
    }
}
