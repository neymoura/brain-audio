package com.ioraptor.brain_audio.particles;

import java.util.List;

/**
 * @author Ney Moura
 * @since 27/03/2017
 */
public abstract class Emitter {

    public abstract List<Particle>  emit(double x, double y);

}
