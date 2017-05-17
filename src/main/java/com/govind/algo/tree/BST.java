package com.govind.algo.tree;

import com.govind.util.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Created by govindp on 11/17/2015.
 */
public class BST {
    public static TreeNode getTreeNode() {
        TreeNode TreeNode1 = new TreeNode(50);
        TreeNode TreeNode2 = new TreeNode(10);
        TreeNode TreeNode3 = new TreeNode(60);
        TreeNode TreeNode4 = new TreeNode(5);
        TreeNode TreeNode5 = new TreeNode(20);
        TreeNode TreeNode6 = new TreeNode(55);
        TreeNode TreeNode7 = new TreeNode(70);

        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;

        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
        TreeNode6.left = new TreeNode(40);
        TreeNode7.right = new TreeNode(80);
//        TreeNode5.right.right.right = new TreeNode(25);
        return TreeNode1;
    }


    public static void main(String[] args) {
        new BST().predecessorAndSuccessor();
    }


    public void predecessorAndSuccessor() {
        TreeNode treeNode = Solution.getTreeNode();
        TreeNode predecessorAndSuccessor = new TreeNode(0);
        findPredecessorAndSuccessor(treeNode, predecessorAndSuccessor, 11);
        System.out.println(predecessorAndSuccessor.left.val + " " + predecessorAndSuccessor.right.val);
    }

    private void findPredecessorAndSuccessor(TreeNode treeNode, TreeNode predecessorAndSuccessor, int key) {
        if (treeNode == null) {
            return;
        }


        if (treeNode.val == key) {
            if (treeNode.left != null) {
                TreeNode temp = treeNode.left;
                while (temp.right != null) {
                    temp = temp.right;
                }

                predecessorAndSuccessor.left = temp;
            }

            if (treeNode.right != null) {
                TreeNode temp = treeNode.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                predecessorAndSuccessor.right = temp;
            }
        }

        if (treeNode.val > key) {
            predecessorAndSuccessor.right = treeNode;
            findPredecessorAndSuccessor(treeNode.left, predecessorAndSuccessor, key);
        }else if (treeNode.val < key){
            predecessorAndSuccessor.left = treeNode;
            findPredecessorAndSuccessor(treeNode.right, predecessorAndSuccessor, key);
        }
    }

    public void printLargestBSTInBinaryTree() {
        TreeNode treeNode = getTreeNode();
        BSTInfo bstInfo = new BSTInfo();
        System.out.println(printLargestBSTInBinaryTree(treeNode, bstInfo));
        TreeNode.inorder(bstInfo.root);
    }

    private int printLargestBSTInBinaryTree(TreeNode treeNode, BSTInfo bst) {
        if (treeNode == null) {
            return 0;
        }

        boolean isBst = true;
        int left = printLargestBSTInBinaryTree(treeNode.left, bst);
        int currentMin = left == 0 ? treeNode.val : bst.min;
        if (left == -1 || (left != 0 && treeNode.val <= bst.max)) {
            isBst = false;
        }
        int right = printLargestBSTInBinaryTree(treeNode.right, bst);
        int currentMax = right == 0 ? treeNode.val : bst.max;
        if (right == -1 || (right != 0 && treeNode.val >= bst.min)) {
            isBst = false;
        }

        if (!isBst) {
            return -1;
        }
        int totalCount = left + right + 1;
        bst.max = currentMax;
        bst.min = currentMin;
        bst.maxCount = Math.max(totalCount, bst.max);
        bst.root = treeNode;
        return totalCount;
    }

    class BSTInfo {
        int max;
        int min;
        TreeNode root;
        int maxCount;

        BSTInfo() {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
        }
    }

    public void printAllBST() {
        int n = 3;
        List<TreeNode> node = printAllBST(1, 3);
        for (TreeNode treeNode : node) {
            TreeNode.inorder(treeNode);
            System.out.println();
        }
    }

    private List<TreeNode> printAllBST(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }


        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = printAllBST(start, i - 1);
            List<TreeNode> rights = printAllBST(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }

        return list;
    }
}
