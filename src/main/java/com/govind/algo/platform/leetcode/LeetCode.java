package com.govind.algo.platform.leetcode;

import com.govind.algo.tree.TreeLinkNode;

/**
 * Created by 609600403 on 15/05/2016.
 */


public class LeetCode {


    public static void main(String args[]) {
       // System.out.println(compare(Arrays.asList(new String[]{"A+++","A--","A--*","B+++","A","B--","B+++","B+++*","B","A---","B---*","B++","A+","A-*","A-","A---*","A+++*"})));
        //System.out.println(new LeetCode().insertionSortList(ListNode.toListNode(new int[] {3, 5, 7, 2, 8, 10}));
        //System.out.println(new LeetCode().buildTree(ArrayListUtils.getArrayList(new int[]{7, 5, 6, 2, 3, 1, 4}), ArrayListUtils.getArrayList(new int[]{5, 6, 3, 1, 4, 2, 7})));
        TreeLinkNode treeLinkNode = new TreeLinkNode(1,new TreeLinkNode(2),new TreeLinkNode(3));
        new LeetCode().connect(treeLinkNode);
    }

    public void connect(TreeLinkNode root) {
        TreeLinkNode cur = root;
        TreeLinkNode leftWall = null;
        TreeLinkNode pre = null;
        while (cur != null){
            while (cur != null){
                System.out.println("cur " + cur.val);
                if (cur.left != null){
                    if (leftWall == null){
                        leftWall = cur.left;
                    }else{
                        pre.next = cur.left;
                    }
                    pre = cur.left;
                }

                if (cur.right != null){
                    if (leftWall == null){
                        leftWall = cur.right;
                    }else{
                        pre.next = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = leftWall;
            leftWall = null;
        }
    }

/*
    public TreeNode buildTree(ArrayList<Integer> preorder, ArrayList<Integer> inorder) {
        return buildTree(preorder, inorder, new Index(0));
    }

    private TreeNode buildTree(ArrayList<Integer> preorder, ArrayList<Integer> inorder, Index index) {
        return null;
    }

    class Index{
        int index;
        Index(int index){
            this.index = index;
        }
    }

    private int getPost(ArrayList<Integer> inorder,  int index, ArrayList<Integer> postOrder){
        Set<Integer> set = new HashSet<>(inorder.subList(0, index));
        for (int i = postOrder.size() - 1; i >= 0; i--){
            if (set.contains(postOrder.get(i))){
                return i;
            }
        }

        return -1;
    }






    public TreeNode buildTree(ArrayList<Integer> a) {
        int max = getMax(a);
        if (max == -1){
            return null;
        }

        TreeNode root = new TreeNode(a.get(max));
        root.left = buildTree(new  ArrayList<Integer>(a.subList(0, max)));
        root.right = buildTree(new ArrayList<Integer> (a.subList(max + 1, a.size())));
        return root;
    }

    private int getMax(ArrayList<Integer> a){
        if (a == null || a.isEmpty()){
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < a.size(); i++){
            if (a.get(i) > max){
                maxIndex = i;
                max = a.get(i);
            }
        }

        return maxIndex;
    }

    class Index{
        int index;

        public Index(int index) {
            this.index = index;
        }
    }



    public ArrayList<Integer> postorderTraversal(TreeNode a) {
        Set<TreeNode> isVisited = new HashSet<>();
        Stack<TreeNode> stack = new Stack();
        ArrayList<Integer> res = new ArrayList<>();
        while (a != null || !stack.isEmpty()){
            if (a != null && !isVisited.contains(a)){
                stack.push(a);
                a = a.left;
            }else{
                a = stack.peek();
                if (isVisited.contains(a)){
                    res.add(a.val);
                    stack.pop();
                    a = (stack.isEmpty() ? null : stack.peek());
                }else{
                    isVisited.add(a);
                }
                a = (a == null ? a : a.right);
            }
        }
        return res;
    }


    public int isBalanced(TreeNode a) {
        if (isBalanced(a, 0) == Integer.MAX_VALUE){
            return 0;
        }

        return 1;
    }

    private int isBalanced(TreeNode a, int depth){
        if (a == null){
            return depth;
        }
        System.out.println(a.val);
        int left = isBalanced(a.left, depth + 1);
        int right = isBalanced(a.right, depth + 1);
        if (Math.abs(left - right) > 1){
            return Integer.MAX_VALUE;
        }

        return Math.max(left, right);
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode newHead = new RandomListNode(0);
        RandomListNode newCur = newHead;
        RandomListNode cur = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (cur != null){
            newCur.next = new RandomListNode(cur.label);
            map.put(cur, newCur.next);
            newCur = newCur.next;
            cur = cur.next;

        }

        newCur = newHead.next;
        cur = head;
        while (cur != null){
            newCur.random = map.get(cur.random);
            cur = cur.next;
            newCur = newCur.next;
        }
        return newHead.next;
    }





    public String getPermutation(int n, int k) {
        k = k - 1;
        ArrayList<Integer> ar = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            ar.add(i);
        }
        String res = "";
        while (!ar.isEmpty()){
            long fact = fact(ar.size() - 1);
            int index = (int)(k / fact) ;
            k -= (index *fact) ;
            //System.out.println("k " + k+" fact "+ fact);
            res += String.valueOf(ar.get(index));
            ar.remove(index);
        }
        return res;
    }

    private long fact(int n){
        if (n == 0){
            return 1;
        }
        long fact = 1;
        for (long i = 2; i<= n; i++){
            fact *= i;
        }

        return fact;
    }


    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        boolean[][] mat = new boolean[a][a];
        nQueen( mat, a, 0, res);
        return res;
    }

    private boolean nQueen(boolean[][] mat, int n, int row, ArrayList<ArrayList<String>> res){
        if (row == n){
            ArrayList<String> curAr = new ArrayList<>();
            for (boolean[] ar : mat){
                String cur = "";
                for (int i = 0;i < ar.length; i++){
                    cur += (ar[i] ?"Q" : ".");
                }
                curAr.add(cur);
            }

            res.add(curAr);
            return true;
        }
        for (int i = 0; i < n; i++){
            if (isPossible(row, i, mat)){
                mat[row][i] = true;
                nQueen(mat, n, row + 1, res);
                mat[row][i] = false;
            }
        }

        return false;
    }

    private boolean isPossible(int row, int col, boolean mat[][]){
        for (int i = 0; i <= row; i++){
            if (mat[i][col]){
                return false;
            }
        }

        for (int i = row, j = col; j >= 0 && i >= 0 ; i--,j--){
            if(mat[i][j]){
                return false;
            }
        }

        for (int i = row, j = col; j < mat.length && i >= 0 ; i--,j++){
            if(mat[i][j]){
                return false;
            }
        }

        return true;
    }


    public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        part( a, new ArrayList<>(), res);
        return res;
    }

    private void part(String a, ArrayList<String> cur, ArrayList<ArrayList<String>> res){
        if (a == null || a.isEmpty()){
            res.add((ArrayList<String>)cur.clone());
            return;
        }
        for (int i = 1; i <= a.length(); i++){
            String word = a.substring(0, i );
            if (isPal(word)){
                cur.add(word);
                part(a.substring(i, a.length()), cur, res);
                cur.remove((int)cur.size() - 1);
            }
        }
    }

    private boolean isPal(String word){
        if (word.length() == 1){
            return true;
        }
        for (int i = 0, j = word.length() - 1; i < j ;i++, j--){
            if (word.charAt(i) != word.charAt(j)){
                return false;
            }
        }
        return true;
    }


    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        Collections.sort(a);
        String str = "";
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < a.size(); i++){
            if (i > 0 && a.get(i) == a.get(i - 1)){
                continue;
            }
            comb(i, a, b, new ArrayList<Integer>(), res);
        }

        return res;
    }

    private void comb(int index, ArrayList<Integer> a, int b,ArrayList<Integer>  cur, ArrayList<ArrayList<Integer>> res){
        if (b == 0){
            res.add((ArrayList<Integer>) cur.clone());
            return;
        }
        if (index == a.size() || b < 0){
            return;
        }
        b -= a.get(index);
        cur.add(a.get(index));
        for (int i = index + 1; i< a.size(); i++){
            if (a.get(index) != a.get(i)){
                comb(index + 1, a, b, cur, res);
            }
            cur.add(a.get(i));
            comb(i + 1, a, b - a.get(i), cur, res);
            cur.remove(a.get(i));

        }

        cur.remove(a.get(index));

    }

    */
/*public String simplifyPath(String a) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.pollLast();
        Stack<String> stack = new Stack<>();
        stack.size();
        for (int i = 1, j = 1; i <= a.length(); i++){
            if ((i == a.length())  || a.charAt(i) == '/'  ){
                String word = a.substring(j, i);
                if (word.equals("..")){
                    stack.pop();
                }else if (!word.isEmpty() &&!word.equals(".")){
                    stack.add(word);
                }
                j = i + 1;
            }
        }
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()){
            str.append(stack.pop());
            str.append("/");
        }

        return str;
    }*//*


    public static List<String> compare(List<String> strings){
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 25, j =  40; i >= 0; i--, j+= 80){
            map.put((char) ('A'+ i),j);
        }
        map.put('+', 10);
        map.put('-', -10);
        map.put('*', 5);
        System.out.println(map.get('A'));
        System.out.println(map.get('B'));
        Collections.sort(strings, new Comparator<String>() {
             @Override
             public int compare(String o1, String o2) {
                 int o1Sum = 0;
                 for (int i = 0; i< o1.length(); i++){
                     o1Sum += map.get(o1.charAt(i));
                 }
                 int o2Sum = 0;
                 for (int i = 0; i < o2.length(); i++){
                     o2Sum += map.get(o2.charAt(i));
                 }

                 return o1Sum - o2Sum;
             }
         });
        return strings;
    }

    public ListNode insertionSortList(ListNode a) {
        ListNode res = null;
        ListNode head = null;
        while (a != null){
            if (head == null){
                res = a;
                head = res;
                a = a.next;
                res.next = null;
                System.out.println(" head " + head.val);
            }else {
                res = head;
                ListNode pre = null;
                System.out.println(" a value " + a.val);
                while (res != null && a != null){
                    ListNode next = a.next;
                    if (res.val >= a.val && pre == null){
                        a.next = head;
                        head = a;
                        a = next;
                        break;
                    }else if (res.val >= a.val && pre != null && a.val >= pre.val){
                        pre.next = a;
                        a.next = res;
                        System.out.println(" intermediate " + res.val);
                        a = next;
                        break;
                    }else if (res.next == null){
                        res.next = a;
                        a.next = null;
                        System.out.println(" last " + res.val);
                        a = next;
                        break;
                    }
                    pre = res;
                    res = res.next;
                    System.out.println("  pre "+ pre.val);

                }
            }

        }
        return head;
    }


    private ListNode atn(ListNode a, ListNode b){
        if (a == null){
            return b;
        }
        if (b == null){
            return a;
        }
        int sum = a.val + b.val;
        ListNode res = new ListNode(sum % 10);
        int carry = (sum / 10);
        a = a.next;
        b = b.next;
        while (a != null && b != null){
            sum = (a.val + b.val) + carry;
            carry = sum / 10;
            sum = sum % 10;
            if (res == null){
                res = new ListNode(sum);
            }else {
                res.next = new ListNode(sum);
            }
            res = res.next;
        }

        while (a != null){
            sum = a.val + carry;
            carry = sum / 10;
            res.next = new ListNode(sum);
            res = res.next;
            a = a.next;
        }
        while (b != null){
            sum = b.val + carry;
            carry = sum / 10;
            res.next = new ListNode(sum);
            b = b.next;
        }

        return res;
    }






    public int kthsmallest(final List<Integer> a, int k) {
        int[] ar = new int[a.size()];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int ele : a) {
            pq.add(ele);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }


    public int removeDuplicates(ArrayList<Integer> a) {
        int j, i;
        for (i = 1, j = 0; i < a.size(); i++) {
            int intI = a.get(i).intValue();
            if (i > 1 && intI == a.get(i - 1).intValue() && intI == a.get(i - 2).intValue()) {
                continue;
            }
            j++;
            a.set(j, a.get(i));
        }

        return j + 1;
    }

    public int power(String a) {
        BigInteger big1 = new BigInteger(a);
        BigInteger big2 = big1.subtract(BigInteger.ONE);
        return big1.and(big2).equals(BigInteger.ZERO) ? 1 : 0;
    }

    public int strStr(final String haystack, final String needle) {
        if (needle.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (needle.charAt(0) == haystack.charAt(i)) {
                int j;
                for (j = 1; j < needle.length() && i + j < haystack.length()
                        && needle.charAt(j) == haystack.charAt(i + j); j++)
                    ;

                if (j == needle.length()) {
                    return i;
                }
            }
        }

        return -1;
    }


    public String reverseWords(String a) {
        int i, j;
        for (i = 0; i < a.length() && a.charAt(i) == ' '; i++) ;
        if (i == a.length()) {
            return " ";
        }

        for (j = a.length() - 1; j >= 0 && a.charAt(j) == ' '; j--) ;
        String res = "";
        for (; j >= i; j--) {
            if (a.charAt(j) == ' ') {
                res += " ";
                for (; j >= 0 && a.charAt(j) == ' '; j--) ;
                j++;
            } else {
                res += a.charAt(j);
            }

        }
        //System.out.println(" Result "+res);
        StringBuilder fResult = new StringBuilder();
        int start, end;
        for (end = 0, start = 0; end < res.length(); end++) {
            if (res.charAt(end) == ' ') {
                fResult.append(reverse(start, end - 1, res));
                fResult.append(' ');
                start = end + 1;
            }
        }

        fResult.append(reverse(start, end - 1, res));

        return fResult.toString();
    }

    private String reverse(int start, int end, String res) {
        StringBuilder cRes = new StringBuilder();
        for (; end >= start; end--) {
            cRes.append(res.charAt(end));
        }

        return cRes.toString();
    }

    public String countAndSay(int a) {
        StringBuilder str = new StringBuilder("1");
        StringBuilder next = new StringBuilder("1");
        for (int i = 1; i < a; i++) {
            int count = 1;
            str = next;
            next = new StringBuilder();
            //System.out.println("next :" + next);
            for (int j = 0; j < str.length() - 1; j++) {
                if (str.charAt(j) == str.charAt(j + 1)) {
                    count++;
                } else {
                    next.append(String.valueOf(count));
                    next.append(str.charAt(j));
                    count = 1;
                }
            }
            next.append(String.valueOf(count));
            next.append(str.charAt(str.length() - 1));
        }

        return next.toString();
    }

    public int sqrt(int a) {
        for (long l = 1, h = a; l <= h; ) {
            long mid = (h - l) / 2 + l;
            long sqr = mid * mid;
            if (sqr == a) {
                return (int) mid;
            } else if (sqr < a && ((mid + 1) * (mid + 1) > a)) {
                return (int) mid;
            } else if (sqr < a) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return a;
    }

    public int searchInsert(ArrayList<Integer> a, int b) {
        for (int l = 0, h = a.size() - 1; l <= h; ) {
            int mid = (l + h) / 2;
            System.out.println(" mid " + mid + " a.get(mid) " + a.get(mid));
            if (a.get(mid) == b || ((mid == 0 || a.get(mid - 1) < b) && a.get(mid) > b)) {
                return mid;
            } else if (mid == a.size() - 1 && a.get(mid) < b) {
                return mid + 1;
            } else if (a.get(mid) < b) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return 0;
    }

    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        int low, high;
        ArrayList<Integer> res = new ArrayList<>(Collections.nCopies(2, -1));
        for (low = 0, high = a.size() - 1; low <= high; ) {
            int mid = (high + low) / 2;
            if (a.get(mid) == b) {
                int left = searchRangeUtil(a, low, mid, b, true);
                int right = searchRangeUtil(a, mid, high, b, false);
                res.set(0, left);
                res.set(1, right);
                return res;
            } else if (a.get(mid) < b) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    private int searchRangeUtil(List<Integer> a, int left, int right, int b, boolean isLeft) {
        for (; left <= right; ) {
            int mid = (right + left) / 2;
            if (a.get(mid) == b) {
                if ((mid == 0 || a.get(mid - 1) != b)) {
                    return mid;
                }
                if ((mid == a.size() - 1 || a.get(mid + 1) != b)) {
                    return mid;
                }
                if (isLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (isLeft) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return isLeft ? 0 : a.size() - 1;
    }


    private int getLeftEnd(List<Integer> a, int left, int right, int b) {
        while (left < right) {
            int mid = (left + right) / 2;
            if ((mid != 0 || a.get(mid - 1) != b) && a.get(mid) == b) {
                return mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int getRightEnd(List<Integer> a, int left, int right, int b) {
        while (left < right) {
            int mid = (left + right) / 2;
            if ((mid != a.size() - 1 || a.get(mid + 1) != b) && a.get(mid) == b) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int findRank(String a) {
        ArrayList<Character> str = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            str.add(a.charAt(i));
        }

        str.sort(new Comparator<Character>() {
            @Override
            public int compare(Character ch1, Character ch2) {
                return ch1 - ch2;
            }
        });

        long fact = getFact(a.length() - 1);
        long rank = 0;
        for (int i = 0; i < a.length() - 1; i++) {
            //System.out.println(" index " + str.indexOf(a.charAt(i)) +" fact " + fact +" rank " + rank);
            rank += str.indexOf(a.charAt(i)) * fact;
            str.remove((Object) a.charAt(i));
            fact /= str.size();
        }

        return (int) (rank + 1);
    }

    public long getFact(int size) {
        if (size == 0) {
            return 1;
        }
        long fact = 1;
        for (int i = 2; i <= size; i++) {
            fact *= i;
        }

        return fact;
    }

    public int reverse(int a) {
        boolean isNeg = false;
        if (a < 0) {
            isNeg = true;
            a = a * -1;
        }
        int rev = 0;
        for (; a > 0; a /= 10) {
            if ((long) rev * 10 > Integer.MAX_VALUE) {
                return 0;
            }
            rev *= 10;
            rev += a % 10;
        }

        return isNeg ? rev * -1 : rev;
    }

    public boolean isPower(int a) {
        if (a == 1) {
            return true;
        }
        int max = Integer.MAX_VALUE;
        for (int i = 32000; i <= a / i && i <= max / i; i++) {
            int j = i * i;
            for (; j <= a; ) {
                if (j == a) {
                    return true;
                }
                j *= i;

            }
        }

        return false;
    }


    public int repeatedNumber(final List<Integer> arr) {
        Integer a = null, b = null;
        int ca = 0, cb = 0;
        for (int ele : arr) {
            if (a == null || ele == a) {
                a = ele;
                ca++;
            } else if ((b == null || ele == b) && ele != a.intValue()) {
                b = ele;
                cb++;
            } else if ((ca == 0) && ele != b.intValue()) {
                a = ele;
                ca++;
            } else if ((b.intValue() == ele || cb == 0)) {
                b = ele;
                cb++;
            } else {
                ca--;
                cb--;
            }
        }

        ca = 0;
        cb = 0;
        for (int ele : arr) {
            if (ele == a) {
                ca++;
            } else if (ele == b) {
                cb++;
            }
        }
        if (ca > arr.size() / 3) {
            return a;
        }

        if (cb > arr.size() / 3) {
            return b;
        }

        return -1;
    }


    public int maximumGap(final List<Integer> a) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int ele : a) {
            min = Math.min(min, ele);
            max = Math.max(max, ele);
        }
        int n = a.size() - 1;
        double nominator = (double) (max - min + 1);
        double res = nominator / n;
        int gap = (int) Math.ceil(res);
        Bucket[] buckets = new Bucket[n];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }
        for (int ele : a) {
            int index = (ele - min) / gap;
            //    System.out.println("index "+index +" n"+n);
            if (index == 24) {
                System.out.println();
            }
            buckets[index].min = Math.min(buckets[index].min, ele);
            buckets[index].max = Math.max(buckets[index].max, ele);
        }

        int maxGap = 0;
        Bucket pre = null;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].min != Integer.MAX_VALUE) {
                if (pre == null) {
                    pre = buckets[i];
                    maxGap = Math.max(buckets[i].max - buckets[i].min, maxGap);
                } else {
                    maxGap = Math.max(buckets[i].min - pre.max, maxGap);
                    pre = buckets[i];
                }
            }
        }

        return maxGap;
    }


    class Bucket {
        int min;
        int max;

        Bucket() {
            min = Integer.MAX_VALUE;
            max = 0;

        }

    }


    public int maxArr(ArrayList<Integer> a) {
        if (a.size() < 2) {
            return 0;
        }

        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            indexes.add(Math.abs(a.get(i) - (i + 1)));
        }

        indexes.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer fir, Integer sec) {
                return fir - sec;
            }

        });

        for (int ele : indexes) {
            System.out.println(ele + " ");
        }

        return indexes.get(indexes.size() - 1) + indexes.get(indexes.size() - 2);

    }


    public String originalDigits(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
        }

        String[][] ar = {{"zero", "", "two", "", "four", "five", "six", "seven", "eigth", "nine"}, {"", "one", "", "three"}};

        String result = "";
        for (int k = 0; k < ar.length; k++) {
            for (int i = 0; i < ar[k].length; i++) {
                String num = ar[k][i];
                boolean isThere = true;
                while (isThere && !num.isEmpty()) {
                    for (int j = 0; j < num.length(); j++) {
                        if (!map.containsKey(num.charAt(j))) {
                            isThere = false;
                            break;
                        }
                    }
                    if (isThere) {
                        result += String.valueOf(i);
                        for (int j = 0; j < num.length(); j++) {
                            if (map.get(num.charAt(j)) == 1) {
                                map.remove(num.charAt(j));
                            } else {
                                map.put(num.charAt(j), map.get(num.charAt(j)) - 1);
                            }
                        }
                    }
                }
            }
        }

        char[] resChar = result.toCharArray();
        Arrays.sort(resChar);
        return new String(resChar);

    }

    */
/*public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval interval1, Interval interval2) {
                return interval1.start - interval2.start;
            }
        });

        int start = intervals[0].start;
        int end = intervals[0].end;
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
            } else {
                count++;
            }
        }

        return count;
    }*//*


    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

   */
/* public int[] findRightInterval(Interval[] intervals) {
        NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalMap.put(intervals[i].start, i);
        }

        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Map.Entry entry = intervalMap.ceilingEntry(intervals[i].end);
            result[i] = (entry != null ? (int) entry.getValue() : -1);
        }

        return result;
    }*//*


    public int arrangeCoins(int n) {
        long sqr = (1 + (8 * (long) n));
        int root = (int) Math.sqrt(sqr);
        if (root % 2 == 1 && sqr != (root * root)) {
            root++;
        }
        root = root / 2;
        int sum = (root * (root + 1)) / 2;
        return (sum - n) == 0 ? root : root - 1;
    }

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map.put(p.charAt(i), map.containsKey(p.charAt(i)) ? map.get(p.charAt(i)) + 1 : 1);
        }
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> mapS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            mapS.put(s.charAt(i), mapS.containsKey(s.charAt(i)) ? mapS.get(s.charAt(i)) + 1 : 1);
            if (i >= p.length() - 1) {
                boolean isAna = true;
                for (char ch : mapS.keySet()) {
                    if (!map.containsKey(ch) || (int) mapS.get(ch) != (int) map.get(ch)) {
                        isAna = false;
                        break;
                    }
                }
                if (isAna) {
                    res.add(i - p.length() + 1);
                }
                mapS.put(s.charAt(i - p.length() + 1), mapS.get(s.charAt(i - p.length() + 1)) - 1);
                if (mapS.get(s.charAt(i - p.length() + 1)) == 0) {
                    mapS.remove(s.charAt(i - p.length() + 1));
                }
            }

        }
        return res;
    }

    public List<String> readBinaryWatch(int num) {
        ArrayList<String> res = new ArrayList<>();
        int[] ar = new int[10];
        Arrays.fill(ar, 0);
        read(ar, num, res, 0);
        return res;
    }

    private void read(int[] ar, int num, ArrayList<String> res, int index) {
        if (num == 0) {
            String number = eval(ar);
            String[] cRes = number.split(":");
            if (Integer.parseInt(cRes[0]) < 13 && Integer.parseInt(cRes[1]) < 60) {
                res.add(number);
            }
            return;
        }

        if (ar.length == index) {
            return;
        }

        read(ar, num, res, index + 1);
        ar[index] = 1;
        read(ar, num - 1, res, index + 1);
        ar[index] = 0;
    }

    private String eval(int[] ar) {
        int val = 0;
        int pow = 1;
        String res = "";
        for (int i = 9; i >= 6; i--) {
            val += ar[i] * pow;
            pow *= 2;
        }
        res = String.valueOf(val);
        val = 0;
        pow = 1;
        for (int i = 5; i >= 0; i--) {
            val += ar[i] * pow;
            pow *= 2;
        }
        String sVal = String.valueOf(val);
        res += ":" + (sVal.length() == 2 ? sVal : "0" + sVal);
        return res;
    }

    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.containsKey(s.charAt(i)) ? map.get(s.charAt(i)) + 1 : 1);
        }

        int len = 0;
        int count = 0;
        for (char key : map.keySet()) {
            len += map.get(key);
            if (map.get(key) % 2 == 1) {
                count++;
            }
        }

        return len - (count == 0 ? 0 : count - 1);
    }



   */
/* public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0){
            return people;
        }

        List<int[]> list = new ArrayList<>();
        list.add(people[0]);
        for (int i = 1; i < people.length; i++){
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j)[1] == people[i][0]){
                    if (list.get(j)[0] >= people[j][0]){
                        list.add(j - 1, people[j - 1]);
                    }
                }
                if (list.get(j)[0] >= people[i][0]){

                }
            }
        }
        return ma
    }*//*


    public int thirdMax(int[] nums) {
        if (nums.length < 3) {
            int max = 0;
            for (int a : nums) {
                max = Math.max(a, max);
            }

            return max;
        }


        int a1 = Integer.MIN_VALUE, a2 = Integer.MIN_VALUE, a3 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (a1 < nums[i]) {
                a3 = a2;
                a2 = a1;
                a1 = nums[i];
            } else if (a2 < nums[i]) {
                a3 = a2;
                a2 = nums[i];
            } else if (a3 < nums[i]) {
                a3 = nums[i];
            }
        }
        System.out.println(a1 + " " + a2 + " " + a3);
        if (a3 != Integer.MIN_VALUE) {
            return a3;
        }
        if (a2 != Integer.MIN_VALUE) {
            return a2;
        }

        return a1;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }

        if (sum % 2 == 1) {
            return false;
        }

        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]]) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][dp[0].length - 1]) {
                    return true;
                }
            }
        }

        return false;
    }

   */
/* public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<Skylines> ar = getSkyline(buildings, 0, buildings.length - 1);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < ar.size(); i++) {
            res.add(new int[]{ar.get(i).left, ar.get(i).height});
        }
        return res;
    }

    private ArrayList<Skylines> getSkyline(int[][] buildings, int l, int h) {
        if (h == l) {
            ArrayList<Skylines> ar = new ArrayList<>();
            add(ar, new Skylines(buildings[l][0], buildings[l][2]));
            add(ar, new Skylines(buildings[l][1], 0));
            return ar;
        }

        int mid = l + (h - l) / 2;
        ArrayList<Skylines> left = getSkyline(buildings, l, mid);
        ArrayList<Skylines> right = getSkyline(buildings, mid + 1, h);
        ArrayList<Skylines> res = mergeSkylines(left, right);
        return res;
    }

    private List<int[]> mergeSkylines(List<int[]> skylineListLower, List<int[]> skylineListHigher)
    {
        int h1 = 0, h2 = 0;
        int newIndex = 0;
        List<int[]> skylineMerged = new ArrayList<int[]>();;

        while (true)
        {
            if (skylineListLower.isEmpty() || skylineListHigher.isEmpty())
            {
                break;
            }

            // first key points from both the skylines
            int [] stripe1 = skylineListLower.get(0);
            int [] stripe2 = skylineListHigher.get(0);

            // 0: 'x' co-ordinate, 1: height
            int [] mergedStripe = new int[2];

            // comparing 'x' co-ordinates of current key points of skyline-1 and skyline-2
            if (stripe1[0] < stripe2[0]) // key point from skyline-1 is chosen
            {
                mergedStripe[0] = stripe1[0];
                mergedStripe[1] = stripe1[1];

                // if 'y' co-ordinate for key point from skyline-1 is less than last seen height of skyline-2
                // then we need to update merged key point's 'y' co-ordinate to last seen height of skyline-2
                if (stripe1[1] < h2)
                {
                    mergedStripe[1] = h2;
                }

                // update the last seen height for skyline-1
                // note that last seen height for skyline-2 is not updated
                h1 = stripe1[1];

                // move to next key point for this skyline
                skylineListLower.remove(0);
            }
            else if (stripe2[0] < stripe1[0])
            {
                mergedStripe[0] = stripe2[0];
                mergedStripe[1] = stripe2[1];

                if (stripe2[1] < h1)
                {
                    mergedStripe[1] = h1;
                }

                // update the last seen height of skyline-2
                // note that last seen height of skyline-
                h2 = stripe2[1];

                skylineListHigher.remove(0);
            }

            // if 'x' co-ordinates of key points for both skylines are same
            // then choose the key point with greater 'y' value
            // advance key points for both and hence update last seen height for both
            else
            {
                mergedStripe[0] = stripe2[0];
                mergedStripe[1] = greater(stripe1[1], stripe2[1]);

                h1 = stripe1[1];
                h2 = stripe2[1];

                skylineListLower.remove(0);
                skylineListHigher.remove(0);
            }

            skylineMerged.add(mergedStripe);
        }

        if (skylineListLower.isEmpty())
        {
            while (!skylineListHigher.isEmpty())
            {
                skylineMerged.add(skylineListHigher.remove(0));
            }
        }
        else
        {
            while (!skylineListLower.isEmpty())
            {
                skylineMerged.add(skylineListLower.remove(0));
            }
        }

        // remove redundant key points from merged skyline
        int current = 0;
        while (current < skylineMerged.size())
        {
            boolean dupeFound = true;
            int i = current + 1;
            while ((i < skylineMerged.size()) &&  dupeFound)
            {
                if (skylineMerged.get(current)[1] == skylineMerged.get(i)[1])
                {
                    dupeFound = true;
                    skylineMerged.remove(i);
                }
                else
                {
                    dupeFound = false;
                }
            }
            current += 1;
        }

        return skylineMerged;
    }


    private  void add(ArrayList<Skylines> ar, Skylines skylines) {
        if (ar.size() == 0 || (skylines.height != ar.get(ar.size() - 1).height)) {
            if (ar.size() != 0 && skylines.left == ar.get(ar.size() - 1).left) {
                ar.get(ar.size() - 1).height = Math.max(skylines.height, ar.get(ar.size() - 1).height);
            } else {
                ar.add(skylines);
            }
        }
    }

    class Skylines {
        int left;
        int height;

        public Skylines(int left, int height) {
            this.left = left;
            this.height = height;
        }
    }*//*



    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode f = head, s = head, pre = null;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }
        if (f != null) {
            s = s.next;
        }
        return isPalindrome(new Pal(head), s).isTrue;
    }

    private Pal isPalindrome(Pal pal, ListNode last) {
        if (last == null) {
            return new Pal(pal.first, true);
        }

        Pal ret = isPalindrome(pal, last.next);

        if (ret.isTrue && ret.first.val == last.val) {
            return new Pal(ret.first.next, true);
        }

        pal.isTrue = false;
        return pal;

    }

    class Pal {
        ListNode first;
        ListNode mid;

        boolean isTrue;

        public Pal(ListNode first) {
            this.first = first;
        }

        public Pal(ListNode first, boolean isTrue) {
            this.first = first;
            this.isTrue = isTrue;
        }

    }

    private static TreeNode root1 = null;
    private static TreeNode root2 = null;
    private static TreeNode cur1 = null;
    private static TreeNode cur2 = null;

    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        cur1 = cur2 = root1 = root2 = root;
        next1(stack1);
        next2(stack2);
        TreeNode pre1 = null, pre2 = null;
        if (cur1.val > cur2.val) {
            swap(cur1, cur2);
            return;
        }
        while (true) {
            if (pre1 == null || pre1.val <= cur1.val) {
                pre1 = cur1;
                next1(stack1);
            }
            if (pre2 == null || pre2.val >= cur2.val) {
                pre2 = cur2;
                next2(stack2);
            }
            if (cur1 == null || cur2 == null) {
                return;
            }
            if (pre1.val > cur1.val && pre2.val < cur2.val) {
                swap(pre1, pre2);
                return;
            }
            if (cur1.val == cur2.val) {
                if (cur1.val < pre1.val) {
                    swap(cur1, pre1);
                } else {
                    swap(cur2, pre2);
                }
                return;
            }

        }
    }

    private void swap(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private TreeNode next1(Stack<TreeNode> stack1) {
        while (hasNext1(stack1)) {
            if (root1 == null) {
                root1 = stack1.pop();
                cur1 = root1;
                root1 = root1.right;
                break;
            } else {
                stack1.add(root1);
                root1 = root1.left;
            }
        }
        return cur1;
    }

    private boolean hasNext1(Stack<TreeNode> stack) {
        return root1 != null || !stack.isEmpty();
    }

    private boolean hasNext2(Stack<TreeNode> stack) {
        return root2 != null || !stack.isEmpty();
    }

    private TreeNode next2(Stack<TreeNode> stack2) {
        while (hasNext2(stack2)) {
            if (root2 == null) {
                root2 = stack2.pop();
                cur2 = root2;
                root2 = root2.left;
                break;
            } else {
                stack2.add(root2);
                root2 = root2.right;
            }
        }
        return cur2;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ele = root;
        List<Integer> res = new ArrayList<>();
        while (ele != null || !stack.isEmpty()) {
            if (ele == null) {
                ele = stack.pop();
                res.add(ele.val);
                ele = ele.right;
            } else {
                stack.push(ele);
                ele = ele.left;
            }

        }
        return res;
    }

    private static class Trie {
        Map<Character, Trie> map;
        int count;

        public Trie() {
            map = new HashMap<>();
            count = 0;
        }

        public void add(String contact) {
            count++;
            if (contact == null || contact.isEmpty()) {
                return;
            }

            char ch = contact.charAt(0);
            Trie next = null;
            if (map.containsKey(ch)) {
                next = map.get(ch);
            } else {
                next = new Trie();
                map.put(ch, next);
            }
            next.add(contact.substring(1));
        }

        public int find(String contact) {
            if (contact == null || contact.isEmpty()) {
                return count;
            }

            char ch = contact.charAt(0);
            if (!map.containsKey(ch)) {
                return 0;
            }


            return map.get(ch).find(contact.substring(1));
        }
    }


   */
/* public void nextGreatest(int num){
        char[] nums = String.valueOf(num).toCharArray();
        int i;
        for (i = nums.length - 1; i > 0;i--){
            if (nums.get(i) > nums[i - 1]){
                break;
            }
        }

        if (i == 0){
            System.out.println("no greater num");
            return;
        }

        int j;
        int minIndex = i;
        for (j = i; j < nums.length; j++) {
            if (nums[i - 1] < nums.get(j)){
                if (nums[minIndex]  > nums.get(j)){
                    minIndex = j;
                }
            }
        }

        swap(nums, i - 1, minIndex);
        Arrays.sort(nums,i,nums.length);
        System.out.println(new String(nums));
    }

    public String reverseWords(String s) {
        char[] array = s.toCharArray();
        reverse(array, 0, array.length);
        int i, start;
        for(i = 0, start = 0; i < array.length; i++){
            if (array.get(i) == ' '){
                reverse(array, start, i);
                start = i + 1;
                i++;
            }
        }

        reverse(array, start, i);
        return new String(array);

    }

    private void reverse(char[] array, int i, int j){
        j--;
        for (; i < j; i++, j--){
            swap(array, i, j);
        }
    }

    private void swap(char[] array, int i ,int j){
        //System.out.println("i "+ array.get(i) +" j" + array.get(j));
        char temp = array.get(i);
        array.get(i) = array.get(j);
        array.get(j) = temp;
    }

    public int rob(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        map.keySet();
        return rob(nums,0, map);
    }

    private int rob(int[] nums, int i, Map<Integer, Integer> map) {
        if (i >= nums.length){
            return 0;
        }

        if (map.containsKey(i)){
            return map.get(i);
        }

        int without = rob(nums,i + 1, map);
        int with = nums.get(i);
        with += rob(nums, i + 2, map);
        map.put(i,Math.max(with, without));
        return map.get(i);
    }


    public int maxProduct(String[] words) {
        int maxLength = 0;
        int[] arr = new int[words.length];
        for (int j = 0; j < words.length; j++) {
            for (int i = 0; i < words.get(j).length(); i++) {
                arr.get(j) |= (1 << words.get(j).charAt(i) - 'a');
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((arr.get(i) & arr.get(j)) == 0) {
                    maxLength = Math.max(maxLength, words.get(i).length() * words.get(j).length());
                }
            }
        }

        return maxLength;
    }

    private boolean contains(Set<Character> jWord, Set<Character> iWord) {
        for (Character ch : jWord) {
            if (iWord.contains(ch)) {
                return true;
            }

        }
        return false;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums.length == 0) {
            return null;
        }
        String s = "";
        Arrays.sort(nums);
        int previous = nums[0];
        int count = 1;

        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (previous == nums.get(i)) {
                count++;
            } else {
                pairs.add(new Pair(previous, count));
                count = 1;
                previous = nums.get(i);
            }
        }

        pairs.add(new Pair(previous, count));
        Collections.sort(pairs, (p1, p2) -> Integer.compare(p1.count, p2.count) * -1);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pairs.get(i).number);
        }

        return result;
    }

    public class Pair {
        int number;
        int count;

        public Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
*/


}
