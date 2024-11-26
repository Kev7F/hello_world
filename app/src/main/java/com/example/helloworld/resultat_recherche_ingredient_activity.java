package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class resultat_recherche_ingredient_activity extends AppCompatActivity
{
    private TextView affichageCompteur;
    private int compteur = 2;

    String[] filtres2 = {"Italien", "Arabe", "Turc", "Français", "Japonais", "Méxicain"};
    String[] temps_preparation = {"Entre 1min et 15 min", "Entre 15min et 30 min", "Entre 30 min et 1h",  "Plus d'1h"};

    // tableau de boolean permettant d'identifier le statut de chaque filtre: faux si le filtre n'est pas sélectionner, true si il est sélectionné
    private boolean[] statut_filtre_selectionne2 = new boolean[filtres2.length];


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat_recherche_ingredient);

        // ----------- Gestion de l'affichage de la recherche de l'utilisateur -----------------
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String ingredient_recherche = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.ingredient_recherche_par_utilisateur);
        textView.setText(ingredient_recherche);

        // ----------- Gestion du compteur -----------------
        affichageCompteur = findViewById(R.id.valeur_du_compteur2);
        Button bouttonMoins = findViewById(R.id.boutton_moins2);
        Button bouttonPlus = findViewById(R.id.boutton_plus2);

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
        Button boutonFiltres = findViewById(R.id.boutton_filtres2);
        //affichage de la boite de dialogue qui contient le filtres selectionnables quand on clique sur le bouton
        boutonFiltres.setOnClickListener(v -> afficherOriginDialog());

        //----------- Gestion du temps de preparation voulu -----------------

        // boutton pour afficher le les temps de preparation, affichage de la boite de dialogue quand on clique sur le bouton
        Button boutonTemps = findViewById(R.id.bouton_temps_prepa);
        //affichage de la boite de dialogue qui contient le filtres selectionnables quand on clique sur le bouton
        boutonTemps.setOnClickListener(v -> afficherTimeDialog());

    }

    // Méthode qui va permettre d'afficher la boite de dialogue contenant les filtres selectionnables
    private void afficherOriginDialog()
    {
        AlertDialog.Builder fenetre_dialogue = new AlertDialog.Builder(this);
        fenetre_dialogue.setTitle("Sélectionner pour filtrer les résultats : ");

        // Variable pour garder la sélection actuelle
        final int[] selection_actuelle = {-1}; // -1 : pas sélection initiale

        // création de la liste des filtres avec setmultichoiceitems, plusieur filtres peuvent être selectionnés en même temps
        fenetre_dialogue.setSingleChoiceItems(filtres2, -1, (dialog, which) -> {
            // Mettre à jour la sélection actuelle lorsqu'un filtre est sélectionné
            selection_actuelle[0] = which;
        });

        // Enregistrer le filtre sélectionné
        fenetre_dialogue.setPositiveButton("OK", (dialog, which) -> {
            // si une sélection a été faite on affiche la selection avec un toast
            if (selection_actuelle[0] != -1)
            {
                String filtreSelectionne = filtres2[selection_actuelle[0]];
                Toast.makeText(this, "Origine du plat voulu : " + filtreSelectionne, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Pas de préférence", Toast.LENGTH_SHORT).show();
            }
        });

        // Annuler pour  fermmer la fenetre sans prendre en compte les filtres sélectionné, on reste dans le même état qu'avant d'ouvrir la fenetre de filtres
        fenetre_dialogue.setNegativeButton("Annuler", null);

        fenetre_dialogue.create().show();
    }

    // Méthode qui va permettre d'afficher la boite de dialogue contenant les filtres selectionnables
    private void afficherTimeDialog()
    {
        AlertDialog.Builder fenetre_dialogue = new AlertDialog.Builder(this);
        fenetre_dialogue.setTitle("Combien de temps pour cuisiner : ");

        // Variable pour garder la sélection actuelle
        final int[] selection_actuelle = {-1}; // -1 : pas sélection initiale

        // création de la liste des filtres avec setmultichoiceitems, plusieur filtres peuvent être selectionnés en même temps
        fenetre_dialogue.setSingleChoiceItems(temps_preparation, -1, (dialog, which) -> {
            // Mettre à jour la sélection actuelle lorsqu'un filtre est sélectionné
            selection_actuelle[0] = which;
        });

        // Enregistrer le filtre sélectionné
        fenetre_dialogue.setPositiveButton("OK", (dialog, which) -> {
            // si une sélection a été faite on affiche la selection avec un toast
            if (selection_actuelle[0] != -1)
            {
                String filtreSelectionne = temps_preparation[selection_actuelle[0]];
                Toast.makeText(this, "Origine du plat voulu : " + filtreSelectionne, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Pas de préférence", Toast.LENGTH_SHORT).show();
            }
        });

        // Annuler pour  fermmer la fenetre sans prendre en compte les filtres sélectionné, on reste dans le même état qu'avant d'ouvrir la fenetre de filtres
        fenetre_dialogue.setNegativeButton("Annuler", null);

        fenetre_dialogue.create().show();
    }

}

