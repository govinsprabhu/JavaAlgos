package com.govind.util.tree.binarytree;

/**
 * Created by 609600403 on 22/04/2016.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public static TreeNode root;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode getBSTTree() {
        TreeNode root6 = new TreeNode(6);
        TreeNode root9 = new TreeNode(9);
        TreeNode root13 = new TreeNode(13);
        TreeNode root11 = new TreeNode(11);
        TreeNode root8 = new TreeNode(8, root6, root9);
        TreeNode root12 = new TreeNode(12, root11, root13);
        TreeNode root1 = new TreeNode(10, root8, root12);
        if (root == null) {
            root = root1;
            return root;
        }
        return root;
    }


    public static TreeNode getTree() {
        TreeNode root6 = new TreeNode(5);
        TreeNode root13 = new TreeNode(8);
        TreeNode root9 = new TreeNode(4, root13, null);
        TreeNode root11 = new TreeNode(7, null, null);
        TreeNode root8 = new TreeNode(2, root9, root6);
        TreeNode root12 = new TreeNode(3, root11,null);
        TreeNode root1 = new TreeNode(1, root8, root12);
        if (root == null) {
            root = root1;
            return root;
        }
        return root;
    }

    public static void inorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inorder(treeNode.left);
        System.out.printf(String.valueOf(treeNode.val) + " ");
        inorder(treeNode.right);
    }

    public static void preorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.printf(String.valueOf(treeNode.val) + " ");
        preorder(treeNode.left);
        preorder(treeNode.right);
    }


}
