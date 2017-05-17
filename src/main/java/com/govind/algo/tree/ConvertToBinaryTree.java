package com.govind.algo.tree;


import com.govind.util.linkedlist.DoubleLinkedList;
import com.govind.util.tree.BinaryTree;

/**
 * Created by govindp on 9/20/2015.
 */
public class ConvertToBinaryTree {
    //private static BinaryTree previous = null;
    public static void main(String[] args) {
/*
        BinaryTree head = new BinaryTree();
        convertFromBinaryTree(BinaryTree.getBinarySearchTree(), head);
        head = head.getLeft();
        while (head.getRight() !=null){
            System.out.print(head.getData()+ " ");
            head = head.getRight();
        }
*/
        new ConvertToBinaryTree().convertToBinaryTree();
    }

    private static void convertFromBinaryTree(BinaryTree binarySearchTree, BinaryTree head) {
        if(binarySearchTree == null){
            return;
        }

        convertFromBinaryTree(binarySearchTree.getLeft(), head);
        if(head.getRight() == null){

            //set head;
            head.setLeft( binarySearchTree);
        }else {
            //set previous
            head.getRight().setRight(binarySearchTree);
            //previous.setRight(binarySearchTree);
            binarySearchTree.setLeft(binarySearchTree.getRight());
        }
        head.setRight(binarySearchTree);
        //previous = binarySearchTree;
        convertFromBinaryTree(binarySearchTree.getRight(), head);
    }

    public void convertToBinaryTree(){
        DoubleLinkedList doubleLinkedList = DoubleLinkedList.getDoubleLinkedList();
        int length = DoubleLinkedList.length(doubleLinkedList);
        Root root = new Root();
        root.root = doubleLinkedList;
        DoubleLinkedList newRoot = convertToBinaryTreeUtil(root, length);
        inorder(newRoot);
    }

    private void inorder(DoubleLinkedList doubleLinkedList){
        if (doubleLinkedList == null){
            return;
        }

        inorder(doubleLinkedList.getNext());
        System.out.println(doubleLinkedList.getData());
        inorder(doubleLinkedList.getPrevious());
    }

    private DoubleLinkedList convertToBinaryTreeUtil(Root root, int length) {
        if (length <= 0 ){
            return null;
        }

        DoubleLinkedList left = convertToBinaryTreeUtil(root, length / 2);
        DoubleLinkedList currentRoot = root.root;
        currentRoot.setPrevious(left);

        root.root = root.root.getNext();


        currentRoot.setNext(convertToBinaryTreeUtil(root, length - length /2 - 1));

        return currentRoot;
    }

    class Root{
        DoubleLinkedList root;
    }







}
