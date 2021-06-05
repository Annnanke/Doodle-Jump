package Menu;

import Basics.Const;
import Main.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
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
    private Label bag = new Label();

    public RadioButton soundOn, soundOff, themeCoffee, themeMilk;
    public static int chosenLvl;

    @Override
    public void start(Stage primaryStage) throws Exception{

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

    public void shop (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        Parent rootP = FXMLLoader.load(getClass().getResource("shop.fxml"));
        ShopScene = new Scene(rootP);
        menuStage.setScene(ShopScene);
        ShopScene.getStylesheets().add(shopStyle);
        menuStage.setX(100);
        menuStage.setY(50);
        menuStage.show();
        bag.setText("Bag: "+ bagOfMagic);

    }

    public void catChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();

    }
    public void cosmoChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((coins - 5000) >= 0){
            coins -= 5000;
        }
    }
    public void ghostChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        if ((coins - 1000) >= 0){
            coins -= 1000;
        }
    }
    public void normalBulletChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();

    }
    public void coolBulletChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
    }
    public void magicWandBought (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
        bagOfMagic ++;
        bag.setText("Bag: "+ bagOfMagic);

    }
    public void bagEd(){
        bag.setText("Bag: "+ bagOfMagic);
    }
    public void normalPlatformChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
    }
    public void coolPlatformChosen (ActionEvent e) throws Exception {
        Sounds.playSoundButton();
    }
    public void back (ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        start(menuStage);
    }
    public void exit (ActionEvent e) throws Exception {

        Sounds.playSoundButton();

        menuStage.close();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
