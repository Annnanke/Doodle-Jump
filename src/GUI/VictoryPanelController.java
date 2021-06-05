package GUI;

import Basics.Const;
import Menu.Menu;
import Menu.Sounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import Main.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VictoryPanelController implements Initializable {

    @FXML
    private Text scoreText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreText.setText(Game.getScorebar().getPoints() + "");
    }

    @FXML
    private void restart(ActionEvent e){
        if(!Game.isRunning()){
            Sounds.playSoundButton();
            Game game = new Game(Menu.chosenLvl);
            Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
            game.setScene(scene);
            Menu.menuStage.setScene(scene);
            Menu.menuStage.show();
        }

    }

    @FXML
    private void nextLvl(ActionEvent e){
        if(!Game.isRunning()){
            Sounds.playSoundButton();
            Menu.chosenLvl++;
            Game game = new Game(Menu.chosenLvl);
            Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
            game.setScene(scene);
            Menu.menuStage.setScene(scene);
            Menu.menuStage.show();
        }
    }

    @FXML
    private void menu(ActionEvent e) throws IOException {
        if(!Game.isRunning()){
            Parent rootD = FXMLLoader.load(getClass().getResource("../Menu/main.fxml"));
            Menu.menuScene = new Scene(rootD);

            if(Menu.style == null){
                Menu.style = this.getClass().getResource("LightStyling.css").toExternalForm();
                Menu.shopStyle = this.getClass().getResource("shopLightStyling.css").toExternalForm();
            }
            Menu.menuScene.getStylesheets().add(Menu.style);
            Image icon = new Image("Images/cat_jump.png");
            Menu.menuStage.setTitle("DoodleJump");
            Menu.menuStage.getIcons().add(icon);
            Menu.menuStage.setScene(Menu.menuScene);
            Menu.menuStage.setX(100);
            Menu.menuStage.setY(50);
            Menu.menuStage.show();
        }
    }
}
