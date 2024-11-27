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



import java.util.ArrayList;
import java.util.List;

public class HistoriqueBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historique, container, false);

        // Récupérer la ListView pour afficher l'historique
        ListView listView = view.findViewById(R.id.liste_historique);


        // Lire les recherches enregistrées dans SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Historique", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        List<String> historique = new ArrayList<>();

        // Ajouter chaque recherche à la liste
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Ajouter les éléments (les valeurs) à l'historique
            historique.add((String) entry.getValue());
        }

        // Créer un adaptateur pour afficher l'historique dans la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, historique);
        listView.setAdapter(adapter);

        return view;
    }
}
