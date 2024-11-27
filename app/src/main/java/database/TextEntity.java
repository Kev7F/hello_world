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
    public int qt_lardons;
    public int qt_jaune_doeuf;
    public int qt_oignons;
    public int qt_creme_fraiche;
    public int qt_pates;
    public int qt_huile;
    public int qt_cacao;
    public int qt_biscuit_cuillere;
    public int qt_sucre;
    public int qt_oeuf;
    public int qt_sucre_vanille;
    public int qt_mascarpone;
    public int qt_cafe_noir;












    //Ingrédients pour pate carbo

    //filtre_temps = "temps_preparation;temps_cuisson"
    public String filtres_temps;
    public String filtres;

    //filtre_origine : selectionner l'origine du plat voulu".
    public String filtre_origine;

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