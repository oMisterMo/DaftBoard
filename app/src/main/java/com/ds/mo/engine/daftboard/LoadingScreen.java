package com.ds.mo.engine.daftboard;

import com.ds.mo.engine.framework.Game;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Graphics.PixmapFormat;
import com.ds.mo.engine.framework.Screen;


/**
 * Loads all assets then sets the first screen
 * <p>
 * Created by Mo on 29/05/2017.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        /*Load all assets here*/

        //Splash screen
        Assets.dsmoSplash = g.newPixmap("mushroom.png", PixmapFormat.ARGB8888);
        Assets.oneUp = game.getAudio().newSound("oneUp.wav");
        //GameScreen
        Assets.background = g.newPixmap("bg.jpeg", PixmapFormat.RGB565);


        //Load sound fx
        Assets.click = game.getAudio().newSound("click.wav");
        Assets.workIt = game.getAudio().newSound("sfx/1.wav");
        Assets.makeIt = game.getAudio().newSound("sfx/2.wav");
        Assets.doIt = game.getAudio().newSound("sfx/3.wav");
        Assets.makesUs = game.getAudio().newSound("sfx/4.wav");
        Assets.harder = game.getAudio().newSound("sfx/5.wav");
        Assets.better = game.getAudio().newSound("sfx/6.wav");
        Assets.faster = game.getAudio().newSound("sfx/7.wav");
        Assets.stronger = game.getAudio().newSound("sfx/8.wav");
        Assets.moreThan = game.getAudio().newSound("sfx/9.wav");
        Assets.hour = game.getAudio().newSound("sfx/10.wav");
        Assets.our = game.getAudio().newSound("sfx/11.wav");
        Assets.never = game.getAudio().newSound("sfx/12.wav");
        Assets.ever = game.getAudio().newSound("sfx/13.wav");
        Assets.after = game.getAudio().newSound("sfx/14.wav");
        Assets.workIs = game.getAudio().newSound("sfx/15.wav");
        Assets.over = game.getAudio().newSound("sfx/16.wav");

        //Load high octave
        Assets.workIt_high = game.getAudio().newSound("sfx/workit.wav");
        Assets.makeIt_high = game.getAudio().newSound("sfx/makeit.wav");
        Assets.doIt_high = game.getAudio().newSound("sfx/doit.wav");
        Assets.makesUs_high = game.getAudio().newSound("sfx/makesus.wav");
        Assets.harder_high = game.getAudio().newSound("sfx/harder.wav");
        Assets.better_high = game.getAudio().newSound("sfx/better.wav");
        Assets.faster_high = game.getAudio().newSound("sfx/faster.wav");
        Assets.stronger_high = game.getAudio().newSound("sfx/stronger.wav");
        Assets.moreThan_high = game.getAudio().newSound("sfx/morethan.wav");
        Assets.hour_high = game.getAudio().newSound("sfx/hour.wav");
        Assets.our_high = game.getAudio().newSound("sfx/our.wav");
        Assets.never_high = game.getAudio().newSound("sfx/never.wav");
        Assets.ever_high = game.getAudio().newSound("sfx/ever.wav");
        Assets.after_high = game.getAudio().newSound("sfx/after.wav");
        Assets.workIs_high = game.getAudio().newSound("sfx/workis.wav");
        Assets.over_high = game.getAudio().newSound("sfx/over.wav");

        Assets.instrumental = game.getAudio().newMusic("instrumental.mp3");

//        game.setScreen(new SplashScreen(game));
        game.setScreen(new DaftBoardScreen(game));
    }

    @Override
    public void draw(float deltaTime) {
        //[NOT REACHED]
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
