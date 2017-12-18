package com.ds.mo.engine.daftboard;

import com.ds.mo.engine.android.AndroidGame;
import com.ds.mo.engine.android.AndroidGraphics;
import com.ds.mo.engine.common.Color;
import com.ds.mo.engine.common.Vector2D;

/**
 * Created by Mo on 17/12/2017.
 */

public class Board {
    public static int BOARD_WIDTH = AndroidGame.GAME_HEIGHT - 50;   // TODO: 17/12/2017 Make final (four constants)
    public static int BOARD_HEIGHT = AndroidGame.GAME_HEIGHT - 50;
    public static int NUM_BUTTONS_X = 4;
    public static int NUM_BUTTONS_Y = 4;
    public static final int TOTAL_BUTTONS = NUM_BUTTONS_X * NUM_BUTTONS_Y;

    private Vector2D position;
    public Button[][] buttons;
//    private List<Button> buttons;

    public Board() {
        int centerX, centerY;
        centerX = AndroidGame.GAME_WIDTH / 2 - BOARD_WIDTH / 2;
        centerY = AndroidGame.GAME_HEIGHT / 2 - BOARD_HEIGHT / 2;
        position = new Vector2D(centerX, centerY);
        initBoard();
        //Set sounds
        setSounds();
    }

    public Board(Vector2D position) {
        this.position = position;
        initBoard();
        //Set sounds
        setSounds();
    }

    private void initBoard() {
        buttons = new Button[NUM_BUTTONS_Y][NUM_BUTTONS_X];
        float fourthBoardWidth = BOARD_WIDTH / NUM_BUTTONS_X;
        float fourthBoardHeight = BOARD_HEIGHT / NUM_BUTTONS_Y;
        float offsetX = fourthBoardWidth / 2;
        float offsetY = fourthBoardHeight / 2;
        for (int y = 0; y < NUM_BUTTONS_Y; y++) {
            for (int x = 0; x < NUM_BUTTONS_X; x++) {
                Vector2D temp = new Vector2D(position.x + (offsetX + x * fourthBoardWidth) - Button.BUTTON_WIDTH / 2,
                        position.y + (offsetY + y * fourthBoardHeight) - Button.BUTTON_HEIGHT / 2);
                buttons[y][x] = new Button(temp, Color.FOREST);
            }
        }
    }

    public void setSounds(){
        buttons[0][0].setSound(Assets.workIt);
        buttons[0][1].setSound(Assets.makeIt);
        buttons[0][2].setSound(Assets.doIt);
        buttons[0][3].setSound(Assets.makesUs);

        buttons[1][0].setSound(Assets.harder);
        buttons[1][1].setSound(Assets.better);
        buttons[1][2].setSound(Assets.faster);
        buttons[1][3].setSound(Assets.stronger);

        buttons[2][0].setSound(Assets.moreThan);
        buttons[2][1].setSound(Assets.hour);
        buttons[2][2].setSound(Assets.our);
        buttons[2][3].setSound(Assets.never);

        buttons[3][0].setSound(Assets.ever);
        buttons[3][1].setSound(Assets.after);
        buttons[3][2].setSound(Assets.workIs);
        buttons[3][3].setSound(Assets.over);
    }

    public void setHighSounds(){
        buttons[0][0].setSound(Assets.workIt_high);
        buttons[0][1].setSound(Assets.makeIt_high);
        buttons[0][2].setSound(Assets.doIt_high);
        buttons[0][3].setSound(Assets.makesUs_high);

        buttons[1][0].setSound(Assets.harder_high);
        buttons[1][1].setSound(Assets.better_high);
        buttons[1][2].setSound(Assets.faster_high);
        buttons[1][3].setSound(Assets.stronger_high);

        buttons[2][0].setSound(Assets.moreThan_high);
        buttons[2][1].setSound(Assets.hour_high);
        buttons[2][2].setSound(Assets.our_high);
        buttons[2][3].setSound(Assets.never_high);

        buttons[3][0].setSound(Assets.ever_high);
        buttons[3][1].setSound(Assets.after_high);
        buttons[3][2].setSound(Assets.workIs_high);
        buttons[3][3].setSound(Assets.over_high);
    }

    public void draw(AndroidGraphics g) {
        //Draw Board
//        g.fillRect((int) position.x, (int) position.y, BOARD_WIDTH, BOARD_HEIGHT, 0xff_b22222);
        g.fillRect((int) position.x, (int) position.y, BOARD_WIDTH, BOARD_HEIGHT, 0xaa_000000); //alpha
        //Draw buttons
        for (int y = 0; y < NUM_BUTTONS_Y; y++) {
            for (int x = 0; x < NUM_BUTTONS_X; x++) {
                buttons[y][x].draw(g);
            }
        }
    }
}
