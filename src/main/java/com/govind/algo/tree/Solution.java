package com.govind.algo.tree;

import com.govind.util.arraylist.ArrayListUtils;
import com.govind.util.tree.BinaryTree;
import com.govind.util.tree.tries.Tries;

import java.util.*;

/**
 * Created by govindp on 10/16/2015.
 */
public class Solution {

    TreeNode current1;
    TreeNode current2;
    Stack<TreeNode> stack1;
    Stack<TreeNode> stack2;
    private Tries node;
    int data;

    public Solution(int data) {
        this.data = data;
    }


    public static TreeNode getTreeNode2() {
        TreeNode TreeNode1 = new TreeNode(10);
        TreeNode TreeNode2 = new TreeNode(8);
        TreeNode TreeNode3 = new TreeNode(12);
        TreeNode TreeNode4 = new TreeNode(7);
        TreeNode TreeNode5 = new TreeNode(9);
        TreeNode TreeNode6 = new TreeNode(11);
        TreeNode TreeNode7 = new TreeNode(13);

        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;
/*        TreeNode2.left = TreeNode4;
        TreeNode3.right = TreeNode5;

        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
*/
        /*TreeNode5.right = new TreeNode(15);
        TreeNode5.right.right = new TreeNode(20);
        TreeNode5.right.right.right = new TreeNode(25);
        */
        return TreeNode1;
    }

    public static TreeNode getTreeNode() {
        TreeNode TreeNode1 = new TreeNode(10);
        TreeNode TreeNode2 = new TreeNode(8);
        TreeNode TreeNode3 = new TreeNode(12);
        TreeNode TreeNode4 = new TreeNode(7);
        TreeNode TreeNode5 = new TreeNode(9);
        TreeNode TreeNode6 = new TreeNode(11);
        TreeNode TreeNode7 = new TreeNode(13);

        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;

        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
        return TreeNode1;
    }


    public static void main(String[] args) {
        new Solution().printNodesKDistanceKfromLeaf();

    }




    public  void printNodesKDistanceKfromLeaf(){
        TreeNode treeNode = getTreeNode();
        int k = 3;
        ArrayList<Integer> result = new ArrayList<>();
        System.out.println(printNodesFromDistanceKFromLeaf(treeNode, result, k));
        System.out.println(result);
    }

    private int printNodesFromDistanceKFromLeaf(TreeNode treeNode, ArrayList<Integer> result, int k) {
        if (treeNode == null){
            return 1;
        }

        int left = printNodesFromDistanceKFromLeaf(treeNode.left, result, k);
        int right = printNodesFromDistanceKFromLeaf(treeNode.right, result, k);
        if (left == k || right == k){
            result.add(treeNode.val);
        }

        return Math.min(left, right) + 1;
    }

    public void uniqueTree() {
        int n = 3;
        for (TreeNode treeNode : printUniqueTree(1, n)) {
            TreeNode.inorder(treeNode);
        }
    }
    public ArrayList<TreeNode> printUniqueTree(int l, int h) {
        if (l > h) {
            ArrayList<TreeNode> arrayList = new ArrayList<>();
            arrayList.add(null);
            return arrayList;
        }

        ArrayList<TreeNode> result = new ArrayList<>();
        for (int i = l; i <= h; i++) {

            ArrayList<TreeNode> left = printUniqueTree(l, i - 1);
            ArrayList<TreeNode> right = printUniqueTree(i + 1, h);
            for (TreeNode leftT : left) {

                for (TreeNode rightT : right) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftT;
                    treeNode.right = rightT;
                    result.add(treeNode);
                }
            }
        }

        return result;
    }


    public void getMaxPath() {
        TreeNode treeNode = getTreeNode();
        Height height = new Height();
        System.out.println(findMaxPath(treeNode, height));
        System.out.println(height.height);

    }

    private int findMaxPath(TreeNode treeNode, Height height) {
        if (treeNode == null) {
            return 0;
        }

        int left = findMaxPath(treeNode.left, height);
        int right = findMaxPath(treeNode.right, height);
        height.height = Math.max(height.height, left + right + treeNode.val);

        return Math.max(left, right) + treeNode.val;
    }


    public void deleteNodeLessThanSum() {
        TreeNode treeNode = getTreeNode();

    }

    public void reverseLevelOrderTraversal() {
        TreeNode treeNode = getTreeNode();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        levelOrder1(treeNode, 0, result);
        Collections.reverse(result);
        System.out.println(result);
    }

    private void levelOrder1(TreeNode treeNode, int depth, ArrayList<ArrayList<Integer>> result) {
        if (treeNode == null) {
            return;
        }

        if (result.size() == depth) {
            result.add(new ArrayList<Integer>());
        }

        result.get(depth).add(treeNode.val);

        levelOrder1(treeNode.left, depth + 1, result);
        levelOrder1(treeNode.right, depth + 1, result);

    }

    public void cntMatrix() {
        TreeNode treeNode = getTreeNode();
        TreeNode treeNode1 = getTreeNode2();
        System.out.println(cntMatrix(treeNode, treeNode1));
    }

    public int cntMatrix(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a != null && b == null) {
            return -1;
        }

        if (a == null && b != null) {

            return numberOfNodesOfB(b, 0);
        }
        int left = cntMatrix(a.left, b.left);
        int right = cntMatrix(a.right, b.right);
        if (left == -1) {
            return left;
        }
        if (right == -1) {
            return right;
        }

        return left + right;

    }

    public void getMode() {
        ArrayList<Integer> numbers = new ArrayListUtils<Integer>().getArrayList(new Integer[]{2, 2, 2, 3, 3});
        ArrayList<ArrayList<Integer>> q = new ArrayList<>();
        ArrayList<Integer> q1 = new ArrayList<>();
        ArrayList<Integer> q2 = new ArrayList<>();
        ArrayList<Integer> q3 = new ArrayList<>();
        q1.add(1);
        q1.add(3);
        q2.add(5);
        q2.add(4);
        q3.add(2);
        q3.add(4);
        q.add(q1);
        q.add(q2);
        q.add(q3);
        System.out.println(getMode(numbers, q));
    }

    public ArrayList<Integer> getMode(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> b) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.size(); i++) {
            if (map.containsKey(a.get(i))) {
                popFromStack(a.get(i), stack);
            }
            map.put(a.get(i), map.containsKey(a.get(i)) ? map.get(a.get(i)) + 1 : 1);
            insertToStack(a.get(i), stack, map);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.get(0).size() - 1; j += 2) {

            }
        }

        return result;
    }

    private void insertToStack(Integer newNumber, Stack<Integer> stack, HashMap<Integer, Integer> map) {
        Stack<Integer> backUp = new Stack<>();
        while (map.get(stack.peek()) > map.get(newNumber) || (map.get(stack.peek()) == map.get(newNumber) && stack.peek() > newNumber)) {
            backUp.push(stack.pop());
        }

        stack.push(newNumber);
        while (!backUp.isEmpty()) {
            stack.push(backUp.pop());
        }
    }

    private void popFromStack(Integer arrayValue, Stack<Integer> stack) {
        Stack<Integer> backUp = new Stack<>();
        while (stack.peek() != arrayValue) {
            backUp.push(stack.pop());
        }

        stack.pop();
        while (!backUp.isEmpty()) {
            stack.push(backUp.pop());
        }
    }

    private int numberOfNodesOfB(TreeNode b, int count) {
        if (b == null) {
            return 0;

        }


        return numberOfNodesOfB(b.left, count + 1) + 1 +
                numberOfNodesOfB(b.right, count + 1);
    }

    public void printDiffereceBetweenOddAndEvenLevel() {
        TreeNode treeNode = getTreeNode();
        System.out.println(printDifference(treeNode));
    }

    private int printDifference(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        return treeNode.val - printDifference(treeNode.left) - printDifference(treeNode.right);
    }

    public void getSumOfLengthOfLeftAndRight() {
        TreeNode treeNode = getTreeNode();

        System.out.println(getSumOfLengthOfLeftAndRightUtil(treeNode, new Height(), false));
    }


    private int getSumOfLengthOfLeftAndRightUtil(TreeNode treeNode, Height height, boolean isLeft) {
        if (treeNode == null) {
            return 0;
        }

        Height leftHeight = new Height();
        Height rightHeight = new Height();

        int left = getSumOfLengthOfLeftAndRightUtil(treeNode.left, leftHeight, true);
        int right = getSumOfLengthOfLeftAndRightUtil(treeNode.right, rightHeight, false);
        if (isLeft) {
            height.height = leftHeight.height + 1;
        } else {
            height.height = rightHeight.height + 1;
        }

        return Math.max(leftHeight.height + rightHeight.height + 1, Math.max(left, right));

    }

    private int diameter(TreeNode treeNode, Height height) {
        if (treeNode == null) {
            return 0;
        }

        Height leftHeight = null;
        Height rightHeight = null;

        int left = diameter(treeNode.left, leftHeight);
        int right = diameter(treeNode.right, rightHeight);
        height.height = leftHeight.height + rightHeight.height + 1;

        return Math.max(height.height, Math.max(left, right));
    }


    public void levelOrder() {
        TreeNode treeNode = getTreeNode();
        //System.out.println(levelOrder(treeNode));
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        levelOrder(treeNode, 0, arrayLists);
        System.out.println(arrayLists);
    }

    public void levelOrder(TreeNode treeNode, int depth, ArrayList<ArrayList<Integer>> arrayLists) {
        if (treeNode == null) {
            return;
        }

        if (arrayLists.size() == depth) {
            arrayLists.add(new ArrayList<Integer>());
        }

        arrayLists.get(depth).add(treeNode.val);
        levelOrder(treeNode.left, depth + 1, arrayLists);
        levelOrder(treeNode.right, depth + 1, arrayLists);
    }


    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode a) {
        Queue<NodeWithLevel> queue = new LinkedList<>();
        queue.add(new NodeWithLevel(a, 0));
        int level = 0;
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!queue.isEmpty()) {
            NodeWithLevel nodeWithLevel = queue.poll();
            if (level == nodeWithLevel.level) {
                arrayList.add(nodeWithLevel.node.val);
            } else {
                arrayLists.add(arrayList);
                arrayList = new ArrayList<>();
                arrayList.add(nodeWithLevel.node.val);
                level++;
            }
            if (nodeWithLevel.node.left != null) {
                queue.add(new NodeWithLevel(nodeWithLevel.node.left, nodeWithLevel.level + 1));
            }
            if (nodeWithLevel.node.right != null) {
                queue.add(new NodeWithLevel(nodeWithLevel.node.right, nodeWithLevel.level + 1));
            }
        }
        arrayLists.add(arrayList);
        return arrayLists;
    }

class NodeWithLevel {
    TreeNode node;
    int level;

    public NodeWithLevel(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}


    public void childrenSumProperty() {
        TreeNode treeNode = getTreeNode();

        childrenSumProperty(treeNode);
        TreeNode.inorder(treeNode);
    }

    private int childrenSumProperty(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        if (treeNode.left == null && treeNode.right == null) {
            return treeNode.val;
        }


        int left = childrenSumProperty(treeNode.left);
        int right = childrenSumProperty(treeNode.right);

        int diff = left + right - treeNode.val;
        if (diff > 0) {
            treeNode.val = treeNode.val + diff;
        } else {
            updateTheChildren(treeNode, diff);

        }

        return treeNode.val;
    }

    private void updateTheChildren(TreeNode root, int diff) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            root.left.val = root.left.val + diff * -1;
            childrenSumProperty(root.left);
        } else if (root.right != null) {
            root.right.val = root.right.val + diff * -1;
            childrenSumProperty(root.right);
        }
        return;
    }

    public void printPath() {
        TreeNode treeNode = getTreeNode();
        int data = 9;

        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> result = printPath(treeNode, data, arrayList);
        if (result == null) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println(result);
        }
    }

    private ArrayList<Integer> printPath(TreeNode treeNode, int data, ArrayList<Integer> arrayList) {
        if (treeNode == null) {
            return null;
        }

        if (treeNode.val == data) {
            return arrayList;
        }

        arrayList.add(1);
        ArrayList<Integer> left = printPath(treeNode.left, data, (ArrayList<Integer>) arrayList.clone());
        arrayList.set(arrayList.size() - 1, 0);
        ArrayList<Integer> right = printPath(treeNode.right, data, (ArrayList<Integer>) arrayList.clone());
        return left == right && left == null ? null : left == null ? right : left;
    }


    public void printBoarder() {
        TreeNode treeNode = getTreeNode();

        printLeft(treeNode.left);
        System.out.print(treeNode.val + " ->");
        printRight(treeNode.right);
    }

    private void printRight(TreeNode right) {
        if (right.left == null && right.right == null) {
            System.out.print(right.val + " ->");
            return;
        }
        System.out.println(right.val + " ->");
        printRight(right.left);
    }

    private void printLeft(TreeNode treeNode) {
        if (treeNode.left == null && treeNode.right == null) {
            System.out.print(treeNode.val + " ->");
            return;
        }
        printLeft(treeNode.right);
        printLeft(treeNode.left);

    }

    public void reorderSwappedTree() {
        TreeNode treeNode = getTreeNode();
        TreeNode.inorder(treeNode);
        TreeNodeForSwappedNodeTree treeNodeForSwappedNodeTree = new TreeNodeForSwappedNodeTree();
        findSwappedNode(treeNode, treeNodeForSwappedNodeTree);
        if (treeNodeForSwappedNodeTree.last == null) {
            int temp = treeNodeForSwappedNodeTree.first.val;
            treeNodeForSwappedNodeTree.first.val = treeNodeForSwappedNodeTree.middle.val;
            treeNodeForSwappedNodeTree.middle.val = temp;
        } else {
            int temp = treeNodeForSwappedNodeTree.first.val;
            treeNodeForSwappedNodeTree.first.val = treeNodeForSwappedNodeTree.last.val;
            treeNodeForSwappedNodeTree.last.val = temp;
        }
        System.out.println();
        TreeNode.inorder(treeNode);
    }

    private void findSwappedNode(TreeNode treeNode, TreeNodeForSwappedNodeTree treeNodeForSwappedNodeTree) {
        if (treeNode == null) {
            return;
        }

        findSwappedNode(treeNode.left, treeNodeForSwappedNodeTree);
        if (treeNodeForSwappedNodeTree.previous != null && treeNodeForSwappedNodeTree.previous.val > treeNode.val) {
            if (treeNodeForSwappedNodeTree.first == null) {
                treeNodeForSwappedNodeTree.first = treeNodeForSwappedNodeTree.previous;
                treeNodeForSwappedNodeTree.middle = treeNode;
            } else {
                treeNodeForSwappedNodeTree.last = treeNode;
            }
        }
        treeNodeForSwappedNodeTree.previous = treeNode;
        findSwappedNode(treeNode.right, treeNodeForSwappedNodeTree);
    }

class TreeNodeForSwappedNodeTree {
    TreeNode first;
    TreeNode middle;
    TreeNode last;
    TreeNode previous;
}

    public void isBalanced() {
        TreeNode treeNode = getTreeNode();
        System.out.println(isBalanced(treeNode, new Height()));

    }

    private boolean isBalanced(TreeNode treeNode, Height height) {
        if (treeNode == null) {
            return true;
        }

        Height left = new Height(), right = new Height();
        boolean leftHeight = isBalanced(treeNode.left, left);
        boolean rightHeight = isBalanced(treeNode.right, right);

        if (Math.abs(left.height - right.height) >= 2) {
            return false;
        }

        height.height = Math.max(left.height, right.height) + 1;
        return leftHeight && rightHeight;
    }

    public void getLongestPath() {
        TreeNode TreeNode1 = new TreeNode(10);
        TreeNode TreeNode2 = new TreeNode(8);
        TreeNode TreeNode3 = new TreeNode(12);
        TreeNode TreeNode4 = new TreeNode(7);
        TreeNode TreeNode5 = new TreeNode(9);
        TreeNode TreeNode6 = new TreeNode(11);
        TreeNode TreeNode7 = new TreeNode(13);

        //TreeNode10.left = TreeNode1;
        //TreeNode10.right = TreeNode2;
        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;
        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
        TreeNode4.left = new TreeNode(5);
        ArrayList<Integer> longestPath = getLongestPath(TreeNode1, new ArrayList<Integer>());
        System.out.println(longestPath);
    }

    public void checkTwoArraysFormsSameBST() {
        int[] array1 = {2, 4, 1, 3};
        int[] array2 = {2, 4, 3, 1};

        System.out.println(checkTwoArraysFormsSameBSTUtil(array1, array2, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    private boolean checkTwoArraysFormsSameBSTUtil(int[] array1, int[] array2, int i1, int i2, int maxValue, int minValue) {
        int j, k;


        for (j = i1; j < array1.length; j++) {
            if (array1[j] > minValue && array1[j] < maxValue) {
                break;
            }
        }

        for (k = i2; k < array2.length; k++) {
            if (array2[k] > minValue && array2[k] < maxValue) {
                break;
            }
        }

        if (j == array1.length && k == array1.length) {
            return true;
        }

        if (j == array1.length || k == array1.length) {
            return false;
        }

        if (array1[j] != array2[k]) {
            return false;
        }

        return checkTwoArraysFormsSameBSTUtil(array1, array2, j + 1, k + 1, array1[j], minValue)
                && checkTwoArraysFormsSameBSTUtil(array1, array2, j + 1, k + 1, maxValue, array1[j]);


    }


    private ArrayList<Integer> getLongestPath(TreeNode treeNode1, ArrayList<Integer> arrayList) {
        if (treeNode1 == null) {
            return arrayList;
        }

        ArrayList<Integer> arrayList1 = getLongestPath(treeNode1.left, (ArrayList<Integer>) arrayList.clone());
        ArrayList<Integer> arrayList2 = getLongestPath(treeNode1.right, (ArrayList<Integer>) arrayList.clone());
        ArrayList<Integer> arrayList3 = arrayList1.size() > arrayList2.size()
                ? arrayList1
                : arrayList2;
        arrayList3.add(treeNode1.val);
        return arrayList3;
    }


    public void diameter() {
        TreeNode TreeNode1 = new TreeNode(10);
        TreeNode TreeNode2 = new TreeNode(8);
        TreeNode TreeNode3 = new TreeNode(12);
        TreeNode TreeNode4 = new TreeNode(7);
        TreeNode TreeNode5 = new TreeNode(9);
        TreeNode TreeNode6 = new TreeNode(11);
        TreeNode TreeNode7 = new TreeNode(13);

        //TreeNode10.left = TreeNode1;
        //TreeNode10.right = TreeNode2;
        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;
        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
        TreeNode4.left = new TreeNode(5);
        System.out.println(getDiameter(TreeNode1, new Height()));
    }

    private int getDiameter(TreeNode treeNode1, Height diameter) {
        if (treeNode1 == null) {
            return 0;
        }

        Height leftHeight = new Height();
        Height rightHeight = new Height();
        int leftDiameter = getDiameter(treeNode1.left, leftHeight);
        int rightDiameter = getDiameter(treeNode1.right, rightHeight);
        diameter.height = Math.max(leftHeight.height, rightHeight.height) + 1;
        return Math.max(leftHeight.height + rightHeight.height + 1, Math.max(leftDiameter, rightDiameter));
    }


class Height {
    int height;
}


    public void nodeFromDistanceK() {
        TreeNode TreeNode1 = new TreeNode(10);
        TreeNode TreeNode2 = new TreeNode(8);
        TreeNode TreeNode3 = new TreeNode(12);
        TreeNode TreeNode4 = new TreeNode(7);
        TreeNode TreeNode5 = new TreeNode(9);
        TreeNode TreeNode6 = new TreeNode(11);
        TreeNode TreeNode7 = new TreeNode(13);

        //TreeNode10.left = TreeNode1;
        //TreeNode10.right = TreeNode2;
        TreeNode1.left = TreeNode2;
        TreeNode1.right = TreeNode3;
        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;
        TreeNode3.left = TreeNode6;
        TreeNode3.right = TreeNode7;
        TreeNode4.left = new TreeNode(5);
        nodeFromDistanceKUtil(TreeNode1, TreeNode2, 2);
    }

    private int nodeFromDistanceKUtil(TreeNode treeNode1, TreeNode target, int k) {
        if (treeNode1 == null) {
            return -1;
        }

        if (target == treeNode1) {
            printNodeKDistanceDown(target, k);
            return 0;
        }

        int dl = nodeFromDistanceKUtil(treeNode1.left, target, k);
        if (dl != -1) {
            if (dl + 1 == k) {
                System.out.println(treeNode1.val);
            } else {
                printNodeKDistanceDown(treeNode1.right, k - dl - 2);
            }

            return dl + 1;
        }


        int dr = nodeFromDistanceKUtil(treeNode1.right, target, k);

        if (dr != -1) {
            if (dr + 1 == k) {
                System.out.println(treeNode1.val);
            } else {
                printNodeKDistanceDown(treeNode1.left, k - dr - 2);
            }

            return dr + 1;
        }

        return -1;
    }

    private void printNodeKDistanceDown(TreeNode target, int k) {
        if (target == null) {
            return;
        }

        if (k == 0) {
            System.out.println(target.val);
        }

        printNodeKDistanceDown(target.left, k - 1);
        printNodeKDistanceDown(target.right, k - 1);
    }


    public boolean isBinaryTree(TreeNode treeNode, int max, int min) {

        if (treeNode == null) {
            return true;
        }


        boolean isLeft = isBinaryTree(treeNode.left, treeNode.val, min);
        boolean isRight = isBinaryTree(treeNode.right, max, treeNode.val);

        if (treeNode.val >= max || treeNode.val <= min) {
            return false;
        }
        return isLeft && isRight;

    }

    public void createBinaryTree(int[] parent) {
        TreeNode[] createdNodes = new TreeNode[parent.length];
        TreeNode head = new TreeNode(-1);
        for (int i = 0; i < parent.length; i++) {
            createBinaryTree(parent, createdNodes, head, i);
        }

        TreeNode.inorder(head.left);

    }


    private void createBinaryTree(int[] parent, TreeNode[] createdNodes, TreeNode head, int index) {
        if (createdNodes[index] != null) {
            return;
        }

        if (parent[index] == -1) {
            createdNodes[index] = new TreeNode(index);
            head.left = createdNodes[index];
            return;
        }


        if (createdNodes[parent[index]] == null) {
            createBinaryTree(parent, createdNodes, head, parent[index]);
        }


        TreeNode newTreeNode = new TreeNode(index);
        TreeNode parentNode = createdNodes[parent[index]];
        if (parentNode.left == null) {
            parentNode.left = newTreeNode;
        } else {
            parentNode.right = newTreeNode;
        }

        createdNodes[index] = newTreeNode;
    }

    public int heightOfParentTreeRepresentation(int[] array) {
        int[] depth = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            getHeight(depth, array, i);
        }

        int height = 0;
        for (int i = 0; i < depth.length; i++) {
            height = Math.max(height, depth[i]);
        }

        return height;

    }

    private void getHeight(int[] depth, int[] array, int index) {
        if (array[index] == -1) {
            depth[index] = 1;
        }
        if (depth[index] != 0) {
            return;
        }


        getHeight(depth, array, array[index]);

        depth[index] = depth[array[index]] + 1;

    }


    public int findSum(TreeNode treeNode, int sum) {
        if (treeNode.left == null && treeNode.right == null) {
            return sum + treeNode.val;
        }

        int data = treeNode.val;
        sum += data;
        int left = findSum(treeNode.left, sum * 10);
        int right = findSum(treeNode.right, sum * 10);
        return left + right;
    }


    public void replaceTreeWithValueOfChildGreaterThanIt(TreeNode treeNode, Sum sum) {
        if (treeNode == null) {
            return;
        }

        replaceTreeWithValueOfChildGreaterThanIt(treeNode.right, sum);
        sum.sum += treeNode.val;
        treeNode.val = sum.sum;
        replaceTreeWithValueOfChildGreaterThanIt(treeNode.left, sum);
    }

    public void extractLeaf(TreeNode treeNode, TreeNodePrevious treeNodePrevious) {
        if (treeNode.left == null && treeNode.right == null) {
            if (treeNodePrevious.head == null) {
                treeNodePrevious.head = treeNode;
                treeNodePrevious.previous = treeNode;
                return;
            }
            treeNodePrevious.previous.right = treeNode;
            treeNode.left = treeNodePrevious.previous;
            treeNodePrevious.previous = treeNode;
            return;

        }


        extractLeaf(treeNode.left, treeNodePrevious);
        extractLeaf(treeNode.right, treeNodePrevious);
    }

    /*public void connect(TreeLinkNode root) {
        TreeLinkNode current = root;
        TreeLinkNode previous = null;
        TreeLinkNode leftWall = null;
        while (current != null) {
            while (current != null) {
                if (current.left != null) {
                    if (previous == null) {
                        leftWall = current.left;
                    } else {
                        previous.next = current.left;
                    }

                    previous = current.left;
                }

                if (current.right != null) {
                    if (previous == null) {
                        leftWall = current.right;
                    } else {
                        previous.next = current.right;
                    }

                    previous = current.right;
                }

                current = current.next;
            }
            current = leftWall;
            previous = null;
            leftWall = null;

        }
        return;
    }*/


    public List<String> addOperators(String num, int target) {
        ArrayList<String> arrayList = new ArrayList<>();
        String operator = "+-*";
        char[] chars = new char[2 * num.length()];
        addOperators(num, 0, target, arrayList, 0, operator, chars, 0);
        return arrayList;
    }

    private void addOperators(String num, int l, int target, ArrayList<String> arrayList, int currentSum, String operators, char[] chars, int index) {
        if (l == num.length() - 1) {
            chars[index] = num.charAt(l);
            currentSum = toOperators(currentSum, chars[index - 1], chars[index]);
            if (currentSum == target) {
                arrayList.add(String.valueOf(chars));
            }
            return;
        }

        chars[index] = num.charAt(l);
        for (int j = 0; j < operators.length(); j++) {
            if (j == 2 && l == 0) {
                currentSum = 1;
            }

            chars[index + 1] = operators.charAt(j);
            currentSum = toOperators(currentSum, operators.charAt(j), chars[index]);
            addOperators(num, l + 1, target, arrayList, currentSum, operators, chars, index + 2);
        }
    }

    public int toOperators(int currentSum, char operator, char operandA) {
        switch (operator) {
            case '+':
                return Integer.parseInt(String.valueOf(operandA)) + currentSum;
            case '-':
                return currentSum - Integer.parseInt(String.valueOf(operandA));
            case '*':
                return Integer.parseInt(String.valueOf(operandA)) * currentSum;
        }

        return -1;
    }

    public void moveZeroes(int[] nums) {

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

        return;
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = numBelow(nums, mid);
            if (count > mid) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public int numBelow(int[] nums, int target) {
        int result = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] <= target) result++;
        return result;
    }

    public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
        if (heights.size() != infronts.size()) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < heights.size(); i++) {
            map.put(heights.get(i), infronts.get(i));
        }

        infronts = new ArrayList<>();
        Collections.sort(heights, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2
                        ? -1
                        : 0;
            }
        });
        for (int i = 0; i < heights.size(); i++) {
            infronts.add(map.get(heights.get(i)));
        }

        RopeDs ropeDs = new RopeDs(heights.get(0), infronts.get(0) + 1);
        for (int i = 1; i < heights.size(); i++) {
            insert(ropeDs, heights.get(i), infronts.get(i));
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        toInorder(arrayList, ropeDs);
        return arrayList;
    }

    private void insert(RopeDs ropeDs, Integer rootValue, Integer rankValue) {
        RopeDs previous = null;
        int rankvalueBackup = rankValue;
        while (ropeDs != null) {
            previous = ropeDs;
            rankvalueBackup = rankValue;
            if (ropeDs.leftNodeCount > rankValue) {
                ropeDs.leftNodeCount++;
                ropeDs = ropeDs.left;
            } else {
                rankValue = rankValue - ropeDs.leftNodeCount;
                ropeDs = ropeDs.right;
            }
        }

        if (previous.leftNodeCount <= rankvalueBackup) {
            previous.right = new RopeDs(rootValue, rankValue + 1);
        } else {
            previous.left = new RopeDs(rootValue, rankValue + 1);
        }

    }

    public void connect1(TreeLinkNode root) {

        TreeLinkNode previous = null;
        TreeLinkNode current = root;
        TreeLinkNode leftWall = null;
        while (current != null) {
            while (current != null) {
                if (current.left != null) {
                    if (previous == null) {
                        leftWall = current.left;
                    } else {
                        previous.next = current.left;
                    }
                    previous = current.left;
                }

                if (current.right != null) {
                    if (previous == null) {
                        leftWall = current.right;
                    } else {
                        previous.next = current.right;
                    }
                    previous = current.right;
                }

                current = current.next;
            }
            previous = null;
            current = leftWall;
            leftWall = null;
        }
    }

    public ArrayList<String> prefix(ArrayList<String> a) {
        Tries root = new Tries();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            insert(root, a.get(i));
        }

        for (int i = 0; i < a.size(); i++) {
            search(root, a.get(i), arrayList);
        }

        return arrayList;
    }

    private void search(Tries root, String string, ArrayList<String> arrayList) {
        Tries crawl = root;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            stringBuilder = stringBuilder.append(string.charAt(i));
            int index = string.charAt(i) - 'a';
            if (crawl.getTries(index).getPrefix() == 1) {
                arrayList.add(String.valueOf(stringBuilder));
                break;
            }
            crawl = crawl.getTries(index);
        }
    }

    private void insert(Tries root, String string) {
        Tries crawl = root;
        root.setCount(root.getCount() + 1);
        for (int i = 0; i < string.length(); i++) {
            int index = string.charAt(i) - 'a';
            if (crawl.getTries(index) == null) {
                crawl.setTries(new Tries(), index);
            }
            crawl.getTries(index).setPrefix(crawl.getTries(index).getPrefix() + 1);
            crawl = crawl.getTries(index);
        }

        crawl.setCount(root.getCount());
    }

    public TreeNode flatten(TreeNode a, Stack<TreeNode> treeNodes) {
        TreeNode root = a;
        while (a != null || !treeNodes.isEmpty()) {
            if (a.right != null) {
                treeNodes.push(a.right);
            }
            if (a.left == null && !treeNodes.isEmpty()) {
                TreeNode top = treeNodes.pop();
                a.right = top;
                a.left = null;
                a = top;
            } else {
                TreeNode left = a.left;
                a.right = left;
                a.left = null;
                a = left;
            }
        }


        return root;
    }

    public int lca(TreeNode root, int value1, int value2) {
        if (root == null) {
            return -1;
        }
        Count countDuplicate = new Count();
        isExist(root, countDuplicate, value1, value2);
        if (!countDuplicate.treeNode2Found || !countDuplicate.treeNode1Found) {
            return -1;
        }

        countDuplicate = new Count();
        TreeNode treeNode = LCA(root, value1, value2, countDuplicate);
        return treeNode.val;
    }

    private void isExist(TreeNode root, Count countDuplicate, int value1, int value2) {
        if (root == null) {
            return;
        }

        isExist(root.left, countDuplicate, value1, value2);
        if (root.val == value1) {
            countDuplicate.treeNode1Found = true;

        }
        if (root.val == value2) {
            countDuplicate.treeNode2Found = true;
        }
        isExist(root.right, countDuplicate, value1, value2);

    }

    private TreeNode LCA(TreeNode root, int value1, int value2, Count count) {
        if (root == null) {
            return null;
        }

        if (root.val == value1 && root.val == value2) {
            count.treeNode1Found = true;
            count.treeNode2Found = true;
            return root;
        }
        if (root.val == value1) {
            count.treeNode1Found = true;
            return root;

        }

        if (root.val == value2) {
            count.treeNode2Found = true;
            return root;
        }


        TreeNode left = LCA(root.left, value1, value2, count);
        TreeNode right = LCA(root.right, value1, value2, count);

        if (count.treeNode1Found && count.treeNode2Found && left != null && right != null) {
            return root;
        }


        return left == null
                ? right
                : left;


    }

    public int sumNumbers(TreeNode a) {
        a = new TreeNode(1);
        a.right = new TreeNode(2);
        System.out.println(sumNumbers(a, 0));
        return sumNumbers(a, 0);
    }

    private int sumNumbers(TreeNode a, int sum) {
        if (a == null) {
            return sum / 10;
        }
        /*if (a.left == null && a.right == null) {
            sum += a.val;
            sum = sum % 1003;
            return sum;
        }*/

        return sumNumbers(a.left, (sum * 10) % 1003) + a.val +
                sumNumbers(a.right, (sum * 10) % 1003);
    }

    private void sumNumbers(TreeNode a, ArrayList<Integer> arrayList, int sum) {
        if (a == null) {
            return;
        }
        if (a.left == null && a.right == null) {
            sum += a.val;
            arrayList.add(sum);
            return;
        }
        sumNumbers(a.left, arrayList, (sum + a.val) * 10);
        sumNumbers(a.right, arrayList, (sum + a.val) * 10);
    }

    public int height(TreeNode root) {
        return minDepth(root);
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(height(root.left), height(root.right)) + 1;
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        pathSum(root, sum, new ArrayList<Integer>(), arrayLists, 0);
        return arrayLists;
    }

    private void pathSum(TreeNode root, int sum, ArrayList<Integer> currentPath, ArrayList<ArrayList<Integer>> arrayLists, int index) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            currentPath.add(root.val);
            arrayLists.add(currentPath);
            return;
        }
        currentPath.add(root.val);
        pathSum(root.left, sum - root.val, currentPath, arrayLists, index + 1);

        pathSum(root.right, sum - root.val, currentPath, arrayLists, index + 1);
        currentPath.remove(currentPath.size() - 1);
    }

    public int hasPathSum(TreeNode a, int b) {
        return sum(a, b) == true
                ? 1
                : 0;

    }

    public boolean sum(TreeNode a, int b) {
        if (a == null) {
            return b == 0;
        }

        return sum(a.left, b - a.val) || sum(a.right, b - a.val);
    }

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(a);
        int count = 0;
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (count % 2 == 0) {
                while (!stack1.isEmpty()) {
                    TreeNode top = stack1.pop();
                    arrayList.add(top.val);
                    if (top.left != null) {
                        stack2.push(top.left);
                    }
                    if (top.right != null) {
                        stack2.push(top.right);
                    }
                }
            }
            if (count % 2 == 1) {
                while (!stack2.isEmpty()) {
                    TreeNode top = stack2.pop();
                    arrayList.add(top.val);
                    if (top.right != null) {
                        stack1.push(top.right);
                    }
                    if (top.left != null) {
                        stack1.push(top.left);
                    }
                }
            }
            arrayLists.add(arrayList);
            count++;
        }

        return arrayLists;

    }


    public void invertTree() {
        TreeNode treeNode = getTreeNode();
        TreeNode.inorder(treeNode);
        invertTree(treeNode);
        System.out.println();
        TreeNode.inorder(treeNode);
    }

    public void invertTree(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        invertTree(root.left);
        invertTree(root.right);
    }

    public Solution() {
    }


    public ArrayList<Integer> recoverTree(TreeNode a) {
        current1 = current2 = a;
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        int x1 = next();
        int previousX1 = x1;
        int x2 = previous();
        int previousX2 = x2;
        while (previousX1 <= x1 && previousX2 >= x2 && x1 <= x2) {
            previousX1 = x1;
            previousX2 = x2;
            x1 = next();
            x2 = previous();
        }
        if (previousX1 > x1) {
            while (previousX2 > x2) {
                previousX2 = x2;
                x2 = previous();
            }
            x1 = previousX1;
            x2 = previousX2;

        } else if (previousX2 < x2) {
            while (previousX1 < x1) {
                previousX1 = x1;
                x1 = next();
            }
            x1 = previousX1;
            x2 = previousX2;
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Math.min(x1, x2));
        arrayList.add(Math.max(x1, x2));
        return arrayList;
    }

    /**
     * @return whether we have a next smallest number
     */


    /**
     * @return the next smallest number
     */
    public int next() {
        if (current1 != null) {
            stack1.push(current1);
            current1 = current1.left;
            return next();
        } else if (!stack1.isEmpty()) {
            TreeNode top = stack1.pop();
            current1 = top.right;
            return top.val;
        }
        return -1;
    }

    public int t2Sum(TreeNode A, int B) {
        int x1 = next();
        int x2 = previous();
        while (x1 < x2) {
            if (x1 + x2 == B) {
                return 1;
            }
            if (x1 + x2 < B) {
                x1 = next();
            } else {
                x2 = next();
            }
        }
        return 0;
    }

    private int previous() {
        if (current2 != null) {
            stack2.push(current2);
            current2 = current2.right;
            return previous();
        } else if (!stack2.isEmpty()) {
            TreeNode top = stack2.pop();
            current2 = top.left;
            return top.val;
        }
        return -1;
    }

    private static void toInorder(ArrayList<Integer> arrayList, TreeNode a) {
        if (a == null) {
            return;
        }

        toInorder(arrayList, a.left);
        arrayList.add(a.val);
        toInorder(arrayList, a.right);
    }

    private static void toInorder(ArrayList<Integer> arrayList, RopeDs a) {
        if (a == null) {
            return;
        }

        toInorder(arrayList, a.left);
        arrayList.add(a.data);
        toInorder(arrayList, a.right);
    }

    /*public static int kthsmallest(TreeNode root, int k) {
        return kthsmallest(root, k, new Count());
    }*/

   /* private static int kthsmallest(TreeNode root, int k, Count count) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }


        int leftValue = kthsmallest(root.left, k, count);
        count.count++;
        if (k == count.count) {
            return root.val;
        }
        int rightValue = kthsmallest(root.right, k, count);
        return leftValue == rightValue && rightValue == Integer.MAX_VALUE
                ? rightValue
                : leftValue == Integer.MAX_VALUE
                ? rightValue
                : leftValue;
    }*/
   /* public static TreeNode buildTree(arraylist<Integer> inorder, arraylist<Integer> postorder){
        return buildTree(postorder,inorder,0, inorder.size() - 1, new Count(inorder.size()));
    }*/

/*
    public static TreeNode buildTree(arraylist<Integer> postorder, arraylist<Integer> inorder, int low, int high, Count count) {
        if (low > high || postorder.isEmpty()) {
            return null;
        }
        int firstPreOrder = postorder.get(count.count);
        int indexInInOrder = fineIndex(firstPreOrder, inorder, low, high);
        if (indexInInOrder == Integer.MAX_VALUE) {
            return null;
        }

        TreeNode treeNode = new TreeNode(inorder.get(indexInInOrder));
        count.count--;
        treeNode.right = buildTree(postorder, inorder, indexInInOrder + 1, high, count);
        treeNode.left = buildTree(postorder, inorder, low, indexInInOrder - 1, count);
        return treeNode;

    }
*/


    private static int fineIndex(int firstPreOrder, ArrayList<Integer> inorder, int low, int high) {
        for (int i = low; i <= high; i++) {
            if (firstPreOrder == inorder.get(i)) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static TreeNode buildTree(ArrayList<Integer> a) {
        return buildTree(a, 0, a.size() - 1);
    }

    public static TreeNode buildTree(ArrayList<Integer> a, int low, int high) {
        if (low > high) {
            return null;
        }
        int largers = Integer.MIN_VALUE;
        for (int i = low; i <= high; i++) {
            largers = Math.max(largers, a.get(i));
        }


        TreeNode treeNode = new TreeNode(largers);

        treeNode.left = buildTree(a, low, a.indexOf(largers) - 1);
        treeNode.right = buildTree(a, a.indexOf(largers) + 1, high);
        return treeNode;
    }

    public static TreeNode sortedArrayToBST(final List<Integer> a) {
        return sortedArrayToBST(a, 0, a.size() - 1);
    }

    private static TreeNode sortedArrayToBST(List<Integer> a, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = low + (high - low) / 2;
        TreeNode treeNode = new TreeNode(a.get(mid));

        treeNode.left = sortedArrayToBST(a, low, mid - 1);

        treeNode.right = sortedArrayToBST(a, mid + 1, high);

        return treeNode;
    }

    public static int isSymmetric(TreeNode a) {
        return isSymmetric(a, a);
    }

    public static int isSymmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return 1;
        }

        if (a == null || b == null) {
            return 0;
        }

        if (a.val != b.val) {
            return 0;
        }

        int leftSide = isSymmetric(a.left, b.right);
        int rightSide = isSymmetric(a.right, b.left);

        return leftSide == rightSide && rightSide == 1
                ? 1
                : 0;

    }

    public static int isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return 1;
        }

        if (a == null || b == null) {
            return 0;
        }

        if (a.val != b.val) {
            return 0;
        }

        int leftSide = isSameTree(a.left, b.left);
        int rightSide = isSameTree(a.right, b.right);

        return leftSide == rightSide && rightSide == 1
                ? 1
                : 0;
    }

    public static com.govind.util.tree.BinaryTree creatTree(int a[]) {
        BinaryTree[] treeArray = new BinaryTree[a.length];
        BinaryTree root = new BinaryTree();
        for (int i = 0; i < a.length; i++) {
            creatTree(a, i, treeArray, root);
        }

        return root;

    }

    private static void creatTree(int[] a, int i, BinaryTree[] treeArray, BinaryTree root) {
        if (treeArray[i] != null) {
            return;
        }

        treeArray[i] = new BinaryTree(i);

        if (a[i] == -1) {
            root.setLeft(treeArray[i]);
            return;
        }

        if (treeArray[a[i]] == null) {
            creatTree(a, a[i], treeArray, root);
        }

        BinaryTree parent = treeArray[a[i]];
        if (parent.getLeft() == null) {
            parent.setLeft(treeArray[i]);
        } else {
            parent.setRight(treeArray[i]);
        }


    }

    public static int isBalanced(TreeNode a) {
        if (a == null) {
            return 1;
        }
        int leftHeight = height1(a.left);
        int rightHeiht = height1(a.right);

        if ((Math.abs(leftHeight - rightHeiht) <= 1) && isBalanced(a.left) == 1 && isBalanced(a.right) == 1) {
            return 1;
        }

        return 0;


    }


    private static int height1(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(height1(root.left), height1(root.right)) + 1;
    }

    private static int minHeight(TreeNode root) {
        if (root == null)
            return 0;

        return Math.min(height1(root.left), height1(root.right)) + 1;
    }
}

class Count {
    boolean treeNode1Found;
    boolean treeNode2Found;
}

class RopeDs {
    int data;
    int leftNodeCount;

    RopeDs left;
    RopeDs right;

    public RopeDs(int data, int leftNodeCount) {
        this.data = data;
        this.leftNodeCount = leftNodeCount;
    }
}

class TreeNodePrevious {
    TreeNode previous;
    TreeNode head;
}


class Sum {
    int sum;
}