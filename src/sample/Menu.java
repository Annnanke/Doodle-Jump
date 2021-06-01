package sample;

import Basics.Const;
import Main.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Menu extends Application {
public static Stage menuStage;
public static Scene menuScene;
public static String style;
    @Override
    public void start(Stage primaryStage) throws Exception{
        menuStage = primaryStage;
        Parent rootD = FXMLLoader.load(getClass().getResource("main.fxml"));
        menuScene = new Scene(rootD);
        style = this.getClass().getResource("DarkStyling.css").toExternalForm();
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
        Parent rootD = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        menuStage.setScene(new Scene(rootD,500,700));
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
        System.out.println("The girl is MAGIC");
        Settings.doSettings();
    }
    public void shop (ActionEvent e) throws Exception {
        System.out.println("The girl is MAGIC");
        Parent rootD = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        menuStage.setScene(new Scene(rootD,500,700));
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();
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
