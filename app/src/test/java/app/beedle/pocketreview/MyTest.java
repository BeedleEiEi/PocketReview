package app.beedle.pocketreview;

import android.content.Intent;

import org.junit.Test;

import app.beedle.pocketreview.model.entity.NoteEntity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyTest {
    @Test
    public void checkEntityValues() {
        //Set
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(1);
        noteEntity.setName("Test");
        noteEntity.setDesc("Description");
        noteEntity.setDetail("Hotel Rent");
        noteEntity.setAmount("20");
        noteEntity.setRating(0);
        noteEntity.setTotal(Float.parseFloat(noteEntity.getAmount()));
        noteEntity.setCurrency("THB");

        //Test
        assertEquals(noteEntity.getId(), 1);
        assertEquals(noteEntity.getName(), "Test");
        assertEquals(noteEntity.getDesc(), "Description");
        assertEquals(noteEntity.getDetail(), "Hotel Rent");
        assertEquals(noteEntity.getAmount(), "20");
        assertEquals(noteEntity.getRating(), 0);
        assertEquals(noteEntity.getTotal(), noteEntity.getTotal());
        assertEquals(noteEntity.getCurrency(), "THB");
    }


}