package com.ds.mo.engine.transition;

import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Screen;

/**
 * Created by Mo on 14/11/2017.
 */

public class WaitEffect extends TransitionEffect {
    protected boolean finished = false;
    private float currentTime = 0;
    private int fadeColor;

    public WaitEffect(float duration, int fadeColor){
        super(duration);
        alpha = TransitionEffect.MAX;
        this.fadeColor = fadeColor;
    }
    @Override
    void update(float deltaTime) {
        currentTime += deltaTime;
        if(currentTime >= duration){
            finished = true;
            Log.d("WaitEffect", "FINISHED");
            return;
        }
    }

    @Override
    void draw(Graphics g, Screen current, Screen next, float deltaTime) {
//        g.clear(fadeColor);
        g.drawRect(0, 0, AndroidGame.GAME_WIDTH, AndroidGame.GAME_HEIGHT, fadeColor);
    }

    @Override
    public int getAlpha(){
        return alpha;
    }

    @Override
    boolean isFinished() {
        return finished;
    }
}
