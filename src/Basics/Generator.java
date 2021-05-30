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

        int type = randomiseForType(1 - Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.CRACKED]);//all except for cracked
        for(Layer l : all) {

            if (Math.abs(l.getPlatformY() - (y - 2*Layer.offset - Const.PLATFORM_HEIGHT)) < Const.DOODLER_HEIGHT_OF_JUMP
                && l.getPlatform().getType() != Platform.CRACKED
                && l.getPlatformY() != y) {
                type = randomiseForType(1); //all
            }
        }
            return type;
    }


    public static int randomiseForType(double upper_bound){
        double prob = Math.random() * upper_bound;

        if(prob < Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.DEFAULT]) return 0;

        if(prob < Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.DEFAULT] +
                  Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.MOVING]) return 1;

        if(prob < Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.DEFAULT] +
                  Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.MOVING] +
                  Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][Platform.TRAMPOLINE]) return 2;

        return 3;
    }
}
