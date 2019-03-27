package com.techiespace.projects.fallingnotes.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ScaleDao {

    @Query("SELECT * FROM Scale")
    List<Scale> loadAllScales();

    @Insert
    void insertScale(Scale scale);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateScale(Scale scale);
    @Insert
    void insertAllScales(Scale... scales);

    @Delete
    void deleteScale(Scale scale);

    @Query("DELETE FROM Scale")
    void deleteALL();

    @Query("SELECT * FROM Scale WHERE scale_type = :scale_type ORDER BY scale_name")
    List<Scale> getScalesByType(String scale_type);

}
