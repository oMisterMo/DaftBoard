package com.ds.mo.engine.daftboard;

import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.android.AndroidGraphics;
import com.ds.mo.engine.common.OverlapTester;
import com.ds.mo.engine.common.Rectangle;
import com.ds.mo.engine.common.Vector2D;
import com.ds.mo.engine.framework.Game;
import com.ds.mo.engine.framework.Input;
import com.ds.mo.engine.framework.Screen;

import java.util.List;

/**
 * Created by Mo on 14/12/2017.
 */

public class DaftBoardScreen extends Screen {
    public static float BUTTON_WIDTH = 120;     //Make me final
    public static float BUTTON_HEIGHT = 120;    //SFX buttons

    private Rectangle workIt;
    private Rectangle makeIt;
    private Rectangle doIt;
    private Rectangle makesUs;
    private Rectangle harder;
    private Rectangle better;
    private Rectangle faster;
    private Rectangle stronger;
    private Rectangle moreThan;
    private Rectangle hour;
    private Rectangle our;
    private Rectangle never;
    private Rectangle ever;
    private Rectangle after;
    private Rectangle workIs;
    private Rectangle over;

    private boolean highOctave = false;
    private Rectangle enable;

    private Rectangle play;     //plays music bounds
    private Vector2D touchPos = new Vector2D();

    public DaftBoardScreen(Game game) {
        super(game);

        int posX = 100;
        int posY = 50;
        float gapW = 50 + BUTTON_WIDTH;
        float gapH = 50 + BUTTON_WIDTH;
        workIt = new Rectangle(posX, posY, BUTTON_WIDTH, BUTTON_HEIGHT);
        makeIt = new Rectangle(posX + (gapW * 1), posY, BUTTON_WIDTH, BUTTON_HEIGHT);
        doIt = new Rectangle(posX + (gapW * 2), posY, BUTTON_WIDTH, BUTTON_HEIGHT);
        makesUs = new Rectangle(posX + (gapW * 3), posY, BUTTON_WIDTH, BUTTON_HEIGHT);

        harder = new Rectangle(posX, posY + (gapH * 1), BUTTON_WIDTH, BUTTON_HEIGHT);
        better = new Rectangle(posX + (gapW * 1), posY + (gapH * 1), BUTTON_WIDTH, BUTTON_HEIGHT);
        faster = new Rectangle(posX + (gapW * 2), posY + (gapH * 1), BUTTON_WIDTH, BUTTON_HEIGHT);
        stronger = new Rectangle(posX + (gapW * 3), posY + (gapH * 1), BUTTON_WIDTH, BUTTON_HEIGHT);

        moreThan = new Rectangle(posX, posY + (gapH * 2), BUTTON_WIDTH, BUTTON_HEIGHT);
        hour = new Rectangle(posX + (gapW * 1), posY + (gapH * 2), BUTTON_WIDTH, BUTTON_HEIGHT);
        our = new Rectangle(posX + (gapW * 2), posY + (gapH * 2), BUTTON_WIDTH, BUTTON_HEIGHT);
        never = new Rectangle(posX + (gapW * 3), posY + (gapH * 2), BUTTON_WIDTH, BUTTON_HEIGHT);

        ever = new Rectangle(posX, posY + (gapH * 3), BUTTON_WIDTH, BUTTON_HEIGHT);
        after = new Rectangle(posX + (gapW * 1), posY + (gapH * 3), BUTTON_WIDTH, BUTTON_HEIGHT);
        workIs = new Rectangle(posX + (gapW * 2), posY + (gapH * 3), BUTTON_WIDTH, BUTTON_HEIGHT);
        over = new Rectangle(posX + (gapW * 3), posY + (gapH * 3), BUTTON_WIDTH, BUTTON_HEIGHT);

        int wh = 50;    //width & height of play button
        play = new Rectangle(AndroidGame.GAME_WIDTH - wh - 50, AndroidGame.GAME_HEIGHT - wh - 50, wh, wh);

        enable = new Rectangle(AndroidGame.GAME_WIDTH - wh - 50, AndroidGame.GAME_HEIGHT - wh * 2 - 50 - 50, wh, wh);
    }

    private void handleTouchDown(Input.TouchEvent event) {
        if (event.type == Input.TouchEvent.TOUCH_DOWN) {
            Log.d("DaftBoard", "Touch: " + touchPos);


        }
    }

    private void handleTouchUp(Input.TouchEvent event) {
        if (event.type == Input.TouchEvent.TOUCH_UP) {
            if (!highOctave) {
                playNormal();
            } else {
                playHigh();
            }

            if (OverlapTester.pointInRectangle(play, touchPos)) {
                if (Assets.instrumental.isPlaying()) {
                    //stop and restart
                    Assets.instrumental.stop();
                } else {
                    Assets.instrumental.play();
                }
            }

            if (OverlapTester.pointInRectangle(enable, touchPos)) {
                highOctave = !highOctave;
            }
        }
    }

    private void playNormal() {
        if (OverlapTester.pointInRectangle(workIt, touchPos)) {
            Assets.workIt.play(1);
        }
        if (OverlapTester.pointInRectangle(makeIt, touchPos)) {
            Assets.makeIt.play(1);
        }
        if (OverlapTester.pointInRectangle(doIt, touchPos)) {
            Assets.doIt.play(1);
        }
        if (OverlapTester.pointInRectangle(makesUs, touchPos)) {
            Assets.makesUs.play(1);
        }
        if (OverlapTester.pointInRectangle(harder, touchPos)) {
            Assets.harder.play(1);
        }
        if (OverlapTester.pointInRectangle(better, touchPos)) {
            Assets.better.play(1);
        }
        if (OverlapTester.pointInRectangle(faster, touchPos)) {
            Assets.faster.play(1);
        }
        if (OverlapTester.pointInRectangle(stronger, touchPos)) {
            Assets.stronger.play(1);
        }
        if (OverlapTester.pointInRectangle(moreThan, touchPos)) {
            Assets.moreThan.play(1);
        }
        if (OverlapTester.pointInRectangle(hour, touchPos)) {
            Assets.hour.play(1);
        }
        if (OverlapTester.pointInRectangle(our, touchPos)) {
            Assets.our.play(1);
        }
        if (OverlapTester.pointInRectangle(never, touchPos)) {
            Assets.never.play(1);
        }
        if (OverlapTester.pointInRectangle(ever, touchPos)) {
            Assets.ever.play(1);
        }
        if (OverlapTester.pointInRectangle(after, touchPos)) {
            Assets.after.play(1);
        }
        if (OverlapTester.pointInRectangle(workIs, touchPos)) {
            Assets.workIs.play(1);
        }
        if (OverlapTester.pointInRectangle(over, touchPos)) {
            Assets.over.play(1);
        }
    }

    private void playHigh() {
        if (OverlapTester.pointInRectangle(workIt, touchPos)) {
            Assets.workIt_high.play(1);
        }
        if (OverlapTester.pointInRectangle(makeIt, touchPos)) {
            Assets.makeIt_high.play(1);
        }
        if (OverlapTester.pointInRectangle(doIt, touchPos)) {
            Assets.doIt_high.play(1);
        }
        if (OverlapTester.pointInRectangle(makesUs, touchPos)) {
            Assets.makesUs_high.play(1);
        }
        if (OverlapTester.pointInRectangle(harder, touchPos)) {
            Assets.harder_high.play(1);
        }
        if (OverlapTester.pointInRectangle(better, touchPos)) {
            Assets.better_high.play(1);
        }
        if (OverlapTester.pointInRectangle(faster, touchPos)) {
            Assets.faster_high.play(1);
        }
        if (OverlapTester.pointInRectangle(stronger, touchPos)) {
            Assets.stronger_high.play(1);
        }
        if (OverlapTester.pointInRectangle(moreThan, touchPos)) {
            Assets.moreThan_high.play(1);
        }
        if (OverlapTester.pointInRectangle(hour, touchPos)) {
            Assets.hour_high.play(1);
        }
        if (OverlapTester.pointInRectangle(our, touchPos)) {
            Assets.our_high.play(1);
        }
        if (OverlapTester.pointInRectangle(never, touchPos)) {
            Assets.never_high.play(1);
        }
        if (OverlapTester.pointInRectangle(ever, touchPos)) {
            Assets.ever_high.play(1);
        }
        if (OverlapTester.pointInRectangle(after, touchPos)) {
            Assets.after_high.play(1);
        }
        if (OverlapTester.pointInRectangle(workIs, touchPos)) {
            Assets.workIs_high.play(1);
        }
        if (OverlapTester.pointInRectangle(over, touchPos)) {
            Assets.over_high.play(1);
        }
    }

    private void handleTouchEvents() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents(); //Get key events to clear the internal buffer

        //Handle touch events
        int len = touchEvents.size();   // len = no of touch events (fingers on screen)
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            touchPos.set(event.x, event.y);
            handleTouchDown(event);
//            handleTouchDragged(event);
            handleTouchUp(event);
        }
    }

    @Override
    public void update(float deltaTime) {
        handleTouchEvents();
    }

    @Override
    public void draw(float deltaTime) {
        AndroidGraphics g = (AndroidGraphics) game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.fillRect(workIt, 0xff_ffffff);
        g.fillRect(makeIt, 0xff_aaaaaa);
        g.fillRect(doIt, 0xff_888888);
        g.fillRect(makesUs, 0xff_333333);

        g.fillRect(harder, 0xff_ffffff);
        g.fillRect(better, 1, 0xff_aaaaaa);
        g.fillRect(faster, 0xff_888888);
        g.fillRect(stronger, 1.5f, 0xff_333333);

        g.fillRect(moreThan, 0xff_ffffff);
        g.fillRect(hour, 0xff_aaaaaa);
        g.fillRect(our, 0xff_888888);
        g.fillRect(never, 0xff_333333);

        g.fillRect(ever, 0xff_ffffff);
        g.fillRect(after, 0xff_aaaaaa);
        g.fillRect(workIs, 0xff_888888);
        g.fillRect(over, 0xff_333333);

        g.fillRect(play, (Assets.instrumental.isPlaying()) ? 0xff_00ff00 : 0xff_ff0000);
        g.fillRect(enable, (highOctave) ? 0xff_00ff00 : 0xff_ff0000);
    }

    @Override
    public void pause() {
        Assets.instrumental.pause();
    }

    @Override
    public void resume() {
//        Assets.instrumental.play();
    }

    @Override
    public void dispose() {

    }
}
