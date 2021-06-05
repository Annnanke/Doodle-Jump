package Models;

import Basics.Const;
import Basics.Generator;
import Main.Game;
import Monsters.Monster;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Layer {
    private Platform p;
    public static ArrayList<Layer> all = new ArrayList<>();
    private static Game root;
    private double y;
    private static double speed = 0;
    private boolean pivot, missedTrampoline = false, toDisappear = false;
    private Line visualiser;
    private static Layer top;
    private static double passed_height = 0;
    private ImageView connectedImage;
    private ArrayList<Shape> connectedShapes = new ArrayList<>();


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
                if(passed_height < Const.TRAMPOLINE_HEIGHT && speed > Const.MIN_SPEED_FOR_JUMP) {
                    passed_height += speed;
                    moveOnce();
                }
                else {
                    passed_height = 0;
                    pivot.setPivot(false);
                }
                return;

            case PIVOT_JETPACK :
                if(passed_height < Const.JETPACK_HEIGHT && speed > Const.MIN_SPEED_FOR_JUMP) {
                    passed_height += speed;
                    moveOnce();
                }
                else {
                    passed_height = 0;
                    pivot.setPivot(false);
                }
                return;
        }
    }

    private static boolean monster = false;
    private static int monsterCounter = 0;

    public static void generateWhenPassed(){
        if(hasGoldenPlatform()) return;
        removeAllToDisappear();
        if(monster) {
            new Monster(getTop().getY() - 2*Const.LAYER_HEIGHT[Game.getLvl() - 1], Generator.randomiseMonster(0,1,2,3),root);
            monsterCounter++;
            monster = false;
        }
        for(Layer l : all)
            if(l.getY() >= Const.STAGE_HEIGHT) {
                l.setDetectable(true);
                l.setY(getTop().getY() - Const.LAYER_HEIGHT[Game.getLvl() - 1]);
                if(Const.LOWER_PLATFORM_OFFSET - l.getPlatformY() >= Const.HEIGHT_1 - Game.getScorebar().getPoints())
                    l.setType(Platform.GOLDEN);
                else {
                    l.setType(Generator.nextType(l.getPlatformY()));
                    if(Math.random() < Const.PROBABILITY_OF_MONSTER_APPEARANCE[Game.getLvl() - 1]
                            && monsterCounter == 0
                            && isReachable(getTop().getY() - 2*Const.LAYER_HEIGHT[Game.getLvl() - 1] + offset + Const.PLATFORM_HEIGHT))
                        monster = true;
                }
                top = l;
            } else if(l.getPlatform().getTranslateY() >= Const.STAGE_HEIGHT) l.getPlatform().setDetectable(false);
    }

    private static void removeAllToDisappear(){
        if(!hasOnesToDisappear()) return;
        Layer toRemove = null;
        for (Layer l : all) if(l.getY() >= Const.STAGE_HEIGHT && l.isToDisappear()) toRemove = l;
        if(toRemove == null) return;
        all.remove(toRemove);
        root.getChildren().remove(toRemove.getPlatform());
        root.getChildren().remove(toRemove.getPlatform().getDetector());
        if(toRemove.getConnectedImage() != null) {
            toRemove.setConnectedImage(null);
            monsterCounter--;
            Monster.monsters.remove(Monster.indexOf(toRemove));
        }
    }

    private static boolean hasOnesToDisappear(){
        for(Layer l : all) if(l.isToDisappear()) return true;
        return false;
    }

    public static boolean hasGoldenPlatform(){
        for(Layer l : all)
            if(l.getType() == Platform.GOLDEN) return true;
            return false;
    }

    public static boolean isReachable(double height){
        for(Layer l : all)
            if(Math.abs(l.getPlatformY() - height) < Const.DOODLER_HEIGHT_OF_JUMP
                    && l.getPlatformY() > height
                    && l.getPlatform().isStable()) return true;
            return false;
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
        for(Layer l : all) {
            l.setY(l.getY() + speed);
            if(l.getConnectedImage() != null) l.getConnectedImage().setTranslateY(l.getConnectedImage().getTranslateY() + speed);
            for(Shape s : l.connectedShapes) s.setTranslateY(s.getTranslateY() + speed);
        }
        speed += Const.GRAVITY;
    }

    public Detector getDetector(){
        return p.getDetector();
    }

    public Detector getAdditionalDetector(){
        return p.getAdditionalDetector();
    }

    public boolean isToDisappear() {
        return toDisappear;
    }

    public void setToDisappear(boolean toDisappear) {
        this.toDisappear = toDisappear;
    }

    public  void setConnectedImage(ImageView connectedImage) {
        this.connectedImage = connectedImage;
    }

    public ImageView getConnectedImage() {
        return connectedImage;
    }

    public void setY(double y) {
        this.y = y;
        p.setTranslateY(y + offset);
        p.moveDetector();
        visualiser.setStartY(y);
        visualiser.setEndY(y);
    }

    public ArrayList<Shape> getConnectedShapes() {
        return connectedShapes;
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

    public static Platform getGoldenPlatform(){
        for(Layer l : all)
            if(l.getType() == Platform.GOLDEN) return l.getPlatform();
            return null;
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
                case Platform.JETPACKED :
                    speed = Const.JETPACK_V_0;
                    pivotType = PIVOT_JETPACK;
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
    public static final int PIVOT_JETPACK = 3;
    private static int pivotType = NOT_A_PIVOT;
}