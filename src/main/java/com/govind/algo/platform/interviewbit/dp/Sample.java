package com.govind.algo.platform.interviewbit.dp;

import java.util.*;

/**
 * Created by 609600403 on 04/05/2016.
 */
public class Sample {
    public static void main(String[] args) {
        System.out.println((long )(Integer.MAX_VALUE * 2));

    }

    public void printEulerTotientFunctionLessThanOrEqualToN(int n){
        int[] phi = new int[n + 1];
        for (int p = 0; p <= n; p++) {
            phi[p] = p;
        }
    for (int p = 2; p <= n; p++) {
            if (phi[p] == p){
                phi[p] = p - 1;
                for (int j = 2 * p; j <= n; j += p) {
                    phi[j] = (phi[j] / p) * (p - 1);
                }
            }

        }

        for (int i = 1; i < phi.length; i++) {
            System.out.println(phi[i]);
        }

    }

    public static String randomString(int i)
    {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int k = ran.nextInt(27);
            if (k == 0)
                break;
            System.out.println(k);
            sb.append((char)('`' + k));
        }

        return sb.toString();
    }
    
    public int cnttrue(String a){
        return cnttrue(a, new HashMap<>());
    }

    public int cnttrue(String a, Map<String, Integer> dp) {
        if (a.length() == 1 && a.charAt(0) == 'T'){
            return 1;
        }

        if (dp.containsKey(a)){
           // return dp.get(a);
        }
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '&' ){
                count += cnttrue(a.substring(0, i),dp) * cnttrue(a.substring(i + 1, a.length()),dp);
            }
            else if (a.charAt(i) == '|'){
                count += cnttrue(a.substring(0, i),dp) * cnttrue(a.substring(i + 1, a.length()),dp);
                count += cnttrue(a.substring(0, i),dp) * cntFalse(a.substring(i + 1, a.length()),dp);
                count += cntFalse(a.substring(0, i),dp) * cnttrue(a.substring(i + 1, a.length()),dp);
            }

            else if (a.charAt(i) == '^'){
                count += cnttrue(a.substring(0, i),dp) * cntFalse(a.substring(i + 1, a.length()),dp);
                count += cntFalse(a.substring(0, i),dp) * cnttrue(a.substring(i + 1, a.length()),dp);
            }
            dp.put(a, count);
        }
        
        return  count;
    }
     

    private int cntFalse(String a, Map<String,Integer> dp) {
        int count = 0;
        if (a.length() == 1 && a.charAt(0) == 'F'){
            return 1;
        }

        if (dp.containsKey(a)){
            //return dp.get(a);
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '|' ){
                count += cntFalse(a.substring(0, i),dp) * cntFalse(a.substring(i + 1, a.length()),dp);
            }
            else if (a.charAt(i) == '&'){
                count += cntFalse(a.substring(0, i),dp) * cntFalse(a.substring(i + 1, a.length()),dp);
                count += cnttrue(a.substring(0, i),dp) * cntFalse(a.substring(i + 1, a.length()),dp);
                count += cntFalse(a.substring(0, i),dp) * cnttrue(a.substring(i + 1, a.length()),dp);
            }

            else if (a.charAt(i) == '^'){
                count += cntFalse(a.substring(0, i),dp) * cntFalse(a.substring(i + 1, a.length()),dp);
                count += cnttrue(a.substring(0, i),dp) * cnttrue(a.substring(i + 1, a.length()),dp);
            }
            dp.put(a, count);
        }

        return  count;
    }

    public void subMatrix(){
        int[][] a = {
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        };

        int[][] dp = new int[a.length][a[0].length];
        int maxArea = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != 1){
                    continue;
                }

                dp[i][j] = j > 0? dp[i][j - 1]  + 1: 1;
                int column = 1;
                maxArea = Math.max(maxArea, dp[i][j]);
                int iBack = i - 1;
                int minRow = dp[i][j];
                while (iBack >= 0 && a[iBack][j] == 1){
                    column++;
                    minRow = Math.min(minRow, dp[iBack][j]);
                    maxArea = Math.max(maxArea, minRow * column);
                    iBack--;
                }
            }
        }

        System.out.println(maxArea);
    }

    private void iterative(String s) {
        int fact = fact(s.length());
        char[] array = s.toCharArray();
        int l = 1;
        int m = 1;
        for (int i = 0; i < fact; i++) {
            int k = 0;
            while (k < fact / s.length()) {
                while (l != s.length() - 1) {
                    System.out.println(new String(array));
                    swap(array, l, l + 1);
                    l++;
                    i++;
                    k++;
                }
                l = 1;
            }
            if (m != s.length())
                swap(array, 0, m++);
        }
    }

    public int numDistinct(String S, String T) {
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i <= S.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                dp[i][j] = dp[i - 1][j] + (S.charAt(i - 1) == T.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
                if (dp[i][j] == 4) {
                    System.out.println();
                }
            }
        }

        return dp[S.length()][T.length()];
    }

    private void swap(char[] array, int l, int i) {
        char tem = array[i];
        array[i] = array[l];
        array[l] = tem;
    }

    private int fact(int length) {
        if (length == 0) {
            return 1;
        }

        int fact = 1;
        for (int i = 2; i <= length; i++) {
            fact *= i;
        }

        return fact;
    }


    public int anytwo(String a) {
        int[][] dp = new int[a.length()][a.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < a.length(); j++) {
                if (i == j || a.charAt(i) != a.charAt(j)) {
                    dp[i][j] = Math.max(i > 0 ? dp[i - 1][j] : 0, j > 0 ? dp[i][j - 1] : 0);
                } else if (a.charAt(i) == a.charAt(j)) {
                    dp[i][j] = i > 0 && j > 0 ? dp[i - 1][j - 1] + 1 : 1;
                }

            }
        }

        return dp[a.length() - 1][a.length() - 1];
    }

    public int longestCommonSubsequece(String a, String b) {
        int[][] dp = new int[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {

                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = i > 0 && j > 0 ? dp[i - 1][j - 1] + 1 : 1;
                } else {
                    dp[i][j] = Math.max(i > 0 ? dp[i - 1][j] : 0, j > 0 ? dp[i][j - 1] : 0);
                }

            }
        }

        return dp[a.length() - 1][b.length() - 1];
    }


    public int jump(ArrayList<Integer> a) {
        int reach = 0;
        int lastReach = 0;
        int step = 0;
        int i = 0;
        for (; i < a.size() && i <= reach; i++) {
            if (i > lastReach) {
                lastReach = reach;
                step++;

            }

            reach = Math.max(reach, a.get(i) + i);
        }

        if (i != a.size()) {
            return -1;
        }

        return step;
    }

    public int maxProduct(final List<Integer> a) {
        int maxPos = 1;
        int maxNeg = 1;
        int maxProduct = Integer.MIN_VALUE;
        a.stream().max(Integer::max).get();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > 0) {
                maxPos *= a.get(i);
                maxNeg *= a.get(i);
            }

            if (a.get(i) == 0) {
                maxPos = 1;
                maxNeg = 1;
            }

            if (i == 300) {
                System.out.println();
            }

            if (a.get(i) < 0) {
                int temp = maxPos;
                maxPos = Math.max(maxPos, maxNeg * a.get(i));
                maxNeg = temp * a.get(i);
            }

            maxProduct = Math.max(maxProduct, maxPos);
            if (maxPos == 4) {
                System.out.println();
            }
        }


        if (maxProduct == 1) {
            maxProduct = Integer.MIN_VALUE;
            for (int i = 0; i < a.size(); i++) {
                maxProduct = Math.max(maxProduct, a.get(i));
            }
        }

        return maxProduct;
    }

    public int numDecodings(String a) {
        Map<String, Integer> map = new HashMap<>();
        return decode(a, map);
    }

    private int decode(String number, Map<String, Integer> map) {
        if (number.isEmpty()) {
            return 1;
        }


        if (map.containsKey(number)) {
            return map.get(number);
        }

        int count = 0;
        int cNumber = number.charAt(0) - '0';
        if (cNumber != 0) {
            count += decode(number.substring(1), map);
        }

        if (number.length() > 1) {
            int reminder = number.charAt(0) - '0';
            reminder = reminder * 10 + (number.charAt(1) - '0');
            if (reminder < 27 && reminder > 9) {
                count += decode(number.substring(2), map);
            }
        }

        map.put(number, count);
        return count;
    }

    public int maxcoin(ArrayList<Integer> a) {
        int[][] dp = new int[a.size()][a.size()];
        for (int gap = 0; gap < a.size(); gap++) {
            for (int i = 0, j = gap; i < a.size() - gap; i++, j++) {
                int x = (gap > 1) ? dp[i + 2][j] : 0;
                int y = (gap > 1) ? dp[i + 1][j - 1] : 0;
                int z = (gap > 1) ? dp[i][j - 1] : 0;
                dp[i][j] = Math.max(a.get(i) + Math.min(x, y), a.get(j) + Math.min(y, z));
            }
        }

        return dp[0][a.size() - 1];
    }

    public int isInterleave(String a, String b, String c) {
        return isInterleaveB(a, b, c) == true ? 1 : 0;
    }

    private boolean isInterleaveB(String a, String b, String c) {
        if (c.isEmpty()) {
            return true;
        }

        if (a.isEmpty() && b.isEmpty()) {
            return false;
        }

        boolean isInterleve = false;
        if (!a.isEmpty() && a.charAt(0) == c.charAt(0)) {
            isInterleve |= isInterleaveB(a.substring(1), b, c.substring(1));
        }

        if (!b.isEmpty() && b.charAt(0) == c.charAt(0)) {
            isInterleve |= isInterleaveB(a, b.substring(1), c.substring(1));
        }

        return isInterleve;
    }


    public int minDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= b.length(); i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }

        for (int i = 1; i <= a.length(); i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        System.out.print("    ");
        for (int i = 0; i < b.length(); i++) {
            System.out.print(b.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i <= a.length(); i++) {
            System.out.print(i == 0 ? "  " : a.charAt(i - 1) + " ");
            for (int j = 0; j <= b.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[a.length()][b.length()];
    }
}
