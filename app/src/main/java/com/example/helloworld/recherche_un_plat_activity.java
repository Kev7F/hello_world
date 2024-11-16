package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class recherche_un_plat_activity extends AppCompatActivity {

    private TextView affichageCompteur;
    private int compteur;
    String[] filtres = {"Végétarien", "Végan", "Halal", "Kasher", "Sans Alcool", "Sans Lactose"};

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_un_plat);

        // ----------- Gestion de l'affichage de la recherche de l'utilisateur -----------------
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.plat_recherche_par_utilisateur);
        textView.setText(message);

        // ----------- Gestion du compteur -----------------
        affichageCompteur = findViewById(R.id.valeur_du_compteur);
        Button bouttonMoins = findViewById(R.id.boutton_moins);
        Button bouttonPlus = findViewById(R.id.boutton_plus);

        //augmentation de la valeur compteur quand j'appuie sur le +
        bouttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                compteur++;
                affichageCompteur.setText(String.valueOf(compteur));
            }
        });

        //diminution de la valeur compteur quand j'appuie sur le -
        bouttonMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                compteur--;
                affichageCompteur.setText(String.valueOf(compteur));
            }
        });

        // ----------- Gestion de la liste de filtre -----------------
        Spinner liste_de_filtre = findViewById(R.id.liste_filtres);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filtres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liste_de_filtre.setAdapter(adapter);

    }
}