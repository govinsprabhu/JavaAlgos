package com.govind.algo.platform.interviewbit.tree;

import com.govind.algo.tree.TreeLinkNode;
import com.govind.util.tree.binarytree.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by 609600403 on 25/04/2016.
 */
public class Sample {
    public static void main(String[] args) throws IOException {
        new Sample().getMaxDiff();
    }

    public void getMaxDiff() {
        TreeNode treeNode = TreeNode.getBSTTree();
        System.out.println(getMax(treeNode, Integer.MIN_VALUE + 10000));
    }

    private int getMax(TreeNode treeNode, int max) {
        if (treeNode == null) {
            return Integer.MIN_VALUE;
        }

        int newMax = Math.max(max, treeNode.val);
        int leftMax = getMax(treeNode.left, newMax);
        int rightMax = getMax(treeNode.right,newMax);
        return Math.max(max - treeNode.val, Math.max(leftMax, rightMax));
    }

    private static int index;

    public TreeNode preOrder(int[] array, int min, int max) {
        if (array.length <= index || array[index] < min || array[index] > max) {
            return null;
        }

        TreeNode treeNode = new TreeNode(array[index]);
        index++;
        if (index < array.length) {
            treeNode.left = preOrder(array, min, treeNode.val);
            treeNode.right = preOrder(array, treeNode.val, max);
        }

        return treeNode;
    }

    public void morris(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                System.out.print(root.val + " ");
                root = root.right;
            } else {
                TreeNode current = root.left;
                while (current.right != null && current.right != root) {
                    current = current.right;
                }
                if (current.right == null) {
                    current.right = root;
                    root = root.left;
                } else {
                    current.right = null;
                    System.out.print(root.val + " ");
                    root = root.right;
                }
            }
        }
    }

    public int rob(TreeNode root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    public int[] helper(TreeNode root) {
        if (root == null) {
            int[] cR = {0, 0};
            return cR;
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int[] result = new int[2];
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }


    public class Value {
        int val;
        boolean isChild;

        public Value() {
        }

        public Value(int valFirst, boolean isChild) {
            this.val = valFirst;
            this.isChild = isChild;
        }
    }


    public int maxPathSum(TreeNode treeNode) {
        Max max = new Max();
        return Math.max(maxPathSumR(treeNode, max), max.max);
    }

    public int maxPathSumR(TreeNode a, Max max) {
        if (a == null) {
            return 0;
        }
        int left = maxPathSumR(a.left, max);
        int right = maxPathSumR(a.right, max);
        max.max = Math.max(max.max, a.val + left + right);
        return Math.max(a.val + left, Math.max(a.val + right, a.val));
    }

    public class Max {
        int max;
    }

    public ArrayList<String> prefix(ArrayList<String> a) {
        PrefixTreeNode root = new PrefixTreeNode();
        for (int i = 0; i < a.size(); i++) {
            insert(root, a.get(i));
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            search(root, a.get(i), result, "");
        }
        return result;
    }

    private void search(PrefixTreeNode root, String s, ArrayList<String> result, String currentResult) {
        if (s.isEmpty()) {
            result.add(currentResult);
            return;
        }

        if (root.count == 1) {
            result.add(currentResult);
            return;
        }
        currentResult += s.charAt(0);

        search(root.map.get(s.charAt(0)), s.substring(1), result, currentResult);


    }

    private void insert(PrefixTreeNode root, String s) {
        if (s.isEmpty()) {
            return;
        }
        root.count++;
        PrefixTreeNode prefixTreeNode;
        if (root.map.containsKey(s.charAt(0))) {
            prefixTreeNode = root.map.get(s.charAt(0));
        } else {
            prefixTreeNode = new PrefixTreeNode();
            root.map.put(s.charAt(0), prefixTreeNode);
        }
        insert(prefixTreeNode, s.substring(1));
    }

    public class PrefixTreeNode {
        int count;
        Map<Character, PrefixTreeNode> map;

        public PrefixTreeNode() {
            count = 0;
            map = new HashMap<>();
        }

    }

    private static int fillTree(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }

        int left = fillTree(array, 2 * index + 1);
        int right = fillTree(array, 2 * index + 2);

        int mul = left * right;
        array[index] = (mul) == 0 ? array[index] : mul;
        return array[index];
    }

   /* public ArrayList<Integer> postorderTraversal(TreeNode a) {
        Stack<TreeNode> parent = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
       *//* Integer.M
        while (a != null){
            parent.push(a);
            if (a.right != null){
                right.push(a.right);
            }if (a.left != null){
                a = a.left;
            }else {
                a = parent.pop();
                result.add(a.val);
            }*//*
        }


        return ;
    }*/


    public ArrayList<Integer> preorderTraversal(TreeNode a) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.isEmpty() || a != null) {
            if (a != null && a.right != null) {
                stack.push(a.right);
            }
            if (a != null) {
                result.add(a.val);
                a = a.left;
            } else {
                a = stack.pop();
            }
        }
        return result;
    }

    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode leftWall = null;
            TreeLinkNode previous = null;
            while (root != null) {
                if (root.left != null) {
                    if (leftWall == null) {
                        leftWall = root.left;
                    } else {
                        previous.next = root.left;
                    }
                    previous = root.left;
                }


                if (root.right != null) {
                    if (leftWall == null) {
                        leftWall = root.right;
                    } else {
                        previous.next = root.right;
                    }
                    previous = root.right;
                }
                root = root.next;
            }
            root = leftWall;
        }

        return;
    }


    public void morrisTraversal(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                System.out.print(root.val + " ");
                root = root.right;
            } else {
                TreeNode left = root.left;
                while (left.right != null && left.right != root) {
                    left = left.right;
                }

                if (left.right == null) {
                    left.right = root;
                    root = root.left;
                } else {
                    System.out.print(root.val + " ");
                    left.right = null;
                    root = root.right;
                }
            }
        }
    }

    public ArrayList<Integer> recoverTree(TreeNode a) {
        int bigger = Integer.MIN_VALUE;
        int neighbour = 0;
        int smaller = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        a = getNext(a, stack);
        int previous = a.val;
        a = a.right;
        while (a != null || !stack.isEmpty()) {
            a = getNext(a, stack);
            int current = a.val;
            a = a.right;
            if (previous > current && Integer.compare(bigger, Integer.MIN_VALUE) == 0) {
                bigger = previous;
                neighbour = current;
            } else if (previous > current) {
                smaller = current;
                break;
            }
            previous = current;
        }

        if (Integer.compare(smaller, Integer.MAX_VALUE) == 0) {
            smaller = neighbour;
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(smaller);
        result.add(bigger);
        return result;

    }

    public TreeNode getNext(TreeNode left, Stack<TreeNode> stack) {
        while (left != null) {
            stack.push(left);
            left = left.left;
        }
        return stack.pop();
    }

    public void inorder(TreeNode a, ArrayList<Integer> inorder) {
        if (a == null) {
            return;
        }

        inorder(a.left, inorder);
        inorder.add(a.val);
        inorder(a.right, inorder);
    }


    TreeNode left;
    TreeNode right;
    Stack<TreeNode> leftStack;
    Stack<TreeNode> rightStack;

    public void initialize(TreeNode root) {
        left = root;
        right = root;
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }


    public TreeNode getLeft() {
        while (left != null) {
            leftStack.push(left);
            left = left.left;
        }

        TreeNode leftNode = leftStack.pop();
        left = leftNode.right;
        return leftNode;
    }


    public TreeNode getRight() {
        while (right != null) {
            rightStack.push(right);
            right = right.right;
        }

        TreeNode rightNode = rightStack.pop();
        right = rightNode.left;
        return rightNode;
    }

    public int t2Sum(TreeNode A, int B) {
        initialize(A);
        TreeNode left = getLeft();
        TreeNode right = getRight();
        while (left.val != right.val) {
            int sum = left.val + right.val;
            if (Integer.compare(sum, B) == 0) {
                return 1;
            } else if (sum < B) {
                left = getLeft();
            } else {
                right = getRight();
            }
        }

        return 0;
    }

    public void nonRecursiveInorder(TreeNode treeNode, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                k--;
                if (k == 0) {
                    System.out.print(treeNode.val + " ");
                    break;
                }
                treeNode = treeNode.right;
            }
        }
    }
}
