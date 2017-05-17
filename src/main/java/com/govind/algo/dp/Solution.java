package com.govind.algo.dp;

import com.govind.algo.tree.TreeNode;
import com.govind.util.arraylist.ArrayListUtils;


import java.util.*;

/**
 * Created by govindp on 10/25/2015.
 */
public class Solution {

    private static int totalCount = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(12);
        //System.out.println(new Solution().TwoXMinGreaterThanMax(new int[]{4, 5, 100, 200, 300}));
        new Solution().equalPartition();
    }


    class MaxSum {
        int start;
        int finish;
        int sum;
    }

    public void maxSumRectangle() {
        int[][] a = {{1, 2, -1, -4, -20},
                     {-8, -3, 4, -2, -1},
                   {3, 8, -10, 100, 300},
                    {-4, -1, -1, 700, 6}
        };

        int maxSum = Integer.MIN_VALUE;
        int maxLeft = 0, maxright = 0, maxBottom = 0, maxTop = 0;
        for (int left = 0; left < a[0].length; left++) {
            int[] tmp = new int[a.length];
            for (int right = left; right < a[0].length; right++) {

                for (int row = 0; row < a.length; row++) {
                    tmp[row] += a[row][right];
                }

                if (left == 3 && right == 4){
                    System.out.println();
                }
                MaxSum currentSum = new MaxSum();
                kadanesMaxSum(tmp, currentSum);
                if (maxSum < currentSum.sum) {
                    maxSum = currentSum.sum;
                    maxLeft = left;
                    maxright = right;
                    maxTop = currentSum.start;
                    maxBottom = currentSum.finish;
                }

            }
        }

        System.out.println("Top corner "+ maxTop+" "+maxLeft);
        System.out.println("Bottom corner "+ maxBottom+" "+ maxright);
    }

    public void kadanesMaxSum(int[] tmp, MaxSum maxSum) {
        int sum = 0;
        int start = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < tmp.length; i++) {
            sum += tmp[i];
            if (sum <= 0) {
                start = i + 1;
                sum = 0;
            }
            if (sum > max){
                maxSum.sum = sum;
                maxSum.start = start;
                maxSum.finish = i;
            }
        }

    }

    public void subsetSumProblem() {
        int[] array = {3, 34, 4, 12, 5,};
        int sum = 12;

        boolean[][] dp = new boolean[array.length + 1][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = false;
        }

        for (int i = 1; i <= array.length; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= array[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - array[i - 1]];
                }
            }
        }

        System.out.println(dp[array.length][sum]);
    }


    public void largestIndependentSet() {
        TreeNode treeNode = com.govind.algo.tree.Solution.getTreeNode();
        System.out.println(largestIndependentSet(treeNode, new HashMap<TreeNode, Integer>()));
    }

    private int largestIndependentSet(TreeNode treeNode, HashMap<TreeNode, Integer> hashMap) {

        if (treeNode == null) {
            return 0;
        }

        if (hashMap.containsKey(treeNode)) {
            return hashMap.get(treeNode);
        }


        int excluding = largestIndependentSet(treeNode.left, hashMap) + largestIndependentSet(treeNode.right, hashMap);

        int including = 1;
        if (treeNode.left != null) {
            including += largestIndependentSet(treeNode.left.left, hashMap) + largestIndependentSet(treeNode.left.right, hashMap);
        }
        if (treeNode.right != null) {
            including += largestIndependentSet(treeNode.right.right, hashMap) + largestIndependentSet(treeNode.right.left, hashMap);
        }

        hashMap.put(treeNode, Math.max(including, excluding));
        return hashMap.get(treeNode);
    }

    public void optimalBinarySearchTree() {
        Integer[] key = {10, 20, 30};
        Integer[] frequesncy = {34, 8, 50};

        //System.out.println(optimalBinarySearchTree(frequesncy, 0, frequesncy.length - 1));
        optimalBinarySearchTreeDP(frequesncy);
    }

    private void optimalBinarySearchTreeDP(Integer[] frequency) {
        int[][] dp = new int[frequency.length][frequency.length];


        for (int i = 0; i < frequency.length; i++) {
            dp[i][i] = frequency[i];
        }
        for (int l = 1; l < frequency.length; l++) {
            for (int i = 0; i < frequency.length - l; i++) {
                int j = i + l;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int currentSum = ((k > i) ? dp[i][k - 1] : 0)
                            + ((k < j) ? dp[k + 1][j] : 0)
                            + sum(frequency, i, j);
                    dp[i][j] = Math.min(dp[i][j], currentSum);
                }
            }
        }

        System.out.println(dp[0][frequency.length - 1]);
    }

    private int sum(Integer[] frequesncy, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += frequesncy[k];
        }
        return sum;
    }

    private int optimalBinarySearchTree(Integer[] frequency, int l, int h) {
        if (h < l) {
            return 0;
        }

        if (l == h) {
            return frequency[l];
        }

        int sum = 0;
        for (int i = l; i <= h; i++) {
            sum += frequency[i];
        }

        int min = Integer.MAX_VALUE;
        for (int i = l; i <= h; i++) {
            min = Math.min(min, optimalBinarySearchTree(frequency, l, i - 1) + optimalBinarySearchTree(frequency, i + 1, h));
        }

        return min + sum;
    }

    public void equalPartition() {
        int[] array = {3, 1, 5, 9, 12};

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        if ((sum & 1) == 1) {
            System.out.println(false);
            return;
        }


        boolean[][] dp = new boolean[array.length + 1][sum / 2 + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = false;
        }

        for (int i = 1; i <= array.length; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= array[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - array[i - 1]];
                }
            }
        }


        for (int i = 0; i <= array.length; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                System.out.print((dp[i][j] == true ? 1: 0) + " ");
            }
            System.out.println();
        }
        System.out.println(dp[array.length][sum / 2]);
    }

    public void longestPalindromeSubsequent() {
        String string = "BBABCBCAB";
        int[][] dp = new int[string.length()][string.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        for (int level = 1; level < string.length(); level++) {
            for (int i = 0; i < string.length() - level; i++) {
                int j = i + level;
                if (level == 1) {
                    if (string.charAt(i) == string.charAt(j)) {
                        dp[i][j] = dp[i][j] + 2;
                    }
                } else if (string.charAt(i) == string.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[0][string.length() - 1]);
    }

    public void kruskals() {
        int[] value = {60, 60, 100, 120};
        int[] weight = {10, 10, 20, 30};
        int maxWeight = 50;
        System.out.println(kruskals(value, weight, maxWeight));
    }

    private int kruskals(int[] value, int[] weight, int maxWeight) {
        int[][] dp = new int[value.length + 1][maxWeight + 1];

        for (int i = 1; i <= value.length; i++) {
            for (int w = 1; w <= maxWeight; w++) {
                dp[i][w] = dp[i - 1][w];
                if (w >= weight[i - 1] && dp[i][w] < dp[i - 1][w - weight[i - 1]] + value[i - 1]) {
                    dp[i][w] = dp[i - 1][w - weight[i - 1]] + value[i - 1];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[value.length][maxWeight];
    }

    public void matrixChain() {
        int[] matrix = {1, 2, 3, 4};
        System.out.println(matrixChain(matrix));
    }

    private int matrixChain(int[] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int level = 1; level < matrix.length; level++) {
            for (int i = 1; i < n - level; i++) {
                int j = i + level;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j], (dp[i][k] + dp[k + 1][j] + matrix[i - 1] * matrix[k] * matrix[j]));
                }
            }
        }

        return dp[1][n - 1];
    }


    class Box {
        int height;
        int width;
        int length;

        public Box(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }
    }

    public void boxStacking() {
        ArrayList<Box> boxes = new ArrayListUtils<Box>().getArrayList(new Box[]{new Box(10, 12, 32), new Box(4, 5, 6), new Box(1, 2, 3), new Box(4, 6, 7)});
        System.out.println(findMaxBoxHeightHeight(boxes));
    }

    private int findMaxBoxHeightHeight(ArrayList<Box> boxes) {
        ArrayList<Box> rotated = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            Box currentBox = boxes.get(i);
            rotated.add(currentBox);
            rotated.add(new Box(currentBox.width, currentBox.height, currentBox.length));
            rotated.add(new Box(currentBox.height, currentBox.length, currentBox.width));
        }

        Collections.sort(rotated, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                if (o1.length != o2.length)
                    return o2.length - o1.length;
                if (o1.width != o2.width)
                    return o2.width - o1.width;
                if (o1.height != o2.height)
                    return o2.length - o1.length;
                return 0;
            }
        });

        int[] maxHeight = new int[rotated.size()];


        for (int i = 0; i < rotated.size(); i++) {
            maxHeight[i] = rotated.get(i).height;
        }

        for (int i = 1; i < rotated.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (rotated.get(i).length < rotated.get(j).length
                        && rotated.get(i).width < rotated.get(j).width
                        && maxHeight[j] + rotated.get(i).height >= maxHeight[i]) {
                    maxHeight[i] = maxHeight[j] + rotated.get(i).height;
                }
            }
        }


        int maximumHeight = 0;
        for (int i = 0; i < maxHeight.length; i++) {
            maximumHeight = Math.max(maximumHeight, maxHeight[i]);
        }

        return maximumHeight;
    }


    public void numberOfValidCutNeededForPalindrome() {
        String string = "aaba";
        boolean[][] isValid = new boolean[string.length()][string.length()];
        int[][] numberOfCut = new int[string.length()][string.length()];

        for (int line = 1; line <= string.length(); line++) {
            for (int i = 0; i <= string.length() - line; i++) {
                int j = i + line - 1;
                if (i == j) {
                    isValid[i][j] = true;
                } else if (line == 2) {
                    isValid[i][j] = (string.charAt(i) == string.charAt(j));
                } else {
                    isValid[i][j] = (string.charAt(i) == string.charAt(j)) && (isValid[i + 1][j - 1]);
                }

                if (isValid[i][j]) {
                    numberOfCut[i][j] = 0;
                } else {
                    numberOfCut[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        numberOfCut[i][j] = Math.min(numberOfCut[i][j], Math.min(numberOfCut[i][k], numberOfCut[k + 1][j]) + 1);
                    }
                }
            }
        }

        System.out.println(numberOfCut[0][string.length() - 1]);
    }

    public void wordBreak2() {
        String a = "myinterviewtrainer";
        ArrayList<String> b = new ArrayListUtils<>().getArrayList(new String[]{"my", "interview", "trainer"});
        boolean[] wordBreak = new boolean[a.length()];
        wordBreak[0] = true;
        for (int i = 0; i < a.length(); i++) {
            if (!wordBreak[i]) {
                return;
            }
            for (int j = 0; j < b.size(); j++) {
                String substring = b.get(i);
                String string = a.substring(i, b.get(i).length());
                if (substring.equals(string)) {
                    wordBreak[i + substring.length()] = true;
                }
            }
        }
        System.out.println();
    }

    public void wordBreak1() {
        String s = "catsanddog";
        ArrayList<String> dic = new ArrayListUtils<>().getArrayList(new String[]{"cat", "cats", "and", "sand", "dog"});
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> value = new ArrayList<>();
        value.add("");
        map.put(s.length(), value);
        for (int i = s.length() - 1; i >= 0; i--) {
            value = new ArrayList<>();
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (dic.contains(substring))
                    for (int k = 0; k < map.get(j).size(); k++) {
                        value.add(substring + " " + map.get(j).get(k));
                    }
            }
            map.put(i, value);
        }

        System.out.println(map.get(0));
    }

    public void maximalRectangle() {
        int[][] rect = {
                {1, 0, 1, 1, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1, 1, 1}};

        int[][] height = new int[rect.length][rect[0].length];
        for (int i = 0; i < rect[0].length; i++) {
            height[0][i] = rect[0][i];
        }

        for (int i = 1; i < rect.length; i++) {
            for (int j = 0; j < rect[0].length; j++) {
                if (rect[i][j] == 1) {
                    height[i][j] = height[i - 1][j] + 1;
                }
            }
        }


        ArrayList<Integer> rowHeight = new ArrayList<>();
        int area = 0;
        for (int i = 0; i < rect.length; i++) {
            for (int j = 0; j < rect[0].length; j++) {
                rowHeight.add(height[i][j]);
            }
            area = Math.max(area, findMaxArea(rowHeight));
            rowHeight.clear();
        }

        System.out.println(area);
    }

    private int findMaxArea(ArrayList<Integer> rowHeight) {
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        for (int i = 0; i < rowHeight.size(); ) {
            if (stack.isEmpty() || rowHeight.get(stack.peek()) <= rowHeight.get(i)) {
                stack.push(i);
                i++;
            } else {
                int topIndex = stack.pop();
                maxArea = Math.max(maxArea, rowHeight.get(topIndex) * (i - (stack.isEmpty() ? 0 : stack.peek()) - 1));
            }
        }

        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            maxArea = Math.max(maxArea, rowHeight.get(topIndex) * (topIndex - (stack.isEmpty() ? 0 : stack.peek())));
        }

        return maxArea;
    }

    public void rodCut() {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8;

        int[] maxPrice = new int[n];
        maxPrice[0] = price[0];
        for (int i = 1; i < 8; i++) {
            maxPrice[i] = price[i];
            for (int j = 0; j < i; j++) {
                maxPrice[i] = Math.max(maxPrice[i], maxPrice[i - 1 - j] + maxPrice[j]);
            }
        }

        System.out.println(maxPrice[n - 1]);
    }

    public void isInterleave() {
        String a = "ab";
        String b = "cd";
        String c = "abcd";
        //System.out.println(isInterleave(a, b, c));
        System.out.println(isInterleaveDp(a, b, c));
    }

    private int isInterleaveDp(String a, String b, String c) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                } else if (i == 0 && b.charAt(j - 1) == c.charAt(j - 1)) {
                    dp[0][j] = dp[0][j - 1];
                } else if (j == 0 && a.charAt(i - 1) == c.charAt(i - 1)) {
                    dp[i][0] = dp[i - 1][0];
                } else if (i > 0 && j > 0 && a.charAt(i - 1) == c.charAt(i + j - 1) && b.charAt(j - 1) != c.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i > 0 && j > 0 && a.charAt(i - 1) != c.charAt(i + j - 1) && b.charAt(j - 1) == c.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                } else if (i > 0 && j > 0 && a.charAt(i - 1) == c.charAt(i + j - 1) && b.charAt(j - 1) == c.charAt(i + j - 1)) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }


            }
        }
        return dp[a.length()][b.length()];
    }

    public int isInterleave(String a, String b, String c) {
        return isInterleave(a, b, c, 0, 0, 0) == true
                ? 1
                : 0;
    }

    private boolean isInterleave(String a, String b, String c, int i, int j, int k) {
        if (i >= a.length() && k >= c.length() && j >= b.length()) {
            return true;
        }

        if (k >= c.length()) {
            return false;
        }


        return (i < a.length() && a.charAt(i) == c.charAt(k) &&
                isInterleave(a, b, c, i + 1, j, k + 1)) ||
                (j < b.length() && b.charAt(j) == c.charAt(k) && isInterleave(a, b, c, i, j + 1, k + 1));
    }

    public void calculateMinimumHP() {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{-2, -3, 3}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{-5, -10, 1}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{10, 30, -5}));
        System.out.println(calculateMinimumHP1(arrayLists));
    }

    public int calculateMinimumHP1(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();

        int[][] dp = new int[m + 1][n + 1];
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        dp[m - 1][n] = 1;
        dp[m][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - a.get(i).get(j);

                if (dp[i][j] < 0) {
                    dp[i][j] = 1;
                }
            }
        }

        return dp[0][0];
    }

    public void minimumTotal() {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{2}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{3, 4}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{6, 5, 7}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 1, 8, 3}));
        System.out.println(minimumTotal1(arrayLists));
    }

    public int minimumTotal1(ArrayList<ArrayList<Integer>> a) {
        int[] array = new int[a.size()];
        for (int i = 0; i < a.get(a.size() - 1).size(); i++) {
            array[i] = a.get(a.size() - 1).get(i);
        }

        for (int i = a.size() - 2; i >= 0; i--) {
            for (int j = 0; j < a.get(i).size(); j++) {
                array[j] = a.get(i).get(j) + Math.min(array[j], array[j + 1]);
            }
        }

        return array[0];
    }

    public void numDistance() {
        String s = "rabbbit";
        String t = "rabbit";

    }

    public void anyTwo() {
        String a = "abab";
        System.out.println(anytwo1(a));
    }


    public void longestValidParentheses() {
//        System.out.println(longestValidParentheses1("()()()"));
        System.out.println(longestValidParentheses2("(()()()"));
    }

    private int longestValidParentheses2(String s) {
        int[] longestValid = new int[s.length()];

        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            }
            if (i - longestValid[i - 1] - 1 >= 0 && s.charAt(i - longestValid[i - 1] - 1) == '(') {
                longestValid[i] = longestValid[i - 1] + 2 + ((i - longestValid[i - 1] - 2 >= 0 ? longestValid[i - longestValid[i - 1] - 2] : 0));
                max = Math.max(max, longestValid[i]);
            }

        }

        return max;

    }

    public int longestValidParentheses1(String A) {

        int max = 0;
        Stack<Integer> stack = new Stack<>();
        char c;
        int n = A.length();

        stack.push(-1);

        for (int i = 0; i < n; i++) {
            if (A.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }

        return max;

    }

    public int anytwo1(String string) {
        int[][] dp = new int[string.length()][string.length()];
        dp[0][0] = 0;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == string.charAt(0)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == string.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }


        for (int i = 1; i < string.length(); i++) {
            for (int j = 1; j < string.length(); j++) {
                if (string.charAt(i) == string.charAt(j) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[string.length() - 1][string.length() - 1];
    }

    public void editDistance() {
        String s = "rabbbilt";
        String t = "rabbitoo";

        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i <= t.length(); i++) {
            for (int j = 0; j <= s.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], //deleting character
                            Math.min(dp[i - 1][j], //inserting the character
                                    dp[i - 1][j - 1])) + 1;//replaceing the character
                }
            }
        }

        System.out.println(dp[t.length()][s.length()]);

    }

    public void assemblyLine() {
        int[][] a = {{4, 5, 3, 2}, {0, 7, 4, 5}};
        int[] e = {10, 12};
        int[] x = {18, 7};
        int[][] t = {{0, 7, 4, 5}, {0, 9, 2, 8}};

        int[] t1 = new int[a[0].length];
        int[] t2 = new int[a[0].length];
        t1[0] = e[0] + a[0][0];
        t2[0] = e[1] + a[1][0];

        for (int j = 1; j < a[0].length; j++) {
            t1[j] = Math.min(t1[j - 1] + a[0][j], t2[j - 1] + t[1][j] + a[0][j]);
            t2[j] = Math.min(t2[j - 1] + a[1][j], t1[j - 1] + t[0][j] + a[1][j]);
        }

        System.out.println(Math.min(t1[a[0].length - 1] + x[0], t2[a[0].length - 1] + x[1]));
    }

    public void wordBreak() {
        String[] array = {"trainer", "mi", "interview"};
        String sentence = "myinterviewtrainer";
        boolean[] dp = new boolean[sentence.length() + 1];
        dp[0] = true;
        for (int i = 0; i < sentence.length(); i++) {
            if (!dp[i])
                continue;
            for (String word : array) {
                if (i + word.length() > sentence.length()) {
                    continue;
                }

                String subStrring = sentence.substring(i, i + word.length());
                if (subStrring.equals(word)) {
                    dp[i + word.length()] = true;
                }

                if (dp[sentence.length()]) {
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");

    }

    public void printLongestPalindrome() {
        String palindrom = "dabcbad";
        boolean[][] array = new boolean[palindrom.length()][palindrom.length()];

        int maxLength = 1;
        int start = 0;
        for (int i = 0; i < array.length - 1; i++) {
            array[i][i] = true;
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (palindrom.charAt(i) == palindrom.charAt(i + 1)) {
                array[i][i + 1] = true;
                maxLength = 2;
                start = i;
            }
        }

        for (int k = 3; k <= array.length; k++) {
            for (int i = 0; i <= array.length - k; i++) {
                int j = i + k - 1;
                if (palindrom.charAt(i) == palindrom.charAt(j) && array[i + 1][j - 1]) {
                    array[i][j] = true;
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        System.out.println(palindrom.substring(start, start + maxLength));

    }


    public void minimumJumps() {
        int[] a = {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1};
        int[] jumps = new int[a.length];

        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = a[0] == 0
                ? Integer.MAX_VALUE
                : 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j <= a[i] && i + j < a.length; j++) {
                jumps[i + j] = Math.min(jumps[i + j], jumps[i] + 1);
            }
        }

        System.out.println(jumps[a.length - 1]);
    }


    public void arrangeHeightAndWeight() {
        ArrayList<HtWt> htWts = new ArrayListUtils<HtWt>().getArrayList(new HtWt[]{new HtWt(60, 100), new HtWt(70, 150), new HtWt(56, 90), new HtWt(75, 190), new HtWt(60, 95), new HtWt(68, 110)});
        int[] dp = new int[htWts.size() + 1];
        dp[0] = 1;
        Collections.sort(htWts);
        for (int i = 1; i < htWts.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (htWts.get(j).isBefore(htWts.get(i))) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(dp[htWts.size() - 1]);
    }


    class HtWt implements Comparable {
        int height;
        int weight;

        public HtWt(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        public boolean isBefore(HtWt second) {
            return (this.height < second.height && this.weight < second.weight);
        }


        @Override
        public int compareTo(Object o) {
            HtWt htWt1 = (HtWt) o;

            int heightCompare = Integer.compare(this.height, htWt1.height);
            if (heightCompare != 0) {
                return heightCompare;
            }

            return Integer.compare(this.weight, htWt1.weight);
        }


    }


    public void decoding() {
        String original = "10";
        int[] dp = new int[original.length()];

        boolean isThisIsAInvalidZeoro = false;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) != '0') {
                dp[i] = i > 1 ? dp[i - 1]
                        : 1;
            }

            if (i < original.length() - 1 && Integer.parseInt(original.substring(i, i + 2)) < 26 && original.charAt(i) != '0' && !isThisIsAInvalidZeoro) {
                dp[i] = dp[i] + 1;
            } else {
                isThisIsAInvalidZeoro = true;
            }
        }
        System.out.println(dp[original.length() - 2]);
    }


    public void findDuplicate(int[] array) {
        BitSet bitSet = new BitSet(32000);
        for (int i = 0; i < array.length; i++) {
            if (bitSet.get(array[i])) {
                System.out.println(array[i]);
            } else {
                bitSet.set(array[i]);
            }
        }
    }


    class BitSet {
        int[] bit;

        public BitSet(int size) {
            this.bit = new int[size >> 5];
        }

        public void set(int value) {
            int word = value >> 5;
            int bitInWord = value & 0x1F;
            bit[word] |= (1 << bitInWord);
        }

        public boolean get(int value) {
            int word = value >> 5;
            int bitInWord = value * 0x1F;
            return ((bit[word] & (1 << bitInWord)) == 1) ?
                    true
                    : false;

        }
    }

    public int TwoXMinGreaterThanMax(int[] array) {
        int max, min;
        int longestStart = -1;
        int longestEnd = -1;
        for (int i = 0; i < array.length; i++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int j = i; j < array.length; j++) {
                int val = array[j];
                max = Math.max(max, val);
                min = Math.min(min, val);
                if (2 * min <= max) {
                    break;
                }

                if (j - i > longestEnd - longestStart || longestStart == -1) {
                    longestStart = i;
                    longestEnd = j;
                }
            }
        }

        if (longestStart == 0 && longestEnd == array.length) {
            return 0;
        }
        return (longestStart - 0 + array.length - longestEnd - 1);
    }

    public int noOfWays(ArrayList<Integer> runs, int totalRuns) {


        /*Collections.sort(runs);
        int[] dp = new int[totalRuns + 1];
        dp[0] = 1;
        for (int i = 0; i < runs.size(); i++) {
            for (int j = runs.get(i); j <= totalRuns; j++) {
                dp [j] += dp[j - runs.get(i)];
            }
        }*/
        if (totalRuns == 0) {
            return 1;
        }


        int count = 0;
        for (int i = 0; i < runs.size(); i++) {
            if (totalRuns >= runs.get(i)) {
                count += noOfWays(runs, totalRuns - runs.get(i));
            }
        }

        return count;
    }


    public int indexEqualToNumber(ArrayList<Integer> array, int l, int h) {
        if (l > h) {
            return -1;
        }

        int mid = l + (h - l) / 2;
        if (array.get(mid) == mid) {
            return mid;
        }
        int midValue = array.get(mid);
        int leftIndex = Math.min(mid - 1, midValue);
        l = indexEqualToNumber(array, l, leftIndex);
        if (l >= 0) {
            return l;
        }

        int rightIndex = Math.max(mid + 1, midValue);
        int right = indexEqualToNumber(array, rightIndex, h);

        return right;
    }


    public boolean getPath(int x, int y, ArrayList<Point> arrayListx, ArrayList<Point> arrayListy) {
        if (x == 0 && y == 0) {
            return true;
        }

        boolean successX = false;
        if (x > 0) {
            successX = getPath(x - 1, y, arrayListx, arrayListy);
        }


        if (successX) {
            arrayListx.add(new Point(x, y));
        }

        boolean successY = false;

        if (y > 0) {
            successY = getPath(x, y - 1, arrayListx, arrayListy);
        }

        if (successY) {
            arrayListy.add(new Point(x, y));
        }

        return successY || successX;

    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }


    public ArrayList<Integer> isSumExist(ArrayList<Integer> a) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
        }

        if (sum % 2 == 1) {
            return new ArrayList<>();
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        return isSumExistUtil(a, sum, 0, 0, sum, arrayList) == true
                ? arrayList
                : new ArrayList<Integer>();
    }

    private boolean isSumExistUtil(ArrayList<Integer> a, int sum, int index, int count, int totalSum, ArrayList<Integer> result) {
        if (index >= a.size()) {
            return false;
        }

        int remaining = a.size() - index + 1;

        if ((sum != totalSum) && sum / remaining == totalSum / a.size()) {
            return true;
        }
        if (a.get(index) > sum) {
            return isSumExistUtil(a, sum, index + 1, count, totalSum, result);
        }

        boolean resultWithoutAdding = isSumExistUtil(a, sum, index + 1, count, totalSum, result);
        result.add(a.get(index));
        boolean resultAfterAdding = isSumExistUtil(a, sum - a.get(index), index + 1, count + 1, totalSum, result);
        if (!resultAfterAdding) {
            result.remove(result.size() - 1);
        }
        return resultAfterAdding || resultWithoutAdding;
    }

    public int maxPathSum(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        Count count = new Count(Integer.MIN_VALUE);
        maxPathSum(treeNode, count);
        return count.count;

    }

    private int maxPathSum(TreeNode treeNode, Count count) {
        if (treeNode == null) {
            return 0;
        }

        int left = maxPathSum(treeNode.left, count);
        int right = maxPathSum(treeNode.right, count);
        count.count = Math.max(left + right + treeNode.val, count.count);
        return treeNode.val + Math.max(left, right);
    }


    public int minCut(String a) {
        int[][] dpArray = new int[a.length()][a.length()];
        boolean[][] isPal = new boolean[a.length()][a.length()];
        for (int i = 0; i < a.length(); i++) {
            isPal[i][i] = true;
        }

        for (int l = 2; l <= a.length(); l++) {
            for (int i = 0; i < a.length() - l + 1; i++) {
                int j = i + l - 1;
                if (l == 2) {
                    isPal[i][j] = a.charAt(i) == a.charAt(j);
                } else {
                    isPal[i][j] = a.charAt(i) == a.charAt(j) && isPal[i + 1][j - 1];
                }

                if (isPal[i][j]) {
                    dpArray[i][j] = 0;
                } else {
                    dpArray[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dpArray[i][j] = Math.min(dpArray[i][j], dpArray[i][k] + dpArray[k + 1][j] + 1);
                    }
                }

            }
        }

        return dpArray[0][a.length() - 1];
    }

    public int wordBreak11(String a, ArrayList<String> b) {
        int[] array = new int[a.length() + 1];
        Arrays.fill(array, -1);

        return wordBreak(a, b, new StringBuilder(), a.length(), array, 0) == true
                ? 1
                : 0;
    }

    public boolean wordBreak(String a, ArrayList<String> b, StringBuilder stringBuilder, int length, int[] array, int index) {
        if (a.isEmpty()) {
            return true;
        }
        if (array[index] != -1) {
            return array[index] == 1
                    ? true
                    : false;
        }


        int indexbackup = index;
        StringBuilder backUp = new StringBuilder(stringBuilder);
        for (int i = 0; i < a.length(); i++, indexbackup++) {
            String subString = a.substring(0, i + 1);
            if (b.contains(subString)) {
                stringBuilder.append(subString);
                if (i + 1 != a.length())
                    stringBuilder.append(" ");
                boolean isTrue = wordBreak(a.substring(i + 1, a.length()), b, new StringBuilder(stringBuilder), length, array, indexbackup + 1);
                if (isTrue) {
                    array[index] = 1;
                    return true;
                }
                if (a.length() == length)
                    stringBuilder = new StringBuilder();
                stringBuilder = new StringBuilder(backUp);
            }

        }


        array[index] = 0;
        return false;

    }


    public int wordBreak(String a, ArrayList<String> b) {
        boolean[] isTrue = new boolean[a.length() + 1];
        isTrue[0] = true;
        for (int i = 0; i < a.length(); i++) {
            if (!isTrue[i])
                continue;
            for (String string : b) {
                if (i + string.length() > a.length()) {
                    continue;
                }
                String subString = a.substring(i, i + string.length());
                if (subString.equals(string)) {
                    isTrue[i + string.length()] = true;
                }
            }
        }

        if (isTrue[a.length()]) {
            return 1;
        }
        return 0;
    }


    public int maximalRectangle(ArrayList<ArrayList<Integer>> a) {
        int[][] maxRect = new int[a.size()][a.get(0).size()];
        int maxArea = 0;
        for (int i = 0; i < a.size(); i++) {
            maxRect[0][i] = a.get(0).get(i);
        }

        for (int i = 1; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                if (a.get(i).get(j) == 1) {
                    maxRect[i][j] = maxRect[i - 1][j] + 1;
                }
            }
        }


        for (int i = 0; i < a.size(); i++) {
            maxArea = Math.max(maxRectangle(maxRect[i]), maxArea);
        }

        return maxArea;
    }

    private int maxRectangle(int[] histogram) {
        Stack<Integer> ss = new Stack<Integer>();
        int maxArea = 0, i = 0;
        while (i < histogram.length) {
            if (ss.isEmpty() || histogram[i] >= histogram[ss.peek()]) {
                ss.push(i++);
            } else {
                maxArea = Math.max(maxArea,
                        histogram[ss.pop()] * (ss.isEmpty() ? i : (i - ss.peek() - 1)));
            }
        }
        while (!ss.isEmpty()) {
            maxArea = Math.max(maxArea,
                    histogram[ss.pop()] * (ss.isEmpty() ? i : (i - ss.peek() - 1)));
        }
        return maxArea;
    }

    private int maxAreaInHistogram(int[] bar) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        ArrayList<Integer> noZeroArray = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < bar.length; i++) {
            if (bar[i] != 0)
                noZeroArray.add(bar[i]);
        }

        for (int i = 0; i < noZeroArray.size(); ) {

            if (stack.isEmpty() || noZeroArray.get(stack.peek()) < noZeroArray.get(i)) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                int area = bar[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = noZeroArray.get(top) * (stack.isEmpty() ? noZeroArray.size() - 1 : noZeroArray.size() - stack.peek() - 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public int calculateMinimumHP(ArrayList<ArrayList<Integer>> a) {
        int[][] minPower = new int[a.size()][a.get(0).size()];
        int m = a.size();
        int n = a.get(0).size();
        minPower[m - 1][n - 1] = a.get(m - 1).get(n - 1) < 0 ? Math.abs(a.get(m - 1).get(n - 1)) + 1 : 1;
        for (int i = m - 2; i >= 0; i--) {
            minPower[i][n - 1] = Math.max(minPower[i + 1][n - 1] - a.get(i).get(n - 1), 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            minPower[m - 1][i] = Math.max(minPower[m - 1][i + 1] - a.get(m - 1).get(i), 1);
        }

        for (int i = a.size() - 2; i >= 0; i--) {
            for (int j = a.get(0).size() - 2; j >= 0; j--) {
                if (a.get(i).get(j) == 1)
                    minPower[i][j] = Math.max(Math.min(minPower[i + 1][j], minPower[i][j + 1]) - a.get(i).get(j), 1);
                else
                    minPower[i][j] = 0;
            }
        }


        return minPower[0][0];
    }

    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> a) {
        int[][] array = new int[a.size()][a.get(0).size()];

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).get(0) == 1)
                break;
            array[i][0] = 1;
        }

        for (int i = 0; i < a.get(0).size(); i++) {
            if (a.get(0).get(i) == 1)
                break;
            array[0][i] = 1;
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[0].length; j++) {
                if (a.get(i).get(j) != 1) {
                    array[i][j] = array[i - 1][j] + array[i][j - 1];
                }
            }
        }
        return array[a.size() - 1][a.get(0).size() - 1] > 0
                ? 0
                : Math.abs(array[a.size() - 1][a.get(0).size() - 1]) + 1;
    }

    private int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> a, int i, int j, int[][] array) {
        if (i == a.size() - 1 && j == a.get(0).size() - 1 && a.get(i).get(j) == 0) {
            return 1;
        }

        if (i >= a.size() || j >= a.get(0).size()) {
            return 0;
        }

        if (array[i][j] != -1) {
            return array[i][j];
        }

        if (a.get(i).get(j) == 0) {
            int leftSum = uniquePathsWithObstacles(a, i + 1, j, array);
            int rightSum = uniquePathsWithObstacles(a, i, j + 1, array);

            return array[i][j] = leftSum + rightSum;
        }

        return array[i][j] = 0;
    }

    public int minPathSum(ArrayList<ArrayList<Integer>> a) {
        int[][] array = new int[a.size()][a.get(0).size()];
        int sum = 0;
        for (int i = 0; i < a.get(0).size(); i++) {
            sum += a.get(0).get(i);
            array[0][i] = sum;
        }

        sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i).get(0);
            array[i][0] = sum;
        }

        for (int i = 1; i < a.size(); i++) {
            for (int j = 1; j < a.get(0).size(); j++) {
                array[i][j] = a.get(i).get(j) + Math.min(array[i - 1][j], array[i][j - 1]);
            }
        }

        return array[a.size() - 1][a.size() - 1];
    }


    public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int[] minSum = new int[a.size()];
        for (int i = a.size() - 2; i >= 0; i--) {
            for (int j = 0; j < a.get(i + 1).size() - 1; j++) {
                minSum[j] = a.get(i).get(j) + Math.min(minSum[j], minSum[j + 1]);
            }
        }

        int total = 0;
        for (int i = 0; i < a.size() - 1; i++) {
            total += minSum[i];
        }
        return total;
    }

    public static int longestValidParentheses(String s) {
        Stack<int[]> stack = new Stack<>();

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int[] array = {i, 0};
                stack.push(array);
            } else {
                if (stack.isEmpty() || stack.peek()[1] == 1) {
                    int[] array = {i, 0};
                    stack.push(array);
                } else {
                    stack.pop();
                    int currentLength = 0;
                    if (stack.isEmpty()) {
                        currentLength = i + 1;
                    } else {
                        currentLength = i - stack.peek()[0];
                    }

                    result = Math.max(currentLength, result);
                }
            }
        }
        return result;
    }

    public int maxProfit111(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int[] profitHist = new int[len];
        // forward calculate profit
        for (int buyDay = 0, today = 1; today < len; ++today) {
            int curP = prices[today] - prices[buyDay];
            if (curP <= 0) buyDay = today;
            else profitHist[today] = Math.max(curP, profitHist[today - 1]);
        }
        // backward calculate profit
        int profit = profitHist[len - 1], backProfit = 0;
        for (int today = len - 2, sellDay = len - 1; today > 0; --today) {
            int curP = prices[sellDay] - prices[today];
            if (curP <= 0) sellDay = today;
            else backProfit = Math.max(curP, backProfit);
            profit = Math.max(profit, profitHist[today] + backProfit);
        }
        return profit;
    }

    public int maxProfit(final List<Integer> a) {
        int maxProfit = 0;
        int minIndex = 0;
        int[] dp = new int[a.size()];
        for (int i = 1; i < a.size(); i++) {
            int currentPrice = a.get(i) - a.get(minIndex);
            if (a.get(minIndex) > a.get(i)) {
                minIndex = i;
            } else if (currentPrice < 0) {
                minIndex = i;
            } else if (currentPrice > maxProfit) {
                dp[i] = currentPrice;
                maxProfit = currentPrice;
            }
        }

        maxProfit = dp[a.size() - 1];

        int backMax = 0;
        for (int last = a.size() - 1, previous = a.size() - 2; previous >= 0; previous--) {
            int currentPrice = a.get(last) - a.get(previous);

            if (currentPrice < 0) {
                last = previous;
            } else {
                backMax = Math.max(backMax, currentPrice);
            }

            maxProfit = Math.max(maxProfit, backMax + dp[previous]);
        }

        return maxProfit;
    }

    public int cnttrue(String a) {
        int[][] trueDp = new int[a.length()][a.length()];
        int[][] falseDp = new int[a.length()][a.length()];
        for (int i = 0; i < trueDp.length; i++) {
            Arrays.fill(trueDp[i], -1);
            Arrays.fill(falseDp[i], -1);
        }

        return numberOfTrues(a, 0, a.length() - 1, trueDp, falseDp);
    }
    public void countTrueDp(){
        String a = "ttft";
        int[][] trueDp = new int[a.length()][a.length()];
        int[][] falseDp = new int[a.length()][a.length()];

    }


    private int numberOfTrues(String a, int i, int j, int[][] trueDp, int[][] falseDp) {
        if (i > j) {
            return 0;
        }

        if (i == j && a.charAt(i) == 'T') {
            return 1;
        }

        if (trueDp[i][j] != -1) {
            return trueDp[i][j];
        }

        int ans = 0;
        for (int k = i; k <= j; k++) {
            if (a.charAt(k) == '&') {
                ans += numberOfTrues(a, i, k - 1, trueDp, falseDp) * numberOfTrues(a, k + 1, j, trueDp, falseDp);
            }

            if (a.charAt(k) == '|') {
                ans += (numberOfTrues(a, i, k - 1, trueDp, falseDp) * numberOfTrues(a, k + 1, j, trueDp, falseDp) +
                        (numberOfTrues(a, i, k - 1, trueDp, falseDp) * numberOfFalse(a, k + 1, j, trueDp, falseDp)) +
                        (numberOfFalse(a, i, k - 1, trueDp, falseDp) * numberOfTrues(a, k + 1, j, trueDp, falseDp)));
            }

            if (a.charAt(k) == '^') {
                ans += (numberOfTrues(a, i, k - 1, trueDp, falseDp) * numberOfFalse(a, k + 1, j, trueDp, falseDp)
                        + (numberOfFalse(a, i, k - 1, trueDp, falseDp) * numberOfTrues(a, k + 1, j, trueDp, falseDp)));

            }

        }

        trueDp[i][j] = ans;
        return ans;

    }

    private int numberOfFalse(String a, int i, int j, int[][] trueDp, int[][] falseDp) {
        if (j > j) {
            return 0;
        }

        if (i == j && a.charAt(i) == 'F') {
            return 1;
        }

        if (falseDp[i][j] != -1) {
            return falseDp[i][j];
        }

        int ans = 0;
        for (int k = i; k <= j; k++) {
            if (a.charAt(k) == '|') {
                ans += numberOfFalse(a, i, k - 1, trueDp, falseDp) * numberOfFalse(a, k + 1, j, trueDp, falseDp);
            }

            if (a.charAt(k) == '&') {
                ans += (numberOfFalse(a, i, k - 1, trueDp, falseDp) * numberOfFalse(a, k + 1, j, trueDp, falseDp) +
                        (numberOfTrues(a, i, k - 1, trueDp, falseDp) * numberOfFalse(a, k + 1, j, trueDp, falseDp)) +
                        (numberOfFalse(a, i, k - 1, trueDp, falseDp) * numberOfTrues(a, k + 1, j, trueDp, falseDp)));
            }

            if (a.charAt(k) == '^') {
                ans += (numberOfTrues(a, i, k - 1, trueDp, falseDp) * numberOfTrues(a, k + 1, j, trueDp, falseDp)
                        + (numberOfFalse(a, i, k - 1, trueDp, falseDp) * numberOfFalse(a, k + 1, j, trueDp, falseDp)));

            }
        }
        falseDp[i][j] = ans;
        return ans;
    }

    public int isMatch(final String s, final String p) {
        int[][] array = new int[s.length()][p.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(array[i], -1);
        }
        return isMatch(s, p, 0, 0);

    }


    public int maxcoin(ArrayList<Integer> a) {
        int[][] array = new int[a.size()][a.size()];
        for (int i = 0; i < a.size(); i++) {
            for (int m = 0, n = i; n < a.size(); m++, n++) {
                int x = m < a.size() - 2 ? array[m + 2][n] : 0;
                int y = n > 0 && m < a.size() - 1 ? array[m + 1][n - 1] : 0;
                int z = n > 1 ? array[m][n - 2] : 0;

                array[m][n] = Math.max(a.get(m) + Math.min(x, y)
                        , a.get(n) + Math.min(y, z));


            }
        }

        return array[0][a.size() - 1];
    }


    public int isMatch(String s, String p, int index1, int index2) {
        if (p.length() <= index2) {
            if (s.length() <= index1) {
                return 1;
            }
            return 0;
        }
        if (index2 >= p.length() - 1) {
            if (s.length() <= index2) {
                return 0;
            }
            if (p.charAt(index2) <= s.charAt(index1)) {
                return 1;
            }
            return 0;
        }

        if (p.charAt(index2 + 1) != '*') {
            if (p.charAt(index2) == s.charAt(index1) || p.charAt(index2) == '.') {
                return isMatch(s, p, index1 + 1, index2 + 1);
            }
        }

        while (p.charAt(index2) == s.charAt(index1) || (p.charAt(index2) == '.' && index1 < s.length())) {
            if (isMatch(s, p, index1, index2 + 2) == 1) {
                return 1;
            }

            index1++;
        }

        return isMatch(s, p, index1, index2 + 2);
    }

    public int isMatch(String s, String p, int index1, int index2, int[][] array) {
        if ((index1 >= s.length() && index2 >= p.length())) {
            return 1;
        }

        if (index2 >= p.length()) {
            return 0;
        }


        if (index1 >= s.length() && index2 == p.length() - 2 && (p.charAt(index2) == '.' || Character.isLetter(p.charAt(index2))) && p.charAt(index2 + 1) == '*') {
            return 1;
        }

        if (index1 >= s.length()) {
            return 0;
        }

        if (array[index1][index2] != -1) {
            return array[index1][index2];
        }

        if (index2 != p.length() - 1 && p.charAt(index2 + 1) == '*') {

            if (s.charAt(index1) == p.charAt(index2) || p.charAt(index2) == '.') {
                int result = Math.max(isMatch(s, p, index1 + 1, index2, array), isMatch(s, p, index1, index2 + 2, array));
                array[index1][index2] = result;
                return result;

            }
            int result = isMatch(s, p, index1, index2 + 2, array);
            array[index1][index2] = result;
            return result;
        }

        if (s.charAt(index1) == p.charAt(index2) || p.charAt(index2) == '.') {

            int result = isMatch(s, p, index1 + 1, index2 + 1, array);
            array[index1][index2] = result;
            return result;

        }


        return 0;
    }

    public int minDistance(String a, String b) {
        int[][] array = new int[a.length() + 1][b.length() + 1];


        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    array[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    array[i][j] = i;
                    continue;
                }

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    array[i][j] = array[i - 1][j - 1];
                } else {
                    array[i][j] = Math.min(array[i - 1][j - 1],
                            Math.min(array[i][j - 1],
                                    array[i - 1][j])) + 1;
                }
            }
        }
        return array[a.length()][b.length()];
    }

    public int minDistance(String a, String b, int index1, int index2, int[][] array) {

        if (index1 == 0) {
            return index2;
        }

        if (index2 == 0) {
            return index1;
        }

        /*if (array[index1][index2] != -1){
            return array[index1][index2];
        }*/

        int min;
        if (a.charAt(index1) == b.charAt(index2)) {

            min = minDistance(a, b, index1 - 1, index2 - 1, array);
            array[index1][index2] = min;
            return min;
        }

        min = Math.min(minDistance(a, b, index1 - 1, index2, array) + 1,
                Math.min(minDistance(a, b, index1, index2 - 1, array) + 1,
                        minDistance(a, b, index1 - 1, index2 - 1, array) + 1));
        array[index1][index2] = min;
        return min;
    }

    public int anytwo(String a) {
        int[][] array = new int[a.length()][a.length()];
        for (int i = 0; i < a.length(); i++) {
            Arrays.fill(array[i], -1);
        }
        String a1 = "govind";
        String a2 = "";
        int result = anytwo(a1, a2, 0, 0, array);
        return result == a2.length()
                ? 1
                : result > a2.length() ?
                result - a2.length() + 1
                : 0;


    }

    int numDistinct(String a1, String a2) {
        int[][] array = new int[a2.length() + 1][a1.length() + 1];
        for (int i = 0; i <= a1.length(); i++) {
            array[0][i] = 1;
        }

        for (int i = 1; i <= a2.length(); i++) {
            for (int j = 1; j <= a1.length(); j++) {
                array[i][j] = array[i][j - 1] + ((a2.charAt(i - 1) == a1.charAt(j - 1)) ? array[i - 1][j - 1] : 0);

            }
        }

        return array[a2.length()][a1.length()];
    }

    public int numDistinct1(String a1, String a2) {
        int[][] array = new int[a2.length() + 1][a1.length() + 1];
        for (int i = 0; i <= a1.length(); i++) {
            array[0][i] = 1;
        }

        for (int i = 1; i <= a2.length(); i++) {
            for (int j = 1; j <= a1.length(); j++) {
                array[i][j] = array[i][j - 1] + ((a2.charAt(i) == a1.charAt(j)) ? array[i - 1][j - 1] : 0);

            }
        }

        return array[a2.length()][a1.length()];
    }


    public int anytwo(String a, String b, int index1, int index2, int[][] array) {

        if (index1 >= a.length() || index2 >= b.length()) {
            return 0;
        }
        if (array[index1][index2] != -1) {
            return array[index1][index2];
        }


        int max = Math.max(anytwo(a, b, index1 + 1, index2, array) + 1,
                anytwo(a, b, index1, index2 + 1, array) + 1);


        array[index1][index2] = max;
        return max;
    }


    public int lis(ArrayList<Integer> arrayList) {

        int[] count = new int[arrayList.size()];
        Arrays.fill(count, 1);
        for (int i = 1; i < arrayList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (arrayList.get(i) > arrayList.get(j) && count[i] < count[j] + 1) {
                    count[i] = count[j] + 1;
                }
            }

        }

        int max = 0;
        for (int i = 0; i < count.length; i++) {
            max = Math.max(count[i], max);
        }
        return max;
    }

    public int adjacent(ArrayList<ArrayList<Integer>> a) {
        int[] array = new int[a.get(0).size()];
        Arrays.fill(array, -1);
        int value = adjacent(a, array, a.get(0).size() - 1);
        return value;
    }

    public int maxProfit12(final List<Integer> a) {
        int maxProfit = 0;
        int minIndex = 0;
        int total = 0;
        for (int i = 1; i < a.size(); i++) {
            if (a.get(minIndex) > a.get(i)) {
                minIndex = i;
            }

            if (maxProfit < a.get(i) - a.get(minIndex)) {
                maxProfit = a.get(i) - a.get(minIndex);
                total += maxProfit;
                maxProfit = 0;
                minIndex = i;
            }
        }

        return total;
    }

    private int adjacent(ArrayList<ArrayList<Integer>> a, int[] array, int c) {
        array[0] = Math.max(a.get(0).get(0), a.get(1).get(0));
        for (int i = 1; i < a.get(0).size(); i++) {
            array[i] = Math.max(Math.max(a.get(0).get(i), a.get(1).get(i)) + (i > 1 ? array[i - 2] : 0), array[i - 1]);
        }

        return array[a.get(0).size() - 1];

    }

    public int jump(ArrayList<Integer> a) {
        if (a.size() <= 1) {
            return 0;
        }
        int currentPosition = a.get(0);
        int maxPosition = a.get(0);
        int step = 1;
        for (int i = 1; i <= maxPosition; i++) {
            if (i >= a.size() - 1) {
                return step;
            }

            currentPosition = Math.max(currentPosition, i + a.get(i));
            if (i == maxPosition) {
                if (currentPosition == maxPosition) {
                    return -1;
                }

                maxPosition = currentPosition;
                step++;
            }
        }

        return -1;
    }

    public void jump() {
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{2, 3, 1, 1, 0, 4});
        System.out.println(jump1(arrayList));
    }

    public int jump1(ArrayList<Integer> a) {
        int maxReachable = 0;
        int numberOfSteps = 0;
        for (int i = 0; i < a.size() - 1; i++) {
            int currentMaxReachable = i + a.get(i);
            if (currentMaxReachable > maxReachable) {
                maxReachable = currentMaxReachable;
                numberOfSteps++;
            }

            if (maxReachable == i) {
                return -1;
            }

            if (maxReachable >= a.size() - 1) {
                return numberOfSteps;
            }


        }

        return numberOfSteps;
    }

    public int canJump(ArrayList<Integer> a) {

        int max = 0;
        int nextMaxIndex = 0;
        int steps = 0;
        for (int i = 0; i < a.size(); i++) {

            max = Math.max(max, i + a.get(i));
            if (max <= i) {
                return 0;
            }
        }
        return 1;
    }


    public int climbStairs(int a, int index, int[] array) {
        if (a == 0) {
            return 1;
        }

        if (array[index] != 0) {
            return array[index];
        }

        int count = 0;
        if (a >= 1) {
            count = climbStairs(a - 1, index + 1, array);
        }
        if (a >= 2)
            count += climbStairs(a - 2, index + 2, array);
        array[index] = count;
        return count;
    }

    public int climbStairs(int a) {
        return climbStairs(a, 0, new int[a + 1]);
    }

    public int numDecoding(String a, int index, int[] array) {

        if (a.length() == index) {
            return 1;
        }
        int count = 0;
        if (array[index] != 0) {
            return array[index];
        }

        if (a.charAt(index) != '0') {
            count = numDecoding(a, index + 1, array);
        }
        if (index + 1 < a.length() && Integer.parseInt(a.substring(index, index + 2)) <= 26 && a.charAt(index) != '0')
            count += numDecoding(a, index + 2, array);
        array[index] = count;
        return count;

    }

    public int numDecodings(String a) {
        return numDecoding(a, 0, new int[a.length()]);
    }

    public int arrange(String a, int b) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            arrayLists.add(new ArrayList<>());
            for (int j = 0; j < b; j++) {
                arrayLists.get(i).add(-1);
            }
        }
        int n = 0;
        return arrange(a, b, 0, 0, arrayLists) == Integer.MAX_VALUE
                ? -1
                : n;

    }

    public int arrange(String a, int b, int start, int stables, ArrayList<ArrayList<Integer>> arrayLists) {

        if (start == a.length()) {
            if (stables == b) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        if (b == stables) {
            return Integer.MAX_VALUE;
        }
        if (arrayLists.get(start).get(stables) != -1) {
            return arrayLists.get(start).get(stables);
        }
        int black = 0;
        int white = 0;
        int min = Integer.MAX_VALUE;
        int i;
        for (i = start; i < a.length(); i++) {
            if (a.charAt(i) == 'W') {
                white++;
            }
            if (a.charAt(i) == 'B') {
                black++;
            }
            int temp = arrange(a, b, i + 1, stables + 1, arrayLists);
            if (temp != Integer.MAX_VALUE) {
                min = Math.min(temp + (white * black), min);
            }

        }
        arrayLists.get(start).set(stables, min);
        return min;
    }

    public int maxProfit1(final List<Integer> a) {
        int minIndex = 0;
        int maxDiff = -1;
        int currentDiff = 0;
        for (int i = 1; i < a.size(); i++) {
            if (a.get(minIndex) < a.get(i)) {
                currentDiff = a.get(i) - a.get(minIndex);
            } else if (a.get(minIndex) > a.get(i)) {
                minIndex = i;
            }
            maxDiff = Math.max(maxDiff, currentDiff);
        }

        return maxDiff;
    }

    public int maxProduct1(List<Integer> a) {
        int iMax = a.get(0);
        int iMin = a.get(0);
        int result = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < 0) {
                int temp = iMax;
                iMax = iMin;
                iMin = temp;
            }
            if (a.get(i) == 0) {
                iMax = iMin = 0;
            }

            iMax = Math.max(iMax, a.get(i) * iMax);
            iMin = Math.min(iMin, a.get(i) * iMin);
            result = Math.max(iMax, result);
        }
        return result;
    }

    public int maxProduct(final List<Integer> a) {
        int maxEndingHere = 1;
        int minEndingHere = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > 0) {
                maxEndingHere = maxEndingHere * a.get(i);
                minEndingHere = Math.min(minEndingHere * a.get(i), 1);
            } else if (a.get(i) == 0) {
                maxEndingHere = minEndingHere = 1;
            } else {
                int temp = maxEndingHere;
                maxEndingHere = Math.max(minEndingHere * a.get(i), maxEndingHere);
                minEndingHere = temp * a.get(i);
            }
            if (max < maxEndingHere) {
                max = maxEndingHere;
            }
        }
        if (max == 1 && (!a.contains(max))) {
            max = Integer.MIN_VALUE;
            for (int i = 0; i < a.size(); i++) {
                max = Math.max(max, a.get(i));
            }
        }
        return max;
    }

    private int coinchange2(ArrayList<Integer> a, int b) {
        //Collections.sort(a);
        int[] count = new int[b + 1];
        count[0] = 1;
        for (int i = 0; i < a.size(); i++) {
            int current = a.get(i);
            for (int j = current; j <= b; j += 1) {
                count[j] += count[j - current];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < count.length; i++) {
            max = Math.max(count[i], max);
        }
        return count[b];
    }

    private int maxProduct(int number) {
        int[] count = new int[number + 1];
        count[1] = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 2; i <= number; i++) {
            for (int j = 1; j < i; j++) {
                max = Math.max(max, ((j) * count[i - j]));
            }

            count[i] = Math.max(max, i);
        }
        return count[number];
    }
}

class Count {
    int count;

    public Count(int count) {
        count = this.count;
    }

}
