// Une entité dans Room représente une table dans votre base de données SQLite
// Chaque entité est une classe Java annotée avec @Entity et ses
// propriétés correspondent aux colonnes de la table
// L'annotation @PrimaryKey sert à désigner la colonne clé primaire.

package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import androidx.room.TypeConverter;

@Entity
public class TextEntity {
    @PrimaryKey(autoGenerate = true)
    public int id; // Identifiant unique pour chaque ligne
    public String nom_recette; // Contenu texte
    public int nbr_personnes;
    public int qt_boeuf;
    public int qt_tomate;
    public double qt_champignon;
    public String ingredients;
    public String recette;
    //filtre_temps = "temps_preparation;temps_cuisson"
    public String filtres_temps;
    public String filtres;

    public String getNomRecette() {
        return nom_recette;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getRecette() {
        return recette;
    }
}