package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;

public class GotTheme extends Theme {
    public GotTheme() {
        super();
        setBackgroundTexture("background/b12.jpeg");
        setDarkBlackKeyColor(new Color(140 / 255f, 179 / 255f, 244 / 255f, 1));
        setDarkWhiteKeyColor(new Color(174 / 255f, 203 / 255f, 251 / 255f, 1));
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setLightBlackKeyColor(new Color(140 / 255f, 179 / 255f, 244 / 255f, 1));
        setLightWhiteKeyColor(new Color(232 / 255f, 239 / 255f, 255 / 255f, 1));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));

    }
}
