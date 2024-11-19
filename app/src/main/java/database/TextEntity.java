// Une entité dans Room représente une table dans votre base de données SQLite
// Chaque entité est une classe Java annotée avec @Entity et ses
// propriétés correspondent aux colonnes de la table
// L'annotation @PrimaryKey sert à désigner la colonne clé primaire.

package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TextEntity {
    @PrimaryKey(autoGenerate = true)
    public int id; // Identifiant unique pour chaque ligne
    public String content; // Contenu texte
}
