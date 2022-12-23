package com.raunits.algorithms.trees;

public class Utils {
    private static int SPACING = 15;

    public static void generateRandomTree(TreeNode root) {
        int height = 3 + (int) (Math.random() * 15);
        addChildren(root, height);
    }
    private static void addChildren(TreeNode root, int height) {
        if (height == 1) return;

        if (Math.random() >= 0.5) {
            root.left = new TreeNode((int)(Math.random() * 10));
            root.left.x = root.x - SPACING * height;
            root.left.y = root.y - SPACING*4;
            addChildren(root.left, height-1);
        }

        if (Math.random() >= 0.5) {
            root.right = new TreeNode((int)(Math.random() * 10));
            root.right.x = root.x + SPACING * height;
            root.right.y = root.y - SPACING*4;
            addChildren(root.right, height-1);
        }
    }
    public static void printTree(TreeNode root) {
        if (root == null) return;
        System.out.println(root);
        printTree(root.left);
        printTree(root.right);
    }
}
