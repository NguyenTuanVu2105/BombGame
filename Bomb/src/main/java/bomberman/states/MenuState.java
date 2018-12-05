package bomberman.states;

import bomberman.Game;
import bomberman.gui.Assets;
import bomberman.input.Mouse;
import bomberman.sound.GameSound;

import java.awt.*;

public class MenuState extends State {
    private Rectangle playButton;
    private Rectangle exitButton;
    private Rectangle soundButton;
    private boolean soundPlayed;
    private Mouse mouse;
    private GameSound gameSound;
    public MenuState(Game game) {
        super(game);
        gameSound = new GameSound(true);
        gameSound.loop(GameSound.MENU);
        soundPlayed = true;
        mouse = new Mouse(this);
        playButton = new Rectangle(380,150,200,70);
        soundButton = new Rectangle(380,230,200,70);
        exitButton = new Rectangle(380,310,200,70);
        game.getDisplay().getCanvas().addMouseListener(mouse);
        game.getDisplay().getCanvas().addMouseMotionListener(mouse);
    }
    public Rectangle getPlayButton() {
        return playButton;
    }

    public Rectangle getExitButton() {
        return exitButton;
    }

    public Rectangle getSoundButton() {
        return soundButton;
    }

    @Override
    public void update() {
        if (mouse.isPlay()){
            game.changeState(new GameState(game,mouse.isSound(),1));
            gameSound.stop();
        }

        if (mouse.isSound()) {
            if (!soundPlayed) {
                gameSound.loop(GameSound.MENU);
                soundPlayed = true;
            }
        }
        else{
            gameSound.stop();
            soundPlayed = false;
        }

        if (mouse.isExit()){
            gameSound.stop();
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        Font font= new Font("arial",Font.BOLD,100);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(Assets.background,0,0,null);
        //PlayHover
        if (mouse.isPlayHover()){
            g2d.drawImage(Assets.playButton1,playButton.x,playButton.y,playButton.width,playButton.height,null);
        }else
            g2d.drawImage(Assets.playButton,playButton.x,playButton.y,playButton.width,playButton.height,null);
        //SoundHover
        if (mouse.isSound())
            if (mouse.isSoundHover())
                g2d.drawImage(Assets.soundOnButton1,soundButton.x,soundButton.y,soundButton.width,soundButton.height,null);
            else
                g2d.drawImage(Assets.soundOnButton,soundButton.x,soundButton.y,soundButton.width,soundButton.height,null);
         else
            if (mouse.isSoundHover())
                g2d.drawImage(Assets.soundOffButton1,soundButton.x,soundButton.y,soundButton.width,soundButton.height,null);
            else
                g2d.drawImage(Assets.soundOffButton,soundButton.x,soundButton.y,soundButton.width,soundButton.height,null);
        //ExitHover
        if (mouse.isExitHover())
            g2d.drawImage(Assets.exitButton1,exitButton.x,exitButton.y,exitButton.width,exitButton.height,null);
        else
            g2d.drawImage(Assets.exitButton,exitButton.x,exitButton.y,exitButton.width,exitButton.height,null);
    }
}
