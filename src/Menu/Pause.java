package Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pause  extends Application {

        @Override
        public void start(Stage pauseStage) throws Exception{
            Parent rootD = FXMLLoader.load(getClass().getResource("pause.fxml"));
            pauseStage.setScene(new Scene(rootD,400,220));
            pauseStage.setX(100);
            pauseStage.setY(50);
            pauseStage.show();


        }

        public static void main(String[] args) {
            launch(args);
        }
    }
