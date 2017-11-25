package app.beedle.pocketreview.model.entity;

/**
 * Created by Beedle on 18/11/2560.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteRoomDAO();
}
