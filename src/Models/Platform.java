package Models;
import Basics.Const;
import Basics.Detector;
import Main.Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Platform extends ImageView {

    private double x, y;
    private  double speed_y;
    private static Game root;
    private boolean pivot;
    private Detector detector;

    public Platform(double x, double y, Game root) {
        super(Const.PLATFORM_1);
        this.x = x;
        this.y = y;
        this.root = root;
        detector = new Detector((x), (y), (getImage().getWidth()), this);
        root.getChildren().add(detector);
        speed_y = Const.PLATFORM_V;
        setTranslateX(x);
        setTranslateY(y);
        root.getChildren().add(this);
    }

//    public void moveDown(int lvl){
//
//        if(!root.hasPivot()) return;
//        if(!pivot){
//        setTranslateY(getTranslateY() + speed_y);
//        moveDetector();
//        speed_y += Const.GRAVITY;
//
////        if(getTranslateY() - (Const.LAYER_HEIGHT[lvl] - Const.PLATFORM_HEIGHT)/2 >= Const.STAGE_HEIGHT) {
////            setTranslateX(new Random().nextInt(Const.STAGE_WIDTH - Const.DOODLER_WIDTH));
////            setTranslateY((Const.LAYER_HEIGHT[lvl] - Const.PLATFORM_HEIGHT)/2 - Const.LAYER_HEIGHT[lvl]);
////            moveDetector();
////        }
//        }
//        else {
//            if(getTranslateY() < Const.LOWER_PLATFORM_OFFSET) {
//                setTranslateY(getTranslateY() + speed_y);
//                moveDetector();
//                for(Platform p : root.getPlatforms()) p.speed_y = Const.PLATFORM_V;
//            }
////            else if(getTranslateY() + speed_y >= Const.LOWER_PLATFORM_OFFSET) {
////                setTranslateY(Const.LOWER_PLATFORM_OFFSET);
////                moveDetector();
////                pivot = false;
////                for(Platform p : root.getPlatforms()) p.speed_y = Const.PLATFORM_V;
////            }
//            else {
//                pivot = false;
//                for(Platform p : root.getPlatforms()) p.speed_y = Const.PLATFORM_V;
//            }
//        }
//    }

    public void moveDown(int lvl){

        if(!root.hasPivot()) return;
        if(!pivot){
            setTranslateY(getTranslateY() + speed_y);
            moveDetector();
            speed_y += Const.GRAVITY;
        } else {
            if(getTranslateY() + speed_y < Const.LOWER_PLATFORM_OFFSET) {
                System.out.println(getTranslateY() + speed_y + " - offset = " + Const.LOWER_PLATFORM_OFFSET);
                setTranslateY(getTranslateY() + speed_y);
                moveDetector();
                speed_y += Const.GRAVITY;
                if(speed_y <= 0){
                    moveToOffset();
                    pivot = false;
                    for(Platform p : root.getPlatforms()) p.speed_y = Const.PLATFORM_V;
                }
            } else {
                System.out.println(getTranslateY() + speed_y + " - offset = " + Const.LOWER_PLATFORM_OFFSET);
                moveToOffset();
                pivot = false;
                for(Platform p : root.getPlatforms()) p.speed_y = Const.PLATFORM_V;
            }
        }
    }

    private void moveToOffset(){
        for (Platform p : root.getPlatforms()) {
            p.setTranslateY(p.getTranslateY() + Const.LOWER_PLATFORM_OFFSET - getTranslateY() - speed_y);
            p.moveDetector();
        }
        setTranslateY(Const.LOWER_PLATFORM_OFFSET);
        moveDetector();
    }

    public static void generateWhenPassed(int lvl){
        for (Platform p : root.getPlatforms())
            if(p.getTranslateY() - (Const.LAYER_HEIGHT[lvl] - Const.PLATFORM_HEIGHT)/2 >= Const.STAGE_HEIGHT) {
                p.setTranslateX(new Random().nextInt(Const.STAGE_WIDTH - Const.DOODLER_WIDTH));
                p.setTranslateY((Const.LAYER_HEIGHT[lvl] - Const.PLATFORM_HEIGHT)/2 - Const.LAYER_HEIGHT[lvl]);
                p.moveDetector();
                //p.speed_y = Const.PLATFORM_V;
            }


    }

    private void moveDetector(){
        detector.setX(getTranslateX());
        detector.setY(getTranslateY());
    }

    public void setPivot(boolean pivot) {
        this.pivot = pivot;
    }

    public boolean isPivot() {
        return pivot;
    }

    public Detector getDetector() {
        return detector;
    }



    public double getSpeed_y() {
        return speed_y;
    }
}
