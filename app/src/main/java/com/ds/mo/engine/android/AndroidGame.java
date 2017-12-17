package com.ds.mo.engine.android;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ds.mo.engine.framework.Audio;
import com.ds.mo.engine.framework.FileIO;
import com.ds.mo.engine.framework.Game;
import com.ds.mo.engine.framework.Graphics;
import com.ds.mo.engine.framework.Input;
import com.ds.mo.engine.framework.Screen;

/**
 * Created by Mo on 29/05/2017.
 */
public class AndroidGame extends Activity implements Game {
    public final static int GAME_WIDTH = 1280;
    public final static int GAME_HEIGHT = 720;

    //Game engine components
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;

    //Permission needed for latest phones (API 23+)
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /*  ACTIVITY LIFECYCLE */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AndroidGame", "***************CREATE***************");
        //Set flags
        setFlags();

        //Get permission
        verifyStoragePermissions(this);

        //Initialise game engine
        initEngine();

        Log.d("AndroidGame", "*Setting the start screen...*");
        screen = getStartScreen();    //same as new LoadingScreen(this)
//        screen = new LoadingScreen(this);

        //Set the content view to game thread
        setContentView(renderView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AndroidGame", "***************PAUSE***************");
        renderView.pause();
        screen.pause();

        if (isFinishing()) {
            Log.d("AndroidGame", "*isFinishing() called from main activity...*");
            screen.dispose();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AndroidGame", "***************RESUME***************");
        screen.resume();
        renderView.resume();
    }

    /* GETTERS & SETTERS */
    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null) {
            throw new IllegalArgumentException("Screen must not be null");
        }
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        Log.d("TEST", "THIS SHOULD NOT PRINT!!!!");
        return null;
    }

    private void setFlags() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * Verify permission on later models
     *
     * @param activity game activity
     */
    private static void verifyStoragePermissions(Activity activity) {
//        System.out.println("Now getting permission...");
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void initEngine() {
        //        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
//        //BASE RESOLUTION
//        int frameBufferWidth = isLandscape ? 640 : 360;
//        int frameBufferHeight = isLandscape ? 360 : 640;
        int frameBufferWidth = GAME_WIDTH;
        int frameBufferHeight = GAME_HEIGHT;
//        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight,
//                Bitmap.Config.RGB_565); //OLD

        // TODO: 09/12/2017 framebuffer -1 ???
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth-1, frameBufferHeight-1,
                Bitmap.Config.ARGB_8888);
        Log.d("AndroidGame", "FrameBuffer config: "+frameBuffer.getConfig());

        float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
        Log.d("AndroidGame", "*WIDTH: " + frameBufferWidth + " ,HEIGHT: " + frameBufferHeight + "*");
        Log.d("AndroidGame", "*scaleX: " + scaleX + " ,scaleY: " + scaleY + "*");

        Log.d("AndroidGame", "*Initialising renderView...*");
        renderView = new AndroidFastRenderView(this, frameBuffer);
        Log.d("AndroidGame", "*Initialising graphics...*");
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        Log.d("AndroidGame", "*Initialising fileIO...*");
        fileIO = new AndroidFileIO(this);
        Log.d("AndroidGame", "*Initialising audio...*");
        audio = new AndroidAudio(this);
        Log.d("AndroidGame", "*Initialising input...*");
        input = new AndroidInput(this, renderView, scaleX, scaleY);
    }
}
