package Models;


import Basics.Const;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class Detector extends Rectangle {

    private ImageView o;

    public Detector(double x, double y, double width, ImageView im){
        super(x, y, width, Const.DETECTOR_HEIGHT);
        setOpacity(Const.DETECTOR_OPACITY);
        o = im;
    }

}
