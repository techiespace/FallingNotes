package com.techiespace.projects.fallingnotes.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LevelDao {

    @Query("SELECT * FROM Level")
    List<Level> loadAllLevels();

    @Insert
    void insertLevel(Level level);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevel(Level level);

    @Delete
    void deleteLevel(Level level);

}
