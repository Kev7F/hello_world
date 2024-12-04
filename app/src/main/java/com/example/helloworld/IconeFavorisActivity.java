package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import database.AppDatabase;
import database.TextEntity;

public class IconeFavorisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        // Initialisation de la BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Gérer les clics sur le menu de navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Ouvre l'activité RechercheActivity
                        startActivity(new Intent(IconeFavorisActivity.this, MainActivity.class));
                        return true;
                    case R.id.nav_search:
                        // Ouvre l'activité PropositionActivity
                        startActivity(new Intent(IconeFavorisActivity.this, IconePropositionActivity.class));
                        return true;
                    case R.id.nav_profile:
                        // Ouvre l'activité FavorisActivity
                        startActivity(new Intent(IconeFavorisActivity.this, IconeFavorisActivity.class));
                        return true;
                }
                return false;
            }
        });

        AppDatabase db = AppDatabase.getInstance(this);

        new Thread(() -> {
            // Récupérer les noms des recettes depuis la base de données
            List<String> recipeNames = db.textDao().getAllRecipeNames();

            // Supprimer les doublons
            List<String> uniqueRecipeNames = new ArrayList<>(new HashSet<>(recipeNames));
            LinearLayout containerRecipes = findViewById(R.id.container_fav);

            List<TextEntity> fav_recette = db.textDao().getFavoriteRecipes();
            List<String> fav_recette_strings = new ArrayList<>();
            for (TextEntity recipe : fav_recette) {
                fav_recette_strings.add(recipe.getNomRecette());
            }


            // Ajout de TextViews dynamiques dans le conteneur
            runOnUiThread(() -> {
                //container_fav.removeAllViews();


                // Vider le conteneur avant d'ajouter de nouveaux éléments

                // Filtrer et afficher uniquement la recette correspondant à la recherche
                for (String name : fav_recette_strings) {
                    // Si le nom de la recette correspond au texte recherché
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
                        //addToFavoritesButton.setBackgroundColor(getResources().getColor(android.R.color.transparent)); // Testez un fond transparent


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
                            startActivity(intent_details);
                        });

                        // Ajouter les vues au LinearLayout
                        recipeLayout.addView(recipeTextView);
                        recipeLayout.addView(addToFavoritesButton);
                        recipeLayout.addView(detailsButton);

                        // Ajouter le LinearLayout au conteneur principal
                        containerRecipes.addView(recipeLayout);

                }
            });
        }).start();



    }
}
