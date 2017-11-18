package app.beedle.pocketreview.entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Beedle on 18/11/2560.
 */

@Dao
public interface NoteDao {
    @Query("SELECT * FROM NOTE")
    List<NoteEntity> getAll();

    @Insert
    void insert(NoteEntity noteEntity);


    @Delete
    void delete(NoteEntity recordInfo);

    @Query("DELETE FROM NOTE")
    void deleteAll();
}
