package com.techiespace.projects.fallingnotes.Database;

import java.util.List;

import androidx.lifecycle.LiveData;
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

    @Query("SELECT COUNT(*) FROM SKILL")
    int getTotalSkillNo();

    @Query("SELECT COUNT(*) FROM SKILL WHERE completed = 1")
    int getCompletedSkillNo();

    @Query("UPDATE Skill set completed = :completed, score = :score WHERE midiPath = :midiName")
    void updateSkillInfo(String midiName, float score, boolean completed);

    @Query("SELECT score from Skill WHERE midiPath=:midiName")
    float getScoreByMidi(String midiName);

    @Query("SELECT completed FROM Skill WHERE skill_id= :skill_id")
    boolean getSkillCompletedStatus(int skill_id);

}
