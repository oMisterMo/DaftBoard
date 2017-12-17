package com.ds.mo.engine.transition;

import android.graphics.Paint;
import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.common.Helper;
import com.ds.mo.engine.common.Tween;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Screen;

/**
 * TRANSPARENT to OPAQUE (0 -> 1)
 * Created by Mo on 09/11/2017.
 */

public class FadeOutTransitionEffect extends TransitionEffect {
    protected boolean finished = false;
    private float currentTime = 0;

    private Paint paint;

    public FadeOutTransitionEffect(float duration, int fadeColor){
        super(duration);
        alpha = TransitionEffect.MIN;
        paint = new Paint();
        paint.setColor(fadeColor);
        paint.setAlpha(alpha);
    }

    @Override
    void update(float deltaTime) {
        currentTime += deltaTime;
        if(alpha >= TransitionEffect.MAX){
            alpha = TransitionEffect.MAX;
            finished = true;
            Log.d("FadeOut", "FINISHED");
            return;
        }
//        old update
//        alpha = Math.min(1, alpha + Math.max(((1 - alpha) / 10), 0.005f));

        //Tween update (if tween end -> finished = true)
        alpha = (int) Tween.linearTween(currentTime, TransitionEffect.MIN, TransitionEffect.MAX, duration);
        alpha = Helper.Clamp(alpha, 0, 255);
        paint.setAlpha(alpha);
    }

    @Override
    void draw(Graphics g, Screen current, Screen next, float deltaTime) {
        current.draw(deltaTime);
        g.drawRect(0, 0, AndroidGame.GAME_WIDTH, AndroidGame.GAME_HEIGHT, paint);
    }

    @Override
    int getAlpha() {
        return Helper.Clamp(alpha, 0, 1);
    }

    @Override
    boolean isFinished() {
        return finished;
    }
}
