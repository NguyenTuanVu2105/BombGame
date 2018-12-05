package bomberman.states;

import java.awt.*;
import bomberman.Game;

public abstract class State {
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public abstract void update();

    public abstract void render(Graphics2D g2d);
}
