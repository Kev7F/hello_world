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

import java.util.ArrayList;
import java.util.List;

public class HistoriqueBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historique, container, false);

        // Obtenir la ListView pour afficher l'historique
        ListView listView = view.findViewById(R.id.liste_historique);

        // Ajouter des exemples d'actions dans l'historique
        List<String> historique = new ArrayList<>();
        historique.add("Recette 1 ajoutée aux favoris");
        historique.add("Recette 2 consultée");
        historique.add("Recette 3 modifiée");
        historique.add("Recette 4 partagée");

        // Adapter pour afficher les données
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, historique);
        listView.setAdapter(adapter);

        return view;
    }
}
