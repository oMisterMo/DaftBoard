package com.ds.mo.engine.transition;

import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Screen;

/**
 * Created by Mo on 09/11/2017.
 */

public abstract class TransitionEffect {
    public static final int MIN = 0;
    public static final int MAX = 255;  //Range of the tween

    protected float duration;     //How long the transition should take
    protected int alpha;

    TransitionEffect(float duration) {
        this.duration = duration;
    }

    /**
     * Between 0 (Invisible) - 1 (Full color)
     *
     * @return transparency value
     */

    abstract void update(float deltaTime);

    abstract void draw(Graphics g, Screen current, Screen next, float deltaTime);

    abstract boolean isFinished();

    int getAlpha(){
        return alpha;
    }

}
