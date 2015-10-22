package com.govind.algo.tree;


import com.govind.util.tree.BinaryTree;

/**
 * Created by govindp on 9/20/2015.
 */
public class ConvertToBinaryTree {
    //private static BinaryTree previous = null;
    public static void main(String[] args) {
        BinaryTree head = new BinaryTree();
        convertToBinaryTree(BinaryTree.getBinarySearchTree(), head);
        head = head.getLeft();
        while (head.getRight() !=null){
            System.out.print(head.getData()+ " ");
            head = head.getRight();
        }
    }

    private static void convertToBinaryTree(BinaryTree binarySearchTree, BinaryTree head) {
        if(binarySearchTree == null){
            return;
        }

        convertToBinaryTree(binarySearchTree.getLeft(), head);
        if(head.getRight() == null){
            head.setLeft( binarySearchTree);
        }else {
            head.getRight().setRight(binarySearchTree);
            //previous.setRight(binarySearchTree);
            binarySearchTree.setLeft(binarySearchTree.getRight());
        }
        head.setRight(binarySearchTree);
        //previous = binarySearchTree;
        convertToBinaryTree(binarySearchTree.getRight(), head);
    }


}
