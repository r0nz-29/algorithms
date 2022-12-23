package com.raunits.algorithms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Utils {
    public static Color createColor(int r, int g, int b, int a) {
        return new Color(r/255f, g/255f, b/255f, a);
    }
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public static void drawline(Coordinate a, Coordinate b, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
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