package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
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

    Piano.PianoKeyType keyType;
    boolean isPressed = false;
    int group;
    int positionInGroup;

    public PianoKey()
    {

    }






    public PianoKey(Vector2 position, String name, float height, float width, Piano.PianoKeyType type, int group, int positionInGroup) {
        this.position = position;
        this.name = name;
        this.height = height;
        this.width = width;
        this.keyType = type;
        this.group = group;
        this.positionInGroup = group;

        if (keyType == Piano.PianoKeyType.BLACK) {
            this.texture = new Texture("black_up.png");

        } else {
            this.texture = new Texture("white_up.png");


        }
    }


    void render(Sprite keySprite, SpriteBatch batch) {

       // Gdx.app.error("renderkeys ",position.x+position.y+name);
        keySprite.setPosition(position.x, position.y);
        keySprite.setSize(width, height);
        keySprite.setRegion(texture);
        keySprite.draw(batch);
    }

    float getHeight() {
        return this.height;
    }
    void setHeight(float height) {
        this.height  = height;
    }

    float getWidth() {
        return this.width;
    }

    void setWidth(float width)
    {
        this.width=width;
    }

    int getGroup() {
        return this.group;

    }
    void setGroup(int group)
    {
        this.group = group;
    }

    int getPositionInGroup() {
        return this.positionInGroup;
    }

    void setPositionInGroup(int positionInGroup)
    {
        this.positionInGroup  = positionInGroup;
    }

    Piano.PianoKeyType getKeyType() {
        return this.keyType;
    }

    void setKeyType(Piano.PianoKeyType type)
    {
        this.keyType  = type;
    }

    String getName() {
        return this.name;
    }

    void setName(String name)
    {
        this.name = name;
    }

    Vector2 getPosition() {
        return this.position;
    }

    void setPosition(Vector2 position)
    {
        this.position  = position;
    }



    Boolean getIsPressed() {
        return this.isPressed;
    }

    void setIsPressed(Boolean isPressed)
    {
        this.isPressed  = isPressed;
    }


    Texture getTexture() {
        return this.texture;
    }

    void setTexture(Texture texture)
    {
        this.texture = texture;
    }

    void updateTextureUp()
    {

//        Gdx.app.log("Pianokey","Up kiya  "+getName());
        if(this.keyType==Piano.PianoKeyType.BLACK)
        this.setTexture(new Texture(Constants.BLACK_UP));
        else
            this.setTexture(new Texture(Constants.WHITE_UP));

        setIsPressed(false);
    }

    void updateTextureDown()
    {
        setIsPressed(true);

//        Gdx.app.log("Pianokey","down kiya  "+getName());
        if(this.keyType == Piano.PianoKeyType.WHITE)
            this.setTexture(new Texture(Constants.WHITE_DOWN));
        else
            this.setTexture(new Texture(Constants.BLACK_DOWN));

    }











}
