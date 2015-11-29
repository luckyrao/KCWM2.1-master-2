package com.example.ivanvillalobos.kcwm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import android.util.Log;

import android.view.View.OnClickListener;
import android.widget.ImageButton;

import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Exhibit extends AppCompatActivity implements Runnable, OnClickListener, OnSeekBarChangeListener {

    private SeekBar seek_bar;

    private MediaPlayer audio;
    private ImageButton startMedia;
    private ImageButton stopMedia;


    int paused;


    public void onBackPressed() {
        if (audio.isPlaying() ) {
            audio.stop();
            startActivity(new Intent(this, Map.class));
            finish();
            return;
        }
        // Otherwise defer to system default behavior.
        super.onBackPressed();
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
        setContentView(R.layout.activity_exhibit);

        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        startMedia = (ImageButton) findViewById(R.id.play);
        stopMedia = (ImageButton) findViewById(R.id.pause);
        startMedia.setOnClickListener(this);
        stopMedia.setOnClickListener(this);
        seek_bar.setOnSeekBarChangeListener(this);
        seek_bar.setEnabled(false);



        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }


    public void run() {
        int currentPosition = audio.getCurrentPosition();
        int total = audio.getDuration();

        while (audio != null && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = audio.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seek_bar.setProgress(currentPosition);
        }
    }
    public void onClick(View v) {
        if (v.equals(startMedia)) {
            if (audio == null) {
                audio = MediaPlayer.create(getApplicationContext(), R.raw.tovivaldi);
                seek_bar.setEnabled(true);
            }
            if (audio.isPlaying()) {
                audio.pause();

            } else {
                audio.start();

                seek_bar.setMax(audio.getDuration());
                new Thread(this).start();
            }
        }
        if (v.equals(stopMedia) && audio != null) {
            if (audio.isPlaying() || audio.getDuration() > 0) {
                audio.stop();
                audio = null;

                seek_bar.setProgress(0);
            }
        }

    }

    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        try {
            if (audio.isPlaying() || audio != null) {
                if (fromUser)
                    audio.seekTo(progress);
            } else if (audio == null) {
                Toast.makeText(getApplicationContext(), "Media is not running",
                        Toast.LENGTH_SHORT).show();
                seekBar.setProgress(0);
            }
        } catch (Exception e) {
            Log.e("seek bar", "" + e);
            seekBar.setEnabled(false);

        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.navigate){
            if (audio.isPlaying() ) {
                audio.stop();

            }
            startActivity(new Intent(this, Exhibit2.class));
            finish();

        }

        if(id==R.id.navigatePrevious){
            if (audio.isPlaying() ) {
                audio.stop();

            }
            startActivity(new Intent(this, Exhibit10.class));

        }

        if(id==R.id.map){
            if (audio.isPlaying() ) {
                audio.stop();

            }
            startActivity(new Intent(this, Map.class));
            finish();

        }


        return super.onOptionsItemSelected(item);
    }

}
