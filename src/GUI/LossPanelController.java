package GUI;

import Basics.Const;
import Basics.Icon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import Main.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Menu.Menu;
import Menu.Sounds;
import Menu.Shop;

public class LossPanelController implements Initializable {

    @FXML
    public Text scoreText;
    public Button restart, home;

    @FXML
    ImageView cloud;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreText.setText(Game.getScorebar().getPoints() + "");
        scoreText.setFill(Color.DARKSLATEGREY);
        restart.setStyle("-fx-background-color: #95C2DC");
        home.setStyle("-fx-background-color: #95C2DC");

        if(Game.getLvl() == 4) {
            cloud.setImage(new Icon("src/Images/loss4_cloud.png"));
            scoreText.setFill(Color.DARKCYAN);
            restart.setStyle("-fx-background-color: #4A5F6C");
            home.setStyle("-fx-background-color: #4A5F6C");
        }
    }

    @FXML
    private void restart(ActionEvent e){
        if(!Game.isRunning()){
            Sounds.playSoundButton();
            Shop.diamands = 0;
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
            Shop.diamands = 0;
            if(Menu.style == null){
                Menu.style = this.getClass().getResource("LightStyling.css").toExternalForm();
                Menu.shopStyle = this.getClass().getResource("shopLightStyling.css").toExternalForm();
            }
            Menu.menuScene.getStylesheets().add(Menu.style);
            Image icon = new Image("Images/cat_jump.png");
            Menu.menuStage.setTitle("DoodleJump");
            Menu.menuStage.getIcons().add(icon);
            Menu.menuStage.setScene(Menu.menuScene);
            Menu.menuStage.show();
        }
    }


}
