package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SeekBar {

    Slider seekSlider;
    Table seekTable;
    Skin skin;
    Pixmap bgPixmap;
    TextureRegionDrawable textureRegionDrawableBg;
    int midiEndTime;



    public SeekBar(Stage stage,int midiEndTime)
    {

        this.midiEndTime = midiEndTime;
        seekTable = new Table();
        //seek bar
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        seekSlider = new Slider(0, midiEndTime, 0.05f, false, skin);
        //bg
        bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(new Color(1, 0, 0, 0.8f));
        bgPixmap.fill();
        textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        seekTable.setBackground(textureRegionDrawableBg);
        bgPixmap.dispose(); //https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table

        seekTable.setBackground(textureRegionDrawableBg);
        seekTable.setSize(Constants.WORLD_WIDTH * 2 / 3, Constants.MENU_OFFSET);
        seekTable.setPosition(Constants.WORLD_WIDTH / 6, 0);
        seekTable.add(seekSlider);
        stage.addActor(seekTable);

        seekSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //might be useful when implementing actual seek functionality
            }
        });
    }


    void updateSeekBar(int Time)
    {
        seekSlider.setValue(Time);
    }

}
