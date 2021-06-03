package Models;
import Basics.Const;
import Main.Game;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Random;

public class Platform extends ImageView {

    private static Game root;
    private Detector additionalDetector, detector;
    private int type, crackedCounter = 0;
    private double horizontal_speed;
    private boolean detectable;


    public Platform(double x, double y, int type, Game root) {
        super();
        this.root = root;
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        setType(type);
        horizontal_speed = Const.HORIZONTAL_SPEED[Game.getLvl() - 1] * Math.pow(-1, new Random().nextInt());
        detector = new Detector((x), (y), (getImage().getWidth()), this);
        detectable = true;
        root.getChildren().add(detector);
        root.getChildren().add(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {

        this.type = type;
        switch (type){
            case DEFAULT :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1[Game.getLvl() - 1]);
                break;
            case MOVING :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1[Game.getLvl() - 1]);
                break;
            case TRAMPOLINE :
                setImage(Const.TRAMPOLINE[Game.getLvl() - 1]);
                additionalDetector = new Detector( getTranslateX() + getImage().getWidth() * (0.4), getTranslateY(),
                        0.2 * getImage().getWidth(), this);

                additionalDetector.setFill(Color.RED);
                root.getChildren().add(additionalDetector);
                break;
            case CRACKED :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1_BROKEN[Game.getLvl() - 1]);
                break;

            case JETPACKED :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setTranslateY(getTranslateY() - 35);
                setImage(Const.JETPACK);
                break;

            case GOLDEN:
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.GOLDEN[Game.getLvl() - 1]);
                break;
        }
    }


    public static void removePostCracked(){
        for(Layer l : Layer.getAll())
        if(l.getPlatform().getType() == CRACKED && l.getPlatform().getImage() == Const.PLATFORM_1_POST_BROKEN[Game.getLvl() - 1])
            l.getPlatform().disposeOfPostCracked();
    }

    public void disposeOfPostCracked(){
        if(++crackedCounter > Const.POST_CRACKED_TIME_OF_LIFE) setImage(null);
    }

    public static void moveAllMovingHorizontally(){
        for(Layer l : Layer.getAll())
            if(l.getPlatform().getType() == MOVING) {
                if(l.getPlatform().getTranslateX() + Const.PLATFORM_WIDTH + l.getPlatform().horizontal_speed > Const.STAGE_WIDTH ||
                   l.getPlatform().getTranslateX() + l.getPlatform().horizontal_speed < 0 ) l.getPlatform().horizontal_speed *= -1;
                l.getPlatform().setTranslateX(l.getPlatform().getTranslateX() + l.getPlatform().horizontal_speed);
                l.getPlatform().moveDetector();
            }


    }


    public boolean isStable(){
        return type != CRACKED;
    }

    public void moveDetector(){
        detector.setX(getTranslateX());
        detector.setY(getTranslateY());
        if(additionalDetector != null){
            additionalDetector.setX(getTranslateX() + getImage().getWidth()*(0.45));
            additionalDetector.setY(getTranslateY());
        }

        if(type == JETPACKED) setTranslateY(getTranslateY() - 40);
    }

    public void remove(){
        root.getChildren().remove(this);
        root.getChildren().remove(getDetector());
        if(getAdditionalDetector() != null) root.getChildren().remove(getAdditionalDetector());
        setDetectable(false);
    }

    public boolean isDetectable(){
        return detectable;
    }

    public void setDetectable(boolean d){
        detectable = d;
    }

    public Detector getDetector() {
        return detector;
    }

    public Detector getAdditionalDetector() {
        return additionalDetector;
    }

    public static final int DEFAULT = 0;
    public static final int MOVING = 1;
    public static final int TRAMPOLINE = 2;
    public static final int CRACKED = 3;
    public static final int JETPACKED = 4;
    public static final int GOLDEN = 10;

}
