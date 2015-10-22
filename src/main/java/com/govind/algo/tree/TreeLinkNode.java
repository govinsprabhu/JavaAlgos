package com.govind.algo.tree;

import java.util.Locale;

/**
 * Created by govindp on 10/22/2015.
 */
public class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
    
    public static void inorder(TreeLinkNode TreeLinkNode){
        if (TreeLinkNode == null){
            return;
        }
        inorder(TreeLinkNode.left);
        System.out.printf(String.valueOf(String.valueOf(TreeLinkNode.val)+" next " +TreeLinkNode.next.val == null
                ? null
                : TreeLinkNode.next.val));
        inorder(TreeLinkNode.right);
    }

}
