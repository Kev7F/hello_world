package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import database.AppDatabase;
import database.TextEntity;


public class recherche_un_plat_activity extends AppCompatActivity
{

    private TextView affichageCompteur;
    private int compteur = 2; //recette pour 2 personnes par defaut.
    String[] filtres = {"Végétarien", "Végan", "Halal", "Kasher", "Sans Alcool", "Sans Lactose"};

    // tableau de boolean permettant d'identifier le statut de chaque filtre: faux si le filtre n'est pas sélectionner, true si il est sélectionné
    private boolean[] statut_filtre_selectionne = new boolean[filtres.length];
    

    @Override
    //La fonction OnCreate est la fonction qui est directement lancé après l'avoir créer
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_un_plat);

        //Récupération de la base de données
        AppDatabase db = AppDatabase.getInstance(this);
        new Thread(() -> {
            List<TextEntity> recettes = db.textDao().getAllTexts(); // Lecture des données
            // Affiche ou utilise les données récupérées (sur un autre thread)
            for (TextEntity recette : recettes) {
                System.out.println(recette.nom_recette);
            }
        }).start();

        // ----------- Gestion de l'affichage de la recherche de l'utilisateur -----------------
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String recette_recherchee = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.plat_recherche_par_utilisateur);
        textView.setText(recette_recherchee);

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
        bouttonMoins.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                compteur--;
                if (compteur<=0)
                {
                    compteur = 0 ;
                }
                affichageCompteur.setText(String.valueOf(compteur));
            }
        });

        // ----------- Gestion de la liste de filtre -----------------

        // boutton pour afficher le menu des filtres, affichage de la boite de dialogue quand on clique sur le bouton
        Button boutonFiltres = findViewById(R.id.boutton_filtres);
        //affichage de la boite de dialogue qui contient le filtres selectionnables quand on clique sur le bouton
        boutonFiltres.setOnClickListener(v -> afficherFiltreDialog());


    }

    // Méthode qui va permettre d'afficher la boite de dialogue contenant les filtres selectionnables
    private void afficherFiltreDialog()
    {
        AlertDialog.Builder fenetre_dialogue = new AlertDialog.Builder(this);
        fenetre_dialogue.setTitle("Sélectionner pour filtrer les résultats : ");

        // création de la liste des filtres avec setmultichoiceitems, plusieur filtres peuvent être selectionnés en même temps
        fenetre_dialogue.setMultiChoiceItems(filtres, statut_filtre_selectionne, (dialog, which, isChecked) -> {
            //quand on appuie sur un filtre, son statut change (false si on désélectionne, true si on sélectionne)
            statut_filtre_selectionne[which] = isChecked;
        });

        // enregistrer les filtres sélectionnés
        fenetre_dialogue.setPositiveButton("OK", (dialog, which) -> {
            // afficher filtres sélectionnés avec un toast
            StringBuilder filtres_selectionnes = new StringBuilder("Filtres sélectionnés : ");
            for (int i = 0; i < filtres.length; i++)
            {
                if (statut_filtre_selectionne[i])
                {
                    filtres_selectionnes.append(filtres[i]).append(", ");
                }
            }

            Toast.makeText(this, filtres_selectionnes.toString(), Toast.LENGTH_SHORT).show();
        });

        // Annuler pour  fermmer la fenetre sans prendre en compte les filtres sélectionné, on reste dans le même état qu'avant d'ouvrir la fenetre de filtres
        fenetre_dialogue.setNegativeButton("Annuler", null);

        fenetre_dialogue.create().show();
    }

}