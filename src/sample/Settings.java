package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Settings extends Application {
    public static Stage aStage;
    @FXML
    private RadioButton soundOn, soundOff, themeCoffee, themeMilk;

    public static void stageSetting(Stage newStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Settings.class.getResource("settings.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
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
        if(soundOn.isSelected()){
            System.out.println("It's ON");
        }
        if(soundOff.isSelected()){
            System.out.println("It's OFF");
        }
        if(themeMilk.isSelected()){
            System.out.println("It's Milk");
            Menu.style = this.getClass().getResource("LightStyling.css").toExternalForm();
        }
        if(themeCoffee.isSelected()){
            Menu.style = this.getClass().getResource("DarkStyling.css").toExternalForm();
        }
    }
}

