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
}
