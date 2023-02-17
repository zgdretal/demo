package com.zgd.menhu.demo.service.test;

public class SortTree {

    int size = 0;

    public static void main(String[] args) {
        SortTree sortTree = new SortTree();
        TreeNode root = new TreeNode();
    }

    public void handle(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.getLeft();
        TreeNode right = root.getRight();
        if (left != null || right != null) {
            if (left != null) {
                handle(left);
            }
            System.out.println(root.getValue());
            if (right != null) {
                handle(right);
            }
        }
    }
}
