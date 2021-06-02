package Basics;

import Main.Game;
import javafx.scene.image.Image;

import java.io.File;

public class Const {

        //STAGE
        public static final int STAGE_WIDTH = 500;
        public static final int STAGE_HEIGHT = 700;

        //GRAVITY
        public static final double GRAVITY = -0.45;

        //DOODLER
        public static final int DOODLER_WIDTH = 70;
        public static final int DOODLER_HEIGHT = 70;
        public static final double DOODLER_V0_X = 10;
        public static final double DOODLER_V0_Y = 15;
        public static final double PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_RIGHT = 0.7;
        public static final double PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_LEFT = 1 - PROPORTION_OF_DISAPPEARANCE_BEHIND_WALL_RIGHT;
        public static final double DOODLER_HEIGHT_OF_JUMP = -DOODLER_V0_Y * DOODLER_V0_Y / (2 * GRAVITY);
        public static final double MIN_SPEED_FOR_JUMP = 3; //doodler can't jump when his speed_y is less then this value
        public static final double VICTORY_SHIFT_SPEED = 3;
        public static final double VICTORY_DOWN_SPEED = 3;

        //DETECTORS
        public static final double DETECTOR_OPACITY = 0;
        public static final double DETECTOR_HEIGHT = 10 ;


        //PLATFORM
        public static final int PLATFORM_WIDTH = 60;
        public static final int PLATFORM_HEIGHT = 12;
        public static final double PLATFORM_V = DOODLER_V0_Y;
        public static final double[] LAYER_HEIGHT = {PLATFORM_HEIGHT + 36, PLATFORM_HEIGHT + 36, PLATFORM_HEIGHT + 200, PLATFORM_HEIGHT + 200
                                                     , PLATFORM_HEIGHT + 200};
        public static final int LOWER_PLATFORM_OFFSET = STAGE_HEIGHT - 150;
        public static final double[][] PROBABILITIES_OF_PLATFORM_TYPES = {{0.5, 0.1, 0.05, 0.3, 0},
                                                                          {0.5, 0.15, 0.04, 0.3, 0.01},
                                                                          {0.5, 0.15, 0.04, 0.3, 0.01},
                                                                          {0.5, 0.15, 0.04, 0.3, 0.01},
                                                                          {0.5, 0.15, 0.04, 0.3, 0.01}}; //{DEFAULT, MOVING, TRAMPOLINE, CRACKED, JETPACKED}
        public static final int POST_CRACKED_TIME_OF_LIFE = 30;
        public static final double[] HORIZONTAL_SPEED = {2, 2, 2, 2, 2}; //only for moving platforms
        public static final double TRAMPOLINE_HEIGHT = STAGE_HEIGHT;
        public static final double TRAMPOLINE_V_0 = Math.sqrt(-2 * GRAVITY * TRAMPOLINE_HEIGHT);
        public static final double JETPACK_HEIGHT = 3 * STAGE_HEIGHT;
        public static final double JETPACK_V_0 = Math.sqrt(-2 * GRAVITY * JETPACK_HEIGHT);
        public static final double VICTORY_SPEED_OF_GOLDEN_PLATFORM = -2;


        //GAME GENERAL:
        public static final double HEIGHT_OF_LOSS_FALL = 2*STAGE_HEIGHT + 2*LAYER_HEIGHT[Game.getLvl() - 1];
        public static final int HEIGHT_1 = 1000;
        public static final int VICTORY_POSITION_X = 50;

        //SCORE BAR

        public static final double SCOREBAR_WIDTH = STAGE_WIDTH;
        public static final double SCOREBAR_HEIGHT = STAGE_HEIGHT * 0.08;

        //IMAGES
        public static final int CHOSEN_CHARACTER = 0;
        public static final Image[] CHARACTER_NORMAL = {new Icon("src/Images/cat_normal.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                        new Icon("src/Images/cosmo_normal.png", DOODLER_WIDTH + 20, DOODLER_HEIGHT + 20),
                                                        new Icon("src/Images/ghost_normal.png", DOODLER_WIDTH, DOODLER_HEIGHT)};

        public static final Image[] CHARACTER_JUMP = {new Icon("src/Images/cat_jump.png", DOODLER_WIDTH + 20, DOODLER_HEIGHT + 20),
                                                      new Icon("src/Images/cosmo_jump.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                      new Icon("src/Images/ghost_jump.png", DOODLER_WIDTH + 15, DOODLER_HEIGHT + 15)};

        public static final Image[] CHARACTER_WITH_JETPACK = {new Icon("src/Images/cat_fly.png", DOODLER_WIDTH + 20, DOODLER_HEIGHT + 20),
                                                      new Icon("src/Images/cosmo_fly.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                      new Icon("src/Images/ghost_fly.png", DOODLER_WIDTH + 10, DOODLER_HEIGHT + 10)};

        public static final Image[] PLATFORM_1 = {new Icon("src/Images/platform1.png", PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/platform1.png", PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/platform1.png", PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/platform1.png", PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/platform1.png", PLATFORM_WIDTH, PLATFORM_HEIGHT)};

        public static final Image[] PLATFORM_1_BROKEN = {new Icon("src/Images/platform1_broken.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                         new Icon("src/Images/platform1_broken.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                         new Icon("src/Images/platform1_broken.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                         new Icon("src/Images/platform1_broken.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                         new Icon("src/Images/platform1_broken.png",PLATFORM_WIDTH, PLATFORM_HEIGHT)};

        public static final Image[] PLATFORM_1_POST_BROKEN = {new Icon("src/Images/post_brokenpallet1.png",100, 37),
                                                              new Icon("src/Images/post_brokenpallet1.png",100, 37),
                                                              new Icon("src/Images/post_brokenpallet1.png",100, 37),
                                                              new Icon("src/Images/post_brokenpallet1.png",100, 37),
                                                              new Icon("src/Images/post_brokenpallet1.png",100, 37)};

        public static final Image[] TRAMPOLINE = {new Icon("src/Images/trampoline.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/trampoline.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/trampoline.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/trampoline.png",PLATFORM_WIDTH, PLATFORM_HEIGHT),
                                                  new Icon("src/Images/trampoline.png",PLATFORM_WIDTH, PLATFORM_HEIGHT)};

        public static final Image[] GOLDEN = {new Icon("src/Images/golden_platform.png",PLATFORM_WIDTH + 6, PLATFORM_HEIGHT + 6),
                                              new Icon("src/Images/golden_platform.png",PLATFORM_WIDTH + 6, PLATFORM_HEIGHT + 6),
                                              new Icon("src/Images/golden_platform.png",PLATFORM_WIDTH + 6, PLATFORM_HEIGHT + 6),
                                              new Icon("src/Images/golden_platform.png",PLATFORM_WIDTH + 6, PLATFORM_HEIGHT + 6),
                                              new Icon("src/Images/golden_platform.png",PLATFORM_WIDTH + 6, PLATFORM_HEIGHT + 6)};

        public static final Image[] BACKGROUND = {new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg")};

        public static final Image JETPACK = new Icon("src/Images/jackpack_on_platform.png", PLATFORM_WIDTH*4, PLATFORM_HEIGHT*4);

        public static final Image PAUSE = new Icon("src/Images/pause.png", Const.SCOREBAR_WIDTH * 0.05, Const.SCOREBAR_WIDTH * 0.05);


        //public static final Image GROUND = new Icon("src/Images/ground.png",Const.STAGE_WIDTH, Const.STAGE_WIDTH*385/1201);
    }


