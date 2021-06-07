package Menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Pause  extends Application {

        @Override
        public void start(Stage pauseStage) throws Exception {
            Parent rootD = FXMLLoader.load(getClass().getResource("pause.fxml"));
            pauseStage.setScene(new Scene(rootD, 400, 220));
            pauseStage.setX(100);
            pauseStage.setY(50);
            pauseStage.show();
        }

            @FXML
            private void unpause(){
                Menu.game.getTimer().start();
                Menu.game.getPauseStage().close();
            }

            @FXML
            private void menu(){
                Parent rootD = null;
                try {
                    rootD = FXMLLoader.load(getClass().getResource("../Menu/main.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                Menu.game.getPauseStage().close();
            }


            public static void main(String[] args) {
            launch(args);
        }
    }
