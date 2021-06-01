package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void lvl1(ActionEvent e) throws Exception {
        /*//Parent rootD = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        Menu.menuStage.setScene(Main.Main.scene);
        Menu.menuStage.setX(100);
        Menu.menuStage.setY(50);
        Menu.menuStage.show();*/

    }
}