package com.raunits.algorithms.trees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.raunits.algorithms.Animation;
import com.raunits.algorithms.Constants;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTrees extends Animation {
    private int WIDTH;
    private int HEIGHT;
    BitmapFont font;
    TextButton refreshButton;
    TextButton startDfs;
    TextButton startBfs;
    TextButton resetDfs;
    TextButton.TextButtonStyle textButtonStyle;
    private final int NODE_RADIUS = 5;
    private TreeNode root;
    private float elapsed, duration;
    private Stack<TreeNode> stackDFS;
    private Queue<TreeNode> bfsQueue;

    public void create(Stage stage, ShapeRenderer shapeRenderer) {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        addRefreshButton(stage);
        addDfsButton(stage);
        addBfsButton(stage);
        addResetDfsButton(stage);

        root = new TreeNode(29);
        root.x = WIDTH / 2f;
        root.y = HEIGHT - NODE_RADIUS * 10;
        Utils.generateRandomTree(root);

        stackDFS = new Stack<>();
        bfsQueue = new LinkedList<>();
        duration = 0.5f;
        elapsed = 0;
    }

    public void render(Stage stage, ShapeRenderer shapeRenderer) {
        drawTree(shapeRenderer);

        animateDfs();
        animateBfs();
    }

    @Override
    public void dispose() {
    }

    private void animateBfs() {
        if (!bfsQueue.isEmpty()) {
            if (elapsed < duration) {
                elapsed += Gdx.graphics.getDeltaTime();
            } else {
                TreeNode curr = bfsQueue.poll();
                curr.color = Constants.VIOLET;
                if (curr.left != null) bfsQueue.add(curr.left);
                if (curr.right != null) bfsQueue.add(curr.right);
                elapsed = 0;
            }
        }
    }
    private void animateDfs() {
        if (!stackDFS.isEmpty()) {
            if (elapsed < duration) {
                elapsed += Gdx.graphics.getDeltaTime();
            } else {
                TreeNode curr = stackDFS.pop();
                curr.color = Constants.VIOLET;
                if (curr.right != null) stackDFS.add(curr.right);
                if (curr.left != null) stackDFS.add(curr.left);
                elapsed = 0;
            }
        }
    }

    private void resetGraphics() {
        root = null;
        ScreenUtils.clear(Constants.BACKGROUND);

        root = new TreeNode(29);
        root.x = WIDTH / 2f;
        root.y = HEIGHT - NODE_RADIUS * 10;
        Utils.generateRandomTree(root);
    }

    private void drawTree(ShapeRenderer shapeRenderer) {
        if (root == null) return;

        drawBranches(root, shapeRenderer);
        drawNodes(shapeRenderer);
    }
    private void reset(TreeNode root) {
        if (root == null) return;

        root.color = Constants.RED;

        reset(root.left);
        reset(root.right);
    }
    private void drawBranches(TreeNode root, ShapeRenderer shapeRenderer) {
        if (root == null) return;

        if (root.left != null) com.raunits.algorithms.Utils.drawline(root, root.left, shapeRenderer);
        if (root.right != null) com.raunits.algorithms.Utils.drawline(root, root.right, shapeRenderer);

        drawBranches(root.left, shapeRenderer);
        drawBranches(root.right, shapeRenderer);
    }

    private void drawNodes(ShapeRenderer shapeRenderer) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // draw current
            com.raunits.algorithms.Utils.drawCircle(current.color, current, NODE_RADIUS, shapeRenderer);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }



    private void addRefreshButton(Stage stage) {
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Constants.RED;

        refreshButton = new TextButton("Refresh", textButtonStyle);
        refreshButton.setColor(Constants.RED);

        refreshButton.setX(WIDTH - refreshButton.getWidth() - 10);
        refreshButton.setY(HEIGHT - refreshButton.getHeight());

        stage.addActor(refreshButton);

        refreshButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                resetGraphics();
            }
        });
    }

    private void addDfsButton(Stage stage) {
        startDfs = new TextButton("Start DFS", textButtonStyle);
        startDfs.setColor(Constants.RED);

        startDfs.setX(refreshButton.getX() - 10);
        startDfs.setY(refreshButton.getY() - 20);

        stage.addActor(startDfs);

        startDfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                stackDFS.push(root);
                elapsed = 0.5f;
            }
        });
    }
    private void addBfsButton(Stage stage) {
        startBfs = new TextButton("Start BFS", textButtonStyle);
        startBfs.setColor(Constants.RED);

        startBfs.setX(refreshButton.getX() - 10);
        startBfs.setY(startDfs.getY() - startBfs.getHeight());

        stage.addActor(startBfs);

        startBfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                bfsQueue.add(root);
                elapsed = 0.5f;
            }
        });
    }
    private void addResetDfsButton(Stage stage) {
        resetDfs = new TextButton("Reset", textButtonStyle);
        resetDfs.setColor(Constants.RED);

        resetDfs.setX(refreshButton.getX() - 15);
        resetDfs.setY(startBfs.getY() - resetDfs.getHeight());

        stage.addActor(resetDfs);

        resetDfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                stackDFS = new Stack<>();
                reset(root);
            }
        });
    }
}