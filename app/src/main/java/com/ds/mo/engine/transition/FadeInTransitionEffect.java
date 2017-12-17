package com.ds.mo.engine.transition;

import android.graphics.Paint;
import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.common.Helper;
import com.ds.mo.engine.common.Tween;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Screen;

/**
 * OPAQUE to TRANSPARENT (1 -> 0)
 * Created by Mo on 09/11/2017.
 */

public class FadeInTransitionEffect extends TransitionEffect {
    protected boolean finished = false;
    private float currentTime = 0;

    private Paint paint;

    public FadeInTransitionEffect(float duration, int fadeColor) {
        super(duration);
        alpha = TransitionEffect.MAX;

        paint = new Paint();
        paint.setColor(fadeColor);
        paint.setAlpha(alpha);
    }

    @Override
    void update(float deltaTime) {
        currentTime += deltaTime;
        if (alpha <= TransitionEffect.MIN) {
            alpha = TransitionEffect.MIN;
            finished = true;
            Log.d("FadeIn", "FINISHED");
            return;
        }
//        old update
//        alpha = Math.max(0, alpha - Math.max((alpha / 10), 0.005f));

        //Tween update
        alpha = (int) Tween.linearTween(currentTime, TransitionEffect.MAX, -TransitionEffect.MAX, duration);
        alpha = Helper.Clamp(alpha, 0, 255);
        paint.setAlpha(alpha);
    }

    @Override
    void draw(Graphics g, Screen current, Screen next, float deltaTime) {
        next.draw(deltaTime);
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
