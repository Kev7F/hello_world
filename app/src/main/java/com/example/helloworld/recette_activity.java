package com.example.helloworld;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import database.AppDatabase;
import database.TextEntity;

public class recette_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette_activity_layout);

        // Récupérer le nom de la recette transmis via l'intention
        String recipeName = getIntent().getStringExtra("recipeName");

        // Initialiser la base de données
        AppDatabase db = AppDatabase.getInstance(this);

        // Récupérer les données associées à la recette dans un thread séparé
        new Thread(() -> {
            TextEntity recette = db.textDao().getEntityByName(recipeName);

            // Assurez-vous que la recette existe avant d'utiliser ses données
            if (recette != null) {
                String ingredients = recette.getIngredients();
                String etapes = recette.getRecette();
                String titre = recette.getNomRecette();

                // Mettre à jour l'UI sur le thread principal
                runOnUiThread(() -> {
                    TextView titreView = findViewById(R.id.titre_recette_activity);
                    TextView ingredientsView = findViewById(R.id.ingredients);
                    TextView etapesView = findViewById(R.id.etapes_recette);

                    // Remplir les TextView avec les données
                    titreView.setText("Recette : " + titre);
                    ingredientsView.setText("Ingrédients : \n" + ingredients);
                    etapesView.setText("Étapes : \n" + etapes);
                });
            } else {
                // Gérer le cas où aucune recette n'est trouvée (si besoin)
                runOnUiThread(() -> {
                    TextView titreView = findViewById(R.id.titre_recette_activity);
                    titreView.setText("Recette introuvable.");
                });
            }
        }).start();
    }
}
