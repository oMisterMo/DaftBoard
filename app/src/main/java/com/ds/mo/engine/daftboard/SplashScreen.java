package com.ds.mo.engine.daftboard;

import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.framework.Game;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Screen;
import com.ds.mo.engine.transition.FadeInTransitionEffect;
import com.ds.mo.engine.transition.FadeOutTransitionEffect;
import com.ds.mo.engine.transition.TransitionEffect;
import com.ds.mo.engine.transition.TransitionScreen;

import java.util.ArrayList;

/**
 * Created by Mo on 05/07/2017.
 */

public class SplashScreen extends Screen {
    private final static float TIME_TO_CHANGE = 3f;

    //Logo position and size
    private int dsmoX, dsmoY, dsmoW, dsmoH;
    private float counter;

    private boolean playedOnce = false;

    public SplashScreen(Game game) {
        super(game);
        dsmoW = Assets.dsmoSplash.getWidth();
        dsmoH = Assets.dsmoSplash.getHeight();
        dsmoX = AndroidGame.GAME_WIDTH / 2 - dsmoW / 2;
        dsmoY = AndroidGame.GAME_HEIGHT / 2 - dsmoH / 2;

        counter = 0;
    }

    private void playSound() {
        //Should really remove code below
        if (!playedOnce && counter > 1) {
            Log.d("SplashScreen", "playing sound...");
            Assets.oneUp.play(1);
            playedOnce = true;
        }//end remove
    }

    private void changeScreen() {
        //If tweening is complete, set screen to main menu
        if (counter >= TIME_TO_CHANGE + 0.3) {
            Screen current = this;
            Screen next = new DaftBoardScreen(game);
            ArrayList<TransitionEffect> effects = new ArrayList<>();
            effects.add(new FadeOutTransitionEffect(.5f, 0xff_ffffff));
            effects.add(new FadeInTransitionEffect(.5f, 0xff_ffffff));
            Screen transitionScreen = new TransitionScreen(game, current, next, effects);
            game.setScreen(transitionScreen);
            return;
        }
    }

    @Override
    public void update(float deltaTime) {
        counter += deltaTime;
//        Log.d("SplashScreen", "Counter: " + counter); //goes up in 1
        playSound();
        changeScreen();
    }

    @Override
    public void draw(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clear(0xff_ffffff);                        //white background
        g.drawPixmap(Assets.dsmoSplash, dsmoX, dsmoY);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
