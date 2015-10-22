package com.govind.util.tree;

/**
 * Created by govindp on 9/12/2015.
 */
public class BinaryTree {
    private BinaryTree left;
    private BinaryTree right;
    private int leftSize;

    public int getLeftSize() {
        return leftSize;
    }

    public void setLeftSize(int leftSize) {
        this.leftSize = leftSize;
    }

    private int data;

    public BinaryTree(BinaryTree left, BinaryTree right, int data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTree() {
    }

    public BinaryTree(BinaryTree left, BinaryTree right) {
        this.left = left;
        this.right = right;
    }

    public BinaryTree(int data) {
        this.data = data;
    }

    public static BinaryTree getBinaryTree(){
        BinaryTree binaryTree5 = new BinaryTree(5);
        BinaryTree binaryTree7 = new BinaryTree(7);
        //BinaryTree binaryTree1 = new BinaryTree(1);
        BinaryTree binaryTree3 = new BinaryTree(new BinaryTree(null,null,8),null,3);
        BinaryTree binaryTree2 = new BinaryTree(null, binaryTree3, 2);
        BinaryTree binaryTree6 = new BinaryTree(binaryTree5, binaryTree7, 6);
        BinaryTree root4 = new BinaryTree(binaryTree2, binaryTree6, 4);
        return root4;
    }

    public static BinaryTree getBinarySearchTree() {
        BinaryTree binaryTree5 = new BinaryTree(5);
        BinaryTree binaryTree7 = new BinaryTree(7);
        BinaryTree binaryTree1 = new BinaryTree(1);
        BinaryTree binaryTree3 = new BinaryTree(3);
        BinaryTree binaryTree2 = new BinaryTree(binaryTree1, binaryTree3, 2);
        BinaryTree binaryTree6 = new BinaryTree(binaryTree5, binaryTree7, 6);
        BinaryTree root4 = new BinaryTree(binaryTree2, binaryTree6, 4);
/*
        BinaryTree binaryTree2 = new BinaryTree();
        BinaryTree binaryTree2 = new BinaryTree();
        BinaryTree binaryTree2 = new BinaryTree();
        BinaryTree binaryTree2 = new BinaryTree();
        BinaryTree binaryTree2 = new BinaryTree();
*/
        return root4;
    }

    public static int height(BinaryTree binaryTree) {
        if (binaryTree == null) {
            return 0;
        }
        return 1 + Math.max(height(binaryTree.getLeft()), height(binaryTree.getRight()));
    }

    public static BinaryTree toBinaryTree(int[] a, int l, int h) {
        if (l > h) {
            return null;
        }
        int mid = (l + h) / 2;
        BinaryTree binaryTree = new BinaryTree(a[mid]);
        binaryTree.setLeft(toBinaryTree(a, l, mid - 1));
        binaryTree.setRight(toBinaryTree(a, mid + 1, h));
        return binaryTree;
    }

    public static void inOrder(BinaryTree binaryTree) {
        if (binaryTree == null) {
            return;
        }
        inOrder(binaryTree.getLeft());
        System.out.print(binaryTree.getData()+" ");
        inOrder(binaryTree.getRight());
    }

    public static boolean isBinaryTree(BinaryTree binaryTree, int min, int max) {
        if (binaryTree == null) {
            return true;
        }

        if (binaryTree.getData() <= min || binaryTree.getData() > max) {
            return false;
        }

        return isBinaryTree(binaryTree.getLeft(), min, binaryTree.getData()) &&
                isBinaryTree(binaryTree.getRight(), binaryTree.getData(), max);
    }

    public static BinaryTree insertToBinaryTree(int data, BinaryTree root) {
        if (root == null) {
            root = new BinaryTree(data);
            return root;
        }

        if (root.getData() < data) {
            root.setRight(insertToBinaryTree(data, root.getRight()));
        } else {
            root.setLeftSize(root.getLeftSize() + 1);
            root.setLeft(insertToBinaryTree(data, root.getLeft()));
        }
        return root;
    }
}
