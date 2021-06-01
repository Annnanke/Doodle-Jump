package sample;

import Basics.Const;
import Main.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Menu extends Application {
public static Stage menuStage;
public static Scene menuScene;
public static Scene lvlChoosingScene;
public static Scene SettingScene;
public static String style;
    @FXML
    public RadioButton soundOn, soundOff, themeCoffee, themeMilk;

    @Override
    public void start(Stage primaryStage) throws Exception{
        menuStage = primaryStage;
        Parent rootD = FXMLLoader.load(getClass().getResource("main.fxml"));
        menuScene = new Scene(rootD);
        if(style == null){
            style = this.getClass().getResource("LightStyling.css").toExternalForm();
        }
        menuScene.getStylesheets().add(style);
        Image icon = new Image("Images/cat_jump.png");
        menuStage.setTitle("DoodleJump");
        menuStage.getIcons().add(icon);
        menuStage.setScene(menuScene);
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();
    }
    public void play(ActionEvent e) throws Exception {
        System.out.println("The girl is MAGIC");
        Parent rootC = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        lvlChoosingScene = new Scene(rootC);
        menuStage.setScene(lvlChoosingScene);
        lvlChoosingScene.getStylesheets().add(style);
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();

    }
    public void lvl1(ActionEvent e) throws Exception {
        Game game = new Game(1);
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();
    }
    public void settings(ActionEvent e) throws Exception {
        Parent rootO = FXMLLoader.load(getClass().getResource("settingsMenu.fxml"));
        SettingScene = new Scene(rootO);
        menuStage.setScene(SettingScene);
        SettingScene.getStylesheets().add(style);
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();
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
            style = this.getClass().getResource("LightStyling.css").toExternalForm();
        }
        if(themeCoffee.isSelected()){
            style = this.getClass().getResource("DarkStyling.css").toExternalForm();
        }
    }

    public void shop (ActionEvent e) throws Exception {
       /* System.out.println("The girl is MAGIC");
        Parent rootD = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        menuStage.setScene(new Scene(rootD,500,700));
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();*/
    }
    public void back (ActionEvent e) throws Exception {
        start(menuStage);
    }
    public void exit (ActionEvent e) throws Exception {
        menuStage.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
