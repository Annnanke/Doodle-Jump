package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelChooserController {
    ImageView levelView;
    Image lvl1Image = new Image(getClass().getResourceAsStream("Images/lvl1.png"));
    Image lvl2Image = new Image(getClass().getResourceAsStream("Images/lvl2.png"));
    Image lvl3Image = new Image(getClass().getResourceAsStream("Images/lvl3.png"));
    Image lvl4Image = new Image(getClass().getResourceAsStream("Images/lvl4.png"));
    Image lvl5Image = new Image(getClass().getResourceAsStream("Images/lvl5.png"));
    public void displayMImage(){
        levelView.setImage(lvl1Image);
        levelView.setImage(lvl2Image);
        levelView.setImage(lvl3Image);
        levelView.setImage(lvl4Image);
        levelView.setImage(lvl5Image);
    }
}