package Models;

import Basics.Const;
import Basics.Detector;
import Main.Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Doodler extends ImageView {

    private double speed_x, speed_y;
    private Detector detector;
    private boolean moving = true;

    public Doodler(double x, double y, Game root) {
        super(Const.CAT_NORMAL);
        setTranslateX(x);
        setTranslateY(y);
        detector = new Detector((int)(x + getImage().getWidth()/3), (int)(y + Const.DOODLER_HEIGHT - 7), (int)(getImage().getWidth() * 0.5), this);
        root.getChildren().add(detector);
        speed_x = Const.DOODLER_V0_X;
        speed_y = Const.DOODLER_V0_Y;
        root.getChildren().add(this);
    }


    public void moveLeft(){
        setImage(Const.CAT_NORMAL_REFL);
        if(getTranslateX() - speed_x >= -Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_RIGHT*Const.DOODLER_WIDTH) {
            setTranslateX(getTranslateX() - speed_x);
            detector.setX(getTranslateX() + getImage().getWidth() - getImage().getWidth()/3 - detector.getWidth());
            detector.setWidth(detector.getWidth());
        } else {
            setTranslateX(Const.STAGE_WIDTH - Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_LEFT*Const.DOODLER_WIDTH);
        }
    }

    public void moveRight(){
        setImage(Const.CAT_NORMAL);
        if(getTranslateX() + speed_x + Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_LEFT*Const.DOODLER_WIDTH <= Const.STAGE_WIDTH) {
            setTranslateX(getTranslateX() + speed_x);
            detector.setX(getTranslateX() + getImage().getWidth()/3);
        } else {
            setTranslateX(-Const.PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_RIGHT*Const.DOODLER_WIDTH);
        }
    }

    public void verticalMovement(){
        setTranslateY(getTranslateY() - speed_y);
        detector.setY(getTranslateY() + Const.DOODLER_HEIGHT - 7);
        speed_y += Const.GRAVITY;
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
