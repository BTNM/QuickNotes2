package com.btdeveloper.quicknotes2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


/**
 * Subclass of viewmodel, that get passed the application in the constructor. Which can be used whenever the context is needed.
 * Should never store an context of activity or a view that references an activity in the viewmodel, becauses the viewmodel is designed to out lift an activity
 * after it is destroyed, and if we hold a reference to an already destroyed activity, we get a memory leak.
 * But have to pass a context to the repository to instanciate the database, so we pass the application via androidviewmodel
 */
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void deleteAllNotes () {
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }



}
