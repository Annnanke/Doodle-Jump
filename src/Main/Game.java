package Main;

import Basics.Const;
import Basics.Generator;
import GUI.LossPanelController;
import Models.Layer;
import Models.Doodler;
import Models.Platform;
import Models.ScoreBar;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.IOException;


public class Game extends Pane {

    private static int lvl;
    private boolean moving_left, moving_right, isRunning;
    private Doodler player;
    private Scene scene = new Scene(new Pane());
    public AnimationTimer timer;
    private ImageView background;
    private static ScoreBar scorebar;
    private Pane lossPanel, victoryPanel;
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
        //if(checkForVictory()) return;
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
//
//    public boolean checkForVictory(){
//        if(!won) return false;
//
//
//    }


    private boolean loss = false;


    public boolean checkForLoss(){
        if(player.getTranslateY() + Const.DOODLER_HEIGHT > Const.STAGE_HEIGHT || loss){
            loss = true;
            if(getChildren().contains(scorebar)) getChildren().remove(scorebar);
            if(!getChildren().contains(lossPanel)){
                try {
                    lossPanel = new FXMLLoader().load(getClass().getResource("../GUI/LossPanel.fxml"));
                    lossPanel.setTranslateY(Const.HEIGHT_OF_LOSS_FALL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getChildren().add(lossPanel);
            }

            if(lossPanel.getTranslateY() + lossPanel.getHeight() + player.getSpeed_y() > Const.STAGE_HEIGHT) {
                for (Layer l : Layer.all) l.getPlatform().setTranslateY(l.getPlatformY() + player.getSpeed_y());
                lossPanel.setTranslateY(lossPanel.getTranslateY() + player.getSpeed_y());
            } else {
                lossPanel.setTranslateY(Const.STAGE_HEIGHT - lossPanel.getHeight());
                loss = false;
                timer.stop();

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
                if(p.getPlatform().isStable()) player.setJumpImage(p.getPlatform().getType());
                switch (p.getPlatform().getType()){

                    default :
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

                        //VICTORY:
                    case Platform.GOLDEN :
                        won = true;
                        if(getScorebar().getPoints() < Const.HEIGHT_1) getScorebar().setPoints(Const.HEIGHT_1);
                        getChildren().remove(scorebar);
                        player.setScaleX(1);
                        player.setSpeed_y(Const.DOODLER_V0_Y);
                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        }
                        break;

                    case Platform.CRACKED :
                        p.getPlatform().setImage(Const.PLATFORM_1_POST_BROKEN[Game.getLvl() - 1]);
                        break;

                        case Platform.JETPACKED :
                            player.setSpeed_y(Const.JETPACK_V_0);
                            if(p.getPlatformY() > Const.LOWER_PLATFORM_OFFSET) {
                                landingSpeed = Math.sqrt(-2 * Const.GRAVITY * (p.getPlatformY() - Const.LOWER_PLATFORM_OFFSET));
                            }
                            else landingSpeed = 0;
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
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
                if(l.getType() == Platform.GOLDEN && l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM >= Const.VICTORY_POSITION_X) {
                    l.getPlatform().setTranslateX(l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM);
                    player.setTranslateX(player.getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM);
                    if(Math.abs(l.getPlatform().getTranslateX() + Const.PLATFORM_WIDTH/2 - Const.DOODLER_WIDTH/2 - player.getTranslateX()) >= Const.VICTORY_SHIFT_SPEED)
                        player.setTranslateX(player.getTranslateX() + Math.signum(l.getPlatform().getTranslateX() + Const.PLATFORM_WIDTH/2 - Const.DOODLER_WIDTH/2 - player.getTranslateX())*Const.VICTORY_SHIFT_SPEED);
                } else if(l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM < Const.VICTORY_POSITION_X) {
                    if(victoryPanel == null){
                        try {
                            victoryPanel = FXMLLoader.load(getClass().getResource("../GUI/VictoryPanel.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(!getChildren().contains(victoryPanel)){
                        victoryPanel.setTranslateY(Layer.getGoldenPlatform().getTranslateY() - 1.5*Const.STAGE_HEIGHT);
                        getChildren().add(victoryPanel);
                    }



                    if(victoryPanel.getTranslateY() < 0) {
                        for (Layer p : Layer.all)
                            if (p.getType() != Platform.GOLDEN) p.setY(p.getY() + Const.VICTORY_DOWN_SPEED);
                        victoryPanel.setTranslateY(victoryPanel.getTranslateY() + Const.VICTORY_DOWN_SPEED);
                    }
                }

        //horizontal movement
        Platform.moveAllMovingHorizontally();

        //vertical movement
        if(Layer.hasPivot()) {
            if((Layer.getPivot().getPivotType() == Layer.PIVOT_TRAMPOLINE
                || Layer.getPivot().getPivotType() == Layer.PIVOT_JETPACK) ) landingMovement();
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


    private double landing, landingSpeed = 0;
}