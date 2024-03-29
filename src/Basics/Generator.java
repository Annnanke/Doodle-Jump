package Basics;

import Main.Game;
import Models.Layer;
import Models.LayerGroup;
import Models.Platform;
import Monsters.Monster;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public static int NUM_OF_PLATFORMS = (int) (Const.STAGE_HEIGHT / Const.LAYER_HEIGHT[Game.getLvl() - 1]) + 2;

    /**
     * generates platforms in the beginning of game
     */
    public static void generatePlatforms(){
        NUM_OF_PLATFORMS = (int) (Const.STAGE_HEIGHT / Const.LAYER_HEIGHT[Game.getLvl() - 1]) + 2;
        for (int i = NUM_OF_PLATFORMS - 2; i >= 0; i--) new Layer((i - 1) * Const.LAYER_HEIGHT[Game.getLvl() - 1]);
    }

    /**
     * returns next platform
     * @param y - y-coordinate
     * @param root - root
     * @return - platform
     */
    public static Platform nextPlatform(double y, Game root){
        return new Platform(new Random().nextInt(Const.STAGE_WIDTH - Const.PLATFORM_WIDTH), y, nextType(y), root);
    }

    /**
     * generates of platform type randomly
     * @param y - y-coordinate
     * @return - int
     */
    public static int nextType(double y){

        int type =  randomiseAnalogue(0,1);
        if(Const.HEIGHT_1[Game.getLvl() - 1] - Game.getScorebar().getPoints() > 2*Const.TRAMPOLINE_HEIGHT)
            type = randomiseAnalogue(0,1,2);
        if(Const.HEIGHT_1[Game.getLvl() - 1] - Game.getScorebar().getPoints() > 2*Const.JETPACK_HEIGHT)
            type = randomiseAnalogue(0,1,2,4);
        for(Layer l : Layer.all) {
            if (Math.abs(l.getPlatformY() - (y - 2*Layer.offset - Const.PLATFORM_HEIGHT)) < Const.DOODLER_HEIGHT_OF_JUMP
                    && l.getPlatform().getType() != Platform.CRACKED
                    && l.getPlatformY() != y
                    && l.getPlatform().isDetectable()) {
                if(Const.HEIGHT_1[Game.getLvl() - 1] - Game.getScorebar().getPoints() > 2*Const.JETPACK_HEIGHT)
                type = randomiseAnalogue(0,1,2,3,4);
                else if(Const.HEIGHT_1[Game.getLvl() - 1] - Game.getScorebar().getPoints() > 2*Const.TRAMPOLINE_HEIGHT)
                    type = randomiseAnalogue(0,1,2,3);
                else type = randomiseAnalogue(0,1,3);
            }
        }
        return type;
    }


    /**
     * randomises typs
     * @param types - types
     * @return int
     */
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

    /**
     * calculates the sum of probabilities of platform types
     * @param types - types
     * @return - double
     */
    public static double probSum(int... types){
        double res = 0;
        for(int prob : types) res += Const.PROBABILITIES_OF_PLATFORM_TYPES[Game.getLvl() - 1][prob];
        return res;
    }

    /**
     * ramdomises monster type
     * @param types - types
     * @return - int
     */
    public static int randomiseMonster(int... types){
        double prob = Math.random() * probMonsterSum(types);
        double sum;
        for(int i = 0; i < types.length; i++){
            sum = 0;
            for (int j = 0; j <= i; j++) sum += Const.MONSTER_TYPE_PROBABILITY[Game.getLvl() - 1][types[j]];
            if(prob < sum) return types[i];
        }
        return -1;
    }

    /**
     * calculates the sum of monster probabilities
     * @param types - types
     * @return double
     */
    public static double probMonsterSum(int... types){
        double res = 0;
        for(int prob : types) res += Const.MONSTER_TYPE_PROBABILITY[Game.getLvl() - 1][prob];
        return res;
    }
}
