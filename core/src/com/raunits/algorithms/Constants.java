package com.raunits.algorithms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Constants {
    public static Color BACKGROUND = new Color(51f/255f, 51f/255f, 51f/255f, 1);
    public static Color RED = new Color(222f/255f, 50f/255f, 73f/255f, 1);
    public static Color VIOLET = new Color(113f/255f, 64f/255f, 146f/255f, 0.02f);
    public static Color GREEN = Utils.createColor(50f, 255f, 50f, 1);
    public static Color PALE_GREEN = Utils.createColor(152f, 251f, 152f, 1);
    public static Color WATER = Utils.createColor(0, 216f, 216f, 1);
    public static BitmapFont buttonFont = new BitmapFont();
    public static SpriteBatch defaultBatch = new SpriteBatch();
}