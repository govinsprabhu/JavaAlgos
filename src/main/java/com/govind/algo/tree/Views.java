package com.govind.algo.tree;


import com.govind.util.tree.BinaryTree;
import payload.BinaryTreePayload;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by govindp on 9/22/2015.
 */
public class Views {
    BinaryTree binaryTree;
    int hd;
    public Views(BinaryTree binaryTree,int hd){
        this.binaryTree = binaryTree;
        this.hd = hd;
    }
    public static void main(String[] args) {
        BinaryTree binaryTree = BinaryTree.getBinaryTree();

        topView(binaryTree);
    }

    public static void topView(BinaryTree root){
        Queue<Views> queue = new LinkedList<>();

        queue.add(new Views(root, 0));
        Set<Integer> hds = new HashSet<>();

        while (!queue.isEmpty()){

            Views views = queue.poll();
            if (!hds.contains(views.hd)){
                System.out.println(views.binaryTree.getData());
                if (views.binaryTree.getLeft() != null)
                    queue.add(new Views(views.binaryTree.getLeft(), views.hd - 1));
                if (views.binaryTree.getRight() != null)
                    queue.add(new Views(views.binaryTree.getRight(), views.hd + 1));
            }

            hds.add(views.hd);
        }

    }

    public static void leftView(BinaryTree root, BinaryTreePayload binaryTreePayload, int level) {
        if (root == null) {
            return;
        }
        if (binaryTreePayload.getMaxLevel() < level) {
            System.out.println(root.getData());
            binaryTreePayload.setMaxLevel(level);
        }

        leftView(root.getLeft(), binaryTreePayload, level + 1);
        leftView(root.getRight(), binaryTreePayload, level + 1);
    }
}
