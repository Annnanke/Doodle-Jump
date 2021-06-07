package Models;

import Basics.Const;
import Main.Game;
import Menu.Shop;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Bullet extends ImageView {

    private Game root;
    private double speed_x, speed_y;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean toRemove = false;

    /**
     * Basic constructor
     * @param x - x
     * @param y - y
     * @param root - game
     */
    public Bullet(double x, double y, Game root){
        super(Const.BULLETS[Shop.typeOfBullet - 1]);
        double angle = Math.pow(-1, new Random().nextInt()) * Const.DISPERSION * Math.random();
        speed_y = Const.BULLET_SPEED_Y;
        speed_x = speed_y * Math.tan(angle);
        setRotate(-Math.toDegrees(angle));
        this.root = root;
        setTranslateX(x);
        setTranslateY(y);
        bullets.add(this);
        root.add(this);
    }

    /**
     * moves bullets
     */
    public static void move(){
        for(Bullet b : bullets){
            if(b.getTranslateX() > Const.STAGE_WIDTH || b.getTranslateX() < 0
                    || b.getTranslateY() > Const.STAGE_HEIGHT || b.getTranslateY() < 0) {
                b.totallyRemove();
                return;
            }
            b.setTranslateX(b.getTranslateX() + b.speed_x);
            b.setTranslateY(b.getTranslateY() + b.speed_y);
        }
    }


    /**
     * setter for toRemove
     * @param toRemove - toRemove
     */
    public void setToRemove(boolean toRemove) {
        this.toRemove = toRemove;
    }

    /**
     * checks whether there're any to remove
     * @return
     */
    public static boolean hasOnesToRemove(){
        for(Bullet m : bullets) if(m.toRemove) return true;
        return false;
    }

    /**
     * getter for the first to remove
     * @return
     */
    public static Bullet getFirstToRemove(){
        for(Bullet m : bullets) if(m.toRemove) return m;
        return null;
    }

    /**
     * removes all to remove
     */
    public static void removeAllToRemove(){
        while(hasOnesToRemove()) getFirstToRemove().totallyRemove();
    }

    /**
     * totally removes the bullet
     */
    public void totallyRemove(){
        root.getChildren().remove(this);
        if(bullets.contains(this)) bullets.remove(this);
    }
}
