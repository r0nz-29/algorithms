package com.raunits.algorithms.matrices;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.raunits.algorithms.Animation;

import java.util.List;

public class Matrix extends Animation {
    Color[][] grid;
    int N = 10;
    public void create (Stage stage, ShapeRenderer shapeRenderer) {
        Color[][] grid = Utils.generateGrid(10);
    }

    public void render (Stage stage, ShapeRenderer shapeRenderer) {
    }

    public List<TextButton> getButtons() {
        return null;
    }

    public void dispose () {
    }
}
