package com.ds.mo.engine.android;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.ds.mo.engine.common.Rectangle;
import com.ds.mo.engine.common.Vector2D;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Pixmap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mo on 29/05/2017.
 */
public class AndroidGraphics implements Graphics {
    private AssetManager assetManager;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assetManager, Bitmap frameBuffer) {
        this.assetManager = assetManager;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();

//        paint.setAntiAlias(true);
    }

    @Override
    public Pixmap newPixmap(String filename, PixmapFormat format) {
        Config config = null;
        if (format == PixmapFormat.RGB565) {
            config = Config.RGB_565;
        } else if (format == PixmapFormat.ARGB4444) {
            config = Config.ARGB_4444;
        } else {
            config = Config.ARGB_8888;
        }
        Options options = new Options();
        options.inPreferredConfig = config;
//        Log.d("AndroidGraphics", "Preferred config == " + options.inPreferredConfig);

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assetManager.open(filename);
//            bitmap = BitmapFactory.decodeStream(in);
            bitmap = BitmapFactory.decodeStream(in, null, options);
//            Log.d("AndroidGraphics", bitmap.getConfig() + " loaded");
            if (bitmap == null) {
                throw new RuntimeException("Couldn't load bitmap from asset: " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset: " + filename);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565) {
            format = PixmapFormat.RGB565;
        } else if (bitmap.getConfig() == Config.ARGB_4444) {
            format = PixmapFormat.ARGB4444;
        } else {
            format = PixmapFormat.ARGB8888;
        }
        return new AndroidPixmap(bitmap, format);
    }

    @Override
    public void clear(int color) {
//        int num = (color & 0xff0000) >> 16;
//        Log.d("COLORS", "**** red: " + num);  //remove log after test
//        num = (color & 0xff00) >> 8;
//        Log.d("COLORS", "**** green: " + num);  //remove log after test
//        num = (color & 0xff);
//        Log.d("COLORS", "**** blue: " + num);  //remove log after test
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void clear(int a, int r, int g, int b) {
        canvas.drawARGB(a, r, g, b);
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, Paint p) {
        canvas.drawRect(x, y, x + width - 1, y + height - 1, p);
    }

    public void drawRect(Rectangle r, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(r.lowerLeft.x, r.lowerLeft.y, r.lowerLeft.x + r.width - 1,
                r.lowerLeft.y + r.height - 1, paint);
    }

    @Override
    public void fillRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    public void fillRect(Rectangle r, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(r.lowerLeft.x, r.lowerLeft.y, r.lowerLeft.x + r.width - 1,
                r.lowerLeft.y + r.height - 1, paint);
    }

    public void fillRect(Rectangle r, float scale, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        Vector2D center = r.getCenter();
        canvas.scale(scale, scale, center.x, center.y);
        canvas.drawRect(r.lowerLeft.x, r.lowerLeft.y, r.lowerLeft.x + r.width - 1,
                r.lowerLeft.y + r.height - 1, paint);
        canvas.restore();
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, null);
    }

    /*
        My function to blend an images alpha value
     */
    public void drawPixmap(Pixmap pixmap, int x, int y, Paint p) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, p);
    }

    public void drawPixmap(Pixmap pixmap, Vector2D p) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, p.x, p.y, null);
    }

    public void drawPixmap(Pixmap pixmap, float x, float y) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, null);
    }

    public void save(){
        canvas.save();
    }

    public void restore(){
        canvas.restore();
    }

    public Canvas getCanvas() {
        return canvas;
    }

//    @Override
//    public void getSubPixmap(Pixmap pixmap, int x, int y, int width, int height) {
////        Bitmap test = ((AndroidPixmap) pixmap).bitmap;
//        ((AndroidPixmap) pixmap).bitmap = Bitmap.createBitmap(((AndroidPixmap) pixmap).bitmap, x, y, width, height);
////        return null;
//    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
