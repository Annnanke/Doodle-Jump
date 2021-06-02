package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import Main.Game;
import java.net.URL;
import java.util.ResourceBundle;

public class LossPanelController implements Initializable {

    @FXML
    public Text scoreText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreText.setText(Game.getScorebar().getPoints() + "");
    }
}
