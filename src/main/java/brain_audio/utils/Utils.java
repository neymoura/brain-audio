package com.ioraptor.brain_audio.utils;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 * @author Ney Moura
 * @since 01/04/2017
 */
public class Utils {

    public static Canvas createCanvas(double width, double height){
        return new Canvas(width, height);
    }

    public static Scene createScene(Canvas canvas) {
        Pane root = new Pane();
        root.setPrefSize(canvas.getWidth(), canvas.getHeight());
        root.getChildren().add(canvas);
        return new Scene(root);
    }

}
