package com.ds.mo.engine.android;

import android.view.MotionEvent;
import android.view.View;

import com.ds.mo.engine.framework.Input.TouchEvent;

import java.util.List;


/**
 * Created by Mo on 29/05/2017.
 */
public class MultiTouchHandler implements TouchHandler {
    @Override
    public boolean isTouchDown(int pointer) {
        return false;
    }

    @Override
    public int getTouchX(int pointer) {
        return 0;
    }

    @Override
    public int getTouchY(int pointer) {
        return 0;
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return null;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
