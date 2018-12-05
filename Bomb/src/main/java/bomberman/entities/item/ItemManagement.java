package bomberman.entities.item;

import java.util.ArrayList;
import java.util.Random;

public class ItemManagement {
    private int x;
    private int y;
    private ArrayList<Item> items;
    public ItemManagement(int x, int y) {
        this.x = x;
        this.y = y;
        items = new ArrayList<Item>();
        items.add(new ItemBoom(x,y));
        items.add(new ItemSpeedDecrease(x,y));
        items.add(new ItemSpeedIncrease(x,y));
        items.add(new ItemFlame(x,y));
    }
    public Item getRandomItem(){
        Random rd = new Random();
        int n = rd.nextInt(8);
        if (n>=4) return null;
        else return items.get(n);
    }
}
