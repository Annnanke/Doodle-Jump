package Basics;


import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class Detector extends Rectangle {

    private ImageView o;

    public Detector(double x, double y, double width, ImageView im){
        super(x, y, width, Const.DETECTOR_HEIGHT);
        setOpacity(Const.DETECTOR_OPACITY);
        o = im;
    }

    //TODO normal intersection
//    public boolean intersection(Detector d){
//        System.out.println(o.getClass());
//        if(o.getClass() != Platform.class) return false;
//        if(getBoundsInParent().intersects(d.getBoundsInParent())) System.out.println(getY() + getHeight() + " - y_p = " + d.getY() + " - v = " + ((Platform)o).getSpeed_y());
//        if(getX() + getWidth() < d.getX() || getX() > d.getX() + d.getWidth()) return false;
//        if(!(getY() + getHeight() >= d.getY() && getY() + getHeight() + ((Platform)o).getSpeed_y() < d.getY())) return false;
//        return true;
//    }

}
