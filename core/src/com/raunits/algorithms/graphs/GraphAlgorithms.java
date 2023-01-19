package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.raunits.algorithms.Algorithm;
import com.raunits.algorithms.Animation;
import com.raunits.algorithms.Constants;

import java.util.*;

public class GraphAlgorithms extends Animation {
    public WeightedGraph graph;
    int WIDTH;
    int HEIGHT;
    Algorithm algorithm;
    List<TextButton> buttons;
    boolean firstRender;

    public void create(Stage stage, ShapeRenderer shapeRenderer) {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        graph = WeightedGraph.random(WIDTH, HEIGHT);
        firstRender = true;
        createButtons();
    }

    public void render(Stage stage, ShapeRenderer shapeRenderer) {
        drawGraph(shapeRenderer);
        if (algorithm != null) algorithm.animate();
    }

    public void dispose() {
    }

    public List<TextButton> getButtons() {
        return this.buttons;
    }

    private void createButtons() {
        buttons = new ArrayList<>();
        buttons.add(createRefreshButton());
        buttons.add(kruskal());
        buttons.add(prims());
    }

    private void resetGraph() {
//        for (Node node: graph.keySet()) {
//            node.color = Constants.RED;
//            node.fakeColor = Color.BLACK;
//        }
    }

    private TextButton kruskal() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Kruskal's MST");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                resetGraph();
                algorithm = new KruskalMST();
                algorithm.init(graph);
            }
        });
        return button;
    }

    private TextButton prims() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Prim's MST");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                resetGraph();
                algorithm = new PrimsMST();
                algorithm.init(graph);
            }
        });
        return button;
    }

    private TextButton createRefreshButton() {
        TextButton refreshBtn = com.raunits.algorithms.Utils.createButton("Change graph");
        refreshBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                algorithm = null;
                ScreenUtils.clear(Constants.BACKGROUND);
                graph = WeightedGraph.random(WIDTH, HEIGHT);
            }
        });
        return refreshBtn;
    }

    private void drawGraph(ShapeRenderer renderer) {
        for (Edge edge : graph.edges) {
            Vertex v = edge.either();
            Vertex w = edge.other(v);
            com.raunits.algorithms.Utils.drawCircle(v.color, v, 5, renderer);
            com.raunits.algorithms.Utils.drawCircle(w.color, w, 5, renderer);
            com.raunits.algorithms.Utils.drawline(v, w, edge.color, renderer);
        }
    }
}