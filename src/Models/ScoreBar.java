package Models;

import Basics.Const;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class ScoreBar extends Pane {

    private Rectangle background;
    private ImageView pause;
    private Text score;
    private int points;

    public ScoreBar(){
        super();
        setWidth(Const.SCOREBAR_WIDTH);
        setHeight(Const.SCOREBAR_HEIGHT);

        background = new Rectangle(getWidth(),getHeight());
        background.setFill(Color.AZURE);
        background.setOpacity(0.7);
        getChildren().add(background);

        points = 0;

        pause = new ImageView(Const.PAUSE);
        pause.setTranslateX(Const.SCOREBAR_WIDTH - Const.PAUSE.getWidth() - (Const.SCOREBAR_HEIGHT - Const.PAUSE.getWidth())/2);
        pause.setTranslateY((Const.SCOREBAR_HEIGHT - Const.PAUSE.getHeight())/2);
        getChildren().add(pause);

        score = new Text("SCORE : 0");
        score.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        score.setTranslateX(15/2);
        score.setTranslateY((Const.SCOREBAR_HEIGHT + 15)/2);
        getChildren().add(score);

    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
        score.setText("SCORE : " + this.points);
    }

}
