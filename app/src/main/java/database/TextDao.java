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

    @Query("SELECT * FROM TextEntity WHERE favoris = 1")
    List<TextEntity> getFavoriteRecipes();

    @Query("SELECT favoris FROM TextEntity WHERE nom_recette = :nomRecette LIMIT 1")
    boolean getFavoris(String nomRecette);

    @Update
    void updateTextEntity(TextEntity entity);



    @Query("UPDATE TextEntity " +
            "SET nbr_personnes = :nbrPersonnes, " +
            "    qt_boeuf = qt_boeuf * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_tomate = qt_tomate * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_champignon = qt_champignon * :nbrPersonnes * 1.0 / nbr_personnes, " +
            "    qt_lardons = qt_lardons * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_jaune_doeuf = qt_jaune_doeuf * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_oignons = qt_oignons * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_creme_fraiche = qt_creme_fraiche * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_pates = qt_pates * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_huile = qt_huile * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_cacao = qt_cacao * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_biscuit_cuillere = qt_biscuit_cuillere * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_sucre = qt_sucre * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_oeuf = qt_oeuf * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_sucre_vanille = qt_sucre_vanille * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_mascarpone = qt_mascarpone * 1.0 * :nbrPersonnes / nbr_personnes, " +
            "    qt_cafe_noir = qt_cafe_noir * 1.0 * :nbrPersonnes / nbr_personnes " +
            "WHERE nom_recette = :nomRecette")
    void updateNbrPersonnesByName(String nomRecette, int nbrPersonnes);


    @Query("SELECT * FROM TextEntity WHERE nom_recette = :nomRecette LIMIT 1")
    TextEntity getEntityByName(String nomRecette);

}
