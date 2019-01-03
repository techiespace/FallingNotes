package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

//This is the parent class for any theme
//In order to add a new theme to your project
//Make a new class and extend this class
//In the constructor of your theme class
//Use set methods to set all the variables


//Copy this template to your new class
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
public class Theme {
    Texture backgroundTexture;
    Texture whiteKeyDownTexture;
    Texture blackKeyDownTexture;


    Color lightBlackKeyColor;
    Color darkBlackKeyColor;
    Color lightWhiteKeyColor;
    Color darkWhiteKeyColor;
    Color labelColor;
    Color verticalLineColor;
    Color gameNameColor;

    String fntPngName;
    String fntFileName;


    float gameNameScale;

    Color[][] whiteNote;
    Color[][] blackNote;

    public Theme()
    {
        //Constructor Contains default values of some variables
        verticalLineColor = new Color(0.5f, 0.5f, 0.5f, 0.5f);
        gameNameColor = Color.WHITE;
        gameNameScale = 0.75f;
        blackKeyDownTexture = new Texture("piano/black_down_red_light.png");
        whiteKeyDownTexture = new Texture("piano/white_down_red.png");
    }

    public Texture getBlackKeyDownTexture() {
        return blackKeyDownTexture;
    }

    public Texture getWhiteKeyDownTexture() {
        return whiteKeyDownTexture;
    }

    public void setBlackKeyDownTexture(Texture blackKeyUpTexture) {
        this.blackKeyDownTexture = blackKeyUpTexture;
    }

    public void setWhiteKeyDownTexture(Texture whiteKeyUpTexture) {
        this.whiteKeyDownTexture = whiteKeyUpTexture;
    }



    public void setGameNameScale(float gameNameScale) {
        this.gameNameScale = gameNameScale;
    }

    public float getGameNameScale() {
        return gameNameScale;
    }

    public Color getVerticalLineColor() {
        return verticalLineColor;
    }

    public void setGameNameColor(Color gameNameColor) {
        this.gameNameColor = gameNameColor;
    }

    public Color getGameNameColor() {
        return gameNameColor;
    }

    public void setBackgroundTexture(String backgroundTexture)
    {
        this.backgroundTexture = new Texture(backgroundTexture);
    }

    public void setDarkBlackKeyColor(Color darkBlackKeyColor) {
        this.darkBlackKeyColor = darkBlackKeyColor;
    }

    public void setDarkWhiteKeyColor(Color darkWhiteKeyColor) {
        this.darkWhiteKeyColor = darkWhiteKeyColor;
    }

    public void setFntFileName(String fntFileName) {
        this.fntFileName = fntFileName;
    }

    public void setFntPngName(String fntPngName) {
        this.fntPngName = fntPngName;
    }

    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }

    public void setLightBlackKeyColor(Color lightBlackKeyColor) {
        this.lightBlackKeyColor = lightBlackKeyColor;
    }

    public void setLightWhiteKeyColor(Color lightWhiteKeyColor) {
        this.lightWhiteKeyColor = lightWhiteKeyColor;
    }

    public Color getDarkBlackKeyColor() {
        return darkBlackKeyColor;
    }

    public Color getDarkWhiteKeyColor() {
        return darkWhiteKeyColor;
    }

    public Color getLabelColor() {
        return labelColor;
    }

    public Color getLightBlackKeyColor() {
        return lightBlackKeyColor;
    }

    public Color getLightWhiteKeyColor() {
        return lightWhiteKeyColor;
    }

    public String getFntFileName() {
        return fntFileName;
    }

    public String getFntPngName() {
        return fntPngName;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }
    public void setVerticalLineColor(Color verticalLineColor) {
        this.verticalLineColor = verticalLineColor;
    }
}
