package Main;

import Basics.Const;
import Models.Doodler;
import Models.Platform;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;


public class Game extends Pane {

    private int lvl;
    private boolean moving_left, moving_right, isRunning;
    private Doodler player;
    private Scene scene = new Scene(new Pane());
    private ArrayList<Platform> platforms;
    private ImageView background;

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

        background = new ImageView(Const.BACKGROUND);
        add(background);

        platforms = new ArrayList<>();

        player = new Doodler((Const.STAGE_WIDTH - Const.DOODLER_WIDTH)/2,Const.LOWER_PLATFORM_OFFSET - Const.DOODLER_HEIGHT, this);

        generatePlatforms();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {update();}
        };

        timer.start();
    }

    private void update(){
        doodlersMovement();
        platformsMovement();
        checkForCollision();
        Platform.generateWhenPassed(lvl - 1);

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

    private void generatePlatforms(){
  /*
        double half_delta = (Const.LAYER_HEIGHT[lvl - 1] - Const.PLATFORM_HEIGHT)/2;
        for(double start = Const.STAGE_HEIGHT - Const.LAYER_HEIGHT[lvl - 1]; start >= 0; start -= Const.LAYER_HEIGHT[lvl - 1])
            add(new Platform(new Random().nextInt(Const.STAGE_WIDTH - Const.PLATFORM_WIDTH), start + half_delta, this));
*/
        double half_delta = (Const.LAYER_HEIGHT[lvl - 1] - Const.PLATFORM_HEIGHT)/2;
        int N = (int) (Const.STAGE_HEIGHT / Const.LAYER_HEIGHT[lvl - 1]);
        for (int i = N; i >= 0; i--) {
            add(new Platform(new Random().nextInt(Const.STAGE_WIDTH - Const.PLATFORM_WIDTH),
                    (i - 1) * Const.LAYER_HEIGHT[lvl - 1] + half_delta, this));


        }
    }

    public ArrayList<Platform> getPlatforms(){
        return platforms;
    }

    public void add(ImageView im){
        if(!getChildren().contains(im)) getChildren().add(im);
        if(im.getClass() == Platform.class) platforms.add((Platform) im);
    }

    public void remove(ImageView im){
        if(getChildren().contains(im)) getChildren().remove(im);
        if(im.getClass() == Platform.class) platforms.remove((Platform) im);
    }

    private void checkForCollision(){
        for (Platform p : platforms) {
            if (
                    player.getDetector().getBoundsInParent().intersects(p.getDetector().getBoundsInParent())
                            //TODO better intersection
           // player.getDetector().intersection(p.getDetector())
                            && player.getSpeed_y() < 0
                            && player.isMoving()
            ) {
            player.setSpeed_y(Const.DOODLER_V0_Y);
            if(p.getTranslateY() < Const.LOWER_PLATFORM_OFFSET) {
                p.setPivot(true);
                landing = p.getTranslateY() - Const.DOODLER_HEIGHT;
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
        if(hasPivot()) {
            player.setTranslateY(landing);
            player.setMoving(false);
        } else player.setMoving(true);
        for (Platform moving_platform : platforms) moving_platform.moveDown(lvl - 1);
    }

    public boolean hasPivot(){
        for(Platform p : platforms) if(p.isPivot()) return true;
        return false;
    }


    private double landing;
}
