package Basics;

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

        //DETECTORS
        public static final double DETECTOR_OPACITY = 0;
        public static final double DETECTOR_HEIGHT = 10 ;


        //PLATFORM
        public static final int PLATFORM_WIDTH = 60;
        public static final int PLATFORM_HEIGHT = 12;
        public static final double PLATFORM_V = DOODLER_V0_Y;
        public static final double[] LAYER_HEIGHT = {PLATFORM_HEIGHT + 36, PLATFORM_HEIGHT + 200, PLATFORM_HEIGHT + 86, PLATFORM_HEIGHT + 86
                                                     , PLATFORM_HEIGHT + 86};
        public static final int LOWER_PLATFORM_OFFSET = STAGE_HEIGHT - 150;
        public static final double[][] PROBABILITIES_OF_PLATFORM_TYPES = {{0.5, 0.15, 0.05, 0.3},
                                                                          {0.5, 0.15, 0.05, 0.3},
                                                                          {0.5, 0.15, 0.05, 0.3},
                                                                          {0.5, 0.15, 0.05, 0.3},
                                                                          {0.5, 0.15, 0.05, 0.3}}; //{DEFAULT, MOVING, TRAMPOLINE, CRACKED}
        public static final int POST_CRACKED_TIME_OF_LIFE = 30;
        public static final double[] HORIZONTAL_SPEED = {2, 2, 2, 2, 2}; //only for moving platforms
        public static final double TRAMPOLINE_V_0 = Math.sqrt(-2 * GRAVITY * STAGE_HEIGHT);


        //IMAGES
        public static final int CHOSEN_CHARACTER = 0;
        public static final Image[] CHARACTER_NORMAL = {new Icon("src/Images/cat_normal.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                        new Icon("src/Images/cat_normal.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                        new Icon("src/Images/cat_normal.png", DOODLER_WIDTH, DOODLER_HEIGHT)};

        public static final Image[] CHARACTER_NORMAL_REFL = {new Icon("src/Images/cat_normal_refl.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                       new Icon("src/Images/cat_normal_refl.png", DOODLER_WIDTH, DOODLER_HEIGHT),
                                                       new Icon("src/Images/cat_normal_refl.png", DOODLER_WIDTH, DOODLER_HEIGHT)};

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

        public static final Image[] BACKGROUND = {new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg"),
                                                  new Icon("src/Images/background.jpg")};
    }


