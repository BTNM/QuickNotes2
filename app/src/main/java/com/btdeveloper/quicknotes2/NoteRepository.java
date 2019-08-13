package com.btdeveloper.quicknotes2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository provides another abstraction layer between the different data sources(sql database, internet api's etc) and the rest of the app
 * differentiate from different sources, fetch which data from where and handle api calls,and cache it locally to the sql database
 * the repository is a way to abstract all these data source operations, and provide a clean api to the rest of the app
 * the viewmodel doesn't have to care where data comes from or how its fetched and only have to call repository directly
 */
public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;

    /**
     * apllication is a subclass of context, can be used as context to create database instance
     * @param application
     */
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO(); // Room autogenerate body for this method because we used its builder method in notedao, for this abstract method
        allNotes = noteDAO.getAllNotes(); // Room autogenerate code for this method from the interface

    }
    //methods that the api repository exposes to the outside, viewmodel only has to use these methods on repository without care for how data is fetched
    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDAO).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }

    public void deleteAllNotes () {
        new DeleteAllNoteAsyncTask(noteDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes () {
        //room auto generate all code for noteDao.getAllNotes, will automatically execute the database operations that returns the livedata on a background thread
        return allNotes;
    }

    /**
     * Other database operations have to execute code on a background thread manually, since not allowed on the main thread
     * multiple Async tasks as inner class
     * Have to create an AsyncTask for each database operation
     */
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        // static so cant be access the noteRepository directly for database operations, so pass over constructor
        private NoteDAO noteDAO;

        private InsertNoteAsyncTask (NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0] );

            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        // static so cant be access the noteRepository directly for database operations, so pass over constructor
        private NoteDAO noteDAO;

        private UpdateNoteAsyncTask (NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0] );

            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        // static so cant be access the noteRepository directly for database operations, so pass over constructor
        private NoteDAO noteDAO;

        private DeleteNoteAsyncTask (NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0] );

            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        // static so cant be access the noteRepository directly for database operations, so pass over constructor
        private NoteDAO noteDAO;

        private DeleteAllNoteAsyncTask (NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            noteDAO.deleteAllNotes( );

            return null;
        }
    }

}
