package com.raunits.algorithms.matrices;

import com.raunits.algorithms.Algorithm;

public class FloodFill extends Algorithm {
    Cell[][] grid;

    public void init(Object o) {
        this.elapsed = 0;
        this.duration = 0.5f;

        grid = (Cell[][]) o;

        int i = (int) (Math.random() * grid.length), j = (int) (Math.random() * grid[0].length);

        while (grid[i][j].color != Utils.EMPTY_COLOR) {
            i = (int) (Math.random() * grid.length);
            j = (int) (Math.random() * grid[0].length);
        }

        fill(i, j);

        elapsed = duration;
    }

    public void animate() {
    }

    public void reset() {
        elapsed = 0;
    }

    private void fill(int i, int j) {
        if (invalid(i, j)) return;

        grid[i][j].color = Utils.FILL_COLOR;

        fill(i - 1, j);
        fill(i + 1, j);
        fill(i, j - 1);
        fill(i, j + 1);
    }

    private boolean invalid(int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||
                grid[i][j].color == Utils.OBSTACLE_COLOR ||
                grid[i][j].color == Utils.FILL_COLOR;
    }
}
