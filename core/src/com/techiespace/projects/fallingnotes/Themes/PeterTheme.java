package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;

public class PeterTheme extends Theme {
    public PeterTheme() {
        super();
        setBackgroundTexture("background/b4.jpg");
        setRH_darkBlackKeyColor(new Color(99 / 255f, 203 / 255f, 42 / 255f, 1));
        setRH_darkWhiteKeyColor(new Color(160 / 255f, 234 / 255f, 63 / 255f, 1));
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setRH_lightBlackKeyColor(new Color(99 / 255f, 203 / 255f, 42 / 255f, 1));
        setRH_lightWhiteKeyColor(new Color(236 / 255f, 244 / 255f, 166 / 255f, 1));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));


        setLH_darkBlackKeyColor(new Color(140 / 255f, 179 / 255f, 244 / 255f, 1));
        setLH_darkWhiteKeyColor(new Color(174 / 255f, 203 / 255f, 251 / 255f, 1));

        setLH_lightBlackKeyColor(new Color(140 / 255f, 179 / 255f, 244 / 255f, 1));
        setLH_lightWhiteKeyColor(new Color(232 / 255f, 239 / 255f, 255 / 255f, 1));

    }
}
