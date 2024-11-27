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