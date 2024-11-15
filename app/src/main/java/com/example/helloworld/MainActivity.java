package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    public static final String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";

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
        }, 1900);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, recherche_un_plat_activity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}