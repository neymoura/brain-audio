package com.ioraptor.brain_audio.particles.component;

import com.ioraptor.brain_audio.particles.Particle;
import com.ioraptor.brain_audio.particles.emitters.Emitter;
import com.ioraptor.brain_audio.player.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Ney Moura
 * @since 27/03/2017
 */
public class ParticleComponent {

    private ArrayList<Particle> particles = new ArrayList<>();

    public void update(GraphicsContext context, Player player) {

        Emitter emitter = player.getEmitter();
        Point2D position= player.getPosition();
        double focus = player.getFocus();

        particles.addAll(emitter.emit(position.getX(),position.getY(), focus));

        for (Iterator<Particle> it = particles.iterator(); it.hasNext(); ){
            Particle p = it.next();
            p.update();

            if(!p.isAlive()){
                it.remove();
                continue;
            }

            p.render(context);

        }

    }

}
