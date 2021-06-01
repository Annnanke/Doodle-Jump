package Basics;

import Main.Game;
import Models.Layer;
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
        type = randomiseAnalogue(0,1,2,4);// all except for cracked (former can be deleted)
       for(Layer l : all) {
            if (Math.abs(l.getPlatformY() - (y - 2*Layer.offset - Const.PLATFORM_HEIGHT)) < Const.DOODLER_HEIGHT_OF_JUMP
                && l.getPlatform().getType() != Platform.CRACKED
                && l.getPlatformY() != y) {
                type = randomiseForType(1); //"all" - can be deleted
                type = randomiseAnalogue(0,1,2,3,4);
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

    public static int randomiseAnalogue(int... types){
        double prob = Math.random() * probSum(types);
        double sum;
        for(int i = 0; i < types.length; i++){
            sum = 0;
            for (int j = 0; j <= i; j++) sum += Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][types[j]];
            if(prob < sum) return types[i];
        }
        return -1;
    }

    public static double probSum(int... types){
        double res = 0;
        for(int prob : types) res += Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][prob];
        return res;
    }
}
