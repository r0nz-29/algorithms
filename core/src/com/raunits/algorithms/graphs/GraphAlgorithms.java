package com.raunits.algorithms.graphs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.raunits.algorithms.Animation;
import com.raunits.algorithms.Constants;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class GraphAlgorithms extends Animation {
    public HashMap<Node, LinkedHashSet<Node>> graph;
    int WIDTH;
    int HEIGHT;
    public void create (Stage stage, ShapeRenderer shapeRenderer) {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT= Gdx.graphics.getHeight();

        graph = Utils.generateRandomGraph(WIDTH, HEIGHT);
    }

    public void render (Stage stage, ShapeRenderer shapeRenderer) {
        drawGraph(shapeRenderer);
    }

    public void dispose () {
    }
    public void onRefresh() {
        ScreenUtils.clear(Constants.BACKGROUND);
        graph = Utils.generateRandomGraph(WIDTH, HEIGHT);
    }
    private void drawGraph (ShapeRenderer renderer) {
        for (Map.Entry<Node, LinkedHashSet<Node>> e: graph.entrySet()) {
            com.raunits.algorithms.Utils.drawCircle(Constants.RED, e.getKey(), Utils.NODE_RADIUS, renderer);

            for (Node nb: e.getValue()) {
                com.raunits.algorithms.Utils.drawCircle(Constants.RED, nb, Utils.NODE_RADIUS, renderer);
                com.raunits.algorithms.Utils.drawline(e.getKey(), nb, renderer);
            }
        }
    }
}
