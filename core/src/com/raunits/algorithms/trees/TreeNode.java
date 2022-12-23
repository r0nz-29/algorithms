package com.raunits.algorithms.trees;

import com.badlogic.gdx.graphics.Color;
import com.raunits.algorithms.Constants;
import com.raunits.algorithms.Coordinate;

public class TreeNode extends Coordinate {
    int val;
    Color color = Constants.RED;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        super();
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{")
                .append("x: ").append(this.x).append(",")
                .append("y: ").append(this.y)
                .append("}");

        return sb.toString();
    }
}