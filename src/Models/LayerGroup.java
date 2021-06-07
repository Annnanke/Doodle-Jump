package Models;


import Basics.Const;
import Main.Game;

import java.util.ArrayList;

public class LayerGroup {

    private ArrayList<Layer> layers;
    private int capacity;

    /**
     * basic constructor
     * @param capacity - int
     */
    public LayerGroup(int capacity) {
        this.capacity = capacity;
        layers = new ArrayList<>();
    }


    /**
     * adds a layer to the group
     * @param l - Layer
     */
    public void add(Layer l){
        if(layers.size() + 1 <= capacity) {
            layers.add(l);
        }
        else System.err.println("Size of the layerGroup can't be bigger then its capacity");
    }

}
