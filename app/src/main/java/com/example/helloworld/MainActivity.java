package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
            Lasagnes.qt_oignons = 1*Lasagnes.nbr_personnes;
            Lasagnes.qt_huile = 12*Lasagnes.nbr_personnes;
            Lasagnes.ingredients = "• Sel\n• Poivre\n• Origan\n• Fromage râpé\n• Huile : "+Lasagnes.qt_huile+"cl\n• Cube de bouillon (quantité : 1)\n• Oignon : "+Lasagnes.qt_oignons+"\n• Boite de lasagnes (quantité : 1)\n• Viande hâchée : "+ Lasagnes.qt_boeuf +"g \n• Tomates pelées : "+ Lasagnes.qt_tomate +"g \n• Champignon : " + Lasagnes.qt_champignon + "G\n• Gousse d'ail (quantité : 1)\n• Béchamel liquide";
            Lasagnes.recette = "ÉTAPE 1\nHacher l'oignon et l'ail.\n\nÉTAPE 2\nFaire chauffer un peu d'huile, y faire rissoler l'ail et l'oignon, puis ajouter la viande hachée et les champignons émincés.\n\nÉTAPE 3\nLaisser revenir, puis ajouter les tomates pelées et le cube de bouillon émietté.\n\nÉTAPE 4\nSaler, poivrer et saupoudre d'origan.\n\nÉTAPE 5\nLaisser cuire 30 min à feu doux.\n\nÉTAPE 6\nBeurrer un plat rectangulaire, recouvrir le fond de béchamel.\n\nÉTAPE 7\nDisposer des plaques de lasagnes, couvrir de béchamel puis de sauce à la viande.\n\nÉTAPE 8\nSaupoudrer de fromage râpé.\n\nÉTAPE 9\nRépéter cette opération 4 fois.\n\nÉTAPE 10\nNapper de béchamel, recouvrir de fromage râpé et faire cuire 30 min au four à 180°C (thermostat 6).\n\n";
            Lasagnes.ingredients = "•sel\n•poivre\n•origan\n•fromage\n•râpé\n•huile\n•cube de bouillon (quantité : 1)\n•Oignon\n•Boite de lasagnes (quantité : 1)\n•Viande hâchée (quantité : " + Lasagnes.qt_boeuf + ")\n•Tomates pelées (quantité : " + Lasagnes.qt_tomate + ")\n•Champignon (quantité : " + Lasagnes.qt_champignon + ")\n•Gousse d'ail (quantité : 1)\n•Béchamel liquide";
            Lasagnes.recette = "ÉTAPE 1\nHacher l'oignon et l'ail.\n\nÉTAPE 2\nFaire chauffer un peu d'huile, y faire rissoler l'ail et l'oignon, puis ajouter la viande hachée et les champignons émincés.\n\nÉTAPE 3\nLaisser revenir, puis ajouter les tomates pelées et le cube de bouillon émietté.\n\nÉTAPE 4\nSaler, poivrer et saupoudre d'origan.\n\nÉTAPE 5\nLaisser cuire 30 min à feu doux.\n\nÉTAPE 6\nBeurrer un plat rectangulaire, recouvrir le fond de béchamel.\n\nÉTAPE 7\nDisposer des plaques de lasagnes, couvrir de béchamel puis de sauce à la viande.\n\nÉTAPE 8\nSaupoudrer de fromage râpé.\n\nÉTAPE 9\nRépéter cette opération 4 fois.\n\nÉTAPE 10\nNapper de béchamel, recouvrir de fromage râpé et faire cuire 30 min au four à 180°C (thermostat 6).\n\n";
            Lasagnes.filtres_temps= "45;30";
            Lasagnes.filtre_origine = "Italien";
            db.textDao().insertText(Lasagnes);

            TextEntity pate_carbo = new TextEntity();
            pate_carbo.nom_recette = "Pates Carbonara";
            pate_carbo.nbr_personnes = 2;
            pate_carbo.qt_lardons = 62*pate_carbo.nbr_personnes;
            pate_carbo.qt_pates = 125*pate_carbo.nbr_personnes;
            pate_carbo.qt_oignons = 1*pate_carbo.nbr_personnes;
            pate_carbo.qt_creme_fraiche = 12 * pate_carbo.nbr_personnes;
            pate_carbo.qt_jaune_doeuf = 1 * pate_carbo.nbr_personnes;
            pate_carbo.ingredients = "• Sel\n• Poivre\n• Oignons : " + pate_carbo.qt_oignons + "g \n• Pâtes de votre choix : "+pate_carbo.qt_pates+"g \n• Lardons :"+pate_carbo.qt_lardons+"g\n• Crème fraîche :" + pate_carbo.qt_creme_fraiche + "cl\n• Jaune d'oeuf (quantité : " +pate_carbo.qt_jaune_doeuf + ")\n\n";
            pate_carbo.recette = "ÉTAPE 1\nCuire les pâtes dans un grand volume d'eau bouillante salée.\n\nÉTAPE 2\nEmincer les oignons et les faire revenir à la poêle. Dès qu'ils ont bien dorés, y ajouter les lardons.\n\nÉTAPE 3\nPréparer dans un saladier la crème fraîche, les oeufs, le sel, le poivre et mélanger.\n\nÉTAPE 4\nRetirer les lardons du feu dès qu'ils sont dorés et les ajouter à la crème.\n\nÉTAPE 5\nUne fois les pâtes cuite al dente, les égoutter et y incorporer la crème. Remettre sur le feu si le plat a refroidi.\n\nÉTAPE 6\nServir et bon appétit ! Vous pouvez également agrémenter votre plat avec des champignons.\n\n ";
            pate_carbo.filtres_temps= "20;30";
            pate_carbo.filtres = "Sans Alcool";
            pate_carbo.filtre_origine="Italien";
            db.textDao().insertText(pate_carbo);

            TextEntity tiramisu = new TextEntity();
            tiramisu.nom_recette = "Tiramisu";
            tiramisu.nbr_personnes = 2;
            tiramisu.qt_cacao = 8*tiramisu.nbr_personnes;
            tiramisu.qt_biscuit_cuillere = 6*tiramisu.nbr_personnes;
            tiramisu.qt_mascarpone = 62*tiramisu.nbr_personnes;
            tiramisu.qt_sucre = 25 * tiramisu.nbr_personnes;
            tiramisu.qt_oeuf = 1 * tiramisu.nbr_personnes;
            tiramisu.qt_sucre_vanille = 1 * tiramisu.nbr_personnes;
            tiramisu.qt_cafe_noir = 12 * tiramisu.nbr_personnes;
            tiramisu.ingredients = "• Cacao Amer : "+tiramisu.qt_cacao+" g\n• Biscuit cuillères : quantité "+tiramisu.qt_biscuit_cuillere+"\n• Sucre : " + tiramisu.qt_sucre + "g \n• Sucre Vanillé : "+tiramisu.qt_sucre_vanille+" sachet \n• Oeufs :"+tiramisu.qt_oeuf+"\n• Mascarpone  :" + tiramisu.qt_mascarpone + "g\n• Café Noir : " +tiramisu.qt_cafe_noir + " cl\n\n";
            tiramisu.recette = "ÉTAPE 1\nSéparer les blancs des jaunes d'oeufs. \n\nÉTAPE 2\nMélanger les jaunes avec le sucre roux et le sucre vanillé\n\nÉTAPE 3\nAjouter le mascarpone au fouet.\n\nÉTAPE 4\nMonter les blancs en neige et les incorporer délicatement à la spatule au mélange précédent. Réserver.\n\nÉTAPE 5\nMouiller les biscuits dans le café rapidement avant d'en tapisser le fond du plat.\n\nÉTAPE 6\nRecouvrir d'une couche de crème au mascarpone puis répéter l'opération en alternant couche de biscuits et couche de crème en terminant par cette dernière.\n\nÉTAPE 7\nSaupoudrer de cacao.\n\nÉTAPE 7\nMettre au réfrigérateur 4 heures minimum puis déguster frais.\n\n ";
            tiramisu.filtres_temps= "15";
            tiramisu.filtres = "Sans Alcool ; Halal";
            tiramisu.filtre_origine = "Italien";
            db.textDao().insertText(tiramisu);


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