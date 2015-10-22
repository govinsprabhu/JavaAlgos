package com.govind.algo.tree;

import com.govind.util.ArrayListUtils;
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

    public static void main(String[] args) {
        TreeLinkNode TreeLinkNode10 = new TreeLinkNode(10);
        TreeLinkNode TreeLinkNode1 = new TreeLinkNode(1);
        TreeLinkNode TreeLinkNode2 = new TreeLinkNode(2);
        TreeLinkNode TreeLinkNode3 = new TreeLinkNode(3);
        TreeLinkNode TreeLinkNode4 = new TreeLinkNode(4);
        TreeLinkNode TreeLinkNode5 = new TreeLinkNode(5);
        TreeLinkNode TreeLinkNode6 = new TreeLinkNode(6);
        TreeLinkNode TreeLinkNode7 = new TreeLinkNode(5);

        TreeLinkNode10.left = TreeLinkNode1;
        TreeLinkNode10.right = TreeLinkNode2;
        TreeLinkNode1.left = TreeLinkNode3;
        TreeLinkNode1.right = TreeLinkNode4;
        TreeLinkNode2.left = TreeLinkNode5;
        TreeLinkNode2.right = TreeLinkNode6;
        TreeLinkNode3.left = null;
        TreeLinkNode3.right = null;
        new Solution().connect(TreeLinkNode10);
        TreeLinkNode.inorder(TreeLinkNode10);

    }

    public void connect(TreeLinkNode root) {

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
        return sumNumbers(a, 0);
    }

    private int sumNumbers(TreeNode a, int sum) {
        if (a == null) {
            return 0;
        }
        if (a.left == null && a.right == null) {
            sum += a.val;
            sum = sum % 1003;
            return sum;
        }

        return sumNumbers(a.left, ((sum + a.val) * 10) % 1003) +
                sumNumbers(a.right, ((sum + a.val) * 10) % 1003);
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

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = invertTree(root.right);
        root.right = invertTree(root.left);
        root.left = temp;
        return root;
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
   /* public static TreeNode buildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder){
        return buildTree(postorder,inorder,0, inorder.size() - 1, new Count(inorder.size()));
    }*/

/*
    public static TreeNode buildTree(ArrayList<Integer> postorder, ArrayList<Integer> inorder, int low, int high, Count count) {
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

class TreeNodeWithLevel {
    TreeLinkNode treeLinkNode;
    int level;

    public TreeNodeWithLevel(TreeLinkNode treeLinkNode, int level) {
        this.treeLinkNode = treeLinkNode;
        this.level = level;
    }
}