package com.govind.algo.backtraking;

import com.govind.util.ArrayListUtils;

import java.util.*;

/**
 * Created by govindp on 10/3/2015.
 */
public class Sample {

    int i, j;

    public static void main(String[] args) {
        ArrayList<Character> arrayList1 = new ArrayListUtils<Character>().getArrayList(new Character[]{'3', '.', '.', '5', '7', '8', '4', '9', '2'});
        ArrayList<Character> arrayList2 = new ArrayListUtils<Character>().getArrayList(new Character[]{'5', '2', '.', '1', '3', '4', '7', '6', '8'});
        ArrayList<Character> arrayList3 = new ArrayListUtils<Character>().getArrayList(new Character[]{'4', '.', '7', '6', '2', '9', '5', '3', '1'});
        ArrayList<Character> arrayList4 = new ArrayListUtils<Character>().getArrayList(new Character[]{'2', '6', '3', '4', '1', '5', '9', '8', '7'});
        ArrayList<Character> arrayList5 = new ArrayListUtils<Character>().getArrayList(new Character[]{'9', '7', '4', '8', '6', '3', '1', '2', '5'});
        ArrayList<Character> arrayList6 = new ArrayListUtils<Character>().getArrayList(new Character[]{'8', '5', '1', '7', '9', '2', '6', '4', '3'});
        ArrayList<Character> arrayList7 = new ArrayListUtils<Character>().getArrayList(new Character[]{'1', '3', '8', '9', '4', '7', '2', '5', '6'});
        ArrayList<Character> arrayList8 = new ArrayListUtils<Character>().getArrayList(new Character[]{'6', '9', '2', '3', '5', '1', '8', '7', '4'});
        ArrayList<Character> arrayList9 = new ArrayListUtils<Character>().getArrayList(new Character[]{'7', '4', '5', '2', '8', '6', '3', '1', '9'});
        ArrayList<String> result = new ArrayListUtils<String>().getArrayList(new String[]{"......2..",
                ".9.7...3.",
                "......8..",
                ".........",
                ".......5.",
                "5........",
                "2........",
                ".6.......",
                "93.....5."});

        List<Integer> a = new ArrayListUtils<Integer>().getArrayList(new Integer[]{100, 4, 200, 1, 3, 2});
        System.out.println(a);
        Collections.sort(a);


        for (int i = 1; i < 10; i++) {
            System.out.println(jumbleNumber(i,105));
        }
    }




    public static Set<Integer> jumbleNumber(int i, int number) {
        Set<Integer> set = new HashSet<>();
        if (number < 0) {
            return new HashSet<>();
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while (!queue.isEmpty() && queue.peek() < number) {
            int first = queue.poll();
            set.add(first);
            int lastDigit = first % 10;

            if (lastDigit == 0){
                queue.add( (first * 10 ) + (lastDigit + 1));
            }

            else if (lastDigit == 9){
                queue.add( (first * 10 ) + (lastDigit - 1));
            }

            else {
                queue.add(first * 10 + lastDigit + 1);
                queue.add(first * 10 + lastDigit - 1);
            }
        }

        return set;
    }

    public static int longestConsecutive(final List<Integer> a) {
        Set<Integer> set = new HashSet<>();
        for (int number : a) {
            set.add(number);
        }
        int max = -1;
        for (int i = 0; i < a.size(); i++) {
            int count = 1;
            int left = a.get(i) - 1;
            int right = a.get(i) + 1;
            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }

            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }

            max = Math.max(max, count);
        }
        return max;
    }


    public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
        Set<ArrayList<Integer>> set = new HashSet<>();
        permute(a, 0, set);

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.addAll(set);
        System.out.println();
        Collections.sort(arrayLists, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int size = Math.min(o1.size(), o2.size());

                for (int i = 0; i < size; i++) {
                    int compare = Integer.compare(o1.get(i), o2.get(i));
                    if (compare != 0) {
                        return compare;
                    }
                }
                return o1.size() > o2.size()
                        ? o1.size()
                        : o2.size();
            }
        });
        return arrayLists;
    }

    private static void permute(ArrayList<Integer> a, int i, Set<ArrayList<Integer>> set) {
        if (i == a.size()) {
            set.add(new ArrayList<>(a));
            return;
        }

        for (int j = i; j < a.size(); j++) {
            swap(a, i, j);
            permute(a, i + 1, set);
            swap(a, i, j);
        }
    }

    public static int isValidSudoku(final List<String> arrayLists) {
        HashMap<Integer, List<Column>> hashMap = new HashMap<>();
        for (int i = 0; i < arrayLists.size(); i++) {
            for (int j = 0; j < arrayLists.size(); j++) {
                if (arrayLists.get(i).charAt(j) == '.') {
                    continue;
                }
                if (hashMap.containsKey(Integer.valueOf(arrayLists.get(i).charAt(j))) && isNotValid(arrayLists, hashMap, i, j)) {
                    return 0;
                }
                List<Column> list = new ArrayList<>();
                if (hashMap.containsKey(Integer.valueOf(arrayLists.get(i).charAt(j)))) {
                    list = hashMap.get(Integer.valueOf(arrayLists.get(i).charAt(j)));
                }
                list.add(new Column(i, j));

                hashMap.put(Integer.valueOf(arrayLists.get(i).charAt(j)), list);
            }
        }
        return 1;
    }

    private static Boolean isNotValid(List<String> arrayLists, HashMap<Integer, List<Column>> hashMap, int i, int j) {
        List<Column> columns = hashMap.get(Integer.valueOf(arrayLists.get(i).charAt(j)));
        for (Column column : columns) {
            if (column.index1 == i
                    || column.index2 == j
                    || (i - i % 3 == column.index1 - column.index1 % 3
                    && j - j % 3 == column.index2 - column.index2 % 3)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> twoSum(final List<Integer> a, int b) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int index1 = Integer.MAX_VALUE;
        int index2 = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            if (map.containsKey(a.get(i))) {
                index1 = map.get(a.get(i));
                index2 = i;
                break;
            }
            if (!map.containsKey(b - a.get(i))) {
                map.put(b - a.get(i), i);

            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        if (index1 == Integer.MAX_VALUE) {
            return result;

        }
        result.add(index1 + 1);
        result.add(index2 + 1);
        return result;
    }

    public static boolean sudoku(ArrayList<ArrayList<Character>> array) {
        if (isFull(array)) {
            return true;
        }

        int row = 0, col = 0;
        int flag = 0;
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size(); j++) {
                if (array.get(i).get(j) == '.') {
                    row = i;
                    col = j;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafeSudoku(array, row, col, num)) {
                array.get(row).set(col, (char) (48 + num));
                if (sudoku(array)) {
                    return true;
                }

                array.get(row).set(col, '.');
            }
        }

        return false;
    }

    private static boolean isSafeSudoku(ArrayList<ArrayList<Character>> array, int row, int col, int i) {
        return isSafeRow(array, row, col, i)
                && isSafeColumn(array, row, col, i)
                && isSafeBox(array, row - row % 3, col - col % 3, i);

    }

    private static boolean isSafeRow(ArrayList<ArrayList<Character>> array, int row, int col, int number) {
        for (int j = 0; j < array.size(); j++) {
            if (array.get(row).get(j) == (48 + number)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSafeColumn(ArrayList<ArrayList<Character>> array, int row, int col, int number) {
        for (int j = 0; j < array.size(); j++) {
            if (array.get(j).get(col) == (48 + number)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSafeBox(ArrayList<ArrayList<Character>> array, int row, int col, int number) {
        for (int j = row; j < row + 3; j++) {
            for (int i = col; i < col + 3; i++) {
                if (array.get(j).get(i) == (48 + number)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isFull(ArrayList<ArrayList<Character>> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size(); j++) {
                if (array.get(i).get(j) == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<String>> solveNQueens(int a) {
        Integer[][] array = new Integer[a][a];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = 0;
            }
        }
        ArrayList<ArrayList<String>> result = new ArrayList<>();


        Nqueen(array, 0, result);
        Collections.sort(result, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o2.get(0).compareTo(o1.get(0));
            }
        });

        return result;
    }

    private static void Nqueen(Integer[][] array, int column, ArrayList<ArrayList<String>> arrayList) {
        if (column >= array.length) {
            ArrayList<String> currentResult = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                String result = "";
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] == 1) {
                        result += "Q";
                    } else {
                        result += ".";
                    }
                }

                currentResult.add(result);
            }
            arrayList.add(currentResult);

        } else {
            for (int i = 0; i < array.length; i++) {
                if (isSafe(array, i, column)) {
                    array[i][column] = 1;
                    Nqueen(array, column + 1, arrayList);
                    array[i][column] = 0;
                }

            }
        }
    }

    private static boolean isSafe(Integer[][] array, int row, int column) {
        for (int j = 0; j < column; j++) {
            if (array[row][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (array[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = column; i < array.length && j >= 0; i++, j--) {
            if (array[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static String getPermutation(int n, int k) {
        k--;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arrayList.add(i);
        }

        String result = "";
        int index = 0;
        while (arrayList.size() != 0 && k <= fact(arrayList.size())) {
            int fact = fact(arrayList.size() - 1);
            int currentIndex = k / fact;
            k %= fact;
            result += arrayList.get(currentIndex);
            arrayList.remove(currentIndex);
        }
        return result;
    }

    private static int fact(int i) {
        if (i == 0) {
            return 1;
        }
        return i * fact(i - 1);
    }

    public static ArrayList<Integer> grayCode(int a) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("0");
        arrayList.add("1");
        for (int i = 2; i < 1 << a; i <<= 1) {
            for (int j = i; j > 0; j--) {
                arrayList.add(arrayList.get(j - 1));
            }

            for (int j = 0; j < i; j++) {
                arrayList.set(j, "0" + arrayList.get(j));
            }

            for (int j = i; j < 2 * i; j++) {
                arrayList.set(j, "1" + arrayList.get(j));
            }
        }
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            int number = 0;
            int base = 1;
            for (int j = arrayList.get(i).length() - 1; j >= 0; j--) {
                number += Integer.parseInt(String.valueOf(arrayList.get(i).charAt(j))) * base;
                base *= 2;
            }
            arrayList1.add(number);
        }

        return arrayList1;
    }

    public static String permute(int n, int k) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            a.add(i);
        }
        permute(a, 0, arrayLists);
        Collections.sort(arrayLists, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int min = Math.min(o1.size(), o2.size());
                for (int i = 0; i < min; i++) {
                    int comparator = Integer.compare(o1.get(i), o2.get(i));
                    if (comparator != 0) {
                        return comparator;
                    }
                }
                return o1.size() > o2.size() ? 1 : -1;

            }
        });
        String result = "";
        if (arrayLists.size() >= k) {
            for (int i = 0; i < arrayLists.get(k - 1).size(); i++) {
                result += arrayLists.get(k - 1).get(i);
            }
        }
        return result;
    }

    private static void permute(ArrayList<Integer> a, int i, ArrayList<ArrayList<Integer>> arrayLists) {
        if (i == a.size()) {
            arrayLists.add(new ArrayList<>(a));
            return;
        }

        for (int j = i; j < a.size(); j++) {
            swap(a, j, i);
            permute(a, i + 1, arrayLists);
            swap(a, i, j);
        }
    }

    private static void swap(ArrayList<Integer> a, int j, int i) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }


    public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> a) {
        Set<ArrayList<Integer>> result = new HashSet<>();

        int size = 1 << a.size();

        for (int i = 0; i < size; i++) {
            int k = i;
            ArrayList<Integer> subset = new ArrayList<>();
            int count = 0;
            while (k > 0) {
                int currentIndex = k & 1;
                k >>= 1;
                if (currentIndex == 1) {
                    subset.add(a.get(count));
                }
                count++;

            }
            Collections.sort(subset);
            result.add(subset);
        }

        ArrayList<ArrayList<Integer>> power = new ArrayList<>();
        power.addAll(result);

        Collections.sort(power, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int min = Math.min(o1.size(), o2.size());
                for (int i = 0; i < min; i++) {
                    int comparator = Integer.compare(o1.get(i), o2.get(i));
                    if (comparator != 0) {
                        return comparator;
                    }
                }
                return o1.size() > o2.size() ? 1 : -1;

            }
        });
        return power;
    }

    ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            current.add(i);
        }

        combineHelper(n, k, ans, new ArrayList<>());
        //Collections.sort(ans);
        return ans;
    }

    static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allsubsets;
        if (set.size() == index) { // Base case - add empty set
            allsubsets = new ArrayList<ArrayList<Integer>>();
            allsubsets.add(new ArrayList<Integer>()); // Empty set

        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moresubsets =
                    new ArrayList<ArrayList<Integer>>();
            for (ArrayList<Integer> subset : allsubsets) {
                ArrayList<Integer> newsubset = new ArrayList<Integer>();
                newsubset.addAll(subset); //
                newsubset.add(item);
                moresubsets.add(newsubset);
            }
            allsubsets.addAll(moresubsets);
        }
        return allsubsets;
    }

    public static void subsets(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> subset, ArrayList<Integer> a, int index) {
        if (index == a.size()) {
            result.add(new ArrayList<>(subset));
            return;
        }

        for (int i = index; i < a.size(); i++) {
            subset.add(a.get(i));
            subsets(result, subset, a, i + 1);
        }
    }

    void combineHelper(int n, int k, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> combination) {
        if (k == 0) {
            ans.add(new ArrayList<>(combination));
            return;
        }
        if (n <= 0) {
            return;
        }


        combineHelper(n - 1, k, ans, combination);
        combination.add(n);
        combineHelper(n - 1, k - 1, ans, combination);
        combination.remove(combination.size() - 1);
    }

    /*public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {

        if (A == null)
            return null;

        res = new ArrayList<>();

        Collections.sort(A);

        rec(A, new ArrayList<>(), B, 0);

        return res;
    }

    public void rec(ArrayList<Integer> A, ArrayList<Integer> ans, int B, int index) {

        if (B == 0) {
            res.add(new ArrayList<>(ans));
            return;
        }

        if (B < 0 || index >= A.size())
            return;

        int i = index + 1;
        for (; i < A.size(); i++)
            if (A.get(i) != A.get(i - 1))
                break;
        rec(A, ans, B, i);

        ans.add(A.get(index));
        rec(A, ans, B - A.get(index), index + 1);
        ans.remove(ans.size() - 1);



    }
*/
    public static ArrayList<ArrayList<Integer>> combinationSum(int n, int b) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            a.add(i);
        }
        Set<ArrayList<Integer>> result = new HashSet<>();

        combinationSum(a, b, new ArrayList<>(), result, 0);
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.addAll(result);
        Collections.sort(arrayLists, (o1, o2) -> {
            int min = Math.min(o1.size(), o2.size());
            for (int i = 0; i < min; i++) {
                int compare = Integer.compare(o1.get(i), o2.get(i));
                if (compare != 0) {
                    return compare;
                }
            }

            return o1.size() > o2.size() ? -1 : 1;
        });
        return arrayLists;
    }

    public static void combinationSum(List<Integer> a, int b, ArrayList<Integer> combination, Set<ArrayList<Integer>> result, int index) {
        if (combination.size() == b) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = index; i < a.size(); i++) {
            combination.add(a.get(i));
            combinationSum(a, b, combination, result, i + 1);
            combination.remove(a.get(i));
        }
    }

    /* static ArrayList<ArrayList<Integer>> res;
     static ArrayList<Integer> A;
     static int N;

     public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
         ArrayList<Integer> temp;
         res = new ArrayList<>();
         temp = new ArrayList<>();
         A = A;
         N = A.size();
         Collections.sort(A);

         subset(0, temp);

         Collections.sort(res, new Comparator<ArrayList<Integer>>() {
             @Override
             public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                 int an = a.size();
                 int bn = b.size();
                 for (int i = 0; i < Math.min(an, bn); i++) {
                     int cmp = Integer.compare(a.get(i), b.get(i));
                     if (cmp != 0)
                         return cmp;
                 }
                 return Integer.compare(a.size(), b.size());
             }
         });

         return res;
     }

     private static void subset(int index, ArrayList<Integer> arr) {

         if (index == N) {
             res.add(new ArrayList<>(arr));
             return;
         }

         subset(index + 1, arr);
         arr.add(A.get(index));
         subset(index + 1, arr);
         arr.remove(arr.size() - 1);

     }*/
    public static ArrayList<String> generateParenthesis(int a) {

        ArrayList<String> result = new ArrayList<>();
        getParenthesis(result, a, a, new char[2 * a], 0);
        return result;
    }

    private static void getParenthesis(ArrayList<String> result, int left, int right, char[] paren, int index) {

        if (right == left && right == 0) {
            result.add(String.valueOf(paren));
        }

        if (left > right || right == 0) {
            return;
        }


        if (left > 0) {
            paren[index] = '(';
            getParenthesis(result, left - 1, right, paren, index + 1);
        }
        if (right >= left) {
            paren[index] = ')';
            getParenthesis(result, left, right - 1, paren, index + 1);
        }
    }


    public static ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (a.isEmpty() || a.length() == 0) {
            return result;
        }
        ArrayList<String> partitioning = new ArrayList<>();

        partition(a, partitioning, 0, result);
        return result;
    }

    private static void partition(String a, ArrayList<String> partitioning, int index, ArrayList<ArrayList<String>> result) {
        if (index == a.length()) {
            result.add(new ArrayList<>(partitioning));

        } else {
            for (int i = index + 1; i <= a.length(); i++) {
                String substring = a.substring(index, i);
                if (isPalindrome(substring)) {
                    partitioning.add(substring);
                    partition(a, partitioning, i, result);
                    partitioning.remove(partitioning.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(String substring) {
        for (int i = 0, j = substring.length() - 1; i < j; i++, j--) {
            if (substring.charAt(i) != substring.charAt(j)) {
                return false;
            }
        }
        return true;
    }


}

class Column {
    int index1;
    int index2;

    public Column(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

}
