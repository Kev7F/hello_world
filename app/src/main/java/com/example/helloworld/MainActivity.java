package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import database.AppDatabase;
import database.TextEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;





public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    public static final String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";
    private BottomNavigationView bottomNavigationView; // Variable pour la BottomNavigationView



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
                //arreter l'audio au bout de 1s
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }, 1000);

        // Initialisation de la BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Gérer les clics sur le menu de navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Ouvre l'activité RechercheActivity
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        return true;
                    case R.id.nav_search:
                        // Ouvre l'activité PropositionActivity
                        startActivity(new Intent(MainActivity.this, IconePropositionActivity.class));
                        return true;
                    case R.id.nav_profile:
                        // Ouvre l'activité FavorisActivity
                        startActivity(new Intent(MainActivity.this, IconeFavorisActivity.class));
                        return true;
                }
                return false;
            }
        });


        // Initialisation de la base de données, le nom de la base de donnée est "my-database"
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database").build();
        new Thread(() -> {      // On utilise un thread car Room interdit l'accès à la base de données sur le thread principale

            TextEntity Lasagnes = new TextEntity();
            Lasagnes.nom_recette = "Lasagnes";
            Lasagnes.nbr_personnes = 2;
            Lasagnes.qt_boeuf = 100*Lasagnes.nbr_personnes;
            Lasagnes.qt_tomate = 50*Lasagnes.nbr_personnes;
            Lasagnes.qt_champignon = 12.5*Lasagnes.nbr_personnes;
            Lasagnes.ingredients = "•sel\n•poivre\n•origan\n•fromage\n•râpé\n•huile\n•cube de bouillon (quantité : 1)\n•Oignon\n•Boite de lasagnes (quantité : 1)\n•Viande hâchée (quantité : " + Lasagnes.qt_boeuf + ")\n•Tomates pelées (quantité : " + Lasagnes.qt_tomate + ")\n•Champignon (quantité : " + Lasagnes.qt_champignon + ")\n•Gousse d'ail (quantité : 1)\n•Béchamel liquide";
            Lasagnes.recette = "ÉTAPE 1\nHacher l'oignon et l'ail.\n\nÉTAPE 2\nFaire chauffer un peu d'huile, y faire rissoler l'ail et l'oignon, puis ajouter la viande hachée et les champignons émincés.\n\nÉTAPE 3\nLaisser revenir, puis ajouter les tomates pelées et le cube de bouillon émietté.\nÉTAPE 4\n\nSaler, poivrer et saupoudre d'origan.\n\nÉTAPE 5\nLaisser cuire 30 min à feu doux.\n\nÉTAPE 6\nBeurrer un plat rectangulaire, recouvrir le fond de béchamel.\n\nÉTAPE 7\nDisposer des plaques de lasagnes, couvrir de béchamel puis de sauce à la viande.\n\nÉTAPE 8\nSaupoudrer de fromage râpé.\n\nÉTAPE 9\nRépéter cette opération 4 fois.\n\nÉTAPE 10\nNapper de béchamel, recouvrir de fromage râpé et faire cuire 30 min au four à 180°C (thermostat 6).\n\n";
            Lasagnes.filtres_temps= "45;30";
            Lasagnes.filtres = "";
            db.textDao().insertText(Lasagnes);

            TextEntity pate_carbo = new TextEntity();
            pate_carbo.nom_recette = "Pâte Carbonara";
            db.textDao().insertText(pate_carbo);

            String nomRecette = db.textDao().getNomRecetteById(1);
            System.out.println(nomRecette); //Affichage de Lasagnes dans logcat

        }).start();


    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, recherche_un_plat_activity.class);
        EditText editText = (EditText) findViewById(R.id.barre_recherche_plat);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}