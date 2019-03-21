package com.techiespace.projects.fallingnotes.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.badlogic.gdx.Net.HttpMethods.DELETE;

@Dao
public interface SkillDao {
    @Query("SELECT * FROM Skill")
    List<Skill> loadAllSkills();

    @Query("SELECT * FROM Skill WHERE level_id = :level_id ORDER BY skill_id")
    List<Skill> getSkillsByLevel(int level_id);

    @Insert
    void insertSkill(Skill skill);

    @Insert
    void insertAllSkills(Skill... skills);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSkill(Skill skill);

    @Delete
    void deleteSkill(Skill skill);

    @Query("DELETE FROM Skill")
    void deleteAllSKills();
}
