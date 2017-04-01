package com.ioraptor.brain_audio.main;

import com.ioraptor.brain_audio.focus.component.FocusComponent;
import com.ioraptor.brain_audio.media.component.MediaPlayerComponent;
import com.ioraptor.brain_audio.particles.emitters.FireEmitter;
import com.ioraptor.brain_audio.particles.component.ParticleComponent;
import com.ioraptor.brain_audio.player.Player;
import com.ioraptor.brain_audio.utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @author Ney Moura
 * @since 27/03/2017
 */
public class Main extends Application implements InvalidationListener{

    private static final double DEFAULT_WIDTH = 400;
    private static final double DEFAULT_HEIGHT = 400;

    private static double screenWidth = DEFAULT_WIDTH;
    private static double screenHeight = DEFAULT_HEIGHT;

    private static final double CORRECTION_25 = 0.25;
    private static final double CORRECTION_75 = 0.75;

    private GraphicsContext context;

    private ArrayList<Player> players = new ArrayList<>();

    private ParticleComponent particleComponent;
    private MediaPlayerComponent mediaPlayerComponent;
    private FocusComponent focusComponent;

    private double fakeFocus = 0d;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create the canvas
        Canvas canvas = Utils.createCanvas(screenWidth, screenHeight);

        //Get the GraphicsContext
        this.context = canvas.getGraphicsContext2D();

        //Create the stage
        primaryStage.setScene(Utils.createScene(canvas));
        primaryStage.show();

        //Create components
        particleComponent = new ParticleComponent();
        mediaPlayerComponent = new MediaPlayerComponent();
        focusComponent = new FocusComponent();

        //Create the players

        //Player 1 (top left)
        //width*CORRECTION_25,height*CORRECTION_25
        Player p1 = new Player("1.mp3", new FireEmitter(), new Point2D(screenWidth *CORRECTION_25, screenHeight *CORRECTION_25+35));
        players.add(p1);

        //Player 2 (top right)
        //width*CORRECTION_75,height*CORRECTION_25
        Player p2 = new Player("2.mp3", new FireEmitter(), new Point2D(screenWidth *CORRECTION_75, screenHeight *CORRECTION_25+35));
        players.add(p2);

        //Player 3 (bottom left)
        //width*CORRECTION_25,height*CORRECTION_75
        Player p3 = new Player("3.mp3", new FireEmitter(), new Point2D(screenWidth *0.5, screenHeight *CORRECTION_75+35));
        players.add(p3);

        //Player 4 (bottom right)
        //width*CORRECTION_75,height*CORRECTION_75
        //Player p4 = new Player(null, new FireEmitter(), new Point2D(screenWidth*CORRECTION_75,screenHeight*CORRECTION_75));
        //players.add(p4);

        //Create and start the timer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    onUpdate();
            }
        };

        //Start the players
        for (Player player : players) {
            player.onStart();
        }

        timer.start();

    }

    private void onUpdate(){

        //Draw/Redraw the background
        context.setGlobalAlpha(1.0);
        context.setGlobalBlendMode(BlendMode.SRC_OVER);
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, screenWidth, screenHeight);

        //Update components
        for (Player player : players) {

            //Fake focus logic
            simulateFocus(player);
            //Fake focus logic

            focusComponent.update(context, player);
            particleComponent.update(context, player);
            mediaPlayerComponent.update(player);

        }

    }

    private void simulateFocus(Player player) {
        fakeFocus += 0.001;
        if(fakeFocus >= 1.0){
            fakeFocus = 0;
        }
        player.setFocus(fakeFocus);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void invalidated(Observable observable) {

    }
}
