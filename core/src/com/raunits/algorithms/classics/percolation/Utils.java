package com.raunits.algorithms.classics.percolation;

import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Constants;

public class Utils {
    public static Cell[][] generateGrid(int n, Cell start, Cell end) {
        Cell[][] grid = new Cell[n+2][n+2];
        Cell blockedBound = new Cell();
        int id = 0;

        start.color = Constants.WATER;
        start.id = -1;

        end.color = Color.WHITE;
        end.id = n*n;

        blockedBound.color = Color.BLACK;
        blockedBound.id = 9999;

        // upper guard
        for (int k=1; k<=n; k++) grid[0][k] = start;

        // last guard
        for (int k=1; k <= n; k++) {
            Cell e = new Cell();
            e.id = n*n + k-1;
            e.color = Color.WHITE;
            grid[n + 1][k] = e;
        }

        // left bound
        for (int k=0; k <= n+1; k++) grid[k][0] = blockedBound;

        // right bound
        for (int k=0; k <= n+1; k++) grid[k][n+1] = blockedBound;

        for (int i=1; i <= n; i++) {
            for (int j=1; j<=n; j++) {
                grid[i][j] = new Cell();
                grid[i][j].id = id++;
                grid[i][j].i = i;
                grid[i][j].j = j;
            }
        }

        return grid;
    }

    public static void print(Cell[][] grid) {
        if (grid == null) return;

        for (Cell[] row : grid) {
            for (Cell c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
