package Models;

import Basics.Const;
import Basics.Generator;
import Main.Game;
import Monsters.Monster;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class Layer {
    private Platform p;
    public static ArrayList<Layer> all;
    private static Game root;
    private double y;
    private static double speed;
    private boolean pivot, missedTrampoline = false, toDisappear = false;
    private Line visualiser;
    private static Layer top;
    private static double passed_height;
    private ImageView connectedImage;
    private ProgressBar connectedProgressBar;
    private ArrayList<Shape> connectedShapes = new ArrayList<>();
    private static boolean monster;
    private static int monsterCounter;

    /**
     * Basic constructor
     * @param y - y-coordinate
     */
    public Layer(double y){
        this.y = y;
        p = Generator.nextPlatform(y + offset,root);
        all.add(this);
        if(top == null) top = this;
        else if(top.getY() > getY()) top = this;
        visualiser = new Line(0,y, Const.STAGE_WIDTH,y);
        visualiser.setOpacity(Const.DETECTOR_OPACITY);
        root.getChildren().add(visualiser);
    }

    /**
     * reloads the class
     */
    public static void reload(){
        if(all != null) cleanAll();
        all = new ArrayList<>();
        speed = 0;
        passed_height = 0;
        top = null;
        monster = false;
        monsterCounter = 0;
    }

    /**
     * moves layers
     */
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
                        root.getPlayer().setFlying(false);
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
                    root.getPlayer().setFlying(false);
                    pivot.setPivot(false);
                }
                return;

            case PIVOT_JETPACK :
                if(passed_height < Const.JETPACK_HEIGHT && speed > Const.MIN_SPEED_FOR_JUMP) {
                    passed_height += speed;
                    moveOnce();
                }
                else {
                    root.getPlayer().setFlying(false);
                    passed_height = 0;
                    pivot.setPivot(false);
                }
                return;
        }
    }

    /**
     * getter for monsterCounter
     * @return - int
     */
    public static int getMonsterCounter() {
        return monsterCounter;
    }

    /**
     * setter for monsterCounter
     * @param monsterCounter - int
     */
    public static void setMonsterCounter(int monsterCounter) {
        Layer.monsterCounter = monsterCounter;
    }


    /**
     * generates platforms. Each one generated for each passed
     */
    public static void generateWhenPassed(){
        if(hasGoldenPlatform()) return;
        removeAllToDisappear();
        if(monster && Const.HEIGHT_1[Game.getLvl() - 1] - Game.getScorebar().getPoints() > Const.STAGE_HEIGHT) {
            getTop().getPlatform().setUnderMonster(true);
            new Monster(getTop().getY() - 2*Const.LAYER_HEIGHT[Game.getLvl() - 1], Generator.randomiseMonster(0,1,2,3),root);
            monsterCounter++;
            monster = false;
        }
        for(Layer l : all)
            if(l.getY() >= Const.STAGE_HEIGHT) {
                l.setDetectable(true);
                l.getPlatform().setUnderMonster(false);
                l.setY(getTop().getY() - Const.LAYER_HEIGHT[Game.getLvl() - 1]);
                if(Const.LOWER_PLATFORM_OFFSET - l.getPlatformY() >= Const.HEIGHT_1[Game.getLvl() - 1] - Game.getScorebar().getPoints())
                    l.setType(Platform.GOLDEN);
                else {
                    l.setType(Generator.nextType(l.getPlatformY()));
                    if(Math.random() < Const.PROBABILITY_OF_MONSTER_APPEARANCE[Game.getLvl() - 1]
                            && monsterCounter == 0
                            && isReachable(l.getY() - 2*Const.LAYER_HEIGHT[Game.getLvl() - 1] - offset - Const.PLATFORM_HEIGHT)) {
                        monster = true;
                        l.setType(new Random().nextInt(1));
                    }
                }
                top = l;
            } else if(l.getPlatform().getTranslateY() >= Const.STAGE_HEIGHT) l.getPlatform().setDetectable(false);
    }

    /**
     * removes all layers toDisappear
     */
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

    /**
     * returns whether there're any layers to disappear
     * @return - bool
     */
    private static boolean hasOnesToDisappear(){
        for(Layer l : all) if(l.isToDisappear()) return true;
        return false;
    }

    /**
     * getter for whether there're golden platform
     * @return - bool
     */
    public static boolean hasGoldenPlatform(){
        for(Layer l : all)
            if(l.getType() == Platform.GOLDEN) return true;
        return false;
    }

    /**
     * checks whether it is an underMonster layer
     * @return
     */
    public static boolean hasUnderMonster(){
        for(Layer l : all)
            if(l.getPlatform().isUnderMonster()) return true;
            return false;
    }

    /**
     * getter for undermonster platform
     * @return
     */
    public static Platform getUnderMonster(){
        if(!hasUnderMonster()) return null;
        for (Layer l : all)
            if(l.getPlatform().isUnderMonster()) return l.getPlatform();
            return null;
    }

    /**
     * checks whether the height is reachable by doodler
     * @param height - height
     * @return - bool
     */
    public static boolean isReachable(double height){
        for(Layer l : all)
            if(Math.abs(l.getPlatformY() - height) < Const.DOODLER_HEIGHT_OF_JUMP
                    && l.getPlatformY() > height
                    && l.getPlatform().isStable()) return true;
        return false;
    }

    /**
     * getter for platform detectable
     * @return - bool
     */
    public boolean isDetectable(){
        return getPlatform().isDetectable();
    }

    /**
     * setter gor detectable
     * @param t - bool
     */
    public void setDetectable(boolean t){
        getPlatform().setDetectable(t);
    }

    /**
     * setts missed trampoline
     * @param missedTrampline - bool
     */
    public void setMissedTrampoline(boolean missedTrampline) {
        this.missedTrampoline = missedTrampline;
    }

    /**
     * getter for missed trampoline
     * @return
     */
    public boolean isMissedTrampoline() {
        return missedTrampoline;
    }

    /**
     * moves all layers ones
     */
    private static void moveOnce(){
        Game.getScorebar().addPoints((int)speed);
        for(Layer l : all) {
            l.setY(l.getY() + speed);
            if(l.getConnectedImage() != null) l.getConnectedImage().setTranslateY(l.getConnectedImage().getTranslateY() + speed);
            if(l.getConnectedProgressBar() != null) l.getConnectedProgressBar().setTranslateY(l.getConnectedProgressBar().getTranslateY() + speed);
            for(Shape s : l.connectedShapes) s.setTranslateY(s.getTranslateY() + speed);
        }
        speed += Const.GRAVITY;
    }

    /**
     * cleans all-arraylist
     */
    public static void cleanAll(){
        for(int i = 0; i < all.size(); i++) {
            all.remove(all.get(i));
        }
    }

    /**
     * getter for platform's detector
     * @return Detector
     */
    public Detector getDetector(){
        return p.getDetector();
    }

    /**
     * getter for platform's additional detector
     * @return - Detector
     */
    public Detector getAdditionalDetector(){
        return p.getAdditionalDetector();
    }

    /**
     * getter for toDisappear
     * @return - bool
     */
    public boolean isToDisappear() {
        return toDisappear;
    }

    /**
     * setter for toDisappear
     * @param toDisappear - bool
     */
    public void setToDisappear(boolean toDisappear) {
        this.toDisappear = toDisappear;
    }

    /**
     * setter for connectedImage
     * @param connectedImage - imageView
     */
    public void setConnectedImage(ImageView connectedImage) {
        this.connectedImage = connectedImage;
    }

    /**
     * getter for connectedImage
     * @return - ImageView
     */
    public ImageView getConnectedImage() {
        return connectedImage;
    }

    /**
     * getter for connectedProgressBar
     * @return - ProgressBar
     */
    public ProgressBar getConnectedProgressBar() {
        return connectedProgressBar;
    }

    /**
     * setter for connectedProgressBar
     * @param connectedProgressBar - ProgressBar
     */
    public void setConnectedProgressBar(ProgressBar connectedProgressBar) {
        this.connectedProgressBar = connectedProgressBar;
    }

    /**
     * setter for y-coordinate
     * @param y - double
     */
    public void setY(double y) {
        this.y = y;
        p.setTranslateY(y + offset);
        p.moveDetector();
        visualiser.setStartY(y);
        visualiser.setEndY(y);
    }

    /**
     * getter for connectedShapes
     * @return - ArrayList<Shape>
     */
    public ArrayList<Shape> getConnectedShapes() {
        return connectedShapes;
    }

    /**
     * setter for type
     * @param type - int
     */
    public void setType(int type){
        getPlatform().setType(type);
    }

    /**
     * getter for type
     * @return - int
     */
    public int getType(){
        return getPlatform().getType();
    }

    /**
     * getter for y-coordinate
     * @return - double
     */
    public double getY() {
        return y;
    }

    /**
     * getter for platform y-coordinate
     * @return
     */
    public double getPlatformY(){
        return p.getTranslateY();
    }

    /**
     * getter for p
     * @return - platform
     */
    public Platform getPlatform() {
        return p;
    }

    /**
     * returns the top layer
     * @return - Layer
     */
    public static Layer getTop(){
        return top;
    }


    /**
     * returns pivot
     * @return - Layer
     */
    public static Layer getPivot(){
        for(Layer l : all) if(l.isPivot()) return l;
        return null;
    }

    /**
     * getter for pivot
     * @return - bool
     */
    public static boolean hasPivot(){
        for(Layer l : all) if(l.isPivot()) return true;
        return false;
    }

    /**
     * getter for pivot
     * @return - bool
     */
    public boolean isPivot() {
        return pivot;
    }

    /**
     * getter for all-arraylist
     * @return - ArrayList<Layer>
     */
    public static ArrayList<Layer> getAll() {
        return all;
    }

    /**
     * getter for pivot type
     * @return - int
     */
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

    /**
     * setter for root
     * @param root
     */
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