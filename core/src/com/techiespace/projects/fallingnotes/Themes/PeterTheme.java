package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;

public class PeterTheme extends Theme {
    public PeterTheme() {
        super();
        setBackgroundTexture("background/b4.jpg");
        setDarkBlackKeyColor(new Color(99 / 255f, 203 / 255f, 42 / 255f, 1));
        setDarkWhiteKeyColor(new Color(160 / 255f, 234 / 255f, 63 / 255f, 1));
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setLightBlackKeyColor(new Color(99 / 255f, 203 / 255f, 42 / 255f, 1));
        setLightWhiteKeyColor(new Color(236 / 255f, 244 / 255f, 166 / 255f, 1));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));
    }
}
