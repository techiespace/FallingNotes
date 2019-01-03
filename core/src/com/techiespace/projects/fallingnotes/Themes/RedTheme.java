package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;

public class RedTheme extends Theme {
    public RedTheme() {
        super();
        setBackgroundTexture("background/b4.jpg");
        setDarkBlackKeyColor(new Color(243 / 255f, 70 / 255f, 59 / 255f, 1));
        setDarkWhiteKeyColor(new Color(243 / 255f, 89 / 255f, 91 / 255f, 1));
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setLightBlackKeyColor(new Color(243 / 255f, 70 / 255f, 59 / 255f, 1));
        setLightWhiteKeyColor(new Color(247 / 255f, 170 / 255f, 170 / 255f, 1));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));

    }
}
