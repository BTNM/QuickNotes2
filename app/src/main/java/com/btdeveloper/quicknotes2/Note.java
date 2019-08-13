package com.btdeveloper.quicknotes2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

//room annotation who would be needed to build all the necessary code to create the sql table for this objects,
// this is how the room library get the info needed which we would normally write in the sqlite class
@Entity(tableName = "note_table")
public class Note {

    // each table needs a primary key to uniqely identify each entry, each new row auto increment this id and add in the id column
    // room auto generate colum for each field
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private int typeIconImageId;

    private String typeIcon;

    private String description;

//    private int date;
    private String date;

    public Note(String title, int typeIconImageId, String typeIcon, String description, String date) {
        this.title = title;
        this.typeIconImageId = typeIconImageId;
        this.typeIcon = typeIcon;
        this.description = description;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeIconImageId() {
        return typeIconImageId;
    }

    public void setTypeIconImageId(int typeIconImageId) {
        this.typeIconImageId = typeIconImageId;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
