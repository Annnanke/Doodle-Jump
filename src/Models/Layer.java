package Models;

import Basics.Const;
import Basics.Generator;
import Main.Game;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class Layer {
    private Platform p;
    public static ArrayList<Layer> all = new ArrayList<>();
    private static Game root;
    private double y;
    private static double speed = 0;
    private boolean pivot, missedTrampoline = false;
    private Line visualiser;
    private static Layer top;
    private static double trampoline_height = 0;


    public Layer(double y){
        this.y = y;
        p = Generator.nextPlatform(y + offset);
        all.add(this);
        if(top == null) top = this;
        else if(top.getY() > getY()) top = this;
        visualiser = new Line(0,y, Const.STAGE_WIDTH,y);
        visualiser.setOpacity(Const.DETECTOR_OPACITY);
        root.getChildren().add(visualiser);
    }

    public static void move(){

        if(!hasPivot()) return;
        Layer pivot = getPivot();


        switch (pivot.getPivotType()){

            case PIVOT_JUMP :
                if(pivot.getY() + speed < Const.LOWER_PLATFORM_OFFSET && speed > Const.MIN_SPEED_FOR_JUMP) {
                    moveOnce();

                }
                else pivot.setPivot(false);

                return;

            case PIVOT_TRAMPOLINE :
                //when jumped not on the spring of the trampoline and it should be an ordinary jump
                if(pivot.isMissedTrampoline()){
                    if(pivot.getY() + speed < Const.LOWER_PLATFORM_OFFSET && speed > Const.MIN_SPEED_FOR_JUMP) {
                        moveOnce();
                        return;
                    }
                    else {
                        pivot.setPivot(false);
                        return;
                    }
                }

                //ordinary trampoline jump
                if(trampoline_height < Const.STAGE_HEIGHT && speed > Const.MIN_SPEED_FOR_JUMP) {
                    trampoline_height += speed;
                    moveOnce();
                }
                else {
                    trampoline_height = 0;
                    pivot.setPivot(false);
                }
                return;

        }


    }

    public static void generateWhenPassed(){
        for(Layer l : all)
            if(l.getY() >= Const.STAGE_HEIGHT) {
                l.setDetectable(true);
                l.setY(getTop().getY() - Const.LAYER_HEIGHT[Game.getLvl() - 1]);
                l.setType(Generator.nextType(l.getPlatformY()));
                top = l;
            } else if(l.getPlatform().getTranslateY() >= Const.STAGE_HEIGHT) l.getPlatform().setDetectable(false);
    }

    public boolean isDetectable(){
        return getPlatform().isDetectable();
    }

    public void setDetectable(boolean t){
        getPlatform().setDetectable(t);
    }

    public void setMissedTrampoline(boolean missedTrampline) {
        this.missedTrampoline = missedTrampline;
    }

    public boolean isMissedTrampoline() {
        return missedTrampoline;
    }

    private static void moveOnce(){
        Game.getScorebar().addPoints((int)speed);
        for(Layer l : all) l.setY(l.getY() + speed);
        speed += Const.GRAVITY;
    }

    public Detector getDetector(){
        return p.getDetector();
    }

    public Detector getAdditionalDetector(){
        return p.getAdditionalDetector();
    }

    public void setY(double y) {
        this.y = y;
        p.setTranslateY(y + offset);
        p.moveDetector();
        visualiser.setStartY(y);
        visualiser.setEndY(y);
    }


    public void setType(int type){
        getPlatform().setType(type);
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

    public int getPivotType() {
        return pivotType;
    }

    /**
     * setter for both pivot and pivotType
     * @param pivot
     */
    public void setPivot(boolean pivot) {
        this.pivot = pivot;


        if(pivot){
            switch (getPlatform().getType()){
                case Platform.TRAMPOLINE :
                    speed = missedTrampoline? Const.PLATFORM_V : Const.TRAMPOLINE_V_0;
                    pivotType = PIVOT_TRAMPOLINE;
                    break;
                case Platform.CRACKED:
                    speed = 0;
                    pivotType = PIVOT_CRACKED;
                    break;
                default :
                    speed = Const.PLATFORM_V;
                    pivotType = PIVOT_JUMP;
                    break;
            }
        } else pivotType = NOT_A_PIVOT;
    }

    public static void setRoot(Game root) {
        Layer.root = root;
    }

    public static final double offset = (Const.LAYER_HEIGHT[Game.getLvl() - 1] - Const.PLATFORM_HEIGHT)/2;
    public static final int NOT_A_PIVOT = -1;
    public static final int PIVOT_JUMP = 0;
    public static final int PIVOT_TRAMPOLINE = 1;
    public static final int PIVOT_CRACKED = 2;
    private static int pivotType = NOT_A_PIVOT;
}