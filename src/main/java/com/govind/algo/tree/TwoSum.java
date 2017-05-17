package com.govind.algo.tree;

import java.util.Stack;

/**
 * Created by govindp on 11/15/2015.
 */
public class TwoSum {
    TreeNode current1;
    TreeNode current2;
    Stack<TreeNode> stack1;
    Stack<TreeNode> stack2;

    public TwoSum(TreeNode root) {
        this.current1 = root;
        this.current2 = root;
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public static TreeNode getTreeNode(){

        TreeNode TreeNode10 = new TreeNode(10);
        TreeNode TreeNode1 = new TreeNode(10);
        TreeNode TreeNode2 = new TreeNode(8);
        TreeNode TreeNode3 = new TreeNode(12);
        TreeNode TreeNode4 = new TreeNode(7);
        TreeNode TreeNode5 = new TreeNode(13);
        TreeNode TreeNode6 = new TreeNode(11);
        TreeNode TreeNode7 = new TreeNode(9);

        //TreeNode10.left = TreeNode1;
        //TreeNode10.right = TreeNode2;
        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;
        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
        //TreeNode4.left = new TreeNode(8);
        //TreeNode4.left.left= new TreeNode(9);
        return TreeNode1;

    }

    public static void main(String[] args) {
        TreeNode treeNode = getTreeNode();
        new TwoSum(treeNode).twoSum();
    }

    public TreeNode next(){
        if (current1 != null){
            stack1.push(current1);
            current1 = current1.left;
            return next();
        }else {
            TreeNode top = stack1.pop();
            current1 = top.right;
            return top;

        }
    }

    public TreeNode previous(){
        if (current2 != null){
            stack2.push(current2);
            current2 = current2.right;
            return previous();
        }else {
            TreeNode top = stack2.pop();
            current2 = top.left;
            return top;
        }
    }

    public boolean hasNext(){
        if (stack1.isEmpty() && current1 == null ){
            return false;
        }
        return true;
    }

    public boolean hasPrevious(){
        if (stack2.isEmpty() && current2 == null){
            return false;
        }

        return true;
    }

    public void twoSum(){
        int k = 21;
        TreeNode current1 = next();
        TreeNode current2 = previous();
        while (current1.val < current2.val){
            int currenSum = current1.val + current2.val;
            if (currenSum == k){
                System.out.println(current1.val +" "+ current2.val);
                break;
            }
            if (hasNext()){
                current1 = next();
            }
            if (hasPrevious()){
                current2 = previous();
            }
        }
    }

}
