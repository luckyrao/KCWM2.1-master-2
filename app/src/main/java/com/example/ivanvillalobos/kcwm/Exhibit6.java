package com.example.ivanvillalobos.kcwm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.os.Handler;
public class Exhibit6 extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{


    private GestureDetectorCompat gestureDetector;
    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        SeekBar seek_bar;//seekbar

    Handler seekHandler = new Handler();//seekbar
    MediaPlayer audio;
    int paused;

    public void playMusic (View view) {
        if(audio == null) {
            audio = MediaPlayer.create(this, R.raw.tovivaldi);
            audio.start();
        }else if(!audio.isPlaying()){
            audio.seekTo(paused);
            audio.start();
        }
    }

    public void pauseMusic(View view) {
        audio.pause();
        paused = audio.getCurrentPosition();
    }

    public void stopMusic(View view) {
        audio.release();
        audio = null;
    }






    private android.support.v7.widget.Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exhibits, menu);

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit6);

        this.gestureDetector= new GestureDetectorCompat(this,this);
        gestureDetector.setOnDoubleTapListener(this);
getInit();
        seekUpdation();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }
    public void getInit() {
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);


        audio = MediaPlayer.create(this, R.raw.tovivaldi);
        seek_bar.setMax(audio.getDuration());
    }
    Runnable run = new Runnable()
    {
        @Override
        public void run()
        {
            seekUpdation();
        }
    };
    public void seekUpdation()
    {
        seek_bar.setProgress(audio.getCurrentPosition());
        seekHandler.postDelayed(run, 0);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.navigate){
            audio.stop();
            startActivity(new Intent(this, Exhibit7.class));
            finish();

        }

        if(id==R.id.navigatePrevious){
            audio.stop();
            startActivity(new Intent(this, Exhibit5.class));
            finish();

        }

        if(id==R.id.map){
            audio.stop();
            startActivity(new Intent(this, Map.class));
            finish();

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        return true;

    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {


    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public void onSwipeLeft() {

        Intent myIntent = new Intent(Exhibit6.this, Exhibit6b.class);
        Exhibit6.this.startActivity(myIntent);
        finish();


    }
    public void onSwipeRight() {


    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (distanceX > 0)
                onSwipeRight();
            else
                onSwipeLeft();
            return true;
        }
        return false;
    }

}
