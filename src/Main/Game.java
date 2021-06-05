package Main;

import Basics.Const;
import Basics.Generator;
import GUI.LossPanelController;
import Menu.Shop;
import Menu.Sounds;
import Models.*;
import Monsters.Monster;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.IOException;


public class Game extends Pane {

    private static int lvl;
    private boolean moving_left, moving_right, canShoot = true, releasedTrigger = true;
    private Doodler player;
    private Scene scene;
    public AnimationTimer timer;
    private ImageView background;
    private static ScoreBar scorebar;
    private Pane lossPanel, victoryPanel;
    private boolean won;
    private int time = 0, start = 0;
    private static boolean isRunning;


    public Game(int lvl){
        super();
        this.lvl = lvl;
        init();
    }


    private void init(){

        isRunning = true;

        setWidth(Const.STAGE_WIDTH);
        setHeight(Const.STAGE_HEIGHT);

        moving_left = false;
        moving_right = false;

        background = new ImageView(Const.BACKGROUND[getLvl() - 1]);
        add(background);


        Line level = new Line(0, Const.LOWER_PLATFORM_OFFSET, Const.STAGE_WIDTH, Const.LOWER_PLATFORM_OFFSET);
        level.setOpacity(Const.DETECTOR_OPACITY);
        getChildren().add(level);

        Monster.reload();
        Layer.reload();
        Layer.setRoot(this);

        player = new Doodler((Const.STAGE_WIDTH - Const.DOODLER_WIDTH)/2,Const.LOWER_PLATFORM_OFFSET - Const.DOODLER_HEIGHT, this);

        Generator.generatePlatforms();

        scorebar = new ScoreBar();
        getChildren().add(scorebar);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {update();}
        };

        timer.start();

        won = false;
        loss = false;

    }

    private void update(){
        time++;
        if(checkForLoss()) return;
        canShoot = time - start > Const.RATE_OF_FIRE;
        doodlersMovement();
        platformsMovement();
        Bullet.move();
        checkForCollision();
        monsterMovement();
        Layer.generateWhenPassed();
        removeDead();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case LEFT:
                    if(!won) moving_left = true;
                    break;
                case RIGHT:
                    if(!won) moving_right = true;
                    break;
                case P :
                    timer.stop();
                    break;
                case SPACE :
                    if(canShoot && releasedTrigger) {
                        player.shoot();
                        start = time;
                        Sounds.playSoundGunSound();
                        releasedTrigger = false;
                    }
                    break;
            }
        });

        this.scene.setOnKeyReleased(e -> {

            switch (e.getCode()){
                case LEFT:
                    moving_left = false;
                    break;
                case RIGHT:
                    moving_right = false;
                    break;
                case P :
                    timer.start();
                    break;
                case SPACE:
                    releasedTrigger = true;
                    break;
            }
        });
    }

    private void removeDead(){
        Platform.removePostCracked();
    }

    public void add(ImageView im){
        if(!getChildren().contains(im)) getChildren().add(im);
    }


    private boolean loss;

    public boolean checkForLoss(){
        if(player.getTranslateY() + Const.DOODLER_HEIGHT > Const.STAGE_HEIGHT || loss){
            if(!loss) Sounds.playSoundGameOver();
            loss = true;
            player.setSpeed_y(Const.DOODLER_LOSS_SPEED);
            if(getChildren().contains(scorebar)) getChildren().remove(scorebar);
            if(!getChildren().contains(lossPanel)){
                try {
                    lossPanel = new FXMLLoader().load(getClass().getResource("../GUI/LossPanel.fxml"));
                    lossPanel.setTranslateY(Const.HEIGHT_OF_LOSS_FALL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getChildren().add(lossPanel);
            }

            if(lossPanel.getTranslateY() + lossPanel.getHeight() + player.getSpeed_y() > Const.STAGE_HEIGHT) {
                for (Layer l : Layer.all) {
                    l.getPlatform().setTranslateY(l.getPlatformY() + player.getSpeed_y());
                    l.getPlatform().moveDetector();
                }
                lossPanel.setTranslateY(lossPanel.getTranslateY() + player.getSpeed_y());
                player.setTranslateY(player.getTranslateY() + player.getSpeed_y() + Const.DOODLER_LOSS_SPEED*0.5);
                for (Monster m : Monster.monsters) {
                    m.getIv().setTranslateY(m.getIv().getTranslateY() + player.getSpeed_y());
                    if(m.getPb() != null) m.getPb().setTranslateY(m.getPb().getTranslateY() + player.getSpeed_y());
                }
                for(Bullet b : Bullet.bullets) b.setTranslateY(b.getTranslateY() + player.getSpeed_y());
            } else {
                lossPanel.setTranslateY(Const.STAGE_HEIGHT - lossPanel.getHeight());
                if(player.getTranslateY() < Const.STAGE_HEIGHT) player.setTranslateY(player.getTranslateY() - 1.5*player.getSpeed_y());
                else {
                    timer.stop();
                    isRunning = false;
                }

            }
            return true;
        }
        return false;
    }

    private void checkForCollision(){
        for (Layer p : Layer.all) {
            if (
                    player.getDetector().getBoundsInParent().intersects(p.getDetector().getBoundsInParent())
                            && player.getSpeed_y() < 0
                            && player.isMoving()
                            && p.isDetectable()
            ) {
                if(p.getPlatform().isStable()) player.setJumpImage(p.getPlatform().getType());
                switch (p.getPlatform().getType()){

                    default :
                        Sounds.playSoundJump();
                        player.setSpeed_y(Const.DOODLER_V0_Y);
                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        }
                        break;

                    case Platform.TRAMPOLINE :
                        if(p.getAdditionalDetector().getBoundsInParent().intersects(player.getDetector().getBoundsInParent())) {
                            p.setMissedTrampoline(false);
                            Sounds.playSoundSuperJump();
                            player.setSpeed_y(Const.TRAMPOLINE_V_0);
                            if(p.getPlatformY() > Const.LOWER_PLATFORM_OFFSET) {
                                landingSpeed = Math.sqrt(-2 * Const.GRAVITY * (p.getPlatformY() - Const.LOWER_PLATFORM_OFFSET));
                            }
                            else landingSpeed = 0;
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        } else {
                            landingSpeed = 0;
                            Sounds.playSoundJump();
                            p.setMissedTrampoline(true);
                            player.setSpeed_y(Const.DOODLER_V0_Y);
                            if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                                p.setPivot(true);
                                landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                            }
                        }
                        break;

                    //VICTORY:
                    case Platform.GOLDEN :
                        if(!won) Sounds.playSoundVictory();
                        won = true;
                        if(getScorebar().getPoints() < Const.HEIGHT_1) getScorebar().setPoints(Const.HEIGHT_1);
                        getChildren().remove(scorebar);
                        player.setScaleX(1);
                        player.setSpeed_y(Const.DOODLER_V0_Y);
                        if(p.getPlatformY() < Const.LOWER_PLATFORM_OFFSET) {
                            p.setPivot(true);
                            landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        }
                        break;

                    case Platform.CRACKED :
                        p.getPlatform().setImage(Const.PLATFORM_1_POST_BROKEN[Game.getLvl() - 1]);
                        break;

                    case Platform.JETPACKED :
                        Sounds.playSoundJetSound();
                        player.setSpeed_y(Const.JETPACK_V_0);
                        if(p.getPlatformY() > Const.LOWER_PLATFORM_OFFSET) {
                            landingSpeed = Math.sqrt(-2 * Const.GRAVITY * (p.getPlatformY() - Const.LOWER_PLATFORM_OFFSET));
                        }
                        else landingSpeed = 0;
                        p.setPivot(true);
                        landing = p.getPlatformY() - Const.DOODLER_HEIGHT;
                        break;
                }

                //Coin pick
                if(p.getPlatform().hasCoin() && p.getPlatform().getCoinOrDiamand().getBoundsInParent().intersects(player.getGeneralDetector().getBoundsInParent())){
                    p.getPlatform().getCoinOrDiamand().setImage(null);
                    p.getPlatform().setCoinOrDiamand(null);
                    Sounds.playSoundCoin();
                    Shop.coins += Const.COIN_PICK;
                }
                if(p.getPlatform().hasDiamond() && p.getPlatform().getCoinOrDiamand().getBoundsInParent().intersects(player.getGeneralDetector().getBoundsInParent())){
                    p.getPlatform().getCoinOrDiamand().setImage(null);
                    p.getPlatform().setCoinOrDiamand(null);
                    Sounds.playSoundDiamondSound();
                    Shop.diamands += Const.DIAMOND_PICK;
                }

            }
        }


        for(Monster m : Monster.monsters){

            if(player.getGeneralDetector().getBoundsInParent().intersects(m.getDetector().getBoundsInParent())){

                switch (m.getType()){
                    case Monster.BLACK_HOLE :
                        player.setSpeed_y(Const.DOODLER_LOSS_SPEED);
                        player.setImage(Const.CHARACTER_LAYS[Const.CHOSEN_CHARACTER]);
                        break;
                    default:
                        player.setSpeed_y(Const.DOODLER_LOSS_SPEED);
                        player.setImage(Const.CHARACTER_LAYS[Const.CHOSEN_CHARACTER]);

                        break;
                }
                if(!loss) Sounds.playSoundGameOver();
                loss = true;
            }

            for (Bullet b : Bullet.bullets) if(b.getBoundsInParent().intersects(m.getDetector().getBoundsInParent())) {
                if(m.getType() != Monster.BLACK_HOLE) m.damage();
                b.setToRemove(true);
            }

        }

        Monster.removeAllToRemove();
        Bullet.removeAllToRemove();
    }

    private void doodlersMovement(){
        if(moving_left) player.moveLeft();
        if(moving_right) player.moveRight();
        player.verticalMovement();
    }

    private void landingMovement(){
        landing -= landingSpeed;
        if(landingSpeed + Const.GRAVITY > 0) landingSpeed += Const.GRAVITY;
    }

    private void platformsMovement(){
        //victory movement
        if(won)
            for(Layer l : Layer.all)
                if(l.getType() == Platform.GOLDEN && l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM >= Const.VICTORY_POSITION_X) {
                    l.getPlatform().setTranslateX(l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM);
                    player.setTranslateX(player.getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM);
                    if(Math.abs(l.getPlatform().getTranslateX() + Const.PLATFORM_WIDTH/2 - Const.DOODLER_WIDTH/2 - player.getTranslateX()) >= Const.VICTORY_SHIFT_SPEED)
                        player.setTranslateX(player.getTranslateX() + Math.signum(l.getPlatform().getTranslateX() + Const.PLATFORM_WIDTH/2 - Const.DOODLER_WIDTH/2 - player.getTranslateX())*Const.VICTORY_SHIFT_SPEED);
                } else if(l.getPlatform().getTranslateX() + Const.VICTORY_SPEED_OF_GOLDEN_PLATFORM < Const.VICTORY_POSITION_X) {
                    if(victoryPanel == null){
                        try {
                            victoryPanel = FXMLLoader.load(getClass().getResource("../GUI/VictoryPanel.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(!getChildren().contains(victoryPanel)){
                        victoryPanel.setTranslateY(Layer.getGoldenPlatform().getTranslateY() - 1.5*Const.STAGE_HEIGHT);
                        getChildren().add(victoryPanel);
                    }



                    if(victoryPanel.getTranslateY() < 0) {
                        for (Layer p : Layer.all)
                            if (p.getType() != Platform.GOLDEN) p.setY(p.getY() + Const.VICTORY_DOWN_SPEED);
                        victoryPanel.setTranslateY(victoryPanel.getTranslateY() + Const.VICTORY_DOWN_SPEED);
                    }
                }

        //horizontal movement
        Platform.moveAllMovingHorizontally();

        //vertical movement
        if(Layer.hasPivot()) {
            if((Layer.getPivot().getPivotType() == Layer.PIVOT_TRAMPOLINE
                    || Layer.getPivot().getPivotType() == Layer.PIVOT_JETPACK) ) landingMovement();
            player.setTranslateY(landing);
            player.setMoving(false);
        } else player.setMoving(true);
        Layer.move();
    }

    public static boolean isRunning() {
        return isRunning;
    }

    private static void monsterMovement(){
        for(Monster m : Monster.monsters) m.move();
    }

    public static ScoreBar getScorebar(){
        return scorebar;
    }

    public static int getLvl() {
        return lvl;
    }

    private double landing, landingSpeed = 0;
}