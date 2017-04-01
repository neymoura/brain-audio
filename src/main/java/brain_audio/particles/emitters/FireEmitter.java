package com.ioraptor.brain_audio.particles.emitters;

import com.ioraptor.brain_audio.particles.Particle;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ney Moura
 * @since 27/03/2017
 */
public class FireEmitter extends Emitter {

    @Override
    public List<Particle> emit(double x, double y, double focus) {

        List<Particle> particles = new ArrayList<>();

        int numParticles = 1;

        for (int i = 0; i < numParticles; i++) {
            Particle p = new Particle(x, y, new Point2D((Math.random() - 0.5) * 0.65, Math.random() * -2),
                    20 * focus,1.0,
                    Color.rgb(0, new Double(14*focus).intValue(), new Double(215*focus).intValue()),
                    BlendMode.ADD);
            particles.add(p);
        }

        return particles;
    }



}
