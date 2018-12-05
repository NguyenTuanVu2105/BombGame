package bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class GameSound {
    public static GameSound instance = null;
    public static final int LOOP = 1;
    public static final int PLAY = 0;
    private static int status;
    public static final String MENU = "/sound/menu.wav";
    public static final String PLAYGAME = "/sound/playgame.mid";
    public static final String BOMB = "/sound/newbomb.wav";
    public static final String BOMBER_DIE = "/sound/bomber_die.wav";
    public static final String MONSTER_DIE = "/sound/monster_die.wav";
    public static final String BONG_BANG = "/sound/bomb_bang.wav";
    public static final String ITEM = "/sound/item.wav";
    public static final String WIN = "/sound/win.wav";
    public static final String LOSE = "/sound/lose.mid";
    private Clip clip;
    public boolean sound;
    public GameSound(boolean sound) {
        this.sound = sound;
    }
    public void play(String name) {
        if (sound){
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(name));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                status = PLAY;
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }
    public void loop(String name) {
        if(sound) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(name));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                status = LOOP;
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }
    public void stop(){
        if(sound) {
            clip.stop();
            clip.close();
        }
    }
}

