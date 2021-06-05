package Models;

import Basics.Const;
import Main.Game;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class Doodler extends ImageView {

    private double speed_x, speed_y;
    private Detector detector;
    private Rectangle generalDetector;
    private boolean moving = true;
    private Game root;

    public Doodler(double x, double y, Game root) {
        super(Const.CHARACTER_NORMAL[Const.CHOSEN_CHARACTER]);
        setTranslateX(x);
        setTranslateY(y);
        this.root = root;
        detector = new Detector((int)(x + getImage().getWidth()/3), (int)(y + Const.DOODLER_HEIGHT - 7),
                (int)(getImage().getWidth() * 0.5), this);
        root.getChildren().add(detector);

        generalDetector = new Rectangle(x + 20,y,getImage().getWidth() - 30, getImage().getHeight());
        generalDetector.setOpacity(Const.DETECTOR_OPACITY);
        root.getChildren().add(generalDetector);
        speed_x = Const.DOODLER_V0_X;
        speed_y = Const.DOODLER_V0_Y;
        root.getChildren().add(this);
    }


    public void shoot(){
        setImage(Const.CHARACTER_SHOOT[Const.CHOSEN_CHARACTER]);
        if(getScaleX() == 1)
            switch (Const.CHOSEN_CHARACTER){
                case 0 -> new Bullet(getTranslateX() + getImage().getWidth() - 30, getTranslateY(), root);
                case 1 -> new Bullet(getTranslateX() + getImage().getWidth() - 22, getTranslateY(), root);
                case 2 -> new Bullet(getTranslateX() + getImage().getWidth() - 20, getTranslateY(), root);
            }
        if(getScaleX() == -1){
            switch (Const.CHOSEN_CHARACTER){
                case 0 -> new Bullet(getTranslateX() + 5, getTranslateY(), root);
                case 1 -> new Bullet(getTranslateX() + 4, getTranslateY(), root);
                case 2 -> new Bullet(getTranslateX() + 20, getTranslateY(), root);
            }
        }

    }


    public void moveLeft(){
        setScaleX(-1);
        if(getTranslateX() - speed_x >= -Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_RIGHT*Const.DOODLER_WIDTH) {
            setTranslateX(getTranslateX() - speed_x);
            detector.setX(getTranslateX() + getImage().getWidth() - getImage().getWidth()/3 - detector.getWidth());
            detector.setWidth(detector.getWidth());
            generalDetector.setX(getTranslateX() + 20);
        } else {
            setTranslateX(Const.STAGE_WIDTH - Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_LEFT*Const.DOODLER_WIDTH);
        }
    }

    public void setJumpImage(int type){
        switch (type){
            default :
                setImage(Const.CHARACTER_JUMP[Const.CHOSEN_CHARACTER]);
                break;
            case Platform.JETPACKED :
                setImage(Const.CHARACTER_WITH_JETPACK[Const.CHOSEN_CHARACTER]);
                break;
        }
    }

    public void moveRight(){
        setScaleX(1);
        if(getTranslateX() + speed_x + Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_LEFT*Const.DOODLER_WIDTH <= Const.STAGE_WIDTH) {
            setTranslateX(getTranslateX() + speed_x);
            detector.setX(getTranslateX() + getImage().getWidth()/3);
            generalDetector.setX(getTranslateX() + 20);
        } else {
            setTranslateX(-Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_RIGHT*Const.DOODLER_WIDTH);
        }
    }

    public void verticalMovement(){
        setTranslateY(getTranslateY() - speed_y);
        detector.setY(getTranslateY() + Const.DOODLER_HEIGHT - 7);
        if(getImage() == Const.CHARACTER_NORMAL[Game.getLvl() - 1]) generalDetector.setY(getTranslateY());
        else if(getImage() == Const.CHARACTER_JUMP[Game.getLvl() - 1]) generalDetector.setY(getTranslateY() + 20);
        else generalDetector.setY(getTranslateY());
        speed_y += Const.GRAVITY;
        if(Math.abs(speed_y) < 1) setImage(Const.CHARACTER_NORMAL[Const.CHOSEN_CHARACTER]);
    }

    public Rectangle getGeneralDetector() {
        return generalDetector;
    }

    public double getSpeed_y() {
        return speed_y;
    }

    public void setSpeed_y(double speed_y) {
        this.speed_y = speed_y;
    }

    public Detector getDetector() {
        return detector;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}

