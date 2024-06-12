package NextGenGaming.Game;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class GameSounds {

    private static void playSound(String soundPath) {
        try {
            File file = new File(soundPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void hitBirdSound()
    {
        playSound("sounds/hit.wav");
    }
    public static void crashSnakeSound()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\crash.wav");
    }
    public static void eatSnakeSound()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\eat.wav");
    }
    public static void hisSnakeSound()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\his.wav");
    }
    public static void moveSnakeSound()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\whoosh.wav");
    }
    public static void jumpBirdSound()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\jump.wav");
    }
    public static void sweepWin()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\win.wav");
    }
    public static void sweepLost()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\gameOver.wav");
    }
    public static void sweepRight()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\right.wav");
    }
    public static void sweepLeft()
    {
        playSound("C:\\Users\\Abdullah Khan\\Desktop\\asjad\\asjad\\src\\main\\java\\asjad\\sounds\\left.wav");
    }
}
