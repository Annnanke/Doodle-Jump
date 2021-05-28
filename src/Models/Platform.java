package Models;
import Basics.Const;
import Basics.Detector;
import Basics.Layer;
import Main.Game;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Random;

public class Platform extends ImageView {

    private static Game root;
    private Detector additionalDetector, detector;
    private int type, pretype, crackedCounter = 0;
    private double horizontal_speed;

    public Platform(double x, double y, int type, Game root) {
        super();
        this.root = root;
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        setType(type);
        horizontal_speed = Const.HORIZONTAL_SPEED[Game.getLvl() - 1] * Math.pow(-1, new Random().nextInt());
        detector = new Detector((x), (y), (getImage().getWidth()), this);
        root.getChildren().add(detector);
        root.getChildren().add(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {

        this.pretype = this.type;
        this.type = type;
        switch (type){
            case 0 :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1);
                break;
            case 1 :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1);
                break;
            case 2 :
                setImage(Const.TRAMPOLINE);
                additionalDetector = new Detector( getTranslateX() + getImage().getWidth() * (0.45), getTranslateY(),
                        0.15 * getImage().getWidth(), this);

                additionalDetector.setFill(Color.RED);
                root.getChildren().add(additionalDetector);
                break;
            case 3 :
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1_BROKEN);
                break;
        }
    }

    public int getPretype() {
        return pretype;
    }

    public boolean isModified(){
        return type != pretype;
    }

    public static void removePostCracked(){
        for(Layer l : Layer.getAll())
        if(l.getPlatform().getType() == CRACKED && l.getPlatform().getImage() == Const.PLATFORM_1_POST_BROKEN)
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

    public void moveDetector(){
        detector.setX(getTranslateX());
        detector.setY(getTranslateY());
        if(additionalDetector != null){
            additionalDetector.setX(getTranslateX() + getImage().getWidth()*(0.45));
            additionalDetector.setY(getTranslateY());
        }
    }


    public Detector getDetector() {
        return detector;
    }

    public Detector getAdditionalDetector() {
        return additionalDetector;
    }

    public static final int DEFAULT = 0;//---50%
    public static final int MOVING = 1;//---20%
    public static final int TRAMPOLINE = 2;//---10%
    public static final int CRACKED = 3; // must always be the last ---20%


}
