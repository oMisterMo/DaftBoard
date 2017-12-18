package com.ds.mo.engine.daftboard;

import android.util.Log;

import com.ds.mo.engine.android.AndroidGraphics;
import com.ds.mo.engine.common.Color;
import com.ds.mo.engine.common.Rectangle;
import com.ds.mo.engine.common.Vector2D;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Input.TouchEvent;
import com.ds.mo.engine.framework.Pixmap;
import com.ds.mo.engine.framework.Sound;

import java.util.List;

/**
 * Created by Mo on 17/12/2017.
 */

public class Button {
    public static final float BUTTON_WIDTH = 120;
    public static final float BUTTON_HEIGHT = 120;

    private Color color = Color.BLACK;  //default color
    private float scale = 1f;           //default scale
    public Rectangle bounds;            //hitbox
    private Sound sound;                //sound to play

    public Button() {

    }

    public Button(Vector2D bounds) {
        this.bounds = new Rectangle(bounds.x, bounds.y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public Button(Vector2D bounds, Color color) {
        this.color = new Color(color);
        this.bounds = new Rectangle(bounds.x, bounds.y, BUTTON_WIDTH, BUTTON_HEIGHT);
        Log.d("Button", "Color: " + color);
        Log.d("Button", "Color.argb8888: " + Color.argb8888(color));
    }

    public Button(Vector2D bounds, Color color, Sound sound) {
        this.color = color;
        this.bounds = new Rectangle(bounds.x, bounds.y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.sound = sound;
        Log.d("Button", "Color: " + color);
        Log.d("Button", "Color.argb8888: " + Color.argb8888(color));
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void playSound() {
        sound.play(1);
    }

    public void setColor(int rgba) {
        this.color.set(rgba);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void update() {
        //do nothing
    }

    public void draw(AndroidGraphics g) {
        // TODO: 17/12/2017 draw a border around the button
        g.fillRect(bounds, scale, Color.argb8888(color));
    }
}
