package com.raunits.algorithms.matrices;

import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Constants;

public class Utils {
    static int MAX_OBSTACLE_DEPTH = 12;
    static int NUM_OBSTACLES = 15;
    static Color OBSTACLE_COLOR = Color.BLACK;
    static Color EMPTY_COLOR = Constants.BACKGROUND;
    static Color FILL_COLOR = Constants.PALE_GREEN;

    public static Cell[][] createBoard(int n) {
        Cell[][] board = new Cell[n][n+20];
        int r = board.length, c = board[0].length;
        MAX_OBSTACLE_DEPTH = 5 + (int)(Math.random() * 5);
        NUM_OBSTACLES = 3 + (int)(Math.random() * 5);

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] = new Cell(20 + j * (Cell.boardCellSize), 20 + i * (Cell.boardCellSize));
            }
        }

        for (int k = 0; k < NUM_OBSTACLES; k++) {
            int _i = (int) (Math.random() * r), _j = (int) (Math.random() * c);
            block(board, _i, _j, 0);
        }

        return board;
    }

    public static Cell[][] generateGrid(int n) {
        Cell[][] grid = new Cell[n][n + 20];
        MAX_OBSTACLE_DEPTH = n;

        int i = 0, j = 0;

        while (i < grid.length) {
            grid[i][j++] = new Cell(20 + j * Cell.width, 20 + i * Cell.width);

            if (j >= grid[0].length) {
                i++;
                j = 0;
            }
        }

        for (int k = 0; k < NUM_OBSTACLES; k++) {
            int _i = (int) (Math.random() * grid.length), _j = (int) (Math.random() * grid[0].length);
            block(grid, _i, _j, 0);
        }

        return grid;
    }

    private static void block(Cell[][] grid, int i, int j, int depth) {
        if (invalid(grid, i, j) || depth > MAX_OBSTACLE_DEPTH) return;

        grid[i][j].color = FILL_COLOR;

        if (Math.random() > Math.random()) block(grid, i - 1, j, depth + 1);
        if (Math.random() > Math.random()) block(grid, i + 1, j, depth + 1);
        if (Math.random() > Math.random()) block(grid, i, j - 1, depth + 1);
        if (Math.random() > Math.random()) block(grid, i, j + 1, depth + 1);
    }

    private static boolean invalid(Cell[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||
                grid[i][j].color == Utils.OBSTACLE_COLOR ||
                grid[i][j].color == Utils.FILL_COLOR;
    }
}