package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algorithm {
    Queue<Node> queue;
    HashSet<Node> visited;
    HashMap<Node, HashSet<Node>> graph;

    public void init(Object o) {
        super.init(o);
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.graph = (HashMap<Node, HashSet<Node>>) o;
        this.duration = 1.0f;

        Node root = graph.keySet().iterator().next();
        queue.add(root);
        visited.add(root);
        elapsed = duration;
    }

    public void animate() {
        if (queue == null) return;

        if (!queue.isEmpty()) {
            if (elapsed < duration) {
                elapsed += Gdx.graphics.getDeltaTime();
            } else {
                Node curr = queue.poll();
                curr.color = Constants.VIOLET;

                for (Node nb: graph.get(curr)) {
                    if (!visited.contains(nb)) {
                        queue.add(nb);
                        visited.add(nb);
                    }
                }
                elapsed = 0;
            }
        }
    }

    public void reset() {
        if (queue == null || visited == null) return;

        queue.clear();
        for (Node node: visited) node.color = Constants.RED;
        visited.clear();
    }
}