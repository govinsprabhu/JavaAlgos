package com.govind.algo.platform.leetcode;

import com.govind.util.array.ArraysUtils;
import com.govind.util.linkedlist.ListNode;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by govindp on 12/13/2015.
 */

public class LeetCode {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map =
                new ConcurrentHashMap<String, String>();
        CountDownLatch latch = new CountDownLatch(1);

    }




    public int maximalRectangle(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        int[][] dp = new int[m][n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (a.get(i).get(j) == 1) {
                    dp[i][j] = ++count;
                } else {
                    count = 0;
                }

                if (i == 6 && j == 8){
                    System.out.println();
                }

                int k = i;
                int width = dp[i][j];
                while (k - 1 >= 0 && a.get(k - 1).get(j) == 1) {
                    k--;
                    width = Math.min(width, dp[k][j]);
                    maxArea = Math.max(maxArea, (i - k + 1) * width);
                }


                System.out.print(dp[i][j] + (dp[i][j] >= 10 ? " " : "  "));
            }
            System.out.println();
        }

        return maxArea;
    }


    public int isInterleave(String a, String b, String c) {
        return inter(a.toCharArray(), b.toCharArray(), c.toCharArray()) ? 1 : 0;
    }


    private boolean inter(char[] A, char[] B, char[] C) {
        int M = A.length;
        int N = B.length;
        boolean[][] IL = new boolean[M + 1][N + 1];
        for (int i = 0; i <= M; ++i) {
            for (int j = 0; j <= N; ++j) {
                // two empty strings have an empty string
                // as interleaving
                if (i == 0 && j == 0)
                    IL[i][j] = true;

                    // A is empty
                else if (i == 0 && B[j - 1] == C[j - 1])
                    IL[i][j] = IL[i][j - 1];

                    // B is empty
                else if (j == 0 && A[i - 1] == C[i - 1])
                    IL[i][j] = IL[i - 1][j];

                    // Current character of C matches with current character of A,
                    // but doesn't match with current character of B
                else if (i != 0 && j != 0 && i + j <= C.length && A[i - 1] == C[i + j - 1] && B[j - 1] != C[i + j - 1])
                    IL[i][j] = IL[i - 1][j];

                    // Current character of C matches with current character of B,
                    // but doesn't match with current character of A
                else if (i != 0 && j != 0 && i + j <= C.length && A[i - 1] != C[i + j - 1] && B[j - 1] == C[i + j - 1])
                    IL[i][j] = IL[i][j - 1];

                    // Current character of C matches with that of both A and B
                else if (i != 0 && j != 0 && i + j <= C.length && A[i - 1] == C[i + j - 1] && B[j - 1] == C[i + j - 1])
                    IL[i][j] = (IL[i - 1][j] || IL[i][j - 1]);
            }
        }
        ArraysUtils.print(IL);
        return IL[M][N];
    }


    public ArrayList<String> wordBreak(String a, ArrayList<String> b) {
        Map<Integer, ArrayList<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>(b);
        ArrayList<String> s = new ArrayList<>();
        s.add("");
        map.put(0, s);
        ArrayList<String> ar = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            if (!map.containsKey(i)) {
                continue;
            }
            for (String word : set) {
                int end = i + word.length();
                if (end > a.length()) {
                    continue;
                }
                String sub = a.substring(i, end);
                if (sub.equals(word) && map.containsKey(i)) {
                    ArrayList<String> val = map.get(i);
                    ArrayList<String> res = new ArrayList<>();
                    if (map.containsKey(end)) {
                        res = map.get(end);
                    }

                    for (String ele : val) {
                        res.add(ele + (i == 0 ? "" : " ") + word);
                    }
                    map.put(end, res);
                }
            }
        }

        ArrayList<String> cur = map.get(a.length());
        if (cur != null) {
            cur.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
        }

        return cur == null ? new ArrayList<>() : cur;
    }

    public int wordBreak1(String a, ArrayList<String> b) {
        Set<String> set = new HashSet<>(b);
        Map<Integer, Boolean> map = new HashMap<>();
        return wBreak(a, set, 0, map) ? 1 : 0;
    }

    private boolean wBreak(String a, Set<String> set, int index, Map<Integer, Boolean> map) {
        if (index == a.length()) {
            return true;
        }
        if (map.containsKey(index)) {
            return map.get(index);
        }
        for (int i = index + 1; i <= a.length(); i++) {
            String sub = a.substring(index, i);
            if (set.contains(sub)) {
                if (wBreak(a, set, i, map)) {
                    return true;
                }
            }
        }
        map.put(index, false);
        return false;
    }


    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            map.put(A.get(i), map.containsKey(A.get(i)) ? map.get(A.get(i)) + 1 : 1);
        }
        res.add(map.size());
        for (int i = B; i < A.size(); i++) {
            if (map.containsKey(A.get(i - B))) {
                if (map.get(A.get(i - B)) == 1) {
                    map.remove(A.get(i - B));
                } else {
                    map.put(A.get(i - B), map.get(A.get(i - B)) - 1);
                }
            }
            map.put(A.get(i), map.containsKey(A.get(i)) ? map.get(A.get(i)) + 1 : 1);
            res.add(map.size());
        }

        return res;
    }

    class IndexWithNum {
        int index;
        int num;

        IndexWithNum(int index, int num) {
            this.index = index;
            this.num = num;
        }

        @Override
        public int hashCode() {
            return index + num;
        }

        @Override
        public boolean equals(Object obj) {
            IndexWithNum indexWithNum = (IndexWithNum) obj;
            return indexWithNum.num == this.num;
        }
    }


    public int nchoc(int A, ArrayList<Integer> B) {
        PriorityQueue<Integer> res = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o2 - (Integer) o1;
            }
        });
        long mod = (int) Math.pow(10, 9) + 7;
        for (int i = 0; i < B.size(); i++) {
            res.add(B.get(i));
        }
        long cur = 0;
        for (int i = 0; i < A; i++) {
            Integer top = res.poll();
            res.add(top / 2);
            cur = (cur + top) % mod;
        }

        return (int) cur;
    }


    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        perm(a, res, 0);
        return res;
    }

    private void perm(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> res, int k) {
        if (k == a.size()) {
            res.add((ArrayList<Integer>) a.clone());
            return;
        }

        for (int i = k; i < a.size(); i++) {
            if ((a.get(k) != a.get(i) && (i == 0 || a.get(i) != a.get(i - 1))) || i == k) {
                swap(a, i, k);
                perm(a, res, k + 1);
                swap(a, i, k);
            }

        }
    }

    private void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    public int magicalString(int n) {
        StringBuilder cur = new StringBuilder("122");

        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }
        int num = 1;
        boolean isOne = false;
        for (int i = 2; i < cur.length() && cur.length() < n; i++) {
            char ch = cur.charAt(i);
            if (ch == '1') {
                if (isOne) {
                    cur.append("2");
                } else {
                    cur.append("1");
                    num++;
                }
            } else {
                if (isOne) {
                    cur.append("22");
                } else {
                    cur.append("11");
                    num += (cur.length() > n ? 1 : 2);
                }
            }
            isOne = !isOne;
        }
        //System.out.println(cur);
        return num;
    }

    public String fractionToDecimal(int numerator, int denominator) {
        boolean isNumNeg = (numerator < 0 ? true : false);
        boolean isDenNeg = (denominator < 0 ? true : false);
        boolean isNeg = false;
        long numeratorL = Math.abs((long) numerator);
        long denominatorL = Math.abs((long) denominator);
        long num = numeratorL / denominatorL;
        Set<Long> set = new HashSet<>();
        long rem = numeratorL % denominatorL;
        if ((isNumNeg ^ isDenNeg) && (num != 0 || rem != 0)) {
            isNeg = true;
        }

        StringBuilder res = new StringBuilder((isNeg ? "-" : ""));
        res.append(num);
        if (rem == 0) {
            return res.toString();
        }
        res.append(".");
        while (rem != 0) {
            if (rem < denominatorL) {
                rem *= 10;
            }
            long cur = rem / denominatorL;
            //System.out.println("cur "+cur +" rem " +rem);

            set.add(rem);

            res.append(cur);

            rem = rem % denominatorL;
            if (set.contains(rem) && cur != 0) {
                break;
            }
        }

        return res.toString();
    }

    public String minWindow(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), map.containsKey(T.charAt(i)) ? map.get(T.charAt(i)) + 1 : 1);
        }

        int start = 0;
        int maxLength = Integer.MAX_VALUE;
        int maxStart = 0;
        int count = map.size();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (count >= 0) {
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) - 1);
                    if (map.get(ch) == 0) {
                        count--;
                    }
                }
            }
            if (count == 0) {
                while (start <= i) {
                    char cur = S.charAt(start);
                    if (map.containsKey(cur)) {
                        if (map.get(cur) == 0) {
                            if (maxLength > i - start + 1) {
                                maxLength = i - start + 1;
                                maxStart = start;
                            }
                            break;
                        }

                        map.put(cur, map.get(cur) + 1);
                        //count = (map.get(cur) == 0 ? count + 1 : count);
                    }
                    start++;
                }
            }

        }

        return maxLength == Integer.MAX_VALUE ? "" : S.substring(maxStart, maxLength + maxStart);
    }


    private class MaxLength {
        private String s;
        private Map<Character, Integer> map;
        private int start;
        private int maxStart;
        private int maxLen;
        private int i;

        public MaxLength(String S, Map<Character, Integer> map, int start, int maxStart, int maxLen, int i) {
            s = S;
            this.map = map;
            this.start = start;
            this.maxStart = maxStart;
            this.maxLen = Integer.MAX_VALUE;
            this.i = i;
        }

        public int getStart() {
            return start;
        }

        public int getMaxStart() {
            return maxStart;
        }

        public int getMaxLen() {
            return maxLen;
        }

        public MaxLength invoke() {
            while (start <= i) {
                char cur = s.charAt(start);
                if (map.containsKey(cur)) {
                    if (map.get(cur) == 0) {
                        if (maxLen > (i - start + 1)) {
                            maxLen = i - start + 1;
                            maxStart = start;
                        }
                        break;
                    }
                    map.put(cur, map.get(cur) + 1);
                }
                start++;
            }
            return this;
        }
    }


    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < a.size() - 1; i++) {
            int num = a.get(i);
            for (int j = i + 1; j < a.size(); j++) {

                int sum = num + a.get(j);
                if (!map.containsKey(sum)) {
                    Set<Integer> set = new HashSet<>();
                    set.add(i);
                    set.add(j);
                    map.put(sum, set);
                } else {
                    if (!map.get(sum).contains(i) && !map.get(sum).contains(j)) {
                        if (res.isEmpty()) {
                            for (int ele : map.get(sum)) {
                                res.add(ele);
                            }
                            Collections.sort(res);
                            res.add(i);
                            res.add(j);
                        } else {
                            ArrayList<Integer> cur = new ArrayList<>();
                            for (int ele : map.get(sum)) {
                                cur.add(ele);
                            }
                            Collections.sort(cur);
                            cur.add(i);
                            cur.add(j);
                            for (int k = 0; k < res.size(); k++) {
                                if (cur.get(k) == res.get(k)) {
                                    continue;
                                }
                                if (cur.get(k) < res.get(k)) {
                                    res = (ArrayList<Integer>) cur.clone();
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    public String largestNumber(final List<Integer> a) {
        a.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String num1 = String.valueOf(a);
                String num2 = String.valueOf(b);
                int i;
                for (i = 0; i < Math.min(num1.length(), num2.length()); i++) {
                    if (num1.charAt(i) > num2.charAt(i)) {
                        return -1;
                    } else if (num1.charAt(i) < num2.charAt(i)) {
                        return 1;
                    }
                }

                if (num1.length() > num2.length()) {
                    for (; i < num1.length(); i++) {
                        if (num1.charAt(i) > num2.charAt(0)) {
                            return -1;
                        } else if (num1.charAt(i) < num2.charAt(0)) {
                            return 1;
                        }
                    }


                }

                if (num2.length() > num1.length()) {
                    for (; i < num2.length(); i++) {
                        if (num2.charAt(i) > num1.charAt(0)) {
                            return 1;
                        } else if (num2.charAt(i) < num1.charAt(0)) {
                            return -1;
                        }
                    }
                    return 1;
                }


                return (num1.length() - num2.length());
            }
        });

        StringBuilder result = new StringBuilder();
        for (int ele : a) {
            result.append(ele);
        }
        return result.toString();
    }


    public int maximumGap(final List<Integer> a) {
        ArrayList<Index> indexes = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            Index index = new Index(a.get(i), i);
            indexes.add(index);
        }

        indexes.sort(new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                if (o1.num >= o2.num) {
                    return 1;
                }

                return -1;
            }
        });
        System.out.println(indexes);
        int maxDiff = -1;
        int smallestTill = Integer.MAX_VALUE;
        for (Index ind : indexes) {
            maxDiff = Math.max(maxDiff, ind.index - smallestTill);
            smallestTill = Math.min(smallestTill, ind.index);
        }


        return maxDiff;
    }


    class Index {
        int num;
        int index;

        public Index(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public String toString() {
            return num + " " + index;
        }
    }

    public int repeatedNumber(final List<Integer> a) {
        int n = a.size();
        float div = (float) Math.sqrt(n);
        int[] arr = new int[(int) Math.ceil(div)];
        for (int i = 0; i < a.size(); i++) {
            arr[(int) Math.ceil(a.get(i) / div) - 1]++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (((float) arr[i] > div) || (arr.length == i + 1 && arr[i] > (n - (div * (i + 1))))) {
                Set<Integer> set = new HashSet<>();
                for (int j = 0; j < a.size(); j++) {
                    if (a.get(j) < div * (i + 1) && a.get(j) > div * i) {
                        if (set.contains(a.get(j))) {
                            return a.get(j);
                        }
                        set.add(a.get(j));
                    }
                }
            }
        }

        return -1;
    }


    public static void bit(int[] ar) {
        int max = 0;
        for (int a : ar) {
            max = Math.max(max, a);
        }
        int[] bit = new int[max + 1];
        for (int i = ar.length - 1; i >= 0; i--) {
            System.out.print(getSum(bit, 5) - getSum(bit, ar[i] - 1) + " ");
            updateBit(bit, 1, ar[i]);
        }
    }

    private static void updateBit(int[] bit, int val, int index) {
        while (index < bit.length) {
            bit[index] += val;
            index += (index & -index);
        }
    }

    private static int getSum(int[] bit, int index) {
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= (index & -index);
        }
        return sum;
    }

    private static int sum(int[] bit, int index, int length) {
        index++;
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= (index & -index);
        }
        return sum;
    }

    private static void update(int[] bit, int val, int n, int index) {
        index++;
        while (index < n + 1) {
            bit[index] += val;
            index += (index & (-index));
        }
    }

    private static void readFromPropertyFile() {
        try {
            InputStream in = new FileInputStream("lastRids.properties");
            Properties pro = new Properties();
            pro.load(in);
            Map map = new HashMap<>(pro);
            for (Object ker : map.keySet()) {
                System.out.println((String) ker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeToPropertyFile() {
        try {
            Properties properties = new Properties();
            Map<String, String> map = new HashMap<>();
            map.put("Krishna", "Hare");
            map.put("Hare", "Rama");
            properties.putAll(map);
            File file = new File("lastRids.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "lastRids");
            fileOut.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printBinary(int n) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(((n >> i) & 1));
        }
        System.out.println();
    }

    public String decodeString(String s) {
        if (s.isEmpty()) {
            return "";
        }
        String cur = "";
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                j++;
            }

            if (j != i) {
                int num = Integer.parseInt(s.substring(i, j));
                i = j;
                i++;
                j = i;
                while (j < s.length() && s.charAt(j) != ']') {
                    j++;
                }
                String sub = s.substring(i, j);
                i = j;
                for (j = 1; j <= num; j++) {
                    cur += sub;
                }

            } else {
                j = i;
                while (j < s.length() && !(s.charAt(j) >= '0' && s.charAt(j) <= '9')) {
                    j++;
                }
                cur += s.substring(i, j);
                i = j - 1;
            }
        }

        return cur;
    }

    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        int i, j;
        for (i = 0, j = 0; i < s.length() && j < t.length(); i++, j++) {
            while (j < t.length() && t.charAt(j) != s.charAt(i)) {
                j++;
            }
        }
        if (i == s.length() && j <= t.length()) {
            return true;
        }
        return false;
    }

    public int lastRemaining(int n) {
        int a = 1, p = 1;
        int cnt = 0;
        while (n > 1) {
            n /= 2;
            cnt += 1;
            p *= 2;
            if (cnt % 2 == 1) {
                a += p / 2 + p * (n - 1);
            } else {
                a -= p / 2 + p * (n - 1);
            }
        }
        return a;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> ar = new ArrayList<>();
        //ar.indexOf();
        Arrays.sort(nums);
        sub(nums, res, ar, 0);
        return res;
    }

    private void sub(int[] nums, List<List<Integer>> res, ArrayList<Integer> ar, int index) {
        if (index >= nums.length) {
            res.add((ArrayList<Integer>) ar.clone());
            return;
        }

        int curIndex = index + 1;
        while (curIndex < nums.length && nums[curIndex] == nums[index]) {
            curIndex++;
        }

        for (int i = 0; i <= (curIndex - index); i++) {
            for (int j = 0; j < i; j++) ar.add(nums[index]);
            sub(nums, res, ar, curIndex);
            for (int j = 0; j < i; j++) ar.remove(ar.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> ar = new ArrayList<>();
        combo(k, n, ar, res);
        return res;
    }

    private void combo(int k, int n, ArrayList<Integer> ar, List<List<Integer>> res) {
        if (k == 0) {
            res.add(ar);
            return;
        }

        if (n == 0) {
            return;
        }
        combo(k, n - 1, (ArrayList<Integer>) ar.clone(), res);
        ar.add(n);
        combo(k - 1, n - 1, (ArrayList<Integer>) ar.clone(), res);
    }

    public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        List<List<String>> list = new ArrayList<>();
        System.out.println(fill(board, 0, list));
        return list;
    }


    private boolean fill(boolean[][] board, int row, List<List<String>> list) {
        if (row == board.length) {
            convertToList(board, list);
            //reset(board);
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            if (isPossible(board, row, i)) {
                board[row][i] = true;
                boolean isTrue = fill(board, row + 1, list);
                if (!isTrue) {

                } else {
                    //return true;
                }
                board[row][i] = false;
            }
        }

        return false;
    }

    private void reset(boolean[][] board) {
        for (boolean[] ar : board) {
            Arrays.fill(ar, false);
        }
    }

    private void convertToList(boolean[][] board, List<List<String>> list) {
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String row = "";
            for (int j = 0; j < board.length; j++) {
                if (!board[i][j]) {
                    row += ".";
                } else {
                    row += "Q";
                }
            }
            rows.add(row);
        }

        list.add(rows);
    }

    private boolean isPossible(boolean[][] board, int row, int col) {
        for (int i = 0; i <= row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        for (int i = 0; i <= col; i++) {
            if (board[row][i]) {
                return false;
            }
        }
        for (int i = row, j = col; Math.min(i, j) >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        for (int i = row, j = col; Math.min(i, j) >= 0 && Math.max(i, j) < board.length; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }
        return true;
    }

    private void permuteUnique(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
        if (start >= num.length) {
            ArrayList<Integer> item = convertArrayToList(num);
            result.add(item);
        }

        for (int j = start; j <= num.length - 1; j++) {
            if (containsDuplicate(num, start, j)) {
                swap(num, start, j);
                permuteUnique(num, start + 1, result);
                swap(num, start, j);
            }
        }
    }

    private boolean containsDuplicate(int[] arr, int start, int end) {
        for (int i = start; i <= end - 1; i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Integer> convertArrayToList(int[] num) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        for (int h = 0; h < num.length; h++) {
            item.add(num[h]);
        }
        return item;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        ArraysUtils.print(dp);
        return dp[s.length()][t.length()];
    }

    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
        }
        int len = words[0].length();
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < len; j++) {
            Map<String, Integer> currentMap = new HashMap<>();
            int start = 0;
            int count = 0;
            for (int i = j; i <= s.length() - len; i += len) {
                String sub = s.substring(i, i + len);
                if (map.containsKey(sub)) {
                    if (currentMap.size() == 0) {
                        start = i;
                    }
                    currentMap.put(sub, currentMap.containsKey(sub) ? currentMap.get(sub) + 1 : 1);
                    count++;
                    while (currentMap.get(sub) > map.get(sub)) {
                        String last = s.substring(start, start + len);
                        currentMap.put(last, currentMap.get(last) - 1);
                        start = start + len;
                        count--;
                    }

                    if (count == words.length) {
                        list.add(start);
                        String last = s.substring(start, start + len);
                        currentMap.put(last, currentMap.get(last) - 1);
                        if (currentMap.get(last) == 0) {
                            currentMap.remove(last);
                        }
                        start = start + len;
                        count--;
                    }


                } else {
                    currentMap.clear();
                    start = start + len;
                    count = 0;
                }
            }
        }
        return list;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int count = 0, j;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
            } else {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }

            if (map.size() > 2) {
                maxLength = Math.max(maxLength, i - start);
                while (map.size() > 2) {
                    if (map.get(s.charAt(start)) > 1) {
                        map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                    } else {
                        map.remove(s.charAt(start));
                    }
                    start++;
                }
            }
        }
        maxLength = Math.max(maxLength, s.length() - start);
        return maxLength;

    }


    public int firstMissingPositive(int[] nums) {
        int i, j;
        for (i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                swap(nums, i, j);
                j++;
            }
        }
        int n = j;
        for (i = 0; i < n; i++) {
            if ((nums[i] - 1) < n && nums[nums[i] - 1] > 0) {
                nums[nums[i] - 1] = -(nums[nums[i] - 1]);
            }
        }

        ArraysUtils.print(nums);
        for (i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

   /* private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }*/

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return pow(x, n);
        }
        return 1 / pow(x, -(long) n);
    }

    private double pow(double x, double n) {
        double res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = res * x;
                n--;
            } else {
                x = x * x;
                n /= 2;
            }
        }
        return res;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        while (!pq.isEmpty()) {
            ListNode listNode = pq.poll();
            cur.next = listNode;
            if (listNode.next != null) {
                pq.add(listNode.next);
            }
        }
        return head.next;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length < 2 || t < 0 || k < 0) {
            return false;
        }
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long cur = nums[i];
            long leftBoundary = cur - t;
            long rightBoundary = cur + t + 1;
            SortedSet<Long> set = treeSet.subSet(leftBoundary, rightBoundary);
            if (set.size() > 0) {
                return true;
            }
            treeSet.add(cur);
            if (i >= k) {
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    private boolean insert(TreeNode root, int data, int t, int k, int index) {
        while (true) {
            if (check(root, data, t, k, index)) {
                return true;
            }
            if (root.data == data) {
                root.index = index;
                break;
            }
            if (root.right != null && root.data < data) {

                root = root.right;
            } else if (root.left != null && root.data > data) {
                root = root.left;
            } else if (root.right == null && root.data < data) {
                root.right = new TreeNode(data, index);
                break;
            } else if (root.left == null && root.data > data) {
                root.left = new TreeNode(data, index);
                break;
            }
        }
        return false;
    }

    private boolean check(TreeNode root, int data, int t, int k, int index) {
        return Math.abs(root.data - data) <= t && index - root.index <= k;
    }


    class TreeNode {
        TreeNode left;
        TreeNode right;
        int data;
        int index;

        public TreeNode(int data, int index) {
            this.index = index;
            this.data = data;
        }
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            String range = String.valueOf(nums[i]);
            if (i < nums.length - 1 && nums[i + 1] == nums[i] + 1) {
                int j = i + 1;
                for (; j < nums.length && (nums[j] == nums[j - 1] + 1); j++) ;

                if (nums[j - 1] == nums[i] + j - i - 1) {
                    range += "->" + nums[j - 1];
                }
                i = j;

            } else {
                i++;
            }
            res.add(range);

        }

        return res;
    }


    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 9) {
            return 1;
        }
        long base = 1;
        while (n / (base * 10) >= 1) {
            base *= 10;
        }

        int rem = (int) (n % base);
        int res = 0;
        if (n >= 2 * base) {
            res += base;
        } else {
            res += rem + 1;
        }
        res += countDigitOne(rem) + (n / base) * countDigitOne((int) (base - 1));
        return res;
    }

    public int lengthLongestPath(String input) {
        ArrayList<Integer> ar = new ArrayList<>();
        int result = 0;
        String[] words = input.split("\n");
        for (String word : words) {
            int level = 0;
            int space = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(level) == '\t') {
                    level++;
                } else if (word.length() - i >= 4 && word.charAt(i) == ' ' && word.charAt(i + 1) == ' ' && word.charAt(i + 2) == ' ' && word.charAt(i + 3) == ' ') {
                    level++;
                    i += 3;
                    space += 3;
                } else {
                    break;
                }
            }
            int length = word.length() - level + 1 - space;
            if (word.contains(".")) {
                if (result < ar.get(level - 1) + length - 1) {
                    result = ar.get(level - 1) + length - 1;
                }
            } else {
                if (level >= ar.size()) {
                    ar.add(length);
                } else {
                    ar.set(level, length + ar.get(level - 1));
                }
            }
        }

        return result;
    }


    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        lexo(n, 1, res);
        return res;
    }

    private void lexo(int n, int c, ArrayList<Integer> cRes) {
        if (c > n) {
            return;
        }

        cRes.add(c);
        lexo(n, c * 10, cRes);
        for (int i = c + 1; i <= Math.min(c == 1 ? c + 8 : c + 9, n + 1); i++) {
            cRes.add(i);
            lexo(n, i * 10, cRes);
        }
    }

    public List<Integer> diffWaysToCompute(String input) {
        ArrayList<String> ar = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                ar.add(input.substring(start, i));
                ar.add(input.substring(i, i + 1));
                start = i + 1;
            }
        }
        ar.add(input.substring(start));
        return compute(ar, 0, ar.size());
    }

    private ArrayList<Integer> compute(ArrayList<String> input, int from, int to) {
        ArrayList<Integer> current = new ArrayList<>();
        if (to - from == 1) {
            current.add(Integer.parseInt(input.get(from)));
            return current;
        }
        if (to - from == 3) {
            current.add(computeResult(Integer.parseInt(input.get(from)), input.get(from + 1).charAt(0), Integer.parseInt(input.get(from + 2))));
            return current;
        }

        ArrayList<Integer> currentTo = new ArrayList<>();
        ArrayList<Integer> currentFrom = new ArrayList<>();
        for (int i = from; i < to - 2; i += 2) {
            currentTo = compute(input, i + 2, to);
            currentFrom = compute(input, from, i + 1);
            for (int j = 0; j < currentFrom.size(); j++) {
                for (int k = 0; k < currentTo.size(); k++) {
                    current.add(computeResult(currentFrom.get(j), input.get(i + 1).charAt(0), currentTo.get(k)));
                }
            }
        }

        return current;
    }

    private int computeResult(int first, char op, int second) {
        switch (op) {
            case '-':
                return first - second;
            case '+':
                return first + second;
            case '*':
                return (first * second);
        }
        return 0;
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (k == 0) {
            return null;
        }
        ArrayList<Integer> al1 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            al1.add(nums1[i]);
        }
        Collections.sort(al1);
        ArrayList<Integer> al2 = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            al2.add(nums2[i]);
        }
        Collections.sort(al2);
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        List<int[]> result = new ArrayList<>();
        while ((k > result.size())) {
            int num1 = al1.get(index1);
            int num2 = al2.get(index2);
            int[] pair = new int[2];
            pair[0] = num1;
            pair[1] = num2;
            if (index1 < al1.size() - 1 && index2 < al2.size() - 1) {
                if (al1.get(index1 + 1) - al1.get(index1) > al2.get(index2 + 1) - al2.get(index2)) {
                    index2++;
                } else if (al1.get(index1 + 1) - al1.get(index1) < al2.get(index2 + 1) - al2.get(index2)) {
                    index1++;
                } else {
                    if (al1.get(index1 + 1) < al2.get(index2 + 1)) {
                        index1++;
                    } else {
                        index2++;
                    }
                }
            } else if (index1 == al1.size() - 1) {
                index2++;
            } else if (index2 == al2.size() - 1) {
                index1++;
            }

            result.add(pair);
        }

        return result;
    }

    public int combinationSum4(int[] nums, int target) {

        return combinationSum4(nums, target, 0, new HashMap<Integer, Integer>());
    }

    private int combinationSum4(int[] nums, int target, int index, HashMap<Integer, Integer> integerIntegerHashMap) {
        if (target == 0) {
            return 1;
        }
        if (integerIntegerHashMap.containsKey(target)) {
            return integerIntegerHashMap.get(target);
        }
        if (index == nums.length) {
            return 0;
        }

        int count = 0;
        for (int i = index; i < nums.length; i++) {
            if (target - nums[i] >= 0) {
                count += combinationSum4(nums, target - nums[i], index, integerIntegerHashMap);
            }
        }
        integerIntegerHashMap.put(target, count);
        return count;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            map.put(ransomNote.charAt(i), map.containsKey(ransomNote.charAt(i)) ? map.get(ransomNote.charAt(i)) + 1 : 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (map.containsKey(magazine.charAt(i))) {
                map.put(magazine.charAt(i), map.get(magazine.charAt(i)) - 1);
            }
        }

        for (Character ch : map.keySet()) {
            if (map.get(ch) > 0) {
                return false;
            }
        }

        return true;
    }


    public int minPatches(int[] nums, int n) {
        long min = 1;
        int count = 0;
        int i = 0;
        while (min <= n) {
            if (i < nums.length && nums[i] <= min) {
                min = min + nums[i];
                i++;
            } else {
                min += min;
                count++;
            }
        }

        return count;

    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[j], j) * Math.max(dp[i - j], i - j));
            }
        }

        return dp[n];
    }

    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        if (num == 1) {
            return new int[]{0, 1};
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        while (arrayList.size() < num) {
            ArrayList<Integer> copy = (ArrayList<Integer>) arrayList.clone();
            for (int i = 0; i < arrayList.size() && copy.size() < num; i++) {
                copy.add(arrayList.get(i) + 1);
            }
            arrayList = copy;
        }

        int[] result = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }

        return result;
    }


    public List<String> wordBreak(String s, Set<String> wordDict) {
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        map.put(s.length(), arrayList);
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subString = s.substring(i, j);
                if (!wordDict.contains(subString)) {
                    continue;
                }
                if (!map.containsKey(j)) {
                    continue;
                }

                ArrayList<String> newWords = map.containsKey(i) ? map.get(i) : new ArrayList<>();
                newWords.add(subString);
                map.put(i, newWords);
            }
        }

        if (!map.containsKey(0)) {
            return new ArrayList<>();
        }

        ArrayList<String> arrayList1 = dfs(map, s, 0);
        return arrayList1;
    }

    private ArrayList<String> dfs(HashMap<Integer, ArrayList<String>> map, String s, int index) {
        if (!map.containsKey(index)) {
            return null;
        }

        if (index == s.length()) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("");
            return arrayList;
        }

        ArrayList<String> newArrayList = new ArrayList<>();
        for (int i = 0; i < map.get(index).size(); i++) {
            ArrayList<String> restWords = dfs(map, s, index + map.get(index).get(i).length());
            if (restWords == null) {
                continue;
            }
            for (int j = 0; j < restWords.size(); j++) {

                newArrayList.add(map.get(index).get(i) + (restWords.get(j).isEmpty() ? "" : " ") + restWords.get(j));
            }
        }

        return newArrayList;
    }
    /*private String wordBreak(String s, int index, Set<String> wordDictionary, HashMap<Integer, String> map, String currentWord, ArrayList<String> list){
         if (index >= s.length()){
            return "";
        }
        if (map.containsKey(index)){
            return map.get(index);
        }

        String current = "";
        for (int i = index; i <= s.length(); i++) {
            if (wordDictionary.contains(s.substring(index, i))){
                current = wordBreak(s, i, wordDictionary, map, currentWord);
            }
            if (isExist){
                return isExist;
            }
        }
        map.put(index, isExist);
        return isExist;
    }*/


    public int singleNumber(int[] nums) {
        int num = 0;
        int[] digit = new int[32];
        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            int count = 0;
            while (number > 0) {
                digit[count++] += ((number & 1));
                number >>= 1;
            }
        }

        int missing = 0;
        for (int i = 0; i < digit.length; i++) {
            if (digit[i] % 3 == 0) {
                continue;
            }
            missing += Math.pow(2, i);
        }

        return missing;
    }

    public int numDecodings(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        return decode(s, 0, map);
    }

    private int decode(String num, int index, Map<Integer, Integer> map) {
        if (index == num.length()) {
            return 1;
        }
        if (map.containsKey(index)) {
            return map.get(index);
        }
        if (num.charAt(index) == '0') {
            return 0;
        }

        int count = decode(num, index + 1, map);
        int number = index < num.length() - 1 ? Integer.parseInt(num.substring(index, index + 2)) : 0;
        //(index  < num.length() - 1 ? ((num.charAt(index) - '0')* 10 + num.charAt(index + 1) - '0') : 0);
        if (number >= 1 && number <= 26) {
            count += decode(num, index + 2, map);
        }
        map.put(index, count);
        return count;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode less = null;
        ListNode lessHead = null;
        ListNode moreHead = null;
        ListNode more = null;
        while (head != null) {
            if (head.val < x) {
                if (lessHead == null) {
                    lessHead = head;
                    less = head;
                } else {
                    less.next = head;
                    less = head;
                }
            } else {
                if (moreHead == null) {
                    moreHead = head;
                    more = head;
                } else {
                    more.next = head;
                    more = head;
                }
            }
            head = head.next;
        }
        if (more != null) {
            more.next = null;
        }

        if (less != null) {
            less.next = moreHead;
            return moreHead;
        }
        return moreHead;
    }


    public String addBinary(String a, String b) {
        String result = "";
        int carry = 0;
        String min = a.length() > b.length() ? b : a;
        String max = a.length() > b.length() ? a : b;
        for (int i = min.length() - 1, j = 1; i >= 0; i--, j++) {
            int num1 = Integer.parseInt(String.valueOf(max.charAt(max.length() - j)));
            int num2 = Integer.parseInt(String.valueOf(min.charAt(i)));
            int sum = num1 + num2 + carry;
            carry = sum / 2;
            sum %= 2;
            result = sum + result;
        }
        for (int i = max.length() - min.length() - 1; i >= 0; i--) {
            int num1 = Integer.parseInt(String.valueOf(max.charAt(i)));
            int sum = num1 + carry;
            carry = sum / 2;
            sum %= 2;
            result = sum + result;
        }

        if (carry == 1) {
            result = carry + result;
        }
        return result;
    }

    public String getPermutation(int n, int k) {
        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            number.add(i);
        }
        String result = "";
        while (number.size() > 0) {
            int num = fact(number.size() - 1);
            int index = 0;
            while (k > (index + 1) * num) {
                index++;
            }
            k -= (index * num);
            result += number.remove(index);
        }

        return result;
    }

    private int fact(int num) {
        if (num == 0) {
            return 1;
        }

        int fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }

    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length() + num2.length()];
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        for (int i = num2.length() - 1, indexI = 0; i >= 0; i--, indexI++) {
            int number1 = Integer.parseInt(String.valueOf(num2.charAt(i)));
            int carry = 0;
            int indexJ = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                indexJ = num1.length() - 1 - j;
                int number2 = Integer.parseInt(String.valueOf(num1.charAt(j)));
                int resultC = (number1 * number2) + carry;
                carry = resultC / 10;
                result[indexI + indexJ] += resultC % 10;
                carry += (result[indexI + indexJ]) / 10;
                result[indexI + indexJ] %= 10;
            }

            result[indexI + indexJ + 1] += carry;
            carry = (result[indexI + indexJ + 1]) / 10;
            result[indexI + indexJ + 1] %= 10;
            if (carry > 0) {
                result[indexI + indexJ + 2] = carry;
            }
        }
        String resultF = "";
        for (int i : result) {
            resultF = i + resultF;
        }
        int i = 0;
        while (resultF.length() - 1 > i && resultF.charAt(i) == '0') {
            i++;
        }
        return resultF.substring(i);
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        sum(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }

    private void sum(int[] candidate, int target, List<List<Integer>> result, ArrayList<Integer> list, int index) {
        if (target == 0) {
            result.add((ArrayList<Integer>) list.clone());
            return;
        }

        if (target < 0 || index >= candidate.length) {
            return;
        }

        for (int i = index; i < candidate.length; i++) {
            if (index != i && candidate[i] == candidate[i - 1]) {
                continue;
            }
            if (i == 3) {
                System.out.println();
            }

            list.add(candidate[i]);
            sum(candidate, target - candidate[i], result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public int search(int[] a, int key) {
        int l = 0;
        int h = a.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (a[m] == key) {
                return m;
            }

            if (a[l] < a[m] && key < a[m] && a[l] <= key) {
                h = m - 1;
            } else if (a[l] <= a[m] && (key > a[m] || key < a[m] && a[l] > key)) {
                l = m + 1;
            } else if (a[l] > a[m] && (a[h] < key || a[m] > key)) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }

        return -1;
    }

    public int reverse(int x) {
        String string = String.valueOf(x);
        boolean isNeg = false;
        if (string.charAt(0) == '-') {
            string = string.substring(1);
            isNeg = true;
        }
        char[] array = string.toCharArray();
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            char temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }

        int number = Integer.parseInt(new String(array));
        if (isNeg) {
            number *= -1;
        }
        return number;
    }

    /*public String serialize(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        String result = "";
        while (!queue.isEmpty()) {
            TreeNode root = queue.poll();
            result += (root == null ? "#" : root.val) + ",";
            if (root == null || (root.left == null && root.right == null)) {
                continue;
            }
            queue.add(root.left);
            queue.add(root.left);
        }
        System.out.println(result);
        return result;
    }*/

    // Decodes your encoded data to tree.
    /*public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] tree = data.split(",");
        return deserialize(tree, 0);

    }*/

    /*private TreeNode deserialize(String[] tree, int index) {
        if (index >= tree.length || tree[index].equals("#")) {
            return null;
        }

        TreeNode treeNode = new TreeNode(Integer.parseInt(tree[index]));
        treeNode.left = deserialize(tree, 2 * index + 1);
        treeNode.right = deserialize(tree, 2 * index + 2);
        return treeNode;
    }*/

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 1 && j == 0) {
                    System.out.println();
                }
                max = Math.max(max, lip(matrix, i, j, Integer.MAX_VALUE, dp));
            }
        }
        return max;
    }

    private int lip(int[][] matrix, int i, int j, int previous, int[][] dp) {
        if (previous != Integer.MAX_VALUE && (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= previous)) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int max = Math.max(lip(matrix, i, j + 1, matrix[i][j], dp), Math.max(lip(matrix, i, j - 1, matrix[i][j], dp),
                Math.max(lip(matrix, i - 1, j, matrix[i][j], dp), lip(matrix, i + 1, j, matrix[i][j], dp)))) + 1;

        dp[i][j] = max;
        if (max == 5) {
            System.out.println();
        }
        return max;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            if (!map.containsKey(tickets[i][0])) {
                PriorityQueue<String> priorityQueue = new PriorityQueue<>();
                priorityQueue.add(tickets[i][1]);
                map.put(tickets[i][0], priorityQueue);
            } else {
                map.get(tickets[i][0]).offer(tickets[i][1]);
            }
        }

        LinkedList<String> list = new LinkedList<>();
        dfs("JFK", map, list);
        return list;
    }

    private void dfs(String value, Map<String, PriorityQueue<String>> map, LinkedList<String> result) {
        PriorityQueue<String> priorityQueue = map.get(value);
        while (priorityQueue != null && !priorityQueue.isEmpty()) {
            dfs(priorityQueue.poll(), map, result);
        }

        result.addFirst(value);
    }

    public int numberOfTriangle(int[] array) {
        Arrays.sort(array);
        int count = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = i - 1; j > 0; j--) {
                int k = 0;
                while (k < j && array[i] >= array[j] + array[k]) {
                    k++;
                }

                count += j - k;
            }
        }

        return count;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Set<List<Integer>> result = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(reverse(words[i]), i);
        }
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            for (int j = 0; j <= words[i].length(); j++) {
                String prefix = current.substring(0, j);
                String postFix = current.substring(j);
                if (map.containsKey(prefix) && isPlindrome(postFix)) {
                    if (i != map.get(prefix)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(prefix));
                        result.add(list);

                    }
                }
            }

            for (int j = current.length(); j >= 0; j--) {
                String postFix = current.substring(j, current.length());
                String prefix = current.substring(0, j);
                if (map.containsKey(postFix) && isPlindrome(prefix)) {
                    if (i != map.get(postFix)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(postFix));
                        result.add(list);
                    }
                }
            }
        }


        List<List<Integer>> cResult = new ArrayList<>();
        for (List<Integer> li : result) {
            cResult.add(li);
        }

        return cResult;
    }

    private String reverse(String word) {
        char[] array = word.toCharArray();
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }

    private boolean isPlindrome(String cPalin) {
        for (int i = 0, j = cPalin.length() - 1; i < j; i++, j--) {
            if (cPalin.charAt(i) != cPalin.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = left(root.left);
        int right = right(root.right);
        if (left == right) {
            return (2 << left) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    private int left(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int left = 0;
        while (treeNode != null) {
            treeNode = treeNode.left;
            left++;
        }

        return left;
    }

    private int right(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int right = 0;
        while (treeNode != null) {
            treeNode = treeNode.right;
            right++;
        }

        return right;
    }


    public int findPeakElement(int[] a) {
        if (a.length == 1) {
            return a[0];
        }
        return peak(a, 0, a.length - 1);
    }

    private int peak(int[] a, int l, int h) {
        if (l == h) {
            return l;
        }

        if (l + 1 == h) {
            return a[l] > a[h] ? l : h;
        }

        int mid = l + (h - l) / 2;

        if (a[mid - 1] < a[mid] && a[mid + 1] < a[mid]) {
            return mid;
        }

        if (a[mid + 1] > a[mid]) {
            return peak(a, mid + 1, h);
        }

        return peak(a, l, mid - 1);
    }

    /*public int[] singleNumber(int[] a) {
        long xor = 0;
        for (int i = 0; i < a.length; i++) {
            xor ^= a[i];
        }
        long difBit = xor ^ (xor - 1);
        int[] result = new int[2];
        for (int i = 0; i < a.length; i++) {
            if ((a[i] & difBit) == 1) {
                result[0] ^= a[i];
            } else {
                result[1] ^= a[i];
            }
        }

        return result;
    }*/

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }


    public boolean isUgly(int num) {
        if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0) {
            return false;
        }
        num = Math.abs(num);
        Set<Integer> set = new HashSet<>();
        while (num % 2 == 0) {
            set.add(2);
            num /= 2;
        }
        for (int i = 3; i <= (int) Math.sqrt(num); i += 2) {
            while (num % i == 0) {
                set.add(i);
                num /= i;
            }
        }

        if (num > 2) {
            set.add(num);
        }

        for (int il : set) {
            if (il != 2 && il != 3 && il != 5) {
                return false;
            }
        }

        return true;

    }

    public String getHint(String secret, String guess) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            map.put(secret.charAt(i), map.containsKey(secret.charAt(i)) ? map.get(secret.charAt(i)) + 1 : 1);
        }

        int bull = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bull++;
                map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
                if (map.get(secret.charAt(i)) == 0) {
                    map.remove(secret.charAt(i));
                }
            }
        }
        int cow = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) != secret.charAt(i)) {
                if (map.containsKey(guess.charAt(i))) {
                    cow++;
                    map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
                    if (map.get(secret.charAt(i)) == 0) {
                        map.remove(secret.charAt(i));
                    }
                }
            }
        }


        return bull + "A" + cow + "B";
    }


    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++) {
            queue.add(new Node(0, primes[i], primes[i]));
        }
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            Node cur = queue.peek();
            result[i] = cur.val;
            do {
                cur = queue.poll();
                cur.val = result[++cur.index] * cur.prime;
                queue.add(cur);
            } while (!queue.isEmpty() && queue.peek().val == result[i]);
        }

        for (int i : result) {
            System.out.print(i + " ");
        }

        return result[n - 1];
    }

    class Node implements Comparable<Node> {
        int index;
        int val;
        int prime;

        public Node(int index, int val, int prime) {
            this.index = index;
            this.val = val;
            this.prime = prime;
        }

        public int compareTo(Node x) {
            return this.val - x.val;
        }


    }


    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[dungeon.length - 1].length];
        int m = dungeon.length;
        int n = dungeon[dungeon.length - 1].length;
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? 1 : 1 - dungeon[m - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = dp[m - 1][i + 1] - dungeon[m - 1][i];
            if (dp[m - 1][i] <= 0) {
                dp[m - 1][i] = 1;
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dp[i + 1][n - 1] - dungeon[i][n - 1];
            if (dp[i][n - 1] <= 0) {
                dp[i][n - 1] = 1;
            }
        }


        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j];
                if (dp[i][j] <= 0) {
                    dp[i][j] = 1;
                }
            }
        }

        return dp[0][0];
    }

    public String largestNumber(int[] a) {
        Integer[] array = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            array[i] = a[i];
        }
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer int1, Integer int2) {
                String s1 = String.valueOf(int1);
                String s2 = String.valueOf(int2);
                String first = s1 + s2;
                String second = s2 + s1;
                return first.compareTo(second) * -1;
            }

        });

        String result = "";
        int i = 0;
        while (i < array.length - 1 && array[i] == 0) {
            i++;
        }
        for (; i < array.length; i++) {
            result += array[i];
        }

        return result;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Boolean> map = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String s1 = s.substring(i, i + 10);
            if (map.containsKey(s1) && !map.get(s1)) {
                result.add(s1);
                map.put(s1, true);
            } else if (!map.containsKey(s1)) {
                map.put(s1, false);
            }
        }

        return result;
    }

    public void rotate(int[] a, int k) {
        k = k % a.length;
        int currentIndex = -1;
        int previousIndex = -1;
        int pre = a[0];
        int index = a.length;
        while (index-- > 0) {
            if (currentIndex == previousIndex) {
                previousIndex++;
                currentIndex++;
                pre = a[previousIndex];
            }
            int next = a[(currentIndex + k) % a.length];
            a[(currentIndex + k) % a.length] = pre;
            currentIndex = (currentIndex + k) % a.length;
            pre = next;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }

    public int maxCoins(int[] a) {
        int[] aNew = new int[a.length + 2];
        for (int i = 0; i < a.length; i++) {
            aNew[i + 1] = a[i];
        }

        aNew[aNew.length - 1] = aNew[0] = 1;
        int[][] mem = new int[aNew.length][aNew.length];
        return burst(aNew, 0, aNew.length - 1, mem);
    }

    private int burst(int[] aNew, int left, int right, int[][] mem) {
        if (left + 1 == right) {
            return 0;
        }
        if (mem[left][right] > 0) return mem[left][right];

        int ans = 0;
        for (int i = left + 1; i < right; i++) {
            ans = Math.max(ans, aNew[left] * aNew[i] * aNew[right]
                    + burst(aNew, left, i, mem)
                    + burst(aNew, i, right, mem));
        }

        mem[left][right] = ans;
        return ans;
    }

    public void countSmaller() {
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));

    }

    private class NodeWithIndex {
        int value;
        int index;
        int inversion;

        public NodeWithIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }


    public List<Integer> countSmaller(int[] a) {
        List<NodeWithIndex> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            list.add(new NodeWithIndex(a[i], i));
        }

        mergeSort(list, 0, list.size() - 1);

        Integer[] result = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[list.get(i).index] = list.get(i).inversion;
        }

        return Arrays.asList(result);
    }

    private void mergeSort(List<NodeWithIndex> list, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        mergeSort(list, low, mid);
        mergeSort(list, mid + 1, high);
        merge(list, low, mid, high);
    }

    private void merge(List<NodeWithIndex> list, int low, int mid, int high) {
        int i, j;
        List<NodeWithIndex> result = new ArrayList<>();
        for (i = low, j = mid + 1; i <= mid && j <= high; ) {
            if (list.get(i).value <= list.get(j).value) {
                result.add(list.get(i));
                i++;
            } else {
                NodeWithIndex nodeWithIndex = list.get(j);
                nodeWithIndex.inversion += (j - i);
                result.add(list.get(j));

                j++;
            }

        }

        while (i <= mid) {
            result.add(list.get(i++));

        }
        while (j <= high) {
            result.add(list.get(j++));
        }

        for (int l = 0; l < result.size(); l++) {
            list.set(low + l, result.get(l));
        }
    }

    public void number() {
        int number = 52;
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 1; i <= 25; i++) {
            map.put(i, (char) (65 + i));
        }
        map.put(0, 'Z');

        String result = "";
        while (number > 0) {
            int remainder = number % 26;
            result = String.valueOf(map.get(remainder)) + remainder;
            number /= 26;
        }

        System.out.println(result);
    }

  /*  public void testStack(){
        LeetCode leetCode = new LeetCode();
        leetCode.push(1);
        leetCode.push(2);
        leetCode.push(3);
        leetCode.pop();
        System.out.println(leetCode.getMin());
        leetCode.pop();
        System.out.println(leetCode.getMin());
        leetCode.pop();
        System.out.println(leetCode.getMin());
    }*/

    /*public void push(int x) {
*//*        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x){
            minStack.push(x);
        }*//*
    }

    public void pop() {
        if (stack.isEmpty()){
            return;
        }
        int pop = stack.pop();
        if (!minStack.isEmpty()  && minStack.peek() == pop){
            minStack.pop();
        }
    }

    public int top() {
        if (stack.isEmpty()){
            return  -1;
        }
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()){
            return -1;
        }
        return minStack.peek();
    }*/


    public void removeDuplicate() {
        String string = "abcdbacd";
        System.out.println(removeDuplicateLetters(string));
    }

    public String removeDuplicateLetters(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        int last = 0;
        char[] result = new char[s.length()];
        boolean[] isAlready = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            if (!isAlready[s.charAt(i)]) {
                result[last++] = s.charAt(i);
                isAlready[s.charAt(i)] = true;
            }
        }

        return new String(result);
    }


}
