package com.ds.mo.engine.android;

import android.graphics.Bitmap;

import com.ds.mo.engine.framework.Graphics.PixmapFormat;
import com.ds.mo.engine.framework.Pixmap;


/**
 * Created by Mo on 29/05/2017.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public AndroidPixmap(Bitmap bitmap, int x, int y, int width, int height, PixmapFormat format) {
        this.bitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
