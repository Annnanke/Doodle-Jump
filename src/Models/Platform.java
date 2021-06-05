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
    private ImageView coinOrDiamand;


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
        if(coinOrDiamand != null) root.getChildren().remove(coinOrDiamand);
        switch (type){

            case DEFAULT :
                setImage(Const.PLATFORM_1[Game.getLvl() - 1]);
                if(Math.random() < Const.PROBABILITY_OF_COIN_OR_DIAMOND_APPEARANCE[Game.getLvl() - 1] && (type == DEFAULT || type == MOVING)){
                    if(Math.random() < Const.DIAMOND_AND_COIN_DISTRIBUTION[Game.getLvl() - 1][0]) coinOrDiamand = new ImageView(Const.COIN);
                    else coinOrDiamand = new ImageView(Const.DIAMOND);
                    coinOrDiamand.setTranslateX(getTranslateX() + getImage().getWidth()/2 - coinOrDiamand.getImage().getWidth()/2);
                    coinOrDiamand.setTranslateY(getTranslateY() - coinOrDiamand.getImage().getHeight());
                    root.getChildren().add(coinOrDiamand);
                }
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                break;

            case MOVING :
                setImage(Const.PLATFORM_1[Game.getLvl() - 1]);
                if(Math.random() < Const.PROBABILITY_OF_COIN_OR_DIAMOND_APPEARANCE[Game.getLvl() - 1] && (type == DEFAULT || type == MOVING)){
                    if(Math.random() < Const.DIAMOND_AND_COIN_DISTRIBUTION[Game.getLvl() - 1][0]) coinOrDiamand = new ImageView(Const.COIN);
                    else coinOrDiamand = new ImageView(Const.DIAMOND);
                    coinOrDiamand.setTranslateX(getTranslateX() + getImage().getWidth()/2 - coinOrDiamand.getImage().getWidth()/2);
                    coinOrDiamand.setTranslateY(getTranslateY() - coinOrDiamand.getImage().getHeight());
                    root.getChildren().add(coinOrDiamand);
                }
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                break;

            case TRAMPOLINE :
                if(coinOrDiamand != null) {
                    root.getChildren().remove(coinOrDiamand);
                    coinOrDiamand = null;
                }
                setImage(Const.TRAMPOLINE[Game.getLvl() - 1]);
                additionalDetector = new Detector( getTranslateX() + getImage().getWidth() * (0.4), getTranslateY(),
                        0.2 * getImage().getWidth(), this);

                additionalDetector.setFill(Color.RED);
                root.getChildren().add(additionalDetector);
                break;

            case CRACKED :
                if(coinOrDiamand != null) {
                    root.getChildren().remove(coinOrDiamand);
                    coinOrDiamand = null;
                }
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setImage(Const.PLATFORM_1_BROKEN[Game.getLvl() - 1]);
                break;

            case JETPACKED :
                if(coinOrDiamand != null) {
                    root.getChildren().remove(coinOrDiamand);
                    coinOrDiamand = null;
                }
                root.getChildren().remove(additionalDetector);
                additionalDetector = null;
                setTranslateY(getTranslateY() - 35);
                setImage(Const.JETPACK);
                break;

            case GOLDEN:
                if(coinOrDiamand != null) {
                    root.getChildren().remove(coinOrDiamand);
                    coinOrDiamand = null;
                }
                root.getChildren().remove(additionalDetector);
                detector.setHeight(50);
                additionalDetector = null;
                setImage(Const.GOLDEN[Game.getLvl() - 1]);
                break;
        }
    }

    public boolean hasDiamond(){
        if(coinOrDiamand == null) return false;
        return coinOrDiamand.getImage() == Const.DIAMOND;
    }

    public boolean hasCoin(){
        if(coinOrDiamand == null) return false;
        return coinOrDiamand.getImage() == Const.COIN;
    }

    public ImageView getCoinOrDiamand(){
        return coinOrDiamand;
    }

    public void setCoinOrDiamand(ImageView coinOrDiamand) {
        this.coinOrDiamand = coinOrDiamand;
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

        if(coinOrDiamand != null){
            coinOrDiamand.setTranslateX(getTranslateX() + getImage().getWidth()/2 - coinOrDiamand.getImage().getWidth()/2);
            coinOrDiamand.setTranslateY(getTranslateY() - coinOrDiamand.getImage().getHeight());
        }

        if(type == JETPACKED) setTranslateY(getTranslateY() - 40);
    }

    public void remove(){
        root.getChildren().remove(this);
        root.getChildren().remove(getDetector());
        root.getChildren().remove(coinOrDiamand);
        coinOrDiamand = null;
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
