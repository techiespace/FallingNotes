package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Controls {
    SeekBar seekBar;
    Button leftHandToggleButton;
    Button righthandToggleButton;
    Button muteButton;

    Button.ButtonStyle leftHandToggleButtonActiveStyle;
    Button.ButtonStyle leftHandToggleButtonInactiveStyle;
    Button.ButtonStyle rightHandToggleButtonActiveStyle;
    Button.ButtonStyle rightHandToggleButtonInactiveStyle;
  //  Button.ButtonStyle muteButtonTo

    Slider tempoSlider;
    TextField tempoVal;

    float tempoSliderVal;

    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    InputMultiplexer inputMultiplexer;

    Preferences playPrefs;



    Controls(FallingNotesScreen context, Stage stage, OrthographicCamera cam, Preferences prefs) {
        setPreferences();
        initializeTheButtons(prefs);
        initializeInputMultipler(context, stage, cam);
        initializeButtonListeners(prefs);
        initializeControlTable(stage);
        initializeTempoVal();
        this.playPrefs = prefs;
    }

    private void initializeTempoVal() {
        tempoVal = new TextField("",skin);

    }

    void setPreferences() {
        Gdx.app.getPreferences("play_prefrences").putString("hand", "both").flush();
    }

    private void initializeTheButtons(Preferences preferences) {


        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/handcontrols.atlas"));
        skin.addRegions(buttonAtlas);

        initStyles();

        leftHandToggleButton = new Button(leftHandToggleButtonActiveStyle);
        righthandToggleButton = new Button(rightHandToggleButtonActiveStyle);
      //  muteButton = new Button();

        //This is called just once! How is it working then?

        skin = new Skin(Gdx.files.internal("skin/tubular-ui.json"));
        tempoSlider = new Slider(0, 1, 0.05f, true, skin);
        tempoSliderVal = 1f;
        preferences.putFloat("tempo_multiplier", tempoSliderVal).flush();
        tempoSlider.setValue(tempoSliderVal);
    }

    private void initStyles() {
        leftHandToggleButtonActiveStyle = new Button.ButtonStyle();
        leftHandToggleButtonActiveStyle.up = skin.getDrawable("left_active");
        leftHandToggleButtonActiveStyle.checked = skin.getDrawable("left_active");

        rightHandToggleButtonActiveStyle = new Button.ButtonStyle();
        rightHandToggleButtonActiveStyle.up = skin.getDrawable("right_active");
        rightHandToggleButtonActiveStyle.checked = skin.getDrawable("right_active");

        leftHandToggleButtonInactiveStyle = new Button.ButtonStyle();
        leftHandToggleButtonInactiveStyle.up = skin.getDrawable("left");
        leftHandToggleButtonInactiveStyle.checked = skin.getDrawable("left");

        rightHandToggleButtonInactiveStyle = new Button.ButtonStyle();
        rightHandToggleButtonInactiveStyle.up = skin.getDrawable("right");
        rightHandToggleButtonInactiveStyle.checked = skin.getDrawable("right");
    }

    void initSeekbar(Notes notes, Stage stage) {
        seekBar = new SeekBar(stage, notes.getAnimationEndTime());
    }

    void updateSeekbar(Notes notes) {
        seekBar.updateSeekBar((int) notes.getInitialTime() * 1000);
    }

    private void initializeControlTable(Stage stage) {
        Table controlsTable = new Table();
        //bg
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(new Color(1, 0, 0, 0f));
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        controlsTable.setBackground(textureRegionDrawableBg);
        bgPixmap.dispose(); //https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table

        //hand

        controlsTable.add(leftHandToggleButton).size(Constants.MENU_OFFSET * 1.5f, Constants.MENU_OFFSET * 1.5f);
        controlsTable.add(righthandToggleButton).size(Constants.MENU_OFFSET * 1.5f, Constants.MENU_OFFSET * 1.5f);
        controlsTable.setSize(Constants.WORLD_WIDTH/2, Constants.MENU_OFFSET);
        controlsTable.setPosition(0, Constants.OFFSET*0.15f);

//        controlsTable.row();
//        controlsTable.add(tempoSlider);

//        controlsTable.debug();
        stage.addActor(controlsTable);
    }

    private void initializeButtonListeners(final Preferences playPrefs) {
//        final Preferences playPrefs = preferences;
        leftHandToggleButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //https://www.badlogicgames.com/forum/viewtopic.php?f=11&t=18544
                switch (playPrefs.getString("hand")) {
                    case "left":    //left -> right
                        leftHandToggleButton.setStyle(leftHandToggleButtonInactiveStyle);
                        righthandToggleButton.setStyle(rightHandToggleButtonActiveStyle);
                        playPrefs.putString("hand", "right").flush();
                        break;
                    case "right":   //right -> both
                        leftHandToggleButton.setStyle(leftHandToggleButtonActiveStyle);
                        righthandToggleButton.setStyle(rightHandToggleButtonActiveStyle);
                        playPrefs.putString("hand", "both").flush();
                        break;
                    case "both":    //both -> left
                        leftHandToggleButton.setStyle(leftHandToggleButtonActiveStyle);
                        righthandToggleButton.setStyle(rightHandToggleButtonInactiveStyle);
                        playPrefs.putString("hand", "left").flush();
                        break;
                }
            }
        });
        righthandToggleButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //https://www.badlogicgames.com/forum/viewtopic.php?f=11&t=18544
                switch (playPrefs.getString("hand")) {
                    case "left":    //left -> right
                        leftHandToggleButton.setStyle(leftHandToggleButtonInactiveStyle);
                        righthandToggleButton.setStyle(rightHandToggleButtonActiveStyle);
                        playPrefs.putString("hand", "right").flush();
                        break;
                    case "right":   //right -> both
                        leftHandToggleButton.setStyle(leftHandToggleButtonActiveStyle);
                        righthandToggleButton.setStyle(rightHandToggleButtonActiveStyle);
                        playPrefs.putString("hand", "both").flush();
                        break;
                    case "both":    //both -> left
                        leftHandToggleButton.setStyle(leftHandToggleButtonActiveStyle);
                        righthandToggleButton.setStyle(rightHandToggleButtonInactiveStyle);
                        playPrefs.putString("hand", "left").flush();
                        break;
                }
            }
        });
        tempoSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playPrefs.putFloat("tempo_multiplier", tempoSlider.getValue()).flush();
            }
        });
    }

    private void initializeInputMultipler(FallingNotesScreen context, Stage stage, OrthographicCamera cam) {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new GestureDetector(new GestureHandler(context, cam)));
        // inputMultiplexer.addProcessor(new KeyboardInputHandler(cam));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    //reseting controls

    void reset()
    {
        seekBar.updateSeekBar(0);
    }

    public void handleTempo(float deltaY)
    {
        float sliderVal =  tempoSlider.getValue();
        Gdx.app.log("Controls ",deltaY+"");


        // Gdx.app.log("Controls",playPrefs.getFloat("tempo_multiplier")+"");

        if(sliderVal<=1&&sliderVal>=0) {
            sliderVal -= deltaY/300;
            sliderVal = MathUtils.clamp(sliderVal,0,1);
            Gdx.app.log("Controls ",sliderVal+"");


            playPrefs.putFloat("tempo_multiplier", sliderVal).flush();
            tempoSliderVal =  sliderVal;
            tempoSlider.setValue(sliderVal);
        }

    }


}
