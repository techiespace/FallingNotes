package com.techiespace.projects.fallingnotes;

public interface dbInterface {

    public void submitScore(String midiName,int totalNotes,int rightNotes);

    public float getScoreByMidi(String midiName);
}
