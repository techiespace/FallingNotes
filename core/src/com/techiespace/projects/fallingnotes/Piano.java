package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Piano {

   static ArrayList<PianoKey[]> whitePianoKeys = new ArrayList<PianoKey[]>();
   static ArrayList<PianoKey[]> blackPianoKeys = new ArrayList<PianoKey[]>();
    BitmapFont font;


    public Piano()
    {
        initWhiteKeys();
        initBlackKeys();
        Texture texture = new Texture(Gdx.files.internal("courgette.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("courgette.fnt"), new TextureRegion(texture), false);
    }

    public static PianoKey findKey(String noteName)
    {

//        Gdx.app.log("Piano","AAYa "+noteName);
        if (noteName.charAt(1) == '#') {
            for (PianoKey[] pianokey : blackPianoKeys) {
                for (int i = 0; i < pianokey.length; i++) {
                    if (pianokey[i].getName().equals(noteName)) {
                        return pianokey[i];
                        }
                }
            }
        } else {
            for (PianoKey[] pianokey : whitePianoKeys) {
                for (int i = 0; i < pianokey.length; i++) {
                    if (pianokey[i].getName().equals(noteName)) {
                        return pianokey[i];
                    }
                }
                }
            }

            return null;

        }


    void initBlackKeys()
    {
        for (int i = 0; i < Constants.BLACK_PIANO_KEY_GROUPS; i++) {
            PianoKey keys[];
            switch (i) {
                case 0:
                    keys = new PianoKey[1];
                    break;
                default:
                    keys = new PianoKey[5];
                    break;
            }
            for (int j = 0; j < keys.length; j++) {
                keys[j] = new PianoKey();
                keys[j].setKeyType(PianoKeyType.BLACK);
                keys[j].setGroup(i);
                keys[j].setPositionInGroup(j);


                keys[j].initKeyTexture();

                keys[j].setHeight(Constants.BLACK_KEY_HEIGHT);
                keys[j].setWidth(Constants.BLACK_NOTE_WIDTH);

                keys[j].setIsPressed(false);

                if (i == 0) {
                    keys[0].setName("A#0");
                } else {
                    switch (j) {
                        case 0:

                            keys[j].setName("C#" + i);
                            break;
                        case 1:

                            keys[j].setName("D#" + i);
                            break;
                        case 2:

                            keys[j].setName("F#" + i);
                            break;
                        case 3:

                            keys[j].setName("G#" + i);
                            break;
                        case 4:

                            keys[j].setName("A#" + i);
                            break;

                    }
                }

                keys[j].setPosition(new Vector2(Note.mapCoordinates(keys[j].getName()), Constants.WHITE_PIANO_KEY_HEIGHT * 0.3f + Constants.OFFSET));

            }
            blackPianoKeys.add(keys);
        }
    }

    void initWhiteKeys()
    {
        for (int i = 0; i < Constants.WHITE_PIANO_KEY_GROUPS; i++) {
            PianoKey[] mKeys;
            switch (i) {
                    case 0:
                        mKeys = new PianoKey[2];
                        break;
                    case 8:
                        mKeys = new PianoKey[1];
                        break;
                    default:
                        mKeys = new PianoKey[7];
                    break;
                    }
            for (int j = 0; j < mKeys.length; j++) {
                mKeys[j] = new PianoKey();

                mKeys[j].setKeyType(PianoKeyType.WHITE);
                mKeys[j].setGroup(i);
                mKeys[j].setPositionInGroup(j);
                mKeys[j].setIsPressed(false);


//                mKeys[j].setTexture(new Texture(Constants.WHITE_UP));
                mKeys[j].initKeyTexture();

                mKeys[j].setHeight(Constants.WHITE_PIANO_KEY_HEIGHT);
                mKeys[j].setWidth(Constants.NOTES_WIDTH);




                if (i == 0) {
                    switch (j) {
                        case 0:

                            mKeys[j].setName("A0");
                            break;
                        case 1:

                            mKeys[j].setName("B0");
                            break;
                    }
                    continue;
                }
                if (i == 8) {
                    mKeys[j].setName("C8");
                    break;
                }

                switch (j) {
                    case 0:
                        mKeys[j].setName("C" + i);
                        break;
                    case 1:
                        mKeys[j].setName("D" + i);
                        break;
                    case 2:
                        mKeys[j].setName("E" + i);
                        break;
                    case 3:
                        mKeys[j].setName("F" + i);
                        break;
                    case 4:
                        mKeys[j].setName("G" + i);
                        break;
                    case 5:
                        mKeys[j].setName("A" + i);
                        break;
                    case 6:
                        mKeys[j].setName("B" + i);
                        break;
                }

                mKeys[j].setPosition(new Vector2(Note.mapCoordinates(mKeys[j].getName()), Constants.OFFSET));

            }
            whitePianoKeys.add(mKeys);
        }

    }





    public enum PianoKeyType {
        BLACK,
        WHITE

    }


    void render(Sprite sprite, SpriteBatch batch) {

        renderWhiteKeys(sprite,batch);

        renderBlackKeys(sprite,batch);

        renderLabel(batch);

        sprite.setPosition(Constants.NOTES_WIDTH * 35, Constants.OFFSET);
        sprite.setSize(Constants.NOTES_WIDTH, Constants.WHITE_PIANO_KEY_HEIGHT);
        sprite.setRegion(new Texture(Constants.WHITE_UP));
        sprite.draw(batch);


    }

    private void renderLabel(SpriteBatch batch) {
        font.setColor(0, 0, 0, 0.6f);
        font.getData().setScale(0.50f);
        font.draw(batch, "C2", Note.mapCoordinates("C2") + 4, 25 + Constants.OFFSET);
        font.draw(batch, "C3", Note.mapCoordinates("C3") + 4, 25 + Constants.OFFSET);
        font.draw(batch, "C4", Note.mapCoordinates("C4") + 4, 25 + Constants.OFFSET);
        font.draw(batch, "C5", Note.mapCoordinates("C5") + 4, 25 + Constants.OFFSET);
        font.draw(batch, "C6", Note.mapCoordinates("C6") + 4, 25 + Constants.OFFSET);
        font.setColor(Color.WHITE);
        font.getData().setScale(0.75f);
        final GlyphLayout layout = new GlyphLayout(font, "ChordSwift");
        // or for non final texts: layout.setText(font, text);
        final float fontX = 0 + (Constants.WORLD_WIDTH - layout.width) / 2;
        final float fontY = 0 + (Constants.WORLD_HEIGHT + layout.height) / 2;

        font.draw(batch, "ChordSwift", fontX, Constants.OFFSET - 10);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
    }

    void renderWhiteKeys(Sprite sprite,SpriteBatch batch)
    {

        for(int i=Constants.STARTING_OCTAVE;i<=Constants.ENDING_OCTAVE;i++)
        {
            PianoKey[] keys = whitePianoKeys.get(i);

            for(int j=0;j<keys.length;j++)
            {
                keys[j].render(sprite,batch);
            }

        }

    }

    void renderBlackKeys(Sprite sprite,SpriteBatch batch)
    {

        for(int i=Constants.STARTING_OCTAVE;i<=Constants.ENDING_OCTAVE;i++)
        {
            PianoKey[] keys = blackPianoKeys.get(i);

            for(int j=0;j<keys.length;j++)
            {
                keys[j].render(sprite,batch);
            }


        }
    }


}
