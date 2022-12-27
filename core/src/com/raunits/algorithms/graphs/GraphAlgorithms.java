package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    public HashMap<Node, LinkedHashSet<Node>> graph;
    int WIDTH;
    int HEIGHT;
    Algorithm algorithm;
    List<TextButton> buttons;

    public void create(Stage stage, ShapeRenderer shapeRenderer) {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        graph = Utils.generateRandomGraph(WIDTH, HEIGHT);
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
        buttons.add(createBfsButton());
        buttons.add(resetBfsButton());
        buttons.add(startColoringButton());
    }

    private void resetGraph() {
        for (Node node: graph.keySet()) {
            node.color = Constants.RED;
            node.fakeColor = Color.BLACK;
        }
    }

    private TextButton startColoringButton() {
        TextButton button = com.raunits.algorithms.Utils.createButton("Check if bipartite");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                resetGraph();
                algorithm = new GraphColoringAlgorithm();
                algorithm.init(graph);
            }
        });
        return button;
    }
    private TextButton resetBfsButton() {
        TextButton btn = com.raunits.algorithms.Utils.createButton("Reset BFS");
        btn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                algorithm = null;
                resetGraph();
            }
        });
        return btn;
    }
    private TextButton createBfsButton() {
        TextButton startBfsButton = com.raunits.algorithms.Utils.createButton("Start BFS");
        startBfsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                resetGraph();
                algorithm = new BFS();
                algorithm.init(graph);
            }
        });
        return startBfsButton;
    }
    private TextButton createRefreshButton() {
        TextButton refreshBtn = com.raunits.algorithms.Utils.createButton("Change graph");
        refreshBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                algorithm = null;
                ScreenUtils.clear(Constants.BACKGROUND);
                graph = Utils.generateRandomGraph(WIDTH, HEIGHT);
            }
        });
        return refreshBtn;
    }
    private void drawGraph(ShapeRenderer renderer) {
        for (Map.Entry<Node, LinkedHashSet<Node>> e : graph.entrySet()) {
            com.raunits.algorithms.Utils.drawCircle(e.getKey().color, e.getKey(), Utils.NODE_RADIUS, renderer);

            for (Node nb : e.getValue()) {
                com.raunits.algorithms.Utils.drawCircle(nb.color, nb, Utils.NODE_RADIUS, renderer);
                com.raunits.algorithms.Utils.drawline(e.getKey(), nb, Color.BLACK, renderer);
            }
        }
    }
}