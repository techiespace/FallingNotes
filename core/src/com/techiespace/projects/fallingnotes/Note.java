package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Note implements Comparable<Note> {

    public static final String TAG = Note.class.getName();

    int id;
    int startTime;
    int endTime;
    String noteName;
    Vector2 position;
    Vector2 velocity;

    public Note(String noteName, Vector2 position, int startTime, int endTime){
        this.noteName = noteName;
        this.position = position;
        this.velocity = new Vector2(0,-Constants.TEMPO);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void render(ShapeRenderer renderer){
        float note_height = (endTime-startTime) * Constants.HEIGTH_MULTIPLIER;
        renderer.setColor(Constants.NOTE_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        if(noteName.contains("#")){
            renderer.rect(
                    position.x, position.y,
                    Constants.BLACK_NOTE_WIDTH,
                    note_height
            );
        }
        else {
            renderer.rect(
                    position.x, position.y,
                    Constants.NOTES_WIDTH,
                    note_height
            );
        }
//        renderer.rect(position.x,position.y);
//        System.out.println(Gdx.graphics.getWidth());

    }

    public void update(float delta){
        position.mulAdd(velocity, delta);
    }

    @Override
    public int compareTo(Note note) {
        if (startTime > note.startTime)
            return 1;
        else if (startTime < note.startTime)
            return -1;
        else
            return 0;
//        return startTime.compareTo(note.startTime);
    }
}
