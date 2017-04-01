package com.ioraptor.brain_audio.player;

import com.ioraptor.brain_audio.particles.emitters.Emitter;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * @author Ney Moura
 * @since 01/04/2017
 */
public class Player {

    private Point2D position;

    private Emitter emitter;

    private MediaPlayer mediaPlayer;

    private double focus;

    public Player(String instrument, Emitter emitter, Point2D position) {
        this.emitter = emitter;
        this.position = position;
        loadMedia(instrument);
    }

    private void loadMedia(String instrument){
        if (instrument != null) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL instrumentUrl = classloader.getResource(instrument);
            if (instrumentUrl != null) {
                Media media = new Media(instrumentUrl.toString());
                mediaPlayer = new MediaPlayer(media);
            }
        }
    }

    public void onStart(){
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public Emitter getEmitter() {
        return emitter;
    }

    public Point2D getPosition(){
        return position;
    }

    public double getFocus() {
        return focus;
    }

    public void setFocus(double focus) {
        this.focus = focus;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
