package Main;

import Basics.Const;
import Basics.Generator;
import Basics.Layer;
import Models.Doodler;
import Models.Platform;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;


public class Game extends Pane {

    private static int lvl;
    private boolean moving_left, moving_right, isRunning;
    private Doodler player;
    private Scene scene = new Scene(new Pane());
    public AnimationTimer timer;
    private ImageView background;

    public Game(int lvl){
        super();
        this.lvl = lvl;
        init();
    }

    public Game(){lvl = 1;}

    private void init(){
        setWidth(Const.STAGE_WIDTH);
        setHeight(Const.STAGE_HEIGHT);

        isRunning = true;
        moving_left = false;
        moving_right = false;

        background = new ImageView(Const.BACKGROUND);
        add(background);

        Layer.setRoot(this);

        player = new Doodler((Const.STAGE_WIDTH - Const.DOODLER_WIDTH)/2,Const.LOWER_PLATFORM_OFFSET - Const.DOODLER_HEIGHT, this);

        Generator.generatePlatforms();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {update();}
        };

        timer.start();
    }

    private void update(){
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
                    moving_left = true;
                    break;
                case RIGHT:
                    moving_right = true;
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


    private void checkForCollision(){
        for (Layer p : Layer.all) {
            if (
                    player.getDetector().getBoundsInParent().intersects(p.getDetector().getBoundsInParent())
                            && player.getSpeed_y() < 0
                            && player.isMoving()
            ) {
                switch (p.getPlatform().getType()){
//                    case Platform.DEFAULT:
//                        player.setSpeed_y(Const.DOODLER_V0_Y);
//                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
//                            p.setPivot(true);
//                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
//                        }
//                        break;
//                    case Platform.MOVING:
//                        player.setSpeed_y(Const.DOODLER_V0_Y);
//                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
//                            p.setPivot(true);
//                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
//                        }
//                        break;
//                    case Platform.TRAMPOLINE:
//                        //TODO change for trampoline jump
//                        trampolineJump(p);
//                        break;
                    default:
                        player.setSpeed_y(Const.DOODLER_V0_Y);
                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        }
                        break;
                    case Platform.TRAMPOLINE :
                        if(p.getAdditionalDetector().getBoundsInParent().intersects(player.getDetector().getBoundsInParent())) {
                            System.out.println("Intersects");
                            p.setMissedTrampoline(false);
                            player.setSpeed_y(Const.TRAMPOLINE_V_0);
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        } else {
                            System.out.println("No intersection");
                            p.setMissedTrampoline(true);
                            player.setSpeed_y(Const.DOODLER_V0_Y);
                            if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                                p.setPivot(true);
                                landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                            }
                        }

                        break;
                    case Platform.CRACKED:
                        p.getPlatform().setImage(Const.PLATFORM_1_POST_BROKEN);
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

    private void platformsMovement(){
        //horizontal movement
        Platform.moveAllMovingHorizontally();

        //vertical movement
        if(Layer.hasPivot()) {
            player.setTranslateY(landing);
            player.setMoving(false);
        } else player.setMoving(true);
        Layer.move();
    }

    public static int getLvl() {
        return lvl;
    }

    public Doodler getPlayer() {
        return player;
    }

    private double landing;
}