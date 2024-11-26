package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class IconePropositionActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);
    }

    /** Called when the user taps the Send button */
    public void sendMessage2(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this, resultat_recherche_ingredient_activity.class);
        EditText editText = (EditText) findViewById(R.id.barre_recherche_plat_ingredient);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
