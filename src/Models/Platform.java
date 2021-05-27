package Models;
import Basics.Const;
import Basics.Detector;
import Basics.Layer;
import Main.Game;
import javafx.scene.image.ImageView;

public class Platform extends ImageView {

    private static Game root;
    private Detector detector;
    private int type, crackedCounter = 0;

    public Platform(double x, double y, int type, Game root) {
        super();
        setType(type);
        this.root = root;
        this.type = type;
        detector = new Detector((x), (y), (getImage().getWidth()), this);
        root.getChildren().add(detector);
        setTranslateX(x);
        setTranslateY(y);
        root.getChildren().add(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        switch (type){
            case 0 :
                setImage(Const.PLATFORM_1);
                break;
            case 1 :
                setImage(Const.PLATFORM_1);
                break;
            case 2 :
                setImage(Const.TRAMPOLINE);
                break;
            case 3 :
                setImage(Const.PLATFORM_1_BROKEN);
                break;
        }
    }

    public static void removePostCracked(){
        for(Layer l : Layer.getAll())
        if(l.getPlatform().getType() == CRACKED && l.getPlatform().getImage() == Const.PLATFORM_1_POST_BROKEN)
            l.getPlatform().disposeOfPostCracked();
    }

    public void disposeOfPostCracked(){
        if(++crackedCounter > Const.POST_CRACKED_TIME_OF_EΧΙSTENCE) setImage(null);
    }

    public void moveDetector(){
        detector.setX(getTranslateX());
        detector.setY(getTranslateY());
    }


    public Detector getDetector() {
        return detector;
    }

    public static final int DEFAULT = 0;//---50%
    public static final int MOVING = 1;//---20%
    public static final int TRAMPOLINE = 2;//---10%
    public static final int CRACKED = 3; // must always be the last ---20%


}
