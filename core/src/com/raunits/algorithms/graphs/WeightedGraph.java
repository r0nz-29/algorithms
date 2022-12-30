package com.raunits.algorithms.graphs;

import java.util.HashSet;

public class WeightedGraph {
    HashSet<Edge> edges;
    HashSet<Vertex> vertices;

    public WeightedGraph() {
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }

    public static WeightedGraph random(int width, int height) {
        return Utils.generateWeightedGraph(width, height);
    }
}