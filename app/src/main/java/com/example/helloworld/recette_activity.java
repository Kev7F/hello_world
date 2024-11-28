package com.example.helloworld;



import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import database.AppDatabase;
import database.TextEntity;

public class recette_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette_activity_layout);

        // Récupérer le nom de la recette transmis via l'intention
        String recipeName = getIntent().getStringExtra("recipeName");
        String compteur_value = getIntent().getStringExtra("compteur_value");

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
                    TextView nbr_personne = findViewById(R.id.nbr_personnes);
                    TextView etapesView = findViewById(R.id.etapes_recette);

                    // Remplir les TextView avec les données
                    titreView.setText("Recette : " + titre);
                    ingredientsView.setText(ingredients);
                    nbr_personne.setText("Nombre de personnes : " + compteur_value);
                    etapesView.setText(etapes);
                });
            } else {
                // Gérer le cas où aucune recette n'est trouvée (si besoin)
                runOnUiThread(() -> {
                    TextView titreView = findViewById(R.id.titre_recette_activity);
                    titreView.setText("Recette introuvable.");
                });
            }
        }).start();

        //Afficher la flèche de retour en haut à gauche pour revenir a l'activité précédente
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Afficher la flèche de retour
        }
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
