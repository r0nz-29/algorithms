package com.raunits.algorithms;

import com.badlogic.gdx.graphics.Color;

public class NodeColorPair<T> {
    public T node;
    public Color color;

    public NodeColorPair(T node, Color color) {
        this.node = node;
        this.color = color;
    }
}