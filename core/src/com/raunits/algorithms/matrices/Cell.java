package com.raunits.algorithms.matrices;

import com.badlogic.gdx.graphics.Color;

import java.util.Objects;

public class Cell {
    Color color;
    float x;
    float y;
    static int width = 5;
    static int boardCellSize = 5;

    public Cell(float x, float y) {
        this.x = x;
        this.y = y;
        color = Utils.EMPTY_COLOR;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cell{");
        sb.append("color=").append(color);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
