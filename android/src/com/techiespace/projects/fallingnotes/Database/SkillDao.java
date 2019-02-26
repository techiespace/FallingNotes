package com.techiespace.projects.fallingnotes.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SkillDao {
    @Query("SELECT * FROM Skill")
    List<Skill> loadAllSkills();

    @Insert
    void insertSkill(Skill skill);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSkill(Skill skill);

    @Delete
    void deleteSkill(Skill skill);
}
