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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Controls {
    SeekBar seekBar;
    Button handToggleButton;
    Button.ButtonStyle leftHandToggleButtonStyle;
    Button.ButtonStyle rightHandToggleButtonStyle;
    Button.ButtonStyle bothHandToggleButtonStyle;
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
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/controls.atlas"));
        skin.addRegions(buttonAtlas);

        initStyles();

        handToggleButton = new Button(bothHandToggleButtonStyle);

        //This is called just once! How is it working then?
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        tempoSlider = new Slider(0, 1, 0.05f, true, skin);
        tempoSliderVal = 1f;
        preferences.putFloat("tempo_multiplier", tempoSliderVal).flush();
        tempoSlider.setValue(tempoSliderVal);
    }

    private void initStyles() {
        leftHandToggleButtonStyle = new Button.ButtonStyle();
        leftHandToggleButtonStyle.up = skin.getDrawable("L");
        leftHandToggleButtonStyle.checked = skin.getDrawable("L");

        rightHandToggleButtonStyle = new Button.ButtonStyle();
        rightHandToggleButtonStyle.up = skin.getDrawable("R");
        rightHandToggleButtonStyle.checked = skin.getDrawable("R");

        bothHandToggleButtonStyle = new Button.ButtonStyle();
        bothHandToggleButtonStyle.up = skin.getDrawable("B");
        bothHandToggleButtonStyle.checked = skin.getDrawable("B");
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
        bgPixmap.setColor(new Color(1, 0, 0, 0.2f));
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        controlsTable.setBackground(textureRegionDrawableBg);
        bgPixmap.dispose(); //https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table

        //hand
        controlsTable.add(handToggleButton).size(40, 40).padBottom(8f);
        controlsTable.setSize(Constants.MENU_OFFSET, Constants.WORLD_HEIGHT * 2 / 3);
        controlsTable.setPosition(Constants.WORLD_WIDTH - Constants.MENU_OFFSET, Constants.WORLD_HEIGHT / 6);

        controlsTable.row();
        controlsTable.add(tempoSlider);

//        controlsTable.debug();
        stage.addActor(controlsTable);
    }

    private void initializeButtonListeners(final Preferences playPrefs) {
//        final Preferences playPrefs = preferences;
        handToggleButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //https://www.badlogicgames.com/forum/viewtopic.php?f=11&t=18544
                switch (playPrefs.getString("hand")) {
                    case "left":
                        handToggleButton.setStyle(rightHandToggleButtonStyle);
                        playPrefs.putString("hand", "right").flush();
                        break;
                    case "right":
                        handToggleButton.setStyle(bothHandToggleButtonStyle);
                        playPrefs.putString("hand", "both").flush();
                        break;
                    case "both":
                        handToggleButton.setStyle(leftHandToggleButtonStyle);
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
