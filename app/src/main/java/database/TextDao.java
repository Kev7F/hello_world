// Le DAO est une interface qui définit les méthodes pour accéder à la base de données.

package database;

import static java.lang.invoke.VarHandle.AccessMode.SET;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TextDao {
    @Insert
    void insertText(TextEntity textEntity);

    @Query("SELECT ingredients FROM TextEntity WHERE nom_recette = :recipeName LIMIT 1")
    String getIngredientsByRecipeName(String recipeName);

    @Query("SELECT nom_recette FROM TextEntity")
    List<String> getAllRecipeNames();

    @Query("SELECT * FROM TextEntity WHERE favoris = true")
    List<TextEntity> getFavoriteRecipes();

    @Query("SELECT favoris FROM TextEntity WHERE nom_recette = :nomRecette LIMIT 1")
    boolean getFavoris(String nomRecette);

    @Update
    void updateRecipe(TextEntity textEntity);

    @Query("SELECT * FROM TextEntity WHERE nom_recette = :nomRecette LIMIT 1")
    TextEntity getRecipeByName(String nomRecette);

    @Query("SELECT * FROM TextEntity WHERE nom_recette = :nomRecette LIMIT 1")
    TextEntity getEntityByName(String nomRecette);

}
