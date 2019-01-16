package com.techiespace.projects.fallingnotes.Themes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

//This is the parent class for any theme
//In order to add a new theme to your project
//Make a new class and extend this class
//In the constructor of your theme class
//Use set methods to set all the variables


//Copy this template to your new class
    // Texture backgroundTexture;
    //
    //         Texture LH_whiteKeyDownTexture;
    //         Texture LH_blackKeyDownTexture;
    //
    //         Texture RH_whiteKeyDownTexture;
    //         Texture RH_blackKeyDownTexture;
    //
    //
    //         Color LH_lightBlackKeyColor;
    //         Color LH_darkBlackKeyColor;
    //         Color LH_lightWhiteKeyColor;
    //         Color LH_darkWhiteKeyColor;
    //
    //         Color RH_lightBlackKeyColor;
    //         Color RH_darkBlackKeyColor;
    //         Color RH_lightWhiteKeyColor;
    //         Color RH_darkWhiteKeyColor;
    //
    //
    //
    //         Color labelColor;
    //         Color verticalLineColor;
    //         Color gameNameColor;
    //
    //         String fntPngName;
    //         String fntFileName;
    //
    //
    //         float gameNameScale;

public class Theme {
    Texture backgroundTexture;

    Texture LH_whiteKeyDownTexture;
    Texture LH_blackKeyDownTexture;

    Texture RH_whiteKeyDownTexture;
    Texture RH_blackKeyDownTexture;


    Color LH_lightBlackKeyColor;
    Color LH_darkBlackKeyColor;
    Color LH_lightWhiteKeyColor;
    Color LH_darkWhiteKeyColor;

    Color RH_lightBlackKeyColor;
    Color RH_darkBlackKeyColor;
    Color RH_lightWhiteKeyColor;
    Color RH_darkWhiteKeyColor;



    Color labelColor;
    Color verticalLineColor;
    Color gameNameColor;

    String fntPngName;
    String fntFileName;


    float gameNameScale;


    public Theme()
    {
        //Constructor Contains default values of some variables
        verticalLineColor = new Color(0.5f, 0.5f, 0.5f, 0.5f);
        gameNameColor = Color.WHITE;
        gameNameScale = 1.2f;
        LH_blackKeyDownTexture = new Texture("piano/black_down_yellow.png");
        LH_whiteKeyDownTexture = new Texture("piano/white_down_yellow.png");

        RH_blackKeyDownTexture = new Texture("piano/black_down_red_light.png");
        RH_whiteKeyDownTexture = new Texture("piano/white_down_red.png");
    }

    public void setLH_blackKeyDownTexture(Texture LH_blackKeyDownTexture) {
        this.LH_blackKeyDownTexture = LH_blackKeyDownTexture;
    }

    public void setLH_whiteKeyDownTexture(Texture LH_whiteKeyDownTexture) {
        this.LH_whiteKeyDownTexture = LH_whiteKeyDownTexture;
    }

    public void setRH_blackKeyDownTexture(Texture RH_blackKeyDownTexture) {
        this.RH_blackKeyDownTexture = RH_blackKeyDownTexture;
    }

    public void setRH_whiteKeyDownTexture(Texture RH_whiteKeyDownTexture) {
        this.RH_whiteKeyDownTexture = RH_whiteKeyDownTexture;
    }

    public Texture getLH_blackKeyDownTexture() {
        return LH_blackKeyDownTexture;
    }

    public Texture getLH_whiteKeyDownTexture() {
        return LH_whiteKeyDownTexture;
    }

    public Texture getRH_blackKeyDownTexture() {
        return RH_blackKeyDownTexture;
    }

    public Texture getRH_whiteKeyDownTexture() {
        return RH_whiteKeyDownTexture;
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

    public void setLH_lightBlackKeyColor(Color LH_darkBlackKeyColor) {
        this.LH_lightBlackKeyColor = LH_darkBlackKeyColor;
    }

    public void setRH_lightBlackKeyColor(Color RH_lightBlackKeyColor) {
        this.RH_lightBlackKeyColor = RH_lightBlackKeyColor;
    }


    public void setLH_darkWhiteKeyColor(Color LH_darkWhiteKeyColor) {
        this.LH_darkWhiteKeyColor = LH_darkWhiteKeyColor;
    }

    public void setRH_darkBlackKeyColor(Color RH_darkBlackKeyColor) {
        this.RH_darkBlackKeyColor = RH_darkBlackKeyColor;
    }


    public void setLH_darkBlackKeyColor(Color LH_darkBlackKeyColor) {
        this.LH_darkBlackKeyColor = LH_darkBlackKeyColor;
    }

    public void setLH_lightWhiteKeyColor(Color LH_lightWhiteKeyColor) {
        this.LH_lightWhiteKeyColor = LH_lightWhiteKeyColor;
    }

    public void setRH_darkWhiteKeyColor(Color RH_darkWhiteKeyColor) {
        this.RH_darkWhiteKeyColor = RH_darkWhiteKeyColor;
    }

    public void setRH_lightWhiteKeyColor(Color RH_lightWhiteKeyColor) {
        this.RH_lightWhiteKeyColor = RH_lightWhiteKeyColor;
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







    public Color getLabelColor() {
        return labelColor;
    }

    public Color getLH_darkBlackKeyColor() {
        return LH_darkBlackKeyColor;
    }

    public Color getLH_darkWhiteKeyColor() {
        return LH_darkWhiteKeyColor;
    }

    public Color getLH_lightBlackKeyColor() {
        return LH_lightBlackKeyColor;
    }

    public Color getLH_lightWhiteKeyColor() {
        return LH_lightWhiteKeyColor;
    }

    public Color getRH_darkBlackKeyColor() {
        return RH_darkBlackKeyColor;
    }

    public Color getRH_darkWhiteKeyColor() {
        return RH_darkWhiteKeyColor;
    }

    public Color getRH_lightBlackKeyColor() {
        return RH_lightBlackKeyColor;
    }

    public Color getRH_lightWhiteKeyColor() {
        return RH_lightWhiteKeyColor;
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
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
