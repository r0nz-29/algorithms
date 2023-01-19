package com.raunits.algorithms.graphs;

import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Coordinate;

import java.util.Objects;

public class Vertex extends Coordinate {
    int id;
    Color color;

    public Vertex() {
        super(0, 0);
        color = Color.BLACK;
    }

    public Vertex(float x, float y, int id) {
        super(x, y);
        color = Color.BLACK;
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("V{");
        sb.append("id=").append(id);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        if (!super.equals(o)) return false;
        Vertex vertex = (Vertex) o;
        return color.equals(vertex.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}