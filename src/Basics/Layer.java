package Basics;

import Main.Game;
import Models.Platform;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class Layer {
    private Platform p;
    public static ArrayList<Layer> all = new ArrayList<>();
    private static Game root = new Game();
    private double y;
    private static double speed = 0;
    private boolean pivot;
    private Line visualiser;
    private static Layer top;
    private static double trampoline_height = 0;

    public Layer(double y){
        this.y = y;
        p = Generator.nextPlatform(y + offset);
        all.add(this);
        if(top == null) top = this;
        else if(top.getY() > getY()) top = this;
        visualiser = new Line(0,y,Const.STAGE_WIDTH,y);
        visualiser.setOpacity(Const.DETECTOR_OPACITY);
        root.getChildren().add(visualiser);
    }

    public static void move(){

        if(!hasPivot()) return;
        Layer pivot = getPivot();

        //if type of pivot is changed and it's no longer accepted as pretype-pivot (esp. trampoline)
        if(pivot.isModified()){
            switch (pivot.getPretype()){

                case Platform.TRAMPOLINE :
                    if(trampoline_height < Const.STAGE_HEIGHT && speed > 0) {
                        trampoline_height += speed;
                        moveOnce();
                    }
                    else {
                        trampoline_height = 0;
                        //shift(Const.LOWER_PLATFORM_OFFSET - pivot.getY());
                        pivot.setPivot(false);
                    }
                    return;

            }
        }

        //Ordinary each-type moving
        switch (pivot.getType()){

            case Platform.TRAMPOLINE:
                if(trampoline_height < Const.STAGE_HEIGHT && speed > 0) {
                    trampoline_height += speed;
                    moveOnce();
                }
                else {
                    trampoline_height = 0;
                    //shift(Const.LOWER_PLATFORM_OFFSET - pivot.getY());
                    pivot.setPivot(false);
                }
                break;

            default:
                if(pivot.getY() + speed < Const.LOWER_PLATFORM_OFFSET && speed > 0) moveOnce();

                else {
                    System.out.println("shifted");
                   // shift(Const.LOWER_PLATFORM_OFFSET - pivot.getY());
                    pivot.setPivot(false);
                }
                break;
        }
    }

    public static void generateWhenPassed(){
        for(Layer l : all)
            if(l.getY() >= Const.STAGE_HEIGHT) {
                l.setY(getTop().getY() - Const.LAYER_HEIGHT[root.getLvl() - 1]);
                //TODO regard the case when trampoline jump leads to cycling round all the time
                // the pivot-trampoline should change position and type but still be regarded as the same trampoline
                // in the move() method of this class!!!!!!!!!
                //if(!(l.getType() == Platform.TRAMPOLINE && l.isPivot()))
                l.setType(Generator.nextType(l.getPlatformY()));
                top = l;
            }
    }

    private static void moveOnce(){
        for(Layer l : all) l.setY(l.getY() + speed);
        speed += Const.GRAVITY;
    }

    //TODO remove shift method
    private static void shift(double l){
        for (Layer lay : all) lay.setY(lay.getY() + l);
    }

    public Detector getDetector(){
        return p.getDetector();
    }

    public void setY(double y) {
        this.y = y;
        p.setTranslateY(y + offset);
        p.moveDetector();
        visualiser.setStartY(y);
        visualiser.setEndY(y);
    }

    public boolean isModified(){
        return getPlatform().isModified();
    }


    public void setType(int type){
        getPlatform().setType(type);
    }

    public int getPretype(){
        return getPlatform().getPretype();
    }

    public int getType(){
        return getPlatform().getType();
    }

    public double getY() {
        return y;
    }

    public double getPlatformY(){
        return p.getTranslateY();
    }

    public Platform getPlatform() {
        return p;
    }

    public static Layer getTop(){
        return top;
    }

    public static Game getRoot() {
        return root;
    }

    public static Layer getPivot(){
        for(Layer l : all) if(l.isPivot()) return l;
        return null;
    }
    public static boolean hasPivot(){
        for(Layer l : all) if(l.isPivot()) return true;
        return false;
    }

    public boolean isPivot() {
        return pivot;
    }

    public static ArrayList<Layer> getAll() {
        return all;
    }


    public void setPivot(boolean pivot) {
        this.pivot = pivot;
//        if(pivot) speed = Const.PLATFORM_V;
//        else speed = 0;

        if(pivot){
            switch (getPlatform().getType()){
                default :
                    speed = Const.PLATFORM_V;
                    break;
                case Platform.TRAMPOLINE :
                    speed = Const.TRAMPOLINE_V_0;
                    break;
            }
        } else speed = 0;
    }

    public static void setRoot(Game root) {
        Layer.root = root;
    }

    public static final double offset = (Const.LAYER_HEIGHT[root.getLvl() - 1] - Const.PLATFORM_HEIGHT)/2;
}