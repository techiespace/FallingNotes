package com.techiespace.projects.fallingnotes.pianoHelpers;


import android.graphics.Rect;
import android.graphics.drawable.Drawable;


public class PianoKey {
    //key TYpe Black or White
    private Piano.PianoKeyType type;

    // C,D,E,F,G,A,B
    //   private Piano.PianoVoice voice;

    //Group of the key
    private int group;

    //position in group
    private int positionOfGroup;

    //image attached to the key
    private Drawable keyDrawable;

    //Sound attached
    private int voiceId;

    //whether the key is pressed or not default = false
    private boolean isPressed;

    //Area occupied bykey //Rect holds cordinates of a rectangle order is
    //left top right bottom. It is defined in android
    private Rect[] areaOfKey;


    //Name of the letter of key
    private String letterName;

    //id of the finger used to press the key
    private int fingerID = -1;

    public Piano.PianoKeyType getType() {
        return type;
    }

    public void setType(Piano.PianoKeyType type) {
        this.type = type;
    }

//   // public Piano.PianoVoice getVoice() {
//        return voice;
//    }

//    public void setVoice(Piano.PianoVoice voice) {
//        this.voice = voice;
//    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getPositionOfGroup() {
        return positionOfGroup;
    }

    public void setPositionOfGroup(int positionOfGroup) {
        this.positionOfGroup = positionOfGroup;
    }

    public Drawable getKeyDrawable() {
        return keyDrawable;
    }

    public void setKeyDrawable(Drawable keyDrawable) {
        this.keyDrawable = keyDrawable;
    }

    public int getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(int voiceId) {
        this.voiceId = voiceId;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public Rect[] getAreaOfKey() {
        return areaOfKey;
    }

    public void setAreaOfKey(Rect[] areaOfKey) {
        this.areaOfKey = areaOfKey;
    }

    public String getLetterName() {
        return letterName;
    }

    public void setLetterName(String letterName) {
        this.letterName = letterName;
    }


    /*
     * Determine if the x, y coordinates are within the click area of the piano key
     *
     * @param x x coordinates
     * @param y y coordinates
     * @return is in the click area
     */
    public boolean contains(int x, int y) {
        boolean isContain = false;
        Rect[] areas = getAreaOfKey();
        int length = getAreaOfKey().length;
        for (int i = 0; i < length; i++) {
            if (areas[i] != null && areas[i].contains(x, y)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    public void resetFingerID() {
        fingerID = -1;
    }

    public int getFingerID() {
        return fingerID;
    }

    public void setFingerID(int fingerIndex) {
        this.fingerID = fingerIndex;
    }


}

