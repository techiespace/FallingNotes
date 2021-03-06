package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PianoKey {

    Vector2 position;
    String name;
    float height;
    float width;
    Texture texture;

    Texture upTexture;


    Texture LH_downTexture;
    Texture RH_downTexture;
    Texture correctTexture;
    Texture wrongTexture;


    Piano.PianoKeyType keyType;
    boolean isPressed = false;
    int group;
    int positionInGroup;

   static Preferences preferences;

    public PianoKey() {


    }


    void render(Sprite keySprite, SpriteBatch batch) {
        keySprite.setPosition(position.x, position.y);
        keySprite.setSize(width, height);
        keySprite.setRegion(texture);
        keySprite.draw(batch);

    }

    float getHeight() {
        return this.height;
    }

    void setHeight(float height) {
        this.height = height;
    }

    float getWidth() {
        return this.width;
    }

    void setWidth(float width) {
        this.width = width;
    }

    int getGroup() {
        return this.group;

    }

    void setGroup(int group) {
        this.group = group;
    }

    int getPositionInGroup() {
        return this.positionInGroup;
    }

    void setPositionInGroup(int positionInGroup) {
        this.positionInGroup = positionInGroup;
    }

    Piano.PianoKeyType getKeyType() {
        return this.keyType;
    }

    void setKeyType(Piano.PianoKeyType type) {
        this.keyType = type;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    Vector2 getPosition() {
        return this.position;
    }

    void setPosition(Vector2 position) {
        this.position = position;
    }


    Boolean getIsPressed() {
        return this.isPressed;
    }

    void setIsPressed(Boolean isPressed) {
        this.isPressed = isPressed;
    }


    Texture getTexture() {
        return this.texture;
    }

    void setTexture(Texture texture) {
        this.texture = texture;
    }

    void updateTextureUp() {

//        Gdx.app.log("Pianokey","Up kiya  "+getName());
        this.setTexture(upTexture);
        setIsPressed(false);
    }

    void updateTextureDown(int track) {
        setIsPressed(true);

//        Gdx.app.log("Pianokey","down kiya  "+getName());

        preferences = Gdx.app.getPreferences("play_prefrences");

        boolean rightHand = preferences.getString("hand").equals("right") || preferences.getString("hand").equals("both");
        boolean leftHand = preferences.getString("hand").equals("left") || preferences.getString("hand").equals("both");



        if (track == 0 && rightHand)
            this.setTexture(RH_downTexture);
        else if(track == 1 && leftHand)
            this.setTexture(LH_downTexture);

    }

    void updatePracticeTexture(boolean correct) {
        setIsPressed(true);

//        Gdx.app.log("Pianokey","down kiya  "+getName());
        if (correct)
            this.setTexture(correctTexture);
        else
            this.setTexture(wrongTexture);

    }

    void initKeyTexture() {

        if (keyType == Piano.PianoKeyType.BLACK) {
            this.texture = new Texture("piano/black_up.png");
            this.upTexture = new Texture("piano/black_up.png");
            this.LH_downTexture = new Texture("piano/black_down_yellow.png");
            this.RH_downTexture = new Texture("piano/black_down_red_light.png");
            this.correctTexture = new Texture("piano/black_down_green.png");
            this.wrongTexture = new Texture("piano/black_down_red_light.png");

        } else {
            this.texture = new Texture("piano/white_up.png");
            this.upTexture = new Texture("piano/white_up.png");
            this.LH_downTexture =new Texture("piano/white_down_yellow.png") ;
            this.RH_downTexture = new Texture("piano/white_down_red.png");

            this.correctTexture = new Texture("piano/white_down_green.png");
            this.wrongTexture = new Texture("piano/white_down_red.png");

        }
    }

    void disposePianoKey()
    {
        upTexture.dispose();
        LH_downTexture.dispose();
        RH_downTexture.dispose();
        texture.dispose();

    }



}













