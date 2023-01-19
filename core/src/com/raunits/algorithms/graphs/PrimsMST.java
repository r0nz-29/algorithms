package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Constants;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PrimsMST extends Algorithm {
    PriorityQueue<Edge> minHeap;
    WeightedGraph graph;
    HashSet<Edge> mst;

    public void init(Object o) {
        super.init(o);
        graph = (WeightedGraph) o;
        minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        mst = new HashSet<>();

        visit(graph.map.keySet().iterator().next());

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

        if (v.color == Constants.RED && w.color == Constants.RED) elapsed = 0;
        else {
            mst.add(curr);
            curr.color = Constants.RED;

            if (v.color == Color.BLACK) visit(v);
            if (w.color == Color.BLACK) visit(w);
            elapsed = 0;
        }
    }

    private void visit(Vertex v) {
        v.color = Constants.RED;
        for (Edge e: graph.edges(v)) {
            if (e.other(v).color == Color.BLACK)
                minHeap.add(e);
        }
    }

    public void reset() {
        elapsed = 0;
    }
}
