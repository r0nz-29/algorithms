package com.raunits.algorithms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.Random;

public class Utils {
    public static Color createColor(float r, float g, float b, float a) {
        return new Color(r/255f, g/255f, b/255f, a);
    }

    public static TextButton.TextButtonStyle getDefaultRedStyle() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Constants.buttonFont;
        textButtonStyle.fontColor = Constants.RED;
        return textButtonStyle;
    }

    public static TextButton createButton(String label) {
        TextButton button = new TextButton(label, getDefaultRedStyle());
        button.setColor(Constants.RED);
        return button;
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public static void drawline(Coordinate a, Coordinate b, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(a.x, a.y, b.x, b.y);
        shapeRenderer.end();
    }
    public static void drawCircle(Color color, Coordinate coordinate, int radius, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(coordinate.x, coordinate.y, radius);
        shapeRenderer.end();
    }
}