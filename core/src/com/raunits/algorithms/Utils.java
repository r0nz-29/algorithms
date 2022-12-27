package com.raunits.algorithms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Random;

public class Utils {
    public static ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static Color createColor(float r, float g, float b, float a) {
        return new Color(r / 255f, g / 255f, b / 255f, a);
    }

    public static void drawRect(float x, float y, float w, float h, Color color, boolean filled) {
        shapeRenderer.begin(filled ?  ShapeRenderer.ShapeType.Filled : ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, w, h);
        shapeRenderer.end();
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

    public static void drawAlertMsg(String msg, Color color) {
        BitmapFont msgFont = new BitmapFont();
        msgFont.setColor(color);
        Constants.defaultBatch.begin();
        msgFont.draw(Constants.defaultBatch, msg, Gdx.graphics.getWidth() / 2f, 200);
        Constants.defaultBatch.end();
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