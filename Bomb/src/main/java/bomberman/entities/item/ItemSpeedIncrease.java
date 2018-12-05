package bomberman.entities.item;

import bomberman.gui.Assets;

public class ItemSpeedIncrease extends Item{
    public ItemSpeedIncrease(int x, int y) {
        super(x, y);
        img = Assets.item_speed_increase;
    }
}
