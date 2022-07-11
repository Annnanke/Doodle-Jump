package Basics;


import javafx.scene.image.Image;

import java.io.File;

public class Icon extends Image {

    /**
     * basic constructor
     * @param file - file
     * @param width - width
     * @param height - height
     */
    public Icon(String file, double width, double height){
        super(new File(file).toURI().toString(), width, height, true, true);
    }


    /**
     * alternative constructor
     * @param file - file
     */
    public Icon(String file){
        super(new File(file).toURI().toString());
    }


}
