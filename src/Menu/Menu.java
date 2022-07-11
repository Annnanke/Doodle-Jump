package Menu;

import Basics.Const;
import Main.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

import static Menu.Shop.*;


public class Menu extends Application {
    public static Stage menuStage;
    public static Scene menuScene;
    public static Scene lvlChoosingScene;
    public static Scene SettingScene;
    public static Scene ShopScene;
    public static String style;
    public static String shopStyle;
    public static int soundStatus;
    public static Game game;
    public static boolean lvl2IsUnlocked = false;
    public static boolean lvl3IsUnlocked = false;
    public static boolean lvl4IsUnlocked = false;
    public static boolean lvl5IsUnlocked = false;

    @FXML
    public Label bag = new Label();
    public TextField code = new TextField();
    public Label yourCoins = new Label();
    public RadioButton soundOn, soundOff, themeCoffee, themeMilk;
    public Button locker, ghostPlayer = new Button(), astraunautPlayer  = new Button(), catPlayer  = new Button(), coolPlatform = new Button(), normalBullet  = new Button(), normalPlatform  = new Button(), coolBullet  = new Button(), ghostPlayer1  = new Button(), cosmoPlayer1  = new Button(), coolPlatform1 = new Button(), coolBullet1  = new Button();

    public static int chosenLvl;

    @Override
    public void start(Stage primaryStage) throws Exception{
       initialize();
        menuStage = primaryStage;
        Parent rootD = FXMLLoader.load(getClass().getResource("main.fxml"));
        menuScene = new Scene(rootD);

        if(style == null){
            style = this.getClass().getResource("LightStyling.css").toExternalForm();
            shopStyle = this.getClass().getResource("shopLightStyling.css").toExternalForm();
        }
        menuScene.getStylesheets().add(style);
        Image icon = new Image("Images/cat_jump.png");
        menuStage.setTitle("DoodleJump");
        menuStage.getIcons().add(icon);
        menuStage.setScene(menuScene);
        menuStage.show();


    }

    /**
     * opens lvlChooser
     * @param e - event
     * @throws Exception - exception
     */
    public void play(ActionEvent e) throws Exception {
        Sounds.playSoundButton();

        Parent rootC = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        lvlChoosingScene = new Scene(rootC);
        menuStage.setScene(lvlChoosingScene);
        lvlChoosingScene.getStylesheets().add(style);
        menuStage.show();

    }

    /**
     * opens lvl 1
     * @param e - event
     */
    public void lvl1(ActionEvent e)  {

        Sounds.playSoundButton();

        Game game = new Game(1);
        this.game = game;
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 1;
    }

    /**
     * opens lvl2
     * @param e - event
     * @throws Exception - exception
     */
    public void lvl2(ActionEvent e) throws Exception {

        if(!lvl2IsUnlocked) return;

        Sounds.playSoundButton();

        Game game = new Game(2);
        this.game = game;
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 2;
    }

    /**
     * opens lvl3
     * @param e - event
     * @throws Exception - exception
     */
    public void lvl3(ActionEvent e) throws Exception {

        if(!lvl3IsUnlocked) return;

        Sounds.playSoundButton();

        Game game = new Game(3);
        this.game = game;
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 3;
    }

    /**
     * opens lvl4
     * @param e - event
     * @throws Exception - exception
     */
    public void lvl4(ActionEvent e) throws Exception {

        if(!lvl4IsUnlocked) return;

        Sounds.playSoundButton();

        Game game = new Game(4);
        this.game = game;
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 4;
    }

    /**
     * opens lvl5
     * @param e - event
     * @throws Exception - exception
     */
    public void lvl5(ActionEvent e) throws Exception {

        if(!lvl5IsUnlocked) return;

        Sounds.playSoundButton();

        Game game = new Game(5);
        this.game = game;
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 5;
    }

    /**
     * opens settings
     * @param e - event
     * @throws Exception - exception
     */
    public void settings(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Parent rootO = FXMLLoader.load(getClass().getResource("settingsMenu.fxml"));
        SettingScene = new Scene(rootO);
        menuStage.setScene(SettingScene);
        SettingScene.getStylesheets().add(style);
        menuStage.show();
    }
    @FXML
    public void unlocked(ActionEvent e) throws Exception {
        code.setVisible(true);
        Sounds.playSoundButton();
        code.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if (code.getText().equals("admin.coins5000")) {
                    coins += 5000;
                }
                if (code.getText().equals("admin.coins1000")) {
                    coins += 1000;
                }
                if (code.getText().equals("admin.coins10000")) {
                    coins += 10000;
                }
                if (code.getText().equals("admin.coins100")) {
                    coins += 100;
                }
                if(code.getText().equals("admin.unlockAll")){
                    lvl2IsUnlocked = true;
                    lvl3IsUnlocked = true;
                    lvl4IsUnlocked = true;
                    lvl5IsUnlocked = true;
                }
                code.setVisible(false);
            }
        });
    }


    /**
     * getter for sound and theme
     * @param e - event
     * @throws Exception - exception
     */
    public void getSoundAndTheme(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        if(soundOn.isSelected()){
            System.out.println("It's ON");
            soundStatus = 1;
        }
        if(soundOff.isSelected()){
            soundStatus = 2;
        }
        if(themeMilk.isSelected()){
            System.out.println("It's Milk");
            style = this.getClass().getResource("LightStyling.css").toExternalForm();
            shopStyle = this.getClass().getResource("shopLightStyling.css").toExternalForm();
        }
        if(themeCoffee.isSelected()){
            style = this.getClass().getResource("DarkStyling.css").toExternalForm();
            shopStyle = this.getClass().getResource("shopDarkStyling.css").toExternalForm();
        }
    }
    @FXML
    public void shop (ActionEvent e) throws Exception {
        initialize();
        Sounds.playSoundButton();
        Parent rootP = FXMLLoader.load(getClass().getResource("shop.fxml"));
        ShopScene = new Scene(rootP);
        menuStage.setScene(ShopScene);
        ShopScene.getStylesheets().add(shopStyle);
        menuStage.show();
        yourCoins.setText( ""+ Shop.coins);
        bag.setText("Bag: "+ bagOfMagic);

    }

    /**
     * buys character Cosmo
     * @param e - event
     * @throws Exception - exception
     */
    public void cosmoBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((Shop.coins - 5000) >= 0){
            Shop.coins  -= 5000;
            initialize();
           astraunautPlayer.setVisible(false);
           cosmoPlayer1.setText("chosen");
           cosmoPlayer1.setVisible(true);
           catPlayer.setText("Cat");
           ghostPlayer1.setText("Ghost");
           cosmoGotten = 1;
            typeOfGG = 3;

        }else{
            yourCoins.setTextFill(Color.RED);
        }
    }
    /**
     * buys character Ghost
     * @param e - event
     * @throws Exception - exception
     */
    public void ghostBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((Shop.coins  - 1000) >= 0){
            Shop.coins  -= 1000;
            initialize();
            ghostPlayer.setVisible(false);
            ghostPlayer1.setText("chosen");
            ghostPlayer1.setVisible(true);
            catPlayer.setText("Cat");
            cosmoPlayer1.setText("Cosmo");
            ghostGotten = 1;
            typeOfGG = 2;
        }else{
            yourCoins.setTextFill(Color.RED);
        }
    }

    /**
     * Buys cool bullet
     * @param e - event
     * @throws Exception - exception
     */
    public void coolBulletBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((Shop.coins - 1000) >= 0){
            Shop.coins  -= 1000;
            initialize();
            coolBullet.setVisible(false);
            coolBullet1.setVisible(true);
            coolBullet1.setText("chosen");
            normalBullet.setText("Simple");
            coolBulletGotten = 1;
            typeOfBullet = 2;
            //
            //
        }else{
        yourCoins.setTextFill(Color.RED);
    }
    }

    /**
     * buys cool platform
     * @param e - event
     * @throws Exception - exception
     */
    public void coolPlatformBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((Shop.coins  - 200) >= 0){
            Shop.coins  -= 200;
            initialize();
            coolPlatform.setVisible(false);
            coolPlatform1.setVisible(true);
            coolPlatform1.setText("chosen");
            normalPlatform.setText("Simple");
            coolPlatformGotten = 1;
            typeOfPlatform = 2;
            ///
            ///
        } else {
            yourCoins.setTextFill(Color.RED);
        }
    }

    /**
     * chooses cat
     * @param e - event
     * @throws Exception - exception
     */
    public void catChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        catPlayer.setText("chosen");
        ghostPlayer1.setText("Ghost");
        cosmoPlayer1.setText("Cosmo");
        typeOfGG = 1;
        ///
    }

    /**
     * chooses cosmo
     * @param e - event
     * @throws Exception - exception
     */
    public void cosmoChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        cosmoPlayer1.setText("chosen");
        ghostPlayer1.setText("Ghost");
        catPlayer.setText("Cat");
        typeOfGG = 3;
        ///
    }

    /**
     * chooses ghost
     * @param e - event
     * @throws Exception - exception
     */
    public void ghostChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        catPlayer.setText("Cat");
        ghostPlayer1.setText("chosen");
        cosmoPlayer1.setText("Cosmo");
        typeOfGG = 2;
        ///
    }

    /**
     * chooses normal bullets
     * @param e - event
     * @throws Exception - exception
     */
    public void normalBulletChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        normalBullet.setText("chosen");
        coolBullet1.setText("Cool");
        typeOfBullet = 1;

    }

    /**
     * chooses cool bullets
     * @param e - event
     * @throws Exception - exception
     */
    public void coolBulletChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        coolBullet1.setText("chosen");
        normalBullet.setText("Simple");
        typeOfBullet = 2;
    }

    /**
     * Buys magic wand
      * @param e - event
     * @throws Exception - exception
     */
    public void magicWandBought (ActionEvent e) throws Exception {
        if ((Shop.coins  - 100) >= 0) {
            Sounds.playSoundButton();
            Shop.coins -= 100;
            initialize();
            bagOfMagic++;
            bag.setText("Bag: " + bagOfMagic);
        }else{
            yourCoins.setTextFill(Color.RED);
        }

    }

    /**
     * chooses normal platforms
     * @param e - event
     * @throws Exception - exception
     */
    public void normalPlatformChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        normalPlatform.setText("chosen");
        coolPlatform1.setText("Cool");
        typeOfPlatform = 1;
    }

    /**
     * chooses cool platforms
     * @param e - event
     * @throws Exception - exception
     */
    public void coolPlatformChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        normalPlatform.setText("Simple");
        coolPlatform1.setText("chosen");
        typeOfPlatform =2;
    }

    /**
     * moves back from lvlChooser to menu
     * @param e - event
     * @throws Exception - exception
     */
    public void back (ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        start(menuStage);
    }

    /**
     * exits game
     * @param e - event
     * @throws Exception - exception
     */
    public void exit (ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        menuStage.close();
    }
    @FXML
    public void initialize(){
        code.setVisible(false);
        if(typeOfBullet==2){
            coolBullet1.setText("chosen");
            normalBullet.setText("Simple");
        }
        if(typeOfBullet==1){
            normalBullet.setText("chosen");
            coolBullet1.setText("Cool");
        }
        if(typeOfPlatform==2){
            coolPlatform1.setText("chosen");
            normalPlatform.setText("Simple");
        }
        if(typeOfPlatform==1){
            normalPlatform.setText("chosen");
            coolPlatform1.setText("Cool");
        }
        if (typeOfGG == 1){
            catPlayer.setText("chosen");
            ghostPlayer1.setText("Ghost");
            cosmoPlayer1.setText("Cosmo");
        }
        if (typeOfGG == 2){
            ghostPlayer1.setText("chosen");
            cosmoPlayer1.setText("Cosmo");
            catPlayer.setText("Cat");
        }
        if (typeOfGG == 3){
            cosmoPlayer1.setText("chosen");
            ghostPlayer1.setText("Ghost");
            catPlayer.setText("Cat");
        }
        if(coolBulletGotten==1){
            coolBullet.setVisible(false);
        }
        if(coolPlatformGotten==1){
            coolPlatform.setVisible(false);
        }
        if(ghostGotten==1){
            ghostPlayer.setVisible(false);
        }
        if(cosmoGotten==1){
           astraunautPlayer.setVisible(false);
        }
        yourCoins.setText( ""+ Shop.coins);
        bag.setText("Bag: "+ bagOfMagic);
    }

    /**
     * Turns diamonds into coins
     */
    public static void diamandsIntoCoins(){
        Shop.coins += diamands*Const.DIAMOND_PICK;
        diamands = 0;
    }

    /**
     * main
     * @param args - args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
