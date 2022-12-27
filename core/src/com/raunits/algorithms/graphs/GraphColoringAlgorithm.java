package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Constants;
import com.raunits.algorithms.NodeColorPair;
import com.raunits.algorithms.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GraphColoringAlgorithm extends Algorithm {
    HashMap<Node, HashSet<Node>> graph;
    HashMap<Color, Color> complements;
    Queue<NodeColorPair<Node>> pendingColorsQueue;
    Node[] sameColorNodes;
    boolean stopped;

    public void init(Object o) {
        super.init(o);
        complements = new HashMap<>();
        pendingColorsQueue = new LinkedList<>();
        this.graph = (HashMap<Node, HashSet<Node>>) o;
        sameColorNodes = new Node[2];

        complements.put(Constants.VIOLET, Constants.GREEN);
        complements.put(Constants.GREEN, Constants.VIOLET);

        stopped = false;
        pendingColorsQueue = new LinkedList<>();
        getOrderedNodes();
        elapsed = duration;
    }

    public void animate() {
        if (pendingColorsQueue == null) return;
        if (pendingColorsQueue.isEmpty()) {
            if (stopped) {
                Utils.drawline(sameColorNodes[0], sameColorNodes[1], Constants.RED, new ShapeRenderer());
                Utils.drawAlertMsg("Non-bipartite graph!!", Constants.RED);
            }
            else Utils.drawAlertMsg("Bipartite graph!!", Constants.GREEN);
        }

        if (!pendingColorsQueue.isEmpty()) {
            if (elapsed < duration) {
                elapsed += Gdx.graphics.getDeltaTime();
            }
            else {
                NodeColorPair<Node> curr = pendingColorsQueue.poll();
                curr.node.color = curr.color;
                elapsed = 0;
            }
        }
    }

    public void reset() {
        stopped = false;
        pendingColorsQueue = null;
        sameColorNodes = new Node[2];

        if (graph != null) for (Node node: graph.keySet()) node.color = Constants.RED;
    }

    private void getOrderedNodes() {
        if (stopped) return;

        Queue<Node> queue = new LinkedList<>();

        Node start = graph.keySet().iterator().next();
        start.fakeColor = Constants.VIOLET;
        queue.add(start);
        pendingColorsQueue.add(new NodeColorPair<>(start, Constants.VIOLET));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Node nb: graph.get(curr)) {
                // un-colored
                if (nb.fakeColor == Color.BLACK) {
                    pendingColorsQueue.add(new NodeColorPair<>(nb, complements.get(curr.fakeColor)));
                    nb.fakeColor = complements.get(curr.fakeColor);
                    queue.add(nb);
                }
                // not bipartite
                else if (nb.fakeColor == curr.fakeColor) {
                    sameColorNodes[0] = nb;
                    sameColorNodes[1] = curr;
                    stopped = true;
                    return;
                }
            }
        }
    }
}