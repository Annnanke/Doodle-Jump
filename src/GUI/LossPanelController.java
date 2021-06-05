package GUI;

import Basics.Const;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import Main.Game;
import java.net.URL;
import java.util.ResourceBundle;
import Menu.Menu;
import Menu.Sounds;

public class LossPanelController implements Initializable {

    @FXML
    public Text scoreText;


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
    private void menu(ActionEvent e){

    }
}
