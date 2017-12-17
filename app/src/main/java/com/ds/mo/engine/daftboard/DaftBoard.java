package com.ds.mo.engine.daftboard;

import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.framework.Screen;

/**
 * MainActivity
 * Created by Mo on 05/07/2017.
 */
public class DaftBoard extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        Log.d("DrawActivity", "*overridden method 'get start screen'*");
        return new LoadingScreen(this);
    }
}
