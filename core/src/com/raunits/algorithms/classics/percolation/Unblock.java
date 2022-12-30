package com.raunits.algorithms.classics.percolation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Constants;

import java.util.HashSet;

public class Unblock extends Algorithm {
    HashSet<Integer> visited;
    static Cell[][] grid;
    static Cell start;
    static Cell end;
    static int N;

    public Unblock(Cell[][] _grid, Cell _s, Cell _e) {
        grid = _grid;
        start = _s;
        end = _e;
        N = grid.length - 2;
    }

    public void init(Object o) {
        super.init(o);
        visited = new HashSet<>();
        grid = (Cell[][]) o;

        duration = 0.05f;
        elapsed = duration;
    }

    public void animate() {
        if (start == null || end == null || visited == null) return;

        for (int k = 1; k <= N; k++) {
            if (start.id == root(N + 1, k).id) return;
        }

//        if (elapsed < duration) {
//            elapsed += Gdx.graphics.getDeltaTime();
//        } else {
            int index = com.raunits.algorithms.Utils.getRandomNumber(0, N * N - 1);

            while (visited.contains(index)) {
                index = com.raunits.algorithms.Utils.getRandomNumber(0, N * N - 1);
            }

            visited.add(index);

            int[] currIJ = toIJ(index);
            int i = currIJ[0], j = currIJ[1];

            Cell curr = grid[i][j];

            union(curr, curr);

            // top
            if (grid[i - 1][j].color != Color.BLACK) {
                union(root(i - 1, j), root(i, j));
            }

            // left
            if (grid[i][j - 1].color != Color.BLACK) {
                union(root(i, j - 1), root(i, j));
            }

            // right
            if (grid[i][j + 1].color != Color.BLACK) {
                union(root(i, j + 1), root(i, j));
            }

            // bottom
            if (grid[i + 1][j].color != Color.BLACK) {
                union(root(i + 1, j), root(i, j));
            }

//            elapsed = 0;
//        }
    }

    private void union(Cell a, Cell b) {
        int id = Math.min(a.id, b.id);
        a.id = id;
        b.id = id;

        Color newColor = id == -1 ? Constants.WATER : Color.WHITE;
        a.color = newColor;
        b.color = newColor;
    }

    public static Cell root(int i, int j) {
        if (i == 0) return start;

        Cell curr = grid[i][j];
        int serial = toIndex(i, j);

        while (curr.id != serial) {
            int[] next = toIJ(curr.id);

            if (next[0] == 0) return start;

            curr = grid[next[0]][next[1]];
            serial = toIndex(next[0], next[1]);
        }

        return curr;
    }

    private static int toIndex(int i, int j) {
        if (i == 0) return -1;
        i = i - 1;
        j = j - 1;
        return i * N + j;
    }

    private static int[] toIJ(int index) {
        if (index == -1) return new int[]{0, 1};
        return new int[]{1 + (index / N), 1 + (index % N)};
    }

    public void reset() {
        grid = Utils.generateGrid(N, start, end);
        visited = new HashSet<>();
    }
}