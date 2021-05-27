package Basics;

import Main.Game;
import Models.Platform;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private static Game root = Layer.getRoot();
    private static int lvl = Game.getLvl();
    private static ArrayList<Layer> all = Layer.getAll();
    public static final int NUM_OF_PLATFORMS = (int) (Const.STAGE_HEIGHT / Const.LAYER_HEIGHT[lvl - 1]) + 1;

    public static void generatePlatforms(){
        for (int i = NUM_OF_PLATFORMS - 1; i >= 0; i--) new Layer((i - 1) * Const.LAYER_HEIGHT[lvl - 1]);
    }

    public static Platform nextPlatform(double y){
        return new Platform(new Random().nextInt(Const.STAGE_WIDTH - Const.PLATFORM_WIDTH), y, nextType(y), root);
    }

    public static int nextType(double y){
        int type = randomiseForType(1 );//all except for cracked
        for(Layer l : all)
            if(Math.abs(l.getPlatformY() - Const.PLATFORM_HEIGHT - y) < Const.DOODLER_HEIGHT_OF_JUMP) type = randomiseForType(1); //all
            return type;
    }


    public static int randomiseForType(double upper_bound){
        double prob = Math.random() * upper_bound;

        if(prob < Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][0]) return 0;

        if(prob < Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][0] +
                  Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][1]) return 1;

        if(prob < Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][0] +
                  Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][1] +
                  Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][2]) return 2;

        return 3;
    }
}
