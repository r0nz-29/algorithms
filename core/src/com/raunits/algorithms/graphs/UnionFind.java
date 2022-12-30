package com.raunits.algorithms.graphs;

import java.util.HashSet;

public class UnionFind {
    public int[] ids;
    
    public UnionFind(HashSet<Vertex> vertices) {
        ids = new int[vertices.size()];

        for (Vertex v: vertices) {
            ids[v.id] = v.id;
        }
    }

    public boolean connected(Vertex v, Vertex w) {
        return root(v) == root(w);
    }

    public void union(Vertex v, Vertex w) {
        int pv = root(v), pw = root(w);
        int min = Math.min(pv, pw);
        ids[pv] = min;
        ids[pw] = min;
    }

    private int root (Vertex v) {
        int id = v.id;
        while (id != ids[id]) {
            id = ids[id];
        }
        return id;
    }
}