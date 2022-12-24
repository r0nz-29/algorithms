package com.raunits.algorithms;

public class Algorithm {
    public float elapsed;
    public float duration;

    public void init(Object o) {
        this.elapsed = 0;
        this.duration = 0.5f;
    }

    public void animate() {

    }

    public void reset() {
        elapsed = 0;
    }
}