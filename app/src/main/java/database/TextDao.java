// Le DAO est une interface qui définit les méthodes pour accéder à la base de données.

package database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TextDao {
    @Insert
    void insertText(TextEntity textEntity);

    @Query("SELECT * FROM TextEntity")
    List<TextEntity> getAllTexts();

    @Query("SELECT nom_recette FROM TextEntity")
    List<String> getAllRecipeNames();

    @Query("SELECT nom_recette FROM TextEntity WHERE id = :id")
    String getNomRecetteById(int id);

    // Récupérer le champ "nbr_personnes" par id
    @Query("SELECT nbr_personnes FROM TextEntity WHERE id = :id")
    int getNbrPersonnesById(int id);

    // Récupérer le champ "ingredients" par id
    @Query("SELECT ingredients FROM TextEntity WHERE id = :id")
    String getIngredientsById(int id);

    // Récupérer le champ "recette" par id
    @Query("SELECT recette FROM TextEntity WHERE id = :id")
    String getRecetteById(int id);

    // Récupérer le champ "filtres_temps" par id
    @Query("SELECT filtres_temps FROM TextEntity WHERE id = :id")
    String getFiltresTempsById(int id);

    // Récupérer le champ "filtres" par id
    @Query("SELECT filtres FROM TextEntity WHERE id = :id")
    String getFiltresById(int id);

    @Query("SELECT * FROM TextEntity WHERE id = :id")
    TextEntity getEntityById(int id);

    @Query("SELECT * FROM TextEntity WHERE nom_recette = :nomRecette LIMIT 1")
    TextEntity getEntityByName(String nomRecette);

}
