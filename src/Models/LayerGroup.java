package Models;


import Basics.Const;
import Main.Game;

import java.util.ArrayList;

public class LayerGroup {

    private ArrayList<Layer> layers;
    private static ArrayList<LayerGroup> allGroups = new ArrayList<>();
    private int capacity;

    public LayerGroup(int capacity) {
        this.capacity = capacity;
        layers = new ArrayList<>();
        allGroups.add(this);
    }


    public void add(Layer l){
        if(layers.size() + 1 <= capacity) {
            layers.add(l);
        }
        else System.err.println("Size of the layerGroup can't be bigger then its capacity");
    }

    public Layer getTopOfGroup(){
        if(layers.size() == 0) return null;
        Layer res = layers.get(0);
        for (Layer l : layers) if(l.getY() < res.getY()) res = l;
        return res;
    }
}
