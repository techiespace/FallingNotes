package com.techiespace.projects.fallingnotes.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Level",indices = {@Index("level_id")})
public class Level {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int level_id;
    @NonNull
    private String level_name;

    @Ignore
    public Level(String level_name)
    {
        this.level_name = level_name;

    }

    public Level(int level_id,String level_name)
    {
        this.level_id = level_id;
        this.level_name = level_name;
    }

    public int getLevel_id() {
        return level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }
}
