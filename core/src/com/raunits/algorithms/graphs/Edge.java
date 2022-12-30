package com.raunits.algorithms.graphs;

import com.badlogic.gdx.graphics.Color;

import java.util.HashSet;
import java.util.Objects;

public class Edge {
    Vertex v;
    Vertex w;
    int weight;
    public Color color;

    public Edge(Vertex v, Vertex w) {
        this.v = v;
        this.w = w;
    }

    public Edge(Vertex v, Vertex w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
        this.color = Color.BLACK;
    }

    public Vertex either() {
        return v;
    }

    public Vertex other(Vertex v) {
        if (v.equals(this.v)) return w;
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge that = (Edge) o;
        return (v.equals(that.v) && w.equals(that.w)) || (v.equals(that.w) && w.equals(that.v));
    }

    @Override
    public int hashCode() {
        HashSet<Vertex> set = new HashSet<>();
        set.add(v);
        set.add(w);
        return Objects.hash(set);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("v=").append(v);
        sb.append(", w=").append(w);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}