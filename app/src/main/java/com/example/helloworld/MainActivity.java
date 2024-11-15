package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.son_demarre);

        //demarrer l'audio
        mediaPlayer.start();
        //Augmenter le volume de l'audio, a fond sinon on entend pas
        mediaPlayer.setVolume(1.0f, 1.0f);

        boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                //arreter l'audio au bout de 1,9s
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }, 1000);
    }
}