package Menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static Menu.Menu.soundStatus;
import static Menu.Menu.style;
import static Menu.Menu.shopStyle;

public class SettingsWindow extends Application {
    public static Stage aStage;
    public static Scene settingsScene;
    @FXML
    public static RadioButton soundOn, soundOff, themeCoffee, themeMilk;

    public static void stageSetting(Stage newStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsWindow.class.getResource("settings.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        settingsScene = new Scene(root1);
        settingsScene.getStylesheets().add(style);
        stage.setScene(settingsScene);
        Image iconSettings = new Image("Images/settings_icon.png");
        stage.getIcons().add(iconSettings);
        stage.setX(100);
        stage.setY(300);
        stage.show();


    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void doSettings() {
        try {
            stageSetting(aStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSoundAndTheme(ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if(soundOn.isSelected()){
            System.out.println("It's ON");
            soundStatus = 1;
        }
        if(soundOff.isSelected()){
            soundStatus = 2;
        }

        if(themeMilk.isSelected()){
            System.out.println("It's Milk");
            style = this.getClass().getResource("LightStyling.css").toExternalForm();
            shopStyle = this.getClass().getResource("shopLightStyling.css").toExternalForm();
        }
        if(themeCoffee.isSelected()){
            style = this.getClass().getResource("DarkStyling.css").toExternalForm();
            shopStyle = this.getClass().getResource("shopDarkStyling.css").toExternalForm();
        }
    }
}

