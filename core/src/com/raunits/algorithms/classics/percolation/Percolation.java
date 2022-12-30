package com.raunits.algorithms.classics.percolation;

import com.badlogic.gdx.Gdx;
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

public class Percolation extends Animation {
    Cell[][] grid;
    List<TextButton> btns;
    Algorithm unblockAlgorithm;

    int windowWidth = Gdx.graphics.getWidth();
    int windowHeight = Gdx.graphics.getHeight();
    float startX;
    float startY;
    Cell start;
    Cell end;
    int N;

    public void create(Stage stage, ShapeRenderer shapeRenderer) {
        N = 43;

        start = new Cell();
        end = new Cell();

        grid = Utils.generateGrid(N, start, end);
        unblockAlgorithm = new Unblock(grid, start, end);

        startX = windowWidth / 2f - (N * Cell.CELL_SIZE) / 2f;
        startY = windowHeight / 2f + (N * Cell.CELL_SIZE) / 2f;
        createBtns();
    }

    public void render(Stage stage, ShapeRenderer shapeRenderer) {
        unblockAlgorithm.animate();
        drawGrid();
    }

    public List<TextButton> getButtons() {
        return btns;
    }

    public void dispose() {
    }

    private void createBtns() {
        btns = new ArrayList<>();
        btns.add(startBtn());
        btns.add(resetBtn());
    }

    private TextButton resetBtn() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Reset");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                unblockAlgorithm.reset();
            }
        });
        return button;
    }

    private TextButton startBtn() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Start");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                unblockAlgorithm.init(grid);
            }
        });
        return button;
    }

    private void drawGrid() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                com.raunits.algorithms.Utils.drawRect(
                        startX + j * Cell.CELL_SIZE,
                        startY - i * Cell.CELL_SIZE,
                        Cell.CELL_SIZE,
                        Cell.CELL_SIZE,
                        Unblock.root(i, j).color,
                        true
                );
            }
        }
    }
}