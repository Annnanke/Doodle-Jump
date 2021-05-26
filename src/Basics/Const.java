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
        public static final double DETECTOR_OPACITY = 0;
        public static final double DETECTOR_HEIGHT = 8 ;


        //PLATFORM
        public static final int PLATFORM_WIDTH = 60;
        public static final int PLATFORM_HEIGHT = 12;
        public static final double PLATFORM_V = DOODLER_V0_Y;
        public static final double[] LAYER_HEIGHT = {PLATFORM_HEIGHT + 36, PLATFORM_HEIGHT + 86};
        public static final int LOWER_PLATFORM_OFFSET = STAGE_HEIGHT - 100;




        //IMAGES
        public static final Image CAT_NORMAL = new Image(new File("src/Images/cat_normal.png").toURI().toString(),
                DOODLER_WIDTH, DOODLER_HEIGHT, true, true);

        public static final Image CAT_NORMAL_REFL = new Image(new File("src/Images/cat_normal_refl.png").toURI().toString(),
                DOODLER_WIDTH, DOODLER_HEIGHT, true, true);

        public static final Image PLATFORM_1 = new Image(new File("src/Images/platform1.png").toURI().toString()
                ,PLATFORM_WIDTH, PLATFORM_HEIGHT, true, true);

        public static final Image PLATFORM_1_BROKEN = new Image(new File("src/Images/platform1_broken.png").toURI().toString()
                ,PLATFORM_WIDTH, PLATFORM_HEIGHT, true, true);

        public static final Image TRAMPOLINE = new Image(new File("src/Images/trampoline.png").toURI().toString()
                ,PLATFORM_WIDTH, PLATFORM_HEIGHT, true, true);

        public static final Image BACKGROUND = new Image(new File("src/Images/background.jpg").toURI().toString());
    }


