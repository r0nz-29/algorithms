package com.raunits.algorithms.classics.percolation;

import com.badlogic.gdx.graphics.Color;

public class Cell {
    public int id;
    public int i;
    public int j;
    public Color color;
    public static float CELL_SIZE = 15;

    public Cell() {
        id = 0;
        color = Color.BLACK;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(id);
        sb.append("}");
        return sb.toString();
    }
}