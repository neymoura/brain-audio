package com.ioraptor.brain_audio.focus.component;

import com.ioraptor.brain_audio.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * @author Ney Moura
 * @since 01/04/2017
 */
public class FocusComponent {

    public void update(GraphicsContext context, Player player){

        String focusString = "Focus: " + new Double(player.getFocus() * 100).intValue() + "%";

        context.setFill(Color.WHITE);
        context.fillText(focusString, player.getPosition().getX()-30, player.getPosition().getY()+30);

    }

}
