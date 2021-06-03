package Monsters;

import Basics.Const;
import Main.Game;
import Models.Layer;
import Models.LayerGroup;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class Monster extends LayerGroup {

    public static ArrayList<Monster> monsters = new ArrayList<>();
    private Layer l1, l2;
    private int type;
    private ImageView iv;
    private double speed_x;
    private Shape detector;

    public Monster(double y, int type, Game root) {
        super(2);
        monsters.add(this);

        l1 = new Layer(y);
        l2 = new Layer(y + Const.LAYER_HEIGHT[Game.getLvl() - 1]);
        l1.setToDisappear(true);
        l2.setToDisappear(true);
        l1.getPlatform().remove();
        l2.getPlatform().remove();
        add(l1);
        add(l2);


        this.type = type;
                l1.getPlatform().remove();
                l2.getPlatform().remove();
        iv =  new ImageView(Const.MONSTERS[type]);
        iv.setTranslateY(y);
        iv.setTranslateX(new Random().nextInt((int) (Const.STAGE_WIDTH - iv.getImage().getWidth())));

        switch (type){
            case STATIONARY :
                detector = new Circle();
                ((Circle) detector).setRadius(iv.getImage().getWidth()/2);
                detector.setTranslateX(iv.getTranslateX() + ((Circle) detector).getRadius());
                detector.setTranslateY(iv.getTranslateY() + ((Circle) detector).getRadius() + 17);
                speed_x = 0;
                if(iv.getTranslateX() + iv.getImage().getWidth()/2 > Const.STAGE_WIDTH/2) iv.setScaleX(-1);
                break;
            case MOVING_BAT:
                detector = new Rectangle();
                ((Rectangle) detector).setWidth(iv.getImage().getWidth() - 30);
                ((Rectangle) detector).setHeight(iv.getImage().getHeight() - 60);
                detector.setTranslateX(iv.getTranslateX() + 15);
                detector.setTranslateY(iv.getTranslateY() + 30);
                speed_x = Const.BAT_SPEED_X[Game.getLvl() - 1];
                iv.setScaleX(-1);
                break;
            case MOVING_DRAGON:
                detector = new Rectangle();
                ((Rectangle) detector).setWidth(iv.getImage().getWidth() - 70);
                ((Rectangle) detector).setHeight(iv.getImage().getHeight() - 60);
                detector.setTranslateX(iv.getTranslateX() + 35);
                detector.setTranslateY(iv.getTranslateY() + 30);
                speed_x = Const.DRAGON_SPEED_X[Game.getLvl() - 1];
                iv.setScaleX(-1);
                break;
            case BLACK_HOLE :
                detector = new Circle();
                ((Circle) detector).setRadius(iv.getImage().getWidth()/2);
                detector.setTranslateX(iv.getTranslateX() + ((Circle) detector).getRadius());
                detector.setTranslateY(iv.getTranslateY() + ((Circle) detector).getRadius());
                speed_x = 0;
                break;
        }
        detector.setOpacity(Const.DETECTOR_OPACITY);
        l1.getConnectedShapes().add(detector);
        root.getChildren().add(detector);
        root.add(iv);
        l1.setConnectedImage(iv);
    }

    public void move(){
        if(iv.getTranslateX() + speed_x < Const.STAGE_WIDTH - iv.getImage().getWidth() && iv.getTranslateX() + speed_x > 0){
            iv.setTranslateX(iv.getTranslateX() + speed_x);
            detector.setTranslateX(detector.getTranslateX() + speed_x);
        } else {
            iv.setScaleX(iv.getScaleX() * (-1));
            speed_x *= -1;
        }
    }

    public ImageView getIv() {
        return iv;
    }

    public static int indexOf(Layer l){
        for(Monster m : monsters) if(m.getL1().equals(l) || m.getL2().equals(l)) return monsters.indexOf(m);
        return -1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public double getSpeed_x() {
        return speed_x;
    }

    public void setSpeed_x(double speed_x) {
        this.speed_x = speed_x;
    }

    public void setDetector(Shape detector) {
        this.detector = detector;
    }

    public Shape getDetector(){
        return detector;
    }

    public Layer getL1() {
        return l1;
    }

    public void setL1(Layer l1) {
        this.l1 = l1;
    }

    public Layer getL2() {
        return l2;
    }

    public void setL2(Layer l2) {
        this.l2 = l2;
    }

    public static double getHeight(){
        return 2*Const.LAYER_HEIGHT[Game.getLvl() - 1];
    }

    public static final int STATIONARY = 0;
    public static final int MOVING_BAT = 1;
    public static final int MOVING_DRAGON = 2;
    public static final int BLACK_HOLE = 3;
}
