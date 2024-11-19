// On créer une base de données qui utilise Room et contient votre DAO

package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TextEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TextDao textDao();
}
