package com.raunits.algorithms.classics.convexhull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.raunits.algorithms.Animation;
import com.raunits.algorithms.Constants;
import com.raunits.algorithms.Coordinate;

import java.util.Random;

public class ConvexHull2D extends Animation {
    private final int POINTS = 10;
    private float[][] points;
    private float[] path;
    private float[][] hull;
    private boolean showHull;
    private int width;
    private int height;
    TextButton refreshButton;
    TextButton toggleHull;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;

    public void create(Stage stage, ShapeRenderer shapeRenderer) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        addRefreshButton(stage);
        addHullToggle(stage);

        generateRandomPoints();
        hull = Utils.generateHull(points);
        createHullPath();

        showHull = true;
    }

    public void render(Stage stage, ShapeRenderer shapeRenderer) {
        renderPoints(shapeRenderer);

        if (showHull) highlightHullPoints(shapeRenderer);
        if (showHull) drawHull(shapeRenderer);
    }

    public void dispose() {
    }

    private void resetGraphics() {
        points = null;
        path = null;
        hull = null;

        ScreenUtils.clear(Color.GRAY);
        generateRandomPoints();
        hull = Utils.generateHull(points);
        createHullPath();
    }

    private void drawHull(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Constants.VIOLET);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(path);
        shapeRenderer.end();
    }

    private void createHullPath() {
        path = new float[hull.length * 2];
        int k = 0;
        for (float[] point : hull) {
            path[k++] = point[0];
            path[k++] = point[1];
        }
    }

    private void highlightHullPoints(ShapeRenderer shapeRenderer) {
        for (float[] point : hull) {
            shapeRenderer.setColor(Constants.RED);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(point[0], point[1], 5);
            shapeRenderer.end();
        }
    }

    private void renderPoints(ShapeRenderer shapeRenderer) {
        for (float[] point : points) com.raunits.algorithms.Utils.drawCircle(
                Constants.RED,
                new Coordinate(point[0], point[1])
                ,5,
                shapeRenderer
        );
    }

    private void generateRandomPoints() {

        points = new float[POINTS][2];

        for (int i = 0; i < POINTS; i++) {
            int x = com.raunits.algorithms.Utils.getRandomNumber(10, width - 10);
            int y = com.raunits.algorithms.Utils.getRandomNumber(10, height - 10);
            points[i] = new float[]{x, y};
        }
    }

    private void addRefreshButton(Stage stage) {
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Constants.RED;

        refreshButton = new TextButton("Refresh", textButtonStyle);
        refreshButton.setColor(Color.BLACK);

        refreshButton.setX(width - refreshButton.getWidth() - 10);
        refreshButton.setY(height - refreshButton.getHeight());

        stage.addActor(refreshButton);

        refreshButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                resetGraphics();
            }
        });
    }

    private void addHullToggle(Stage stage) {
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Constants.RED;

        toggleHull = new TextButton("Toggle Hull", textButtonStyle);

        toggleHull.setX(refreshButton.getX() - refreshButton.getWidth() - toggleHull.getWidth()/2 - 20);
        toggleHull.setY(height - refreshButton.getHeight());

        stage.addActor(toggleHull);

        toggleHull.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                showHull = !showHull;
            }
        });
    }
}
