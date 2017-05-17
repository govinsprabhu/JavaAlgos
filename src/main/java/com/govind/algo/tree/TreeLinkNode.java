package com.govind.algo.tree;

import java.util.Locale;

/**
 * Created by govindp on 10/22/2015.
 */
public class TreeLinkNode {
    public int val;
    public TreeLinkNode left, right, next;
    public TreeLinkNode(int x) { val = x; }

    public TreeLinkNode(int val, TreeLinkNode left, TreeLinkNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void inorder(TreeLinkNode TreeLinkNode){
        if (TreeLinkNode == null){
            return;
        }
        inorder(TreeLinkNode.left);
        System.out.printf(String.valueOf(String.valueOf(TreeLinkNode.val)+" next " +TreeLinkNode.next == null
                ? null
                : TreeLinkNode.next.val));
        inorder(TreeLinkNode.right);
    }

    public static TreeLinkNode getTree(){
        TreeLinkNode root6 = new TreeLinkNode(6);
        TreeLinkNode root9 = new TreeLinkNode(9);
        TreeLinkNode root11 = new TreeLinkNode(11);
        TreeLinkNode root13 = new TreeLinkNode(13);
        TreeLinkNode root8 = new TreeLinkNode(8,root6,root9);
        TreeLinkNode root12 = new TreeLinkNode(12, root11,root13);
        TreeLinkNode root1 = new TreeLinkNode(10,root8,root12);

        return root1;
    }

}
