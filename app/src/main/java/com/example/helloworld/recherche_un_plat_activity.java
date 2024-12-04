package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
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

        //Afficher la flèche de retour en haut à gauche pour revenir a l'activité précédente
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Récupération de la base de données
        AppDatabase db = AppDatabase.getInstance(this);

        //Fonction permettant de récupérer tout les noms de recettes dans une liste
        new Thread(() -> {
            List<String> recipeNames = db.textDao().getAllRecipeNames();

            // Retirer les doublons en utilisant un HashSet
            recipeNames = new ArrayList<>(new HashSet<>(recipeNames));

            // Si besoin, convertir en une chaîne séparée par ";"
            String namesAsString = String.join(";", recipeNames);

            // Imprimer les noms dans la console
            System.out.println("Noms des recettes : " + namesAsString);
        }).start();


        // ----------- Gestion de l'affichage de la recherche de l'utilisateur -----------------
        Intent intent = getIntent();
        String recette_recherchee = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

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

        //Définition de l'action du bouton appliquer
        Button btnApply = findViewById(R.id.appliquer);
        btnApply.setOnClickListener(v -> {

            String recherche = textView.getText().toString().trim().toLowerCase();
            LinearLayout containerRecipes = findViewById(R.id.container_recipes);
            new Thread(() -> {
                // Récupérer les noms des recettes depuis la base de données
                List<String> recipeNames = db.textDao().getAllRecipeNames();

                // Supprimer les doublons
                List<String> uniqueRecipeNames = new ArrayList<>(new HashSet<>(recipeNames));

                // Ajout de TextViews dynamiques dans le conteneur
                runOnUiThread(() -> {
                    // Vider le conteneur avant d'ajouter de nouveaux éléments
                    containerRecipes.removeAllViews();

                    // Filtrer et afficher uniquement la recette correspondant à la recherche
                    for (String name : uniqueRecipeNames) {
                        // Si le nom de la recette correspond au texte recherché
                        if (name.toLowerCase().equals(recherche)) {
                            // Créer un LinearLayout horizontal pour chaque recette
                            LinearLayout recipeLayout = new LinearLayout(this);
                            recipeLayout.setOrientation(LinearLayout.HORIZONTAL);

                            // Appliquer des marges/paddings si nécessaire
                            LinearLayout.LayoutParams recipeLayoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            recipeLayoutParams.setMargins(0, 16, 0, 16);
                            recipeLayout.setLayoutParams(recipeLayoutParams);

                            // 1. Créer un TextView pour le nom de la recette
                            TextView recipeTextView = new TextView(this);
                            recipeTextView.setText(name);
                            recipeTextView.setTextSize(18);
                            recipeTextView.setTextColor(getResources().getColor(R.color.marron));

                            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                                    0,
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    1.0f // Pour prendre l'espace disponible
                            );
                            recipeTextView.setLayoutParams(textViewParams);

                            // 2. Créer un ImageButton pour ajouter aux favoris
                            ImageButton addToFavoritesButton = new ImageButton(this);
                            addToFavoritesButton.setImageResource(R.drawable.ic_favoris); // Icône des favoris
                            addToFavoritesButton.setBackgroundResource(R.drawable.ic_rechercher_boutton_background);


                            // Ajouter un OnClickListener pour détecter le clic sur le bouton
                            addToFavoritesButton.setOnClickListener(t -> {
                                // Affichage du toast pour tester si le clic est capté
                                Toast.makeText(this, "Ajouté aux favoris", Toast.LENGTH_SHORT).show();

                                // Liste/ tableau avec l'id de la recette
                                new Thread(() -> {
                                    TextEntity recipe = db.textDao().getRecipeByName(name);

                                    if (recipe != null) {
                                        // Inverser la valeur de favoris
                                        recipe.favoris = !recipe.favoris;

                                        // Mettre à jour la base de données
                                        db.textDao().updateRecipe(recipe);
                                    }
                                }).start();
                            });



                            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            buttonParams.setMargins(8, 0, 16, 0);
                            addToFavoritesButton.setLayoutParams(buttonParams);


                            // 3. Créer un bouton "Détails"
                            Button detailsButton = new Button(this);
                            detailsButton.setText("Détails");
                            detailsButton.setTextColor(getResources().getColor(R.color.marron));

                            detailsButton.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            ));

                            // Ajouter un écouteur de clic pour le bouton "Détails"
                            detailsButton.setOnClickListener(btn -> {
                                Toast.makeText(this, "Détails pour " + name, Toast.LENGTH_SHORT).show();
                                Intent intent_details = new Intent(this, recette_activity.class); // Remplacez par le nom réel de votre classe
                                intent_details.putExtra("recipeName", name);
                                intent_details.putExtra("compteur_value", String.valueOf(compteur));
                                startActivity(intent_details);
                            });

                            // Ajouter les vues au LinearLayout
                            recipeLayout.addView(recipeTextView);
                            recipeLayout.addView(addToFavoritesButton);
                            recipeLayout.addView(detailsButton);

                            // Ajouter le LinearLayout au conteneur principal
                            containerRecipes.addView(recipeLayout);
                        }
                    }
                });
            }).start();
    });

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


    //Méthode pour gérer la flèche de retour à l'activité précédente.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Si l'utilisateur clique sur la flèche de retour, retour à l'activité précédente
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}