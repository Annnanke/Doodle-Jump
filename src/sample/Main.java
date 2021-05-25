package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
public static Stage menuStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        menuStage = primaryStage;
       Parent rootD = FXMLLoader.load(getClass().getResource("main.fxml"));
       Image icon = new Image("Images/cat_jump.png");
        menuStage.setTitle("DoodleJump");
        menuStage.getIcons().add(icon);
        menuStage.setScene(new Scene(rootD,500,700));
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();


    }
    public void play(ActionEvent e) throws Exception {
        Parent rootD = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        menuStage.setScene(new Scene(rootD,500,700));
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
