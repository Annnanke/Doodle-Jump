package GUI;

import Basics.Const;
import Basics.Icon;
import Menu.Menu;
import Menu.Sounds;
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
import Menu.Shop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VictoryPanelController implements Initializable {

    @FXML
    private Text scoreText;
    public Button restart, nextLvl, home;
    public ImageView sun;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreText.setText(Game.getScorebar().getPoints() + "");
        scoreText.setFill(Color.DARKSLATEGREY);
        nextLvl.setStyle("-fx-background-color: #95C2DC");
        restart.setStyle("-fx-background-color: #95C2DC");
        home.setStyle("-fx-background-color: #95C2DC");

        if(Game.getLvl() == 4) {
            sun.setVisible(false);
            scoreText.setFill(Color.DARKCYAN);
            nextLvl.setStyle("-fx-background-color: #95C2DC");
            restart.setStyle("-fx-background-color: #95C2DC");
            home.setStyle("-fx-background-color: #95C2DC");
        }
    }

    @FXML
    private void restart(ActionEvent e){
        if(!Game.isRunning()){
            Shop.diamands = 0;
            Sounds.playSoundButton();
            Game game = new Game(Menu.chosenLvl);
            Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
            game.setScene(scene);
            Menu.menuStage.setScene(scene);
            Menu.menuStage.show();
        }

    }

    @FXML
    private void nextLvl(ActionEvent e) throws IOException {
        if(!Game.isRunning()){
            if(Game.getLvl() != 5){
                Sounds.playSoundButton();
                switch (Game.getLvl()){
                    case 1 -> Menu.lvl2IsUnlocked = true;
                    case 2 -> Menu.lvl3IsUnlocked = true;
                    case 3 -> Menu.lvl4IsUnlocked = true;
                    case 4 -> Menu.lvl5IsUnlocked = true;
                }
                Menu.chosenLvl++;
                Game game = new Game(Menu.chosenLvl);
                Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
                game.setScene(scene);
                Menu.menuStage.setScene(scene);
                Menu.menuStage.show();
            } else {
                menu(e);
            }
        }
    }

    @FXML
    private void menu(ActionEvent e) throws IOException {
        if(!Game.isRunning()){
            switch (Game.getLvl()){
                case 1 -> Menu.lvl2IsUnlocked = true;
                case 2 -> Menu.lvl3IsUnlocked = true;
                case 3 -> Menu.lvl4IsUnlocked = true;
                case 4 -> Menu.lvl5IsUnlocked = true;
            }
            Menu.diamandsIntoCoins();
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
            Menu.menuStage.show();
        }
    }
}
