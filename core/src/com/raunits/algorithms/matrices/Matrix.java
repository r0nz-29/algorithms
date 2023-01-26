package com.raunits.algorithms.matrices;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Animation;

import java.util.ArrayList;
import java.util.List;

public class Matrix extends Animation {
    Cell[][] grid;
    List<TextButton> buttons;
    Algorithm algorithm;
    int N = 90;
    public void create(Stage stage, ShapeRenderer shapeRenderer) {
        grid = Utils.createBoard(N);
        createButtons();
    }

    public void render(Stage stage, ShapeRenderer shapeRenderer) {
        drawGrid();
        if (algorithm != null) algorithm.animate();
    }

    public void dispose() {
    }

    private void createButtons() {
        buttons = new ArrayList<>();
        buttons.add(startGOL());
        buttons.add(reset());
    }

    public List<TextButton> getButtons() {
        return this.buttons;
    }

    private void drawGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Cell c = grid[i][j];
                com.raunits.algorithms.Utils.drawRect(c.x, c.y, Cell.boardCellSize, Cell.boardCellSize, c.color, true);
                com.raunits.algorithms.Utils.drawRect(c.x, c.y, Cell.boardCellSize, Cell.boardCellSize, Color.BLACK, false);
            }
        }
    }

    private TextButton startGOL() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Start");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                algorithm = new GameOfLife();
                algorithm.init(grid);
            }
        });
        return button;
    }
    private TextButton reset() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Reset");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                algorithm = null;
                grid = Utils.createBoard(N);
            }
        });
        return button;
    }
}