package com.raunits.algorithms.graphs;

import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Constants;
import com.raunits.algorithms.Coordinate;

public class Node extends Coordinate {
    Color color = Constants.RED;
    Color fakeColor;

    public Node() {
        super();
    }

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
        this.fakeColor = Color.BLACK;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{x: ").append(this.x).append(", y: ").append(this.y).append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Node node) {
        return super.equals(node);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}