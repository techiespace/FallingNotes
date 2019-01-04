package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;

public class PirateTheme extends Theme {
    public PirateTheme() {
        super();
        setBackgroundTexture("background/b11.jpg");
        setDarkBlackKeyColor(new Color(52 / 255f, 0 / 255f, 196 / 255f, 1));
        setDarkWhiteKeyColor(new Color(4 / 255f, 110 / 255f, 200 / 255f, 1));
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setLightBlackKeyColor(new Color(52 / 255f, 0 / 255f, 196 / 255f, 1));
        setLightWhiteKeyColor(new Color(146 / 255f, 243 / 255f, 239 / 255f, 1));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));

    }
}
