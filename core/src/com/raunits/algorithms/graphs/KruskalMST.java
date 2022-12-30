package com.raunits.algorithms.graphs;

import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Constants;

import java.util.*;

public class KruskalMST extends Algorithm {
    PriorityQueue<Edge> minHeap;
    Queue<Edge> mst;
    UnionFind uf;
    WeightedGraph graph;

    public void init(Object o) {
        super.init(o);
        graph = (WeightedGraph) o;
        uf = new UnionFind(graph.vertices);
        mst = new LinkedList<>();
        minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        minHeap.addAll(graph.edges);
    }

    public void animate() {
        if (minHeap == null || minHeap.isEmpty()) return;

        Edge curr = minHeap.poll();
        Vertex v = curr.either();
        Vertex w = curr.other(v);

        if (!uf.connected(v, w)) {
            uf.union(v, w);

            v.color = Constants.RED;
            w.color = Constants.RED;
            curr.color = Constants.RED;
            mst.add(curr);
        }
    }

    public void reset() {
        elapsed = 0;
    }
}