package Menu;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import static Menu.Menu.soundStatus;

public class Sounds {
    public static  MediaPlayer audioPlayer;
    public static  MediaPlayer audioPlayer1;
    public static  MediaPlayer audioPlayer2;
    public static  MediaPlayer audioPlayer3;
    public static  MediaPlayer audioPlayer4;
    public static void playSoundButton() {
        File audioFile = new File("src\\buttonSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer.play();
        }
    }
    public static void playSoundVictory() {
        File audioFile = new File("src\\victorySound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer1 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer1.play();
        }
    }
    public static void playSoundGameOver() {
        File audioFile = new File("src\\gameOverSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer2 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer2.play();
        }
    }

    public static void playSoundSuperJump() {
        File audioFile = new File("src\\superJumpSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer3 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer3.play();
        }
    }

    public static void playSoundCoin() {
        File audioFile = new File("src\\coinSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer3 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer3.play();
        }

    }
    public static void playSoundJump() {
        File audioFile = new File("src\\jumpSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }


    public static void playSoundDiamondSound() {
        File audioFile = new File("src\\diamondSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }

    public static void playSoundGunSound() {
        File audioFile = new File("src\\gunSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }


    public static void playSoundJetSound() {
        File audioFile = new File("src/jetSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }

}
