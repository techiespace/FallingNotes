package com.techiespace.projects.fallingnotes.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
/*
        (foreignKeys = @ForeignKey(entity = Level.class,
                parentColumns = "level_id",
                childColumns = "level_id"),indices = {@Index("level_id")})*/
public class Skill {

    @PrimaryKey
    @NonNull
    private  int skill_id;
    @NonNull
   private String skill_name;
    @NonNull
   private String skill_list;
    @NonNull
    int level_id;
    @NonNull
   private String midiPath;
    @NonNull
   private String instructions;

    private int score;
    private boolean completed;

    @Ignore
    public Skill(String skill_name,String skill_list,int level_id,String midiPath,String instructions)
    {
        this.skill_name = skill_name;
        this.skill_list = skill_list;
        this.level_id = level_id;
        this.midiPath = midiPath;
        this.instructions = instructions;
        this.score = 0;
        this.completed = false;
    }

    public Skill(int skill_id,String skill_name,String skill_list,int level_id,String midiPath,String instructions)
    {
        this.skill_id = skill_id;
        this.skill_name = skill_name;
        this.skill_list = skill_list;
        this.level_id = level_id;
        this.midiPath = midiPath;
        this.instructions = instructions;
        this.score = 0;
        this.completed = false;
    }

    public int getLevel_id() {
        return level_id;
    }

    public int getScore() {
        return score;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getMidiPath() {
        return midiPath;
    }

    public String getSkill_list() {
        return skill_list;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setMidiPath(String midiPath) {
        this.midiPath = midiPath;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public void setSkill_list(String skill_list) {
        this.skill_list = skill_list;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }
}
