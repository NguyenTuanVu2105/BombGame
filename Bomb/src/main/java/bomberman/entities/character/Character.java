package bomberman.entities.character;

import bomberman.entities.Entity;

public abstract class Character extends Entity {
    public static final float DEFAULT_SPEED = 1.0f;
    protected float xMove,yMove;
    protected float speed;
    public Character(float x, float y) {
        super(x, y);
        xMove = 0;
        yMove = 0;
    }


    public void move(){
            x += xMove;
            y += yMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
