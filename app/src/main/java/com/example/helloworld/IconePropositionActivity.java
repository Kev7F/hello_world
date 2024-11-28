package com.example.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_historique) {
            // Ouvrir le BottomSheet lorsqu'on clique sur l'historique
            HistoriqueBottomSheetFragment bottomSheetFragment = new HistoriqueBottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** Called when the user taps the Send button */
    public void sendMessage2(View view) {
        // Récupérer la recherche depuis la barre de recherche
        EditText editText = findViewById(R.id.barre_recherche_plat_ingredient);
        String message = editText.getText().toString();

        // Sauvegarder cette recherche dans SharedPreferences (pour garder l'historique)
        SharedPreferences sharedPreferences = getSharedPreferences("Historique", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Ajouter la recherche dans SharedPreferences en tant que nouvelle entrée
        long currentTime = System.currentTimeMillis();  // Utiliser un timestamp unique pour chaque recherche
        editor.putString("search_" + currentTime, message);  // Sauvegarder la recherche avec un identifiant unique
        editor.apply();  // Appliquer les modifications

        // Passer à l'activité suivante (par exemple recherche d'un plat)
        Intent intent = new Intent(this, resultat_recherche_ingredient_activity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
//    /** Called when the user taps the Send button */
//    public void sendMessage2(View view)
//    {
//        // Do something in response to button
//        Intent intent = new Intent(this, resultat_recherche_ingredient_activity.class);
//        EditText editText = (EditText) findViewById(R.id.barre_recherche_plat_ingredient);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
