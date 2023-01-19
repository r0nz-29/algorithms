package com.raunits.algorithms.graphs;

import java.util.*;

public class Utils {
    public static int MIN_NODE_COUNT = 5;
    public static int NODE_RADIUS = 5;

    public static WeightedGraph generateWeightedGraph(int width, int height) {
        // total no. of nodes in the graph - minimum 10
        int NUM_NODES = 6;
        int id = 0;

        // graph can be represented as a hashmap of nodes and list of edges
        WeightedGraph graph = new WeightedGraph();

        // LinkedHashSet is used instead of a list in order to get rid of duplicates and thus prevent self loops
        HashSet<Vertex> vertices = graph.vertices;

        // nodes will be randomly placed on the canvas
        while (vertices.size() < NUM_NODES) {
            float x = com.raunits.algorithms.Utils.getRandomNumber(150, width - 150);
            float y = com.raunits.algorithms.Utils.getRandomNumber(10, height - 10);
            vertices.add(new Vertex(x, y, id++));
        }

        // list of all nodes - needed because hashsets are not indexed in java
        List<Vertex> uniqueNodeList = new ArrayList<>(vertices);

        // for each parent, calculate no of neighbours(nb) the parent should have,
        // and fill its adjacency list with that many edges
        for (Vertex parent : vertices) {
            HashSet<Edge> edges = new HashSet<>();

            // randomly set no. of children for each parent {min:0, max: total_nodes-1}
            int CHILDREN_COUNT = 1 + (int)(Math.random() * 5);

            // fill nb nodes
            for (int i=0; i < CHILDREN_COUNT; i++) {
                // get a random valid index
                int index = com.raunits.algorithms.Utils.getRandomNumber(0, NUM_NODES);
                Vertex potentialNB = uniqueNodeList.get(index);

                // add the potentialNB node to the adjacency list only if it is different from the parent node
                // else it'll result in a self-loop during traversals
                if (!potentialNB.equals(parent)) {
                    float edgeWeight = (parent.x - potentialNB.x)*(parent.x - potentialNB.x) + (parent.y - potentialNB.y)*(parent.y - potentialNB.y);
                    Edge e = new Edge(parent, potentialNB, (int)edgeWeight);
                    graph.map.computeIfAbsent(parent, k -> new HashSet<>());
                    graph.map.computeIfAbsent(potentialNB, k -> new HashSet<>()).add(e);
                    graph.map.get(parent).add(e);
                    graph.map.get(potentialNB).add(e);
                    edges.add(e);
                }
            }
            graph.edges.addAll(edges);
        }

        return graph;
    }

    public static HashMap<Node, LinkedHashSet<Node>> generateRandomGraph(int width, int height) {
        // total no. of nodes in the graph - minimum 5
        int NUM_NODES = MIN_NODE_COUNT + (int)(Math.random() * 5);
//        int NUM_NODES = MIN_NODE_COUNT;

        // graph can be represented as a hashmap of nodes and list of their neighbours
        HashMap<Node, LinkedHashSet<Node>> graph = new HashMap<>();

        // LinkedHashSet is used instead of a list in order to get rid of duplicates and thus prevent self loops
        LinkedHashSet<Node> nodes = new LinkedHashSet<>();

        // nodes will be randomly placed on the canvas
        while (nodes.size() < NUM_NODES) {
            float x = com.raunits.algorithms.Utils.getRandomNumber(10, width - 150);
            float y = com.raunits.algorithms.Utils.getRandomNumber(10, height - 10);
            nodes.add(new Node(x, y));
        }

        // list of all nodes - needed because hashsets are not indexed in java
        List<Node> uniqueNodeList = new ArrayList<>(nodes);

        // for each parent, calculate no of neighbours(nb) the parent should have,
        // and fill its adjacency list with that many nb nodes
        for (Node parent : nodes) {
            graph.computeIfAbsent(parent, k -> new LinkedHashSet<>());
            LinkedHashSet<Node> neighbours = graph.get(parent);

            // randomly set no. of children for each parent {min:0, max: total_nodes-1}
//            int CHILDREN_COUNT = com.raunits.algorithms.Utils.getRandomNumber(0,  NUM_NODES);
            int CHILDREN_COUNT = 2;

            // fill nb nodes
            while (neighbours.size() < CHILDREN_COUNT) {
                // get a random valid index
                int index = com.raunits.algorithms.Utils.getRandomNumber(0, NUM_NODES);
                Node potentialNB = uniqueNodeList.get(index);

                // add the potentialNB node to the adjacency list only if it is different from the parent node
                // else it'll result in a self-loop during traversals
                if (!potentialNB.equals(parent)) {
                    neighbours.add(potentialNB);
                    graph.computeIfAbsent(potentialNB, k -> new LinkedHashSet<>()).add(parent);
                }
            }
        }

        return graph;
    }

    private static void printGraph(HashMap<Node, LinkedHashSet<Node>> graph) {
        for (Map.Entry<Node, LinkedHashSet<Node>> e : graph.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(e.getKey().toString()).append(" -> ").append("[");
            for (Node nb : e.getValue()) sb.append(nb.toString()).append(", ");
            sb.append("]");
            System.out.println(sb);
        }
        System.out.println("------------------------------------------------------------------------------------");
    }
}