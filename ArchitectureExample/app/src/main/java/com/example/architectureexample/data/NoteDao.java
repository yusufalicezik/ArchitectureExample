package com.example.architectureexample.data;

import com.example.architectureexample.data.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("Delete from note_table")
    void deleteAllNotes();

    @Query("Select * from note_table order by priority_level desc")
    LiveData<List<Note>> getAllNotes();
}
