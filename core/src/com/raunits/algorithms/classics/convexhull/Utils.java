package com.raunits.algorithms.classics.convexhull;

import java.util.LinkedHashSet;

public class Utils {
    public static float[][] generateHull(float[][] points) {
        float[] start = points[0];
        for (float[] tree: points) {
            if (tree[0] < start[0]) start = tree;
            else if (tree[0] == start[0]) {
                if (tree[1] < start[1]) start = tree;
            }
        }

        float[] source = start;

        LinkedHashSet<float[]> result = new LinkedHashSet<>(), collinears = new LinkedHashSet<>();
        result.add(start);

        float[] target = points[0];
        do {
            for (int i=0; i<points.length; i++) {
                if (equal(points[i], source) || equal(points[i], target)) {
                    continue;
                }

                float product = crossProduct(source, target, points[i]);

                if (product > 0) {
                    target = points[i];
                    collinears.clear();
                }
                else if (product == 0) {
                    if (distance(source, target) <= distance(source, points[i])) {
                        collinears.add(target);
                        target = points[i];
                    }
                    else {
                        collinears.add(points[i]);
                    }
                }
            }

            for (float[] p: collinears) result.add(p);
            result.add(target);
            source = target;

        } while (!equal(target, start));

        int i=0;
        float[][] ans = new float[result.size()][2];
        for (float[] p: result) ans[i++] = p;
        return ans;
    }

    private static float crossProduct(float[] p, float[] q, float[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }

    private static float distance(float[] a, float[] b) {
        float x1 = a[0], x2 = b[0], y1 = a[1], y2 = b[1];
        return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
    }

    private static boolean equal(float[] a, float[] b) {
        return (a[0] == b[0] && a[1] == b[1]);
    }
}