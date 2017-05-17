package com.govind.algo.tree;

/**
 * Created by govindp on 10/16/2015.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static void inorder(TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        inorder(treeNode.left);
        System.out.printf(String.valueOf(treeNode.val)+" ");
        inorder(treeNode.right);
    }

}
