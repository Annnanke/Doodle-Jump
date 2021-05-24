package Models;
import Basics.Const;
import Basics.Detector;
import Main.Game;
import javafx.scene.image.ImageView;

public class Platform extends ImageView {

    private static Game root;
    private Detector detector;

    public Platform(double x, double y, Game root) {
        super(Const.PLATFORM_1);
        this.root = root;
        detector = new Detector((x), (y), (getImage().getWidth()), this);
        root.getChildren().add(detector);
        setTranslateX(x);
        setTranslateY(y);
        root.getChildren().add(this);
    }

    public void moveDetector(){
        detector.setX(getTranslateX());
        detector.setY(getTranslateY());
    }


    public Detector getDetector() {
        return detector;
    }


}
