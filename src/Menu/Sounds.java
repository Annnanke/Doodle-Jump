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

    /**
     * Plays sound "buttonSound.wav"
     */
    public static void playSoundButton() {
        File audioFile = new File("src\\buttonSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer.play();
        }
    }

    /**
     * Plays sound "victorySound.wav"
     */
    public static void playSoundVictory() {
        File audioFile = new File("src\\victorySound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer1 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer1.play();
        }
    }

    /**
     * Plays sound "gameOverSound.wav"
     */
    public static void playSoundGameOver() {
        File audioFile = new File("src\\gameOverSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer2 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer2.play();
        }
    }

    /**
     * Plays sound "superJumpSound.wav"
     */
    public static void playSoundSuperJump() {
        File audioFile = new File("src\\superJumpSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer3 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer3.play();
        }
    }

    /**
     * Plays sound "coinSound.wav"
     */
    public static void playSoundCoin() {
        File audioFile = new File("src\\coinSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer3 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer3.play();
        }

    }
    /**
     * Plays sound "jumpSound.wav"
     */
    public static void playSoundJump() {
        File audioFile = new File("src\\jumpSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }


    /**
     * Plays sound "diamondSound.wav"
     */
    public static void playSoundDiamondSound() {
        File audioFile = new File("src\\diamondSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }

    /**
     * Plays sound "gunSound.wav"
     */
    public static void playSoundGunSound() {
        File audioFile = new File("src\\gunSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }


    /**
     * Plays sound "jetSound.wav"
     */
    public static void playSoundJetSound() {
        File audioFile = new File("src/jetSound.wav");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayer4 = new MediaPlayer(audio);
        if (soundStatus != 2) {
            audioPlayer4.play();
        }
    }

}
