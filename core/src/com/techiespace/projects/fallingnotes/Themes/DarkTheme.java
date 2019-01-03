package com.techiespace.projects.fallingnotes.Themes;

//         Texture backgroundTexture;
//         Texture whiteKeyDownTexture;[optional]
//         Texture blackKeyDownTexture;[optional]
//
//
//         Color lightBlackKeyColor;
//         Color darkBlackKeyColor;
//         Color lightWhiteKeyColor;
//         Color darkWhiteKeyColor;
//         Color labelColor;
//         Color verticalLineColor;    [optional]
//         Color gameNameColor;        [optional]
//
//         String fntPngName;
//         String fntFileName;
//
//
//         float gameNameScale;        [Optional]


import com.badlogic.gdx.graphics.Color;

public class DarkTheme extends Theme {


    public DarkTheme()
    {
        super();
        setBackgroundTexture("background/b4.jpg");
        setDarkBlackKeyColor(new Color(178 / 255f, 102 / 255f, 255 / 255f, 1));
        setDarkWhiteKeyColor(new Color(127 / 255f, 0 / 255f, 255 / 255f, 1));
        setFntFileName("font/courgette.fnt");
        setFntPngName("font/courgette.png");
        setLabelColor(new Color(0, 0, 0, 0.6f));
        setLightBlackKeyColor(new Color(153 / 255f, 51 / 255f, 255 / 255f, 1));
        setLightWhiteKeyColor(new Color(102 / 255f, 0 / 255f, 204 / 255f, 1));
        setVerticalLineColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));

   }


}
