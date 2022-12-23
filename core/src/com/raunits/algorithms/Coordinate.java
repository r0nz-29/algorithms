package com.raunits.algorithms;

import com.raunits.algorithms.graphs.Node;

import java.util.Objects;

public class Coordinate {
    public float x;
    public float y;

    public Coordinate() {
    }

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Node node) {
        return this.x == node.x && this.y == node.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}