package com.ioraptor.brain_audio.particles;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Ney Moura
 * @since 27/03/2017
 */
public class ParticlesApp extends Application {

    private static final double SCREEN_WIDTH = 300;
    private static final double SCREEN_HEIGHT = 300;

    private static final double CORRECTION_25 = 0.25;
    private static final double CORRECTION_75 = 0.75;

    private Emitter emitter = new FireEmitter();

    private ArrayList<Particle> particles = new ArrayList<>();

    private GraphicsContext g;

    private void onUpdate() {

        g.setGlobalAlpha(1.0);
        g.setGlobalBlendMode(BlendMode.SRC_OVER);
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        //Particle 1 (top left)
        particles.addAll(emitter.emit(SCREEN_WIDTH*CORRECTION_25,SCREEN_HEIGHT*CORRECTION_25));

        //Particle 2 (top right)
        particles.addAll(emitter.emit(SCREEN_WIDTH*CORRECTION_75,SCREEN_HEIGHT*CORRECTION_25));

        //Particle 3 (bottom left)
        particles.addAll(emitter.emit(SCREEN_WIDTH*CORRECTION_25,SCREEN_HEIGHT*CORRECTION_75));

        //Particle 4 (bottom right)
        particles.addAll(emitter.emit(SCREEN_WIDTH*CORRECTION_75,SCREEN_HEIGHT*CORRECTION_75));

        for (Iterator<Particle> it = particles.iterator(); it.hasNext(); ){
            Particle p = it.next();
            p.update();

            if(!p.isAlive()){
                it.remove();
                continue;
            }

            p.render(g);

        }
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        g = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };

        timer.start();

    }

}
