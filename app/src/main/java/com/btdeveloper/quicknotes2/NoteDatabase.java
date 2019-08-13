package com.btdeveloper.quicknotes2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    //create singleton so that it can be accessed everywhere from the app
    private static NoteDatabase instance;

    //abstract for we dont implement the method body,
    //use noteDAO for later access database operations methods that added to noteDAO interface
    public abstract NoteDAO noteDAO();

    //singleton, synchronized so that only 1 thread can access it at a time
    //migration for how to migrate for version number
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration() // deletes the database and recreate it from scratch
                    .addCallback(roomCallback)// populate with some test notes
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDAO;

        private PopulateDbAsyncTask (NoteDatabase database) {
            noteDAO = database.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Date tempDate = new Date(2019,8,8);
            noteDAO.insert(new Note("Title 1", R.drawable.ic_note_blue, "work1", "test description1", tempDate ));
            noteDAO.insert(new Note("Title 2", R.drawable.ic_note_blue, "work2", "test description2", tempDate ));
            noteDAO.insert(new Note("Title 3", R.drawable.ic_note_blue, "work3", "test description3", tempDate ));
            noteDAO.insert(new Note("Title 4", R.drawable.ic_note_blue, "work4", "test description4", tempDate ));

//            noteDAO.insert(new Note("Title 4", "description 4", 4));
            return null;
        }
    }

}
