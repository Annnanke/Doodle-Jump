package Models;

import Basics.Const;
import Menu.Shop;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

import static Menu.Menu.chosenLvl;


public class ScoreBar extends Pane {

    private Rectangle background;
    private ImageView pause;
    private ImageView magic;
    private ImageView coinS;
    private ImageView diamondS;
    private Text score;
    private Text amountOfWands;
    private Text amountOfCoins;
    private Text amountOfDiamonds;
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

        magic = new ImageView(Const.MAGICWAND);
        magic.setTranslateX((Const.SCOREBAR_WIDTH - Const.MAGICWAND.getWidth() - (Const.SCOREBAR_HEIGHT - Const.MAGICWAND.getWidth())/2) - 80);
        magic.setTranslateY((Const.SCOREBAR_HEIGHT - Const.MAGICWAND.getHeight())/2);
        getChildren().add(magic);


        coinS = new ImageView(Const.COIN_SCORE);
        coinS.setTranslateX((Const.SCOREBAR_WIDTH - Const.PAUSE.getWidth() - (Const.SCOREBAR_HEIGHT - Const.PAUSE.getWidth())/2) - 160);
        coinS.setTranslateY((Const.SCOREBAR_HEIGHT - Const.PAUSE.getHeight())/2);
        getChildren().add(coinS);

        diamondS = new ImageView(Const.DIAMOND_SCORE);
        diamondS.setTranslateX((Const.SCOREBAR_WIDTH - Const.PAUSE.getWidth() - (Const.SCOREBAR_HEIGHT - Const.PAUSE.getWidth()) / 2) - 240);
        diamondS.setTranslateY((Const.SCOREBAR_HEIGHT - Const.PAUSE.getHeight()) / 2);
        getChildren().add(diamondS);


        score = new Text("SCORE : 0");
        score.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        score.setTranslateX(15/2);
        score.setTranslateY((Const.SCOREBAR_HEIGHT + 15)/2);
        getChildren().add(score);

        amountOfCoins = new Text("x"+ Shop.coins);
        amountOfCoins.setFont(Font.font("Times New Roman", FontWeight.BOLD, 11));
        amountOfCoins.setTranslateX((15/2)+325);
        amountOfCoins.setTranslateY((Const.SCOREBAR_HEIGHT + 15)/2);
        getChildren().add(amountOfCoins);

        amountOfDiamonds = new Text("x");//???????????????????????????????????????????????????????????????
        amountOfDiamonds.setFont(Font.font("Times New Roman", FontWeight.BOLD, 11));
        amountOfDiamonds.setTranslateX((15/2)+240);
        amountOfDiamonds.setTranslateY((Const.SCOREBAR_HEIGHT + 15)/2);
        getChildren().add(amountOfDiamonds);

        amountOfWands = new Text("x" + Shop.bagOfMagic);
        amountOfWands.setFont(Font.font("Times New Roman", FontWeight.BOLD, 11));
        amountOfWands.setTranslateX((15/2)+410);
        amountOfWands.setTranslateY((Const.SCOREBAR_HEIGHT + 15)/2);
        getChildren().add(amountOfWands);

    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
        score.setText("SCORE : " + this.points);
    }

    public void setPoints(int points) {
        this.points = points;
        score.setText("SCORE: " + this.points);
    }
}
