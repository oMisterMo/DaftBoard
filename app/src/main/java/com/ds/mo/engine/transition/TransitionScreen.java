package com.ds.mo.engine.transition;

import com.ds.mo.engine.framework.Game;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Screen;

import java.util.ArrayList;

/**
 * Created by Mo on 09/11/2017.
 */

public class TransitionScreen extends Screen {
    private ArrayList<TransitionEffect> effects;
    private int currentTransition;      //Current transition pointer within the effects array
    private Screen current;
    private Screen next;

    public TransitionScreen(Game game, Screen current, Screen next,
                            ArrayList<TransitionEffect> effects) {
        super(game);
        this.effects = effects;
        this.current = current;     //Screen currently being rendered
        this.next = next;
    }

    @Override
    public void update(float deltaTime) {
        game.getInput().getKeyEvents();
        game.getInput().getTouchEvents();
    }

    @Override
    public void draw(float deltaTime) {
        Graphics g = game.getGraphics();
//        g.clear(0xff_ffffff);    //white background
        //If we have reached the end of the transition
        if (currentTransition >= effects.size()) {
            game.setScreen(next);
//            game.continueScreen(next);
            return;
        }

//        Log.d("TransitionScreen", "CurrentTrans: " + currentTransition);
        effects.get(currentTransition).update(deltaTime);
        effects.get(currentTransition).draw(g, current, next, deltaTime);

        float alpha = effects.get(currentTransition).getAlpha();


        if (effects.get(currentTransition).isFinished()) {
            currentTransition++;
        }
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
