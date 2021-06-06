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


import static Menu.Shop.bagOfMagic;
import static Menu.Shop.coins;


public class Menu extends Application {
    public static Stage menuStage;
    public static Scene menuScene;
    public static Scene lvlChoosingScene;
    public static Scene SettingScene;
    public static Scene ShopScene;
    public static String style;
    public static String shopStyle;
    public static int soundStatus;

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
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();


    }

    public void play(ActionEvent e) throws Exception {
        Sounds.playSoundButton();

        System.out.println("The girl is MAGIC");
        Parent rootC = FXMLLoader.load(getClass().getResource("lvlchooser.fxml"));
        lvlChoosingScene = new Scene(rootC);
        menuStage.setScene(lvlChoosingScene);
        lvlChoosingScene.getStylesheets().add(style);
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();

    }
    public void lvl1(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Game game = new Game(1);
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 1;
    }

    public void lvl2(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Game game = new Game(2);
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 2;
    }

    public void lvl3(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Game game = new Game(3);
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 3;
    }

    public void lvl4(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Game game = new Game(4);
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 4;
    }

    public void lvl5(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Game game = new Game(5);
        Scene scene = new Scene(game, Const.STAGE_WIDTH, Const.STAGE_HEIGHT);
        game.setScene(scene);
        menuStage.setScene(scene);
        menuStage.show();

        chosenLvl = 5;
    }

    public void settings(ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        Parent rootO = FXMLLoader.load(getClass().getResource("settingsMenu.fxml"));
        SettingScene = new Scene(rootO);
        menuStage.setScene(SettingScene);
        SettingScene.getStylesheets().add(style);
        menuStage.setX(100);
        menuStage.setY(50);
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
                code.setVisible(false);
            }
        });
    }


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
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();
        System.out.println(""+ Shop.coins);
        yourCoins.setText( ""+ Shop.coins);
        bag.setText("Bag: "+ bagOfMagic);

    }

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

        }else{
            yourCoins.setTextFill(Color.RED);
        }
    }
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
        }else{
            yourCoins.setTextFill(Color.RED);
        }
    }
    public void coolBulletBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((Shop.coins - 1000) >= 0){
            Shop.coins  -= 1000;
            initialize();
            coolBullet.setVisible(false);
            coolBullet1.setVisible(true);
            coolBullet1.setText("chosen");
            normalBullet.setText("Simple");
        }else{
        yourCoins.setTextFill(Color.RED);
    }
    }
    public void coolPlatformBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((Shop.coins  - 200) >= 0){
            Shop.coins  -= 200;
            initialize();
            coolPlatform.setVisible(false);
            coolPlatform1.setVisible(true);
            coolPlatform1.setText("chosen");
            normalPlatform.setText("Simple");
        }
    }
    public void catChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        catPlayer.setText("chosen");
        ghostPlayer1.setText("Ghost");
        cosmoPlayer1.setText("Cosmo");

    }
    public void cosmoChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        cosmoPlayer1.setText("chosen");
        ghostPlayer1.setText("Ghost");
        catPlayer.setText("Cat");
    }
    public void ghostChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        catPlayer.setText("Cat");
        ghostPlayer1.setText("chosen");
        cosmoPlayer1.setText("Cosmo");
    }
    public void normalBulletChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        normalBullet.setText("chosen");
        coolBullet1.setText("Cool");

    }
    public void coolBulletChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        coolBullet1.setText("chosen");
        normalBullet.setText("Simple");
    }
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
    public void normalPlatformChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        normalPlatform.setText("chosen");
        coolPlatform1.setText("Cool");
    }
    public void coolPlatformChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        normalPlatform.setText("Simple");
        coolPlatform1.setText("chosen");
    }
    public void back (ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        start(menuStage);
    }
    public void exit (ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        menuStage.close();
    }
    @FXML
    public void initialize(){
        code.setVisible(false);
        yourCoins.setText( ""+ Shop.coins);
        bag.setText("Bag: "+ bagOfMagic);
    }

    public static void main(String[] args) {
        launch(args);


    }

}
