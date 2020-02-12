package com.example.architectureexample.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) { //Context
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDao = noteDataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new ProcessAsyncTask<Note>(noteDao, ProcessType.INSERT).execute(note);
    }

    public void update(Note note) {
        new ProcessAsyncTask<Note>(noteDao, ProcessType.UPDATE).execute(note);
    }

    public void delete(Note note) {
        new ProcessAsyncTask<Note>(noteDao, ProcessType.DELETE).execute(note);
    }

    public void deleteAllNotes() {
        new ProcessAsyncTask<Void>(noteDao, ProcessType.DELETE_ALL).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class ProcessAsyncTask<T> extends AsyncTask<T, Void, Void> {
        private NoteDao noteDao;
        private ProcessType processType;

        private ProcessAsyncTask(NoteDao noteDao, ProcessType processType) {
            this.noteDao = noteDao;
            this.processType = processType;
        }

        @Override
        protected Void doInBackground(T... notes) {
            switch (processType){
                case INSERT:
                    noteDao.insert((Note) notes[0]);
                case UPDATE:
                    noteDao.update((Note) notes[0]);
                case DELETE:
                    noteDao.delete((Note) notes[0]);
                case DELETE_ALL:
                    noteDao.deleteAllNotes();
            }
            return null;
        }
    }
 }

 enum ProcessType {
     INSERT,
     UPDATE,
     DELETE,
     DELETE_ALL
 }