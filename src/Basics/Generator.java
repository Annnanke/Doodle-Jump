package Basics;

import Main.Game;
import Models.Layer;
import Models.LayerGroup;
import Models.Platform;
import Monsters.Monster;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public static final int NUM_OF_PLATFORMS = (int) (Const.STAGE_HEIGHT / Const.LAYER_HEIGHT[Game.getLvl() - 1]) + 2;

    public static void generatePlatforms(){
        for (int i = NUM_OF_PLATFORMS - 2; i >= 0; i--) new Layer((i - 1) * Const.LAYER_HEIGHT[Game.getLvl() - 1]);
    }

    public static Platform nextPlatform(double y, Game root){
        return new Platform(new Random().nextInt(Const.STAGE_WIDTH - Const.PLATFORM_WIDTH), y, nextType(y), root);
    }

    public static int nextType(double y){

        int type =  randomiseAnalogue(0,1,2,4);// all except for cracked (former can be deleted)
        for(Layer l : Layer.all) {
            if (Math.abs(l.getPlatformY() - (y - 2*Layer.offset - Const.PLATFORM_HEIGHT)) < Const.DOODLER_HEIGHT_OF_JUMP
                    && l.getPlatform().getType() != Platform.CRACKED
                    && l.getPlatformY() != y
                    && l.getPlatform().isDetectable()) {
                type = randomiseAnalogue(0,1,2,3,4);
            }
        }
        return type;
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

    public static double probMonsterSum(int... types){
        double res = 0;
        for(int prob : types) res += Const.MONSTER_TYPE_PROBABILITY[Game.getLvl() - 1][prob];
        return res;
    }
}
