// On créer une base de données qui utilise Room et contient votre DAO

package database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

@Database(entities = {TextEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TextDao textDao();

    private static AppDatabase instance; // Instance unique de la base de données

    // Méthode pour obtenir l'instance unique
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "my-database").build();
        }
        return instance;
    }
}
