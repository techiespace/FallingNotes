package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;

public class HpTheme extends Theme {
    public HpTheme() {
        super();
        setBackgroundTexture("background/Hog.jpg");
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));

        setLH_darkBlackKeyColor(new Color(229 / 255f, 186 / 255f, 48 / 255f, 1));
        setLH_lightBlackKeyColor(new Color(229 / 255f, 186 / 255f, 61 / 255f, 1));

        setLH_darkWhiteKeyColor(new Color(246 / 255f, 239 / 255f, 60 / 255f, 1));
        setLH_lightWhiteKeyColor(new Color(246 / 255f, 239 / 255f, 95 / 255f, 1));

        setRH_darkBlackKeyColor(new Color(174 / 255f, 48 / 255f, 37 / 255f, 1));
        setRH_lightBlackKeyColor(new Color(135 / 255f, 37 / 255f, 46 / 255f, 1));

        setRH_darkWhiteKeyColor(new Color(110 / 255f, 27 / 255f, 28 / 255f, 1));
        setRH_lightWhiteKeyColor(new Color(230 / 255f, 108 / 255f, 108 / 255f, 1));

    }
}
