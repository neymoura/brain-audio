package com.ioraptor.brain_audio.media.component;

import com.ioraptor.brain_audio.player.Player;
import javafx.scene.media.MediaPlayer;

/**
 * @author Ney Moura
 * @since 01/04/2017
 */
public class MediaPlayerComponent {

    public void update(Player player){

        MediaPlayer mediaPlayer = player.getMediaPlayer();

        if (mediaPlayer != null) {
            mediaPlayer.setVolume(player.getFocus());
        }

    }

}
