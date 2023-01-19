package com.raunits.algorithms.graphs;

import java.util.HashMap;
import java.util.HashSet;

public class WeightedGraph {
    HashSet<Edge> edges;
    HashSet<Vertex> vertices;
    HashMap<Vertex, HashSet<Edge>> map;

    public WeightedGraph() {
        edges = new HashSet<>();
        vertices = new HashSet<>();
        map = new HashMap<>();
    }

    public HashSet<Edge> edges(Vertex v) {
        HashSet<Edge> es = map.get(v);
        System.out.println(es.size());
        return map.get(v);
    }

    public static WeightedGraph random(int width, int height) {
        return Utils.generateWeightedGraph(width, height);
    }
}