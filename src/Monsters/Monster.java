package Monsters;

import Basics.Const;
import Main.Game;
import Models.Layer;
import Models.LayerGroup;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Monster extends LayerGroup {

    public static ArrayList<Monster> monsters = new ArrayList<>();
    private Layer l1, l2;
    private int type;
    private ImageView iv;
    private double speed_x;

    public Monster(double y, int type, Game root) {
        super(2);
        monsters.add(this);

        l1 = new Layer(y);
        l2 = new Layer(y + Const.LAYER_HEIGHT[Game.getLvl() - 1]);
        l1.setToDisappear(true);
        l2.setToDisappear(true);
        l1.getPlatform().setDetectable(false);
        l2.getPlatform().setDetectable(false);
        add(l1);
        add(l2);


        this.type = type;

        iv =  new ImageView(Const.MONSTERS[type]);
        iv.setTranslateY(y);
        iv.setTranslateX(new Random().nextInt((int) (Const.STAGE_WIDTH - iv.getImage().getWidth())));

        switch (type){
            case STATIONARY :
                speed_x = 0;
                if(iv.getTranslateX() + iv.getImage().getWidth()/2 > Const.STAGE_WIDTH/2) iv.setScaleX(-1);
                break;
            case MOVING_BAT:
                speed_x = Const.BAT_SPEED_X[Game.getLvl() - 1];
                l1.getPlatform().remove();
                l2.getPlatform().remove();
                iv.setScaleX(-1);
                break;
            case MOVING_DRAGON:
                speed_x = Const.DRAGON_SPEED_X[Game.getLvl() - 1];
                l1.getPlatform().remove();
                l2.getPlatform().remove();
                iv.setScaleX(-1);
                break;
            case BLACK_HOLE :
                speed_x = 0;
                l1.getPlatform().remove();
                l2.getPlatform().remove();
                break;
        }
        root.add(iv);
        l1.setConnectedImage(iv);
    }

    public void move(){
        if(iv.getTranslateX() + speed_x < Const.STAGE_WIDTH - iv.getImage().getWidth() && iv.getTranslateX() + speed_x > 0){
            iv.setTranslateX(iv.getTranslateX() + speed_x);
        } else {
            iv.setScaleX(iv.getScaleX() * (-1));
            speed_x *= -1;
        }
    }

    public static int indexOf(Layer l){
        for(Monster m : monsters) if(m.getL1().equals(l) || m.getL2().equals(l)) return monsters.indexOf(m);
        return -1;
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
