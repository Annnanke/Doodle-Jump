package Basics;


import javafx.scene.image.Image;

import java.io.File;

public class Icon extends Image {

    public Icon(String file, int width, int height){
        super(new File(file).toURI().toString(), width, height, true, true);
    }

    public Icon(String file){
        super(new File(file).toURI().toString());
    }
}
