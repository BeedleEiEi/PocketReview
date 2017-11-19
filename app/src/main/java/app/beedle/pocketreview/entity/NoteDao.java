package app.beedle.pocketreview.entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    List<NoteEntity> getAll();

    @Insert
    void insert(NoteEntity noteEntity);


    @Delete
    void deleteNoteRecord(NoteEntity recordInfo);

    @Delete
    void deleteNoteAll(List<NoteEntity> recordInfo);
}
