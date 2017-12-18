package com.ds.mo.engine.daftboard;

import android.util.Log;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.android.AndroidGraphics;
import com.ds.mo.engine.common.OverlapTester;
import com.ds.mo.engine.common.Rectangle;
import com.ds.mo.engine.common.Vector2D;
import com.ds.mo.engine.framework.Game;
import com.ds.mo.engine.framework.Input.TouchEvent;
import com.ds.mo.engine.framework.Screen;

import java.util.List;

/**
 * Created by Mo on 14/12/2017.
 */

public class DaftBoardScreen extends Screen {
    private boolean highOctave = false;
    private Rectangle enable;

    private Rectangle play;     //'play music' bounds
    private Vector2D touchPos = new Vector2D();

    private Board board;

    public DaftBoardScreen(Game game) {
        super(game);

        //Code above is gonna get cut
        int wh = 50;    //width & height of play button
        play = new Rectangle(AndroidGame.GAME_WIDTH - wh - 50, AndroidGame.GAME_HEIGHT - wh - 50, wh, wh);
        enable = new Rectangle(AndroidGame.GAME_WIDTH - wh - 50, AndroidGame.GAME_HEIGHT - wh * 2 - 50 - 50, wh, wh);

        board = new Board();
    }

    private void boardDown() {
        //Errrm No!

        //row 1
        if (OverlapTester.pointInRectangle(board.buttons[0][0].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[0][1].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[0][2].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[0][3].bounds, touchPos)) {

        }
        //row 2
        if (OverlapTester.pointInRectangle(board.buttons[1][0].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[1][1].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[1][2].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[1][3].bounds, touchPos)) {

        }

        //row 3
        if (OverlapTester.pointInRectangle(board.buttons[2][0].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[2][1].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[2][2].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[2][3].bounds, touchPos)) {

        }

        //row 4
        if (OverlapTester.pointInRectangle(board.buttons[3][0].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[3][1].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[3][2].bounds, touchPos)) {

        }
        if (OverlapTester.pointInRectangle(board.buttons[3][3].bounds, touchPos)) {
//            Assets.over.play(1);
            board.buttons[3][3].playSound();
            Log.d("DBS", "HIT [3][3]");
        }
    }

    private void boardDown2() {
        for (int y = 0; y < Board.NUM_BUTTONS_Y; y++) {
            for (int x = 0; x < Board.NUM_BUTTONS_X; x++) {
                Button b = board.buttons[y][x];
                if (OverlapTester.pointInRectangle(b.bounds, touchPos)) {
                    b.setScale(1.3f);
                    b.setColor(0x00ff00ff);   //rgba
                }
            }
        }
    }

    private void boardDragged() {
        for (int y = 0; y < Board.NUM_BUTTONS_Y; y++) {
            for (int x = 0; x < Board.NUM_BUTTONS_X; x++) {
                Button b = board.buttons[y][x];
                if (OverlapTester.pointInRectangle(b.bounds, touchPos)) {
                    b.setScale(1.3f);
                    b.setColor(0x00ff00ff);   //rgba (GREEN)
                }
                if (!OverlapTester.pointInRectangle(b.bounds, touchPos)) {
                    b.setScale(1f);
                    b.setColor(0x228b22ff);   //rgba (FOREST)
                }
            }
        }
    }

    private void boardUp() {
        for (int y = 0; y < Board.NUM_BUTTONS_Y; y++) {
            for (int x = 0; x < Board.NUM_BUTTONS_X; x++) {
                Button b = board.buttons[y][x];
                if (OverlapTester.pointInRectangle(b.bounds, touchPos)) {
                    b.playSound();
                    b.setScale(1f);
                    b.setColor(0x228b22ff);   //rgba (FOREST)
                }
            }
        }
    }

    private void handleTouchDown(TouchEvent event) {
        if (event.type == TouchEvent.TOUCH_DOWN) {
            Log.d("DaftBoard", "Touch: " + touchPos);

//            boardDown();
            boardDown2();
        }
    }

    private void handleTouchDragged(TouchEvent event) {
        if (event.type == TouchEvent.TOUCH_DRAGGED) {
            boardDragged();
        }
    }

    private void handleTouchUp(TouchEvent event) {
        if (event.type == TouchEvent.TOUCH_UP) {
            //If music square is touched
            if (!highOctave) {
//                playNormal();
                board.setSounds();
            } else {
//                playHigh();
                board.setHighSounds();
            }
            boardUp();

            //If play button is touched
            if (OverlapTester.pointInRectangle(play, touchPos)) {
                if (Assets.instrumental.isPlaying()) {
                    //stop and restart
                    Assets.instrumental.stop();
                } else {
                    Assets.instrumental.play();
                }
            }

            //If high octave button is touched
            if (OverlapTester.pointInRectangle(enable, touchPos)) {
                highOctave = !highOctave;
            }
        }
    }

    private void handleTouchEvents() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents(); //Get key events to clear the internal buffer

        //Handle touch events
        int len = touchEvents.size();   // len = no of touch events (fingers on screen)
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            touchPos.set(event.x, event.y);
            handleTouchDown(event);
            handleTouchDragged(event);
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
        g.drawPixmap(Assets.bg, 0, 0);
//        g.fillRect(workIt, 0xff_ffffff);
//        g.fillRect(makeIt, 0xff_aaaaaa);
//        g.fillRect(doIt, 0xff_888888);
//        g.fillRect(makesUs, 0xff_333333);

        g.fillRect(play, (Assets.instrumental.isPlaying()) ? 0xff_00ff00 : 0xff_ff0000);
        g.fillRect(enable, (highOctave) ? 0xff_00ff00 : 0xff_ff0000);

        //new
        board.draw(g);
    }

    @Override
    public void pause() {
        Assets.instrumental.pause();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
