package Main;

import Basics.Const;
import Basics.Generator;
import Models.Layer;
import Models.Doodler;
import Models.Platform;
import Models.ScoreBar;
import javafx.animation.AnimationTimer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;


import java.io.IOException;


public class Game extends Pane {

    private static int lvl;
    private boolean moving_left, moving_right, isRunning;
    private Doodler player;
    private Scene scene = new Scene(new Pane());
    public AnimationTimer timer;
    private ImageView background;
    private static ScoreBar scorebar;
    private Pane lossPanel;
    private boolean won = false;


    public Game(int lvl){
        super();
        this.lvl = lvl;
        init();
    }

    private void init(){
        setWidth(Const.STAGE_WIDTH);
        setHeight(Const.STAGE_HEIGHT);

        isRunning = true;
        moving_left = false;
        moving_right = false;

        background = new ImageView(Const.BACKGROUND[getLvl() - 1]);
        add(background);

        try {
            lossPanel = new FXMLLoader().load(getClass().getResource("../GUI/LossPanel.fxml"));
            lossPanel.setTranslateY(Const.HEIGHT_OF_LOSS_FALL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Line level = new Line(0, Const.LOWER_PLATFORM_OFFSET, Const.STAGE_WIDTH, Const.LOWER_PLATFORM_OFFSET);
        level.setOpacity(Const.DETECTOR_OPACITY);
        getChildren().add(level);

        Layer.setRoot(this);

        player = new Doodler((Const.STAGE_WIDTH - Const.DOODLER_WIDTH)/2,Const.LOWER_PLATFORM_OFFSET - Const.DOODLER_HEIGHT, this);

        Generator.generatePlatforms();

        scorebar = new ScoreBar();
        getChildren().add(scorebar);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {update();}
        };

        timer.start();


    }

    private void update(){
        checkForVictory();
        if(checkForLoss()) return;
        doodlersMovement();
        platformsMovement();
        checkForCollision();
        Layer.generateWhenPassed();
        removeDead();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case LEFT:
                    if(!won) moving_left = true;
                    break;
                case RIGHT:
                    if(!won) moving_right = true;
                    break;

            }
        });

        this.scene.setOnKeyReleased(e -> {

            switch (e.getCode()){
                case LEFT:
                    moving_left = false;
                    break;
                case RIGHT:
                    moving_right = false;
                    break;
            }
        });
    }

    private void removeDead(){
        Platform.removePostCracked();
    }

    public void add(ImageView im){
        if(!getChildren().contains(im)) getChildren().add(im);
    }

    public void checkForVictory(){

    }


    private boolean loss = false;


    @FXML
    public Label scoreLabel;

    public boolean checkForLoss(){
        if(player.getTranslateY() + Const.DOODLER_HEIGHT > Const.STAGE_HEIGHT || loss){
            loss = true;
            if(getChildren().contains(scorebar)) getChildren().remove(scorebar);
            if(!getChildren().contains(lossPanel))getChildren().add(lossPanel);

            if(lossPanel.getTranslateY() + lossPanel.getHeight() + player.getSpeed_y() > Const.STAGE_HEIGHT) {
                for (Layer l : Layer.all) l.getPlatform().setTranslateY(l.getPlatformY() + player.getSpeed_y());
                lossPanel.setTranslateY(lossPanel.getTranslateY() + player.getSpeed_y());
            } else {
                lossPanel.setTranslateY(Const.STAGE_HEIGHT - lossPanel.getHeight());
                loss = false;
                timer.stop();



                //scoreLabel.textProperty().bind(Bindings.convert(scorebar.getPoints().valueProperty()));
            }
            return true;
        }
        return false;
    }

    private void checkForCollision(){
        for (Layer p : Layer.all) {
            if (
                    player.getDetector().getBoundsInParent().intersects(p.getDetector().getBoundsInParent())
                    && player.getSpeed_y() < 0
                    && player.isMoving()
                    && p.isDetectable()
            ) {
                switch (p.getPlatform().getType()){

                    default:
                        player.setSpeed_y(Const.DOODLER_V0_Y);
                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        }
                        break;
                    case Platform.TRAMPOLINE :
                        if(p.getAdditionalDetector().getBoundsInParent().intersects(player.getDetector().getBoundsInParent())) {
                            p.setMissedTrampoline(false);
                            player.setSpeed_y(Const.TRAMPOLINE_V_0);
                            if(p.getPlatformY() > Const.LOWER_PLATFORM_OFFSET) {
                                landingSpeed = Math.sqrt(-2 * Const.GRAVITY * (p.getPlatformY() - Const.LOWER_PLATFORM_OFFSET));
                            }
                            else landingSpeed = 0;
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        } else {
                            landingSpeed = 0;
                            p.setMissedTrampoline(true);
                            player.setSpeed_y(Const.DOODLER_V0_Y);
                            if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                                p.setPivot(true);
                                landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                            }
                        }


                        break;
                    case Platform.GOLDEN:
                        won = true;
                        player.setSpeed_y(Const.DOODLER_V0_Y);
                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        }

                        break;
                    case Platform.CRACKED:
                        p.getPlatform().setImage(Const.PLATFORM_1_POST_BROKEN[Game.getLvl() - 1]);
                        break;
                }

            }
        }
    }

    private void doodlersMovement(){
        if(moving_left) player.moveLeft();
        if(moving_right) player.moveRight();
        player.verticalMovement();
    }

    private void landingMovement(){
        landing -= landingSpeed;
        if(landingSpeed + Const.GRAVITY > 0) landingSpeed += Const.GRAVITY;
    }

    private void platformsMovement(){
        //victory movement
        if(won)
            for(Layer l : Layer.all)
                if(l.getType() == Platform.GOLDEN && l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM >= 0) {
                    l.getPlatform().setTranslateX(l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM);
                    player.setTranslateX(player.getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM);
                }

        //horizontal movement
        Platform.moveAllMovingHorizontally();

        //vertical movement
        if(Layer.hasPivot()) {
            if((Layer.getPivot().getPivotType() == Layer.PIVOT_TRAMPOLINE) ) landingMovement();
            player.setTranslateY(landing);
            player.setMoving(false);
        } else player.setMoving(true);
        Layer.move();
    }

    public static ScoreBar getScorebar(){
        return scorebar;
    }

    public static int getLvl() {
        return lvl;
    }

    public Doodler getPlayer() {
        return player;
    }

    private double landing, landingSpeed = 0;
}