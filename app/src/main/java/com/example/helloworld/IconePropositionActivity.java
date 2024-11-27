package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IconePropositionActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        // Initialisation de la BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Gérer les clics sur le menu de navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Ouvre l'activité RechercheActivity
                        startActivity(new Intent(IconePropositionActivity.this, MainActivity.class));
                        return true;
                    case R.id.nav_search:
                        // Ouvre l'activité PropositionActivity
                        startActivity(new Intent(IconePropositionActivity.this, IconePropositionActivity.class));
                        return true;
                    case R.id.nav_profile:
                        // Ouvre l'activité FavorisActivity
                        startActivity(new Intent(IconePropositionActivity.this, IconeFavorisActivity.class));
                        return true;
                }
                return false;
            }
        });
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
