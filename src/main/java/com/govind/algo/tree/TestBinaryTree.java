package com.govind.algo.tree;


import com.govind.util.tree.BinaryTree;
import payload.LCAPayload;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by govindp on 9/12/2015.
 */
public class TestBinaryTree {
    private BinaryTree root;
    private int count = 0;

    public static void main(String[] args) {
        leftView(BinaryTree.getBinaryTree(), new TestBinaryTree(),1);

    }

    public static void leftView(BinaryTree binaryTree, TestBinaryTree testBinaryTree, int count){

        if (binaryTree == null){
            return;
        }
        if (testBinaryTree.count < count){
            System.out.println(binaryTree.getData());
            testBinaryTree.count = count;
        }
        count++;
        leftView(binaryTree.getLeft(), testBinaryTree, count);
        leftView(binaryTree.getRight(), testBinaryTree, count);

    }

    public static int minHeight(BinaryTree root){
        if (root == null){
            return 0;
        }

        return 1+ Math.min(minHeight(root.getLeft()),minHeight(root.getRight()));


    }

    public static void toDDL(BinaryTree root, BinaryTree start, BinaryTree head){
        if (root == null){
            return ;
        }

        toDDL(root.getLeft(), start,head);
        if(start.getRight() == null){
            start.setRight(root);//previous
            head.setRight(root);//head
        }else{
            start.getRight().setRight(root);
            root.setLeft(start.getLeft());
            start.setRight(root);
        }

        toDDL(root.getRight(), start,head);
    }

    public  int getRank( BinaryTree binaryTree, int data) {

        if (binaryTree.getData() == data) {
            return binaryTree.getLeftSize();
        }

        if (binaryTree.getData() > data) {
            if (binaryTree.getLeft() == null)
                return -1;
            return getRank(binaryTree.getLeft(),data);
        } else {
            if (binaryTree.getRight() == null) {
                return -1;
            }
            int right = getRank(binaryTree.getRight(),data);
            return right + 1 + binaryTree.getLeftSize();
        }
    }

    private void insert(int data) {
        if(root == null){
            root= new BinaryTree(data);
        }else
            insetInto(root, data);
    }

    private void insetInto(BinaryTree binaryTree, int data) {
        if (binaryTree.getData() >= data) {
            if (binaryTree.getLeft() != null) {
                insetInto(binaryTree.getLeft(), data);

            }
            else
                binaryTree.setLeft(new BinaryTree(data));
            binaryTree.setLeftSize(binaryTree.getLeftSize()+1);
        } else {
            if (binaryTree.getRight() != null) {
                insetInto(binaryTree.getRight(), data);
            } else
                binaryTree.setRight(new BinaryTree(data));
        }
    }


    private static BinaryTree LCA(BinaryTree binaryTree, int i, int i1) {
        if (binaryTree == null) {
            return null;
        }

        LCAPayload lcaPayload = new LCAPayload();
        BinaryTree root = LCAUtil(binaryTree, i, i1, lcaPayload);
        if ((lcaPayload.isVisited1() == true && lcaPayload.isVisited2() == true) || (lcaPayload.isVisited1() == true && isFound(binaryTree, i1)) || (lcaPayload.isVisited2() == true && isFound(binaryTree, i))) {
            return root;
        }
        return null;
    }

    private static boolean isFound(BinaryTree binaryTree, int i1) {
        if (binaryTree == null) {
            return false;
        }

        if (binaryTree.getData() == i1 || isFound(binaryTree.getLeft(), i1) || isFound(binaryTree.getRight(), i1)) {
            return true;
        }

        return false;
    }

    private static BinaryTree LCAUtil(BinaryTree binaryTree, int p, int q, LCAPayload lcaPayload) {
        if (binaryTree == null) {
            return null;
        }

        if (p == binaryTree.getData()) {
            lcaPayload.setVisited1(true);
            return binaryTree;
        }

        if (q == binaryTree.getData()) {
            lcaPayload.setVisited2(true);
            return binaryTree;
        }

        BinaryTree left = LCAUtil(binaryTree.getLeft(), p, q, lcaPayload);
        BinaryTree right = LCAUtil(binaryTree.getRight(), p, q, lcaPayload);
        if (left != null && right != null) {
            return binaryTree;
        }

        return left != null ? left : right;
    }

    private static void findSum(BinaryTree binaryTree, int sum, int[] array, int level) {
        if (binaryTree == null) {

            return;
        }
        sum -= binaryTree.getData();
        array[level++] = binaryTree.getData();
        if (binaryTree.getLeft() == null && binaryTree.getRight() == null && sum == 0) {
            for (int i = 0; i < level; i++) {
                System.out.print(array[i]);
            }
            System.out.println();
            return;
        }

        findSum(binaryTree.getLeft(), sum, array, level);
        findSum(binaryTree.getRight(), sum, array, level);
    }
}
