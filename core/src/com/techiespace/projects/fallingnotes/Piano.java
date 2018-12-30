package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Piano {

   static ArrayList<PianoKey[]> whitePianoKeys = new ArrayList<PianoKey[]>();
   static ArrayList<PianoKey[]> blackPianoKeys = new ArrayList<PianoKey[]>();



    public Piano()
    {
        initWhiteKeys();
        initBlackKeys();

    }

    public static PianoKey findKey(String noteName)
    {

        Gdx.app.log("Piano","AAYa "+noteName);
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



                keys[j].setTexture(new Texture(Constants.BLACK_UP));

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

                keys[j].setPosition(new Vector2(Note.mapCoordinates(keys[j].getName()),(float)(Constants.WHITE_PIANO_KEY_HEIGHT*0.3f)));

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


                mKeys[j].setTexture(new Texture(Constants.WHITE_UP));

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

                mKeys[j].setPosition(new Vector2(Note.mapCoordinates(mKeys[j].getName()), 0));

            }
            whitePianoKeys.add(mKeys);
        }

    }





    public enum PianoKeyType {
        BLACK,
        WHITE

    }


    void render(Sprite sprite, SpriteBatch batch)
    {

        renderWhiteKeys(sprite,batch);

        renderBlackKeys(sprite,batch);
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
