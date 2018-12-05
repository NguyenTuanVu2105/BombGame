package bomberman.entities.item;

import bomberman.gui.Assets;

public class ItemSpeedDecrease extends Item{
    public ItemSpeedDecrease(int x, int y) {
        super(x, y);
        img = Assets.item_speed_decrease;
    }
}
