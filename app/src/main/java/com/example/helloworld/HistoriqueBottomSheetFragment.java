package com.example.helloworld;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import android.widget.AdapterView;
import android.widget.Toast;




import java.util.ArrayList;
import java.util.List;

public class HistoriqueBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historique, container, false);

        // Récupérer la ListView pour afficher l'historique
        ListView listView = view.findViewById(R.id.liste_historique);

        // Liste des clés (pour supprimer les éléments spécifiques)
        List<String> keys = new ArrayList<>();

        // Lire les recherches enregistrées dans SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Historique", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        List<String> historique = new ArrayList<>();

        // Ajouter chaque recherche à l'historique et les clés correspondantes à 'keys'
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Ajouter les éléments (les valeurs) à l'historique
            historique.add((String) entry.getValue());
            // Ajouter la clé (entry.getKey()) correspondante à la liste 'keys'
            keys.add(entry.getKey());
        }

        // Créer un adaptateur pour afficher l'historique dans la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, historique);
        listView.setAdapter(adapter);

        // Gérer l'événement d'appui long sur un élément de la liste
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer la clé de l'élément sélectionné à partir de 'keys'
                String keyToRemove = keys.get(position);

                // Supprimer l'élément correspondant dans SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(keyToRemove);  // Supprimer l'élément spécifique
                editor.apply();

                // Supprimer l'élément de la liste locale et notifier l'adaptateur pour rafraîchir la vue
                historique.remove(position);
                keys.remove(position);  // Supprimer également la clé correspondante
                adapter.notifyDataSetChanged();

                // Afficher un message confirmant la suppression
                Toast.makeText(getContext(), "Recherche supprimée", Toast.LENGTH_SHORT).show();

                return true;  // Retourner true pour indiquer que l'événement a été géré
            }
        });

        return view;
    }
}
