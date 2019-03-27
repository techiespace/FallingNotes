package com.techiespace.projects.fallingnotes.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Scale", indices = {@Index("scale_id")})
public class Scale {

    @PrimaryKey
    @NonNull
    private int scale_id;
    @NonNull
    private String scale_name;
    @NonNull
    private  String midi_name;

    @NonNull
    private String scale_type;

    private String instructions;

    private boolean completed;



    @Ignore
    public Scale(String scale_name,String midi_name,String scale_type,String instructions) {
        this.scale_name = scale_name;
        this.midi_name = midi_name;
        this.scale_type = scale_type;
        this.instructions = instructions;
        this.completed = false;

    }

    public Scale(int scale_id, String scale_name,String midi_name,String scale_type,String instructions) {
        this.scale_id = scale_id;
        this.scale_name = scale_name;
        this.midi_name = midi_name;
        this.scale_type = scale_type;
        this.instructions = instructions;
        this.completed = false;

    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setMidi_name(@NonNull String midi_name) {
        this.midi_name = midi_name;
    }

    public void setScale_id(@NonNull int scale_id) {
        this.scale_id = scale_id;
    }

    public void setScale_name(@NonNull String scale_name) {
        this.scale_name = scale_name;
    }

    public void setScale_type(@NonNull String scale_type) {
        this.scale_type = scale_type;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getInstructions() {
        return instructions;
    }

    @NonNull
    public int getScale_id() {
        return scale_id;
    }

    @NonNull
    public String getMidi_name() {
        return midi_name;
    }

    @NonNull
    public String getScale_name() {
        return scale_name;
    }

    @NonNull
    public String getScale_type() {
        return scale_type;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
