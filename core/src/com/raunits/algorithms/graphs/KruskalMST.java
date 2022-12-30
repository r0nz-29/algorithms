package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Constants;

import java.util.*;

public class KruskalMST extends Algorithm {
    PriorityQueue<Edge> minHeap;
    UnionFind uf;
    WeightedGraph graph;

    public void init(Object o) {
        super.init(o);
        graph = (WeightedGraph) o;
        uf = new UnionFind(graph.vertices);
        minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        minHeap.addAll(graph.edges);
        duration = 0.1f;
        elapsed = duration;
    }

    public void animate() {
        if (minHeap == null || minHeap.isEmpty()) return;

        if (elapsed < duration) {
            elapsed += Gdx.graphics.getDeltaTime();
            return;
        }

        Edge curr = minHeap.poll();
        Vertex v = curr.either();
        Vertex w = curr.other(v);

        if (!uf.connected(v, w)) {
            uf.union(v, w);

            v.color = Constants.RED;
            w.color = Constants.RED;
            curr.color = Constants.RED;
        }
        elapsed = 0;
    }

    public void reset() {
        elapsed = 0;
    }
}