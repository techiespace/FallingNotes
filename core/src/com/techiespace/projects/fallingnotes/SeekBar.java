package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SeekBar {

    ProgressBar seekSlider;
    Table seekTable;
    Skin skin;
    Pixmap bgPixmap;
    TextureRegionDrawable textureRegionDrawableBg;
    int midiEndTime;


    public SeekBar(Stage stage, float animationEndTime) {

        this.midiEndTime = midiEndTime;
        seekTable = new Table();
        //seek bar
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        seekSlider = new ProgressBar(0, animationEndTime, 0.02f, false, skin);
        bgPixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(new Color(1, 0, 0, 0.8f));
        bgPixmap.fill();
        textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        seekTable.setBackground(textureRegionDrawableBg);
        bgPixmap.dispose();

       // seekSlider.sizeBy(500);

        //bg
        //https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table

//        Container<Slider> container=new Container<Slider>(seekSlider);
//        container.setTransform(true);   // for enabling scaling and rotation
//        container.size(100, 60);
//        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
//        container.setPosition(Constants.MENU_OFFSET*4,Constants.WORLD_HEIGHT-Constants.MENU_OFFSET/2);
//        container.setScale(30);  //scale according to your requirement


//


        seekTable.setSize(Constants.WORLD_WIDTH, Constants.MENU_OFFSET);
        seekTable.setPosition(0, Constants.WORLD_HEIGHT-Constants.MENU_OFFSET);

//        seekSlider.getStyle().knob.setMinHeight(40);
//        seekSlider.getStyle().knob.setMinWidth(40);



        seekTable.add(seekSlider).width(Constants.WORLD_WIDTH).minHeight(60);

        stage.addActor(seekTable);


        seekSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //might be useful when implementing actual seek functionality
            }
        });
    }


    void updateSeekBar(int Time) {
        seekSlider.setValue(Time);
    }

}
