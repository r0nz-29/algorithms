package com.raunits.algorithms.matrices;

import com.badlogic.gdx.Gdx;
import com.raunits.algorithms.Algorithm;

public class GameOfLife extends Algorithm {
    Cell[][] board;

    public void init(Object o) {
        this.duration = 0.01f;

        this.board = (Cell[][]) o;
        elapsed = duration;
    }

    public void animate() {
//        if (elapsed < duration) {
//            elapsed += Gdx.graphics.getDeltaTime();
//            return;
//        }
        update();
//        reset();
    }

    public void reset() {
        elapsed = 0;
    }

    public void update() {
        int r = board.length, c = board[0].length;
        Cell[][] next = new Cell[r][c];

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                int live = 0;
                // top left
                if (i-1 >= 0 && j-1 >= 0 && board[i-1][j-1].color == Utils.FILL_COLOR) live++;
                // top
                if (i-1 >= 0 && board[i-1][j].color == Utils.FILL_COLOR) live++;
                // top right
                if (i-1 >= 0 && j+1 < c && board[i-1][j+1].color == Utils.FILL_COLOR) live++;
                // left
                if (j-1 >= 0 && board[i][j-1].color == Utils.FILL_COLOR) live++;
                // right
                if (j+1 < c && board[i][j+1].color == Utils.FILL_COLOR) live++;
                // bottom left
                if (i+1 < r && j-1 >= 0 && board[i+1][j-1].color == Utils.FILL_COLOR) live++;
                // bottom
                if (i+1 < r && board[i+1][j].color == Utils.FILL_COLOR) live++;
                // bottom right
                if (i+1 < r && j+1 < c && board[i+1][j+1].color == Utils.FILL_COLOR) live++;

                next[i][j] = board[i][j];

                // live
                if (board[i][j].color == Utils.FILL_COLOR) {
                    if (live < 2) next[i][j].color = Utils.EMPTY_COLOR;
                    else if (live == 2 || live == 3) next[i][j].color = Utils.FILL_COLOR;
                    else if (live > 3) next[i][j].color = Utils.EMPTY_COLOR;
                }
                // dead
                else if (board[i][j].color == Utils.EMPTY_COLOR && live == 3) next[i][j].color = Utils.FILL_COLOR;
            }
        }
        board = next;
    }
}
