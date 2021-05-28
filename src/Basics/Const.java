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

        //DETECTORS
        public static final double DETECTOR_OPACITY = 1;
        public static final double DETECTOR_HEIGHT = 8 ;


        //PLATFORM
        public static final int PLATFORM_WIDTH = 60;
        public static final int PLATFORM_HEIGHT = 12;
        public static final double PLATFORM_V = DOODLER_V0_Y;
        public static final double[] LAYER_HEIGHT = {PLATFORM_HEIGHT + 36, PLATFORM_HEIGHT + 86};
        public static final int LOWER_PLATFORM_OFFSET = STAGE_HEIGHT - 150;
        public static final double[][] PROBABILITIES_OF_PLATFORM_TYPES = {{0.5, 0.15, 0.05, 0.3}}; //{DEFAULT, MOVING, TRAMPOLINE, CRACKED}
        public static final int POST_CRACKED_TIME_OF_LIFE = 30;
        public static final double[] HORIZONTAL_SPEED = {2}; //only for moving platforms
        public static final double TRAMPOLINE_V_0 = Math.sqrt(-2 * GRAVITY * STAGE_HEIGHT);


        //IMAGES
        public static final Image CAT_NORMAL = new Image(new File("src/Images/cat_normal.png").toURI().toString(),
                DOODLER_WIDTH, DOODLER_HEIGHT, true, true);

        public static final Image CAT_NORMAL_REFL = new Image(new File("src/Images/cat_normal_refl.png").toURI().toString(),
                DOODLER_WIDTH, DOODLER_HEIGHT, true, true);

        public static final Image PLATFORM_1 = new Image(new File("src/Images/platform1.png").toURI().toString()
                ,PLATFORM_WIDTH, PLATFORM_HEIGHT, true, true);

        public static final Image PLATFORM_1_BROKEN = new Image(new File("src/Images/platform1_broken.png").toURI().toString()
                ,PLATFORM_WIDTH, PLATFORM_HEIGHT, true, true);

        public static final Image PLATFORM_1_POST_BROKEN = new Image(new File("src/Images/post_brokenpallet1.png").toURI().toString()
                ,100, 37, true, true);

        public static final Image TRAMPOLINE = new Image(new File("src/Images/trampoline.png").toURI().toString()
                ,PLATFORM_WIDTH, PLATFORM_HEIGHT, true, true);

        public static final Image BACKGROUND = new Image(new File("src/Images/background.jpg").toURI().toString());
    }


