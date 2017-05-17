package com.govind.algo.platform.interviewbit.backtracking;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.*;

/**
 * Created by 609600403 on 17/04/2016.
 */
public class Sample {
    public static void main(String args[]) throws Exception {
        ArrayList<Integer> x = new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 8, 4});
        ArrayList<Integer> y = new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 4, 4});
        System.out.println(new Sample().permute(new ArrayListUtils<Integer>().getArrayList(new Integer[]{0,0,0,1,9})));

    }

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Collections.sort(a);
        permute(a, 0, result);
       /* Collections.sort(result, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                for (int i = 0; i < o1.size(); i++) {
                    if (o1.get(i) < o2.get(i)) {
                        return -1;
                    } else if (o1.get(i) > o2.get(i)) {
                        return 1;
                    }
                }
                return 0;
            }
        });*/
        return result;
    }

    private void permute(ArrayList<Integer> a, int l, ArrayList<ArrayList<Integer>> result) {
        if (a.size() - 1 <= l) {
            result.add((ArrayList<Integer>) a.clone());
            return;
        }


        for (int i = l; i < a.size(); i++) {

            if (i != l && (a.get(i - 1) == a.get(i) || (a.get(l) == a.get(i)))) {
                continue;
            }
            swap(a, i, l);
            permute(a, l + 1, result);
            swap(a, i, l);
        }
    }

    public String minWindow(String S, String T) {
        Map<Character, Integer> characterCountMap = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            characterCountMap.put(T.charAt(i), characterCountMap.containsKey(T.charAt(i)) ? characterCountMap.get(T.charAt(i)) + 1 : 1);
        }

        int count = characterCountMap.keySet().size();
        int startIndex = -1;
        int minStart = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); i++) {
            if (!characterCountMap.containsKey(S.charAt(i))) {
                continue;
            }

            startIndex = startIndex == -1 ? i : startIndex;
            characterCountMap.put(S.charAt(i), characterCountMap.get(S.charAt(i)) - 1);
            if (characterCountMap.get(S.charAt(i)) == 0) {
                count--;
            }

            if (count > 0) {
                continue;
            }

            for (; startIndex < i; startIndex++) {
                if (!characterCountMap.containsKey(S.charAt(startIndex))) {
                    continue;
                }

                if (characterCountMap.get(S.charAt(startIndex)) == 0) {
                    break;
                }
                if (characterCountMap.get(S.charAt(startIndex)) < 0) {
                    characterCountMap.put(S.charAt(startIndex), characterCountMap.get(S.charAt(startIndex)) + 1);
                }

            }
            if (minLength > i - startIndex + 1) {
                minLength = i - startIndex + 1;
                minStart = startIndex;
            }

        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return S.substring(minStart, minStart + minLength);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        long numeratorL = numerator;
        long denominatorL = denominator;
        String currentResult = "";
        if (numerator < 0 ^ denominator < 0) {
            currentResult = "-";
            numeratorL = numeratorL < 0 ? numeratorL * -1 : numeratorL;
            denominatorL = denominatorL < 0 ? denominatorL * -1 : denominatorL;
        }

        currentResult += String.valueOf(numeratorL / denominatorL);
        long reminder = numeratorL % denominatorL * 10;
        if (reminder == 0) {
            return currentResult;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Integer> set = new HashMap<>();
        while (reminder != 0) {
            if (set.containsKey(reminder)) {
                int index = set.get(reminder);
                return currentResult + "." + stringBuilder.substring(0, index) + "(" + stringBuilder.substring(index) + ")";

            }
            set.put(reminder, stringBuilder.length());
            stringBuilder.append(reminder / denominatorL);
            reminder = reminder % denominatorL * 10;
        }

        return currentResult + "." + stringBuilder.toString();
    }

    public int maxPoints(ArrayList<Integer> x, ArrayList<Integer> y) {
        if (x.isEmpty() || y.isEmpty()) {
            return 0;
        }


        Map<Float, Integer> slopAndCountMap = new HashMap<>();
        int maxCount = 1;
        for (int i = 0; i < x.size() - 1; i++) {
            int x1 = x.get(i);
            int y1 = y.get(i);

            int samePoints = 1;
            for (int j = i + 1; j < x.size(); j++) {
                int x2 = x.get(j);
                int y2 = y.get(j);
                float slop = Integer.MAX_VALUE;
                if (Integer.compare(x1, x2) == 0 && Integer.compare(y2, y1) == 0) {
                    samePoints++;
                } else if (x1 != x2) {
                    slop = (float) (y2 - y1) / (float) (x2 - x1);
                }
                if (!(x1 == x2 && y2 == y1))
                    slopAndCountMap.put(slop, slopAndCountMap.containsKey(slop) ? slopAndCountMap.get(slop) + 1 : 1);

            }

            for (Float slop : slopAndCountMap.keySet()) {
                maxCount = Math.max(maxCount, slopAndCountMap.get(slop) + samePoints);
            }

            maxCount = slopAndCountMap.isEmpty() ? Math.max(samePoints, maxCount) : maxCount;
            slopAndCountMap.clear();
        }

        return maxCount;
    }

    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        Map<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                int sum = a.get(i) + a.get(j);
                if (hashMap.containsKey(sum)) {
                    ArrayList<Integer> indexes = hashMap.get(sum);
                    boolean isValid = true;
                    for (Integer index : indexes) {
                        if (index == i || index == j) {
                            isValid = false;
                            break;
                        }
                    }
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    if (isValid) {
                        arrayList.add(hashMap.get(sum).get(0));
                        arrayList.add(hashMap.get(sum).get(1));
                        arrayList.add(i);
                        arrayList.add(j);
                        result.add(arrayList);
                    }
                } else {
                    ArrayList<Integer> indexes = new ArrayList<>();
                    indexes.add(i);
                    indexes.add(j);
                    hashMap.put(sum, indexes);
                }
            }
        }

        System.out.println(result);

        Collections.sort(result, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                for (int i = 0; i < 4; i++) {
                    if (o1.get(i) < o2.get(i)) {
                        return -1;
                    }
                    if (o1.get(i) > o2.get(i)) {
                        return 1;
                    }
                }

                return 0;
            }
        });

        System.out.println("sorted " + result);

        return result.isEmpty() ? new ArrayList<>() : result.get(0);
    }

    public int lengthOfLongestSubstring(String a) {
        Map<Character, Integer> map = new HashMap<>();
        int maxStart = 0;
        int maxLength = 0;
        for (int i = 0; i < a.length(); i++) {
            if (map.containsKey(a.charAt(i))) {
                if (i - maxStart > maxLength) {
                    maxLength = i - maxStart;
                }
                maxStart = (maxStart > map.get(a.charAt(i)) + 1) ? maxStart : map.get(a.charAt(i)) + 1;
            }
            map.put(a.charAt(i), i);
        }

        if (a.length() - maxStart > maxLength) {
            maxLength = a.length() - maxStart;
        }
        return maxLength;
    }

    public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, int b) {
        Set<ArrayList<Integer>> result = new HashSet<>();
        Collections.sort(a);
        for (int i = 0; i < a.size() - 3; i++) {

            for (int j = i + 1; j < a.size() - 2; j++) {
                int sum = b - (a.get(i) + a.get(j));
                Map<Integer, Integer> set = new HashMap<>();
                for (int k = j + 1; k < a.size(); k++) {

                    if (set.containsKey(sum - a.get(k))) {
                        ArrayList<Integer> currentResult = new ArrayList<>();
                        currentResult.add(a.get(i));
                        currentResult.add(a.get(j));
                        currentResult.add(a.get(set.get(sum - a.get(k))));
                        currentResult.add(a.get(k));
                        if (!result.contains(currentResult))
                            result.add(currentResult);
                    }
                    if (!set.containsKey(a.get(k)))
                        set.put(a.get(k), k);


                }
            }
        }

        ArrayList<ArrayList<Integer>> resultArrayList = new ArrayList<>(result);
        Collections.sort(resultArrayList, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                for (int i = 0; i < 4; i++) {
                    if (o1.get(i) < o2.get(i)) {
                        return -1;
                    } else if (o1.get(i) > o2.get(i)) {
                        return 1;
                    }
                }

                return 0;

            }
        });
        return resultArrayList;
    }

    public int diffPossible(final List<Integer> a, int b) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            if (map.containsKey(a.get(i) + b) || map.containsKey(a.get(i) - b)) {
                return 1;
            }
            map.put(a.get(i), i);
        }

        return 0;
    }

    public int isValidSudoku(final List<String> a) {
        Map<Integer, ArrayList<Position>> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).length(); j++) {
                if (a.get(i).charAt(j) == '.') {
                    continue;
                }
                int key = Integer.parseInt(String.valueOf(a.get(i).charAt(j)));
                if (map.containsKey(key)) {
                    ArrayList<Position> positions = map.get(key);
                    for (Position position : positions) {
                        if (position.row == i) {
                            return 0;
                        }
                        if (position.col == j) {
                            return 0;
                        }

                        if ((position.row >= (i / 3 * 3) && position.row < (((i / 3) + 1) * 3) && position.col >= (j / 3 * 3)) && position.col < ((j / 3) + 1) * 3) {
                            return 0;
                        }
                    }
                    positions.add(new Position(i, j));
                } else {
                    ArrayList<Position> positions = new ArrayList<>();
                    positions.add(new Position(i, j));
                    map.put(key, positions);
                }
            }
        }
        return 1;
    }

    private class Position {
        int row;

        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    public ArrayList<Integer> twoSum(final List<Integer> a, int b) {
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            if (map.containsKey(b - a.get(i))) {
                result.add(map.get(b - a.get(i)) + 1);
                result.add(i + 1);
                return result;
            }
            if (!map.containsKey(a.get(i))) {
                map.put(a.get(i), i);
            }
        }

        return result;
    }

    public ArrayList<Integer> lszero(ArrayList<Integer> a) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxSequenceLength = 0;
        int startIndex = -1;
        map.put(0, -1);
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
            if (map.containsKey(sum)) {
                int index = map.get(sum);
                if (i - index > maxSequenceLength) {
                    maxSequenceLength = i - index;
                    startIndex = index + 1;
                }
            } else {
                map.put(sum, i);
            }
        }

        if (startIndex == -1) {
            return null;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = startIndex; i < startIndex + maxSequenceLength; i++) {
            result.add(a.get(i));
        }

        return result;
    }

    public int colorful(int a) {
        Set<Integer> mulSet = new HashSet<>();
        String number = String.valueOf(a);
        for (int i = 0; i < number.length(); i++) {
            for (int j = 0; j < number.length() - i; j++) {
                int k = j + i;
                int subNumber = getMul(number.substring(j, k + 1));
                if (mulSet.contains(subNumber)) {
                    return 0;
                }
                mulSet.add(subNumber);
            }
        }

        return 1;
    }

    private int getMul(String substring) {
        int mul = 1;
        for (int i = 0; i < substring.length(); i++) {
            mul *= Integer.parseInt(String.valueOf(substring.charAt(i)));
        }

        return mul;
    }

    public String getPermutation(int n, int k) {
        long nFact = getFact(n);
        if (nFact < k) {
            return null;
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        //ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            linkedList.add(i);
        }

        String result = "";

        k--;
        while (!linkedList.isEmpty()) {
            nFact = nFact / n;
            nFact = nFact == 0 ? 1 : nFact;
            result += linkedList.remove((int) (k / nFact));
            k %= nFact;
            n--;
        }

        return result;
    }

    private long getFact(int i) {
        if (i == 0) {
            return 1;
        }
        long fact = 1;
        while (i > 1) {
            fact *= i;
            i--;
        }

        return fact;
    }

    public void testSudoku() {
        ArrayList<ArrayList<Character>> sudoku = new ArrayList<>();
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'}));
        sudoku.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}));

        solveSudoku(sudoku);

    }

    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        solveSudokuBoolean(a);
    }

    private boolean solveSudokuBoolean(ArrayList<ArrayList<Character>> a) {
        int col = -1, row = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a.get(i).get(j) == '.') {
                    col = j;
                    row = i;
                    break;
                }

            }
            if (col != -1) {
                break;
            }
        }

        if (row == -1) {
            return true;
        }

        for (int number = 1; number <= 9; number++) {
            if (isPossible(a, row, col, number)) {
                a.get(row).set(col, (char) (number + 48));
                if (solveSudokuBoolean(a)) {
                    return true;
                }
                a.get(row).set(col, '.');
            }
        }


        return false;
    }

    private boolean isPossible(ArrayList<ArrayList<Character>> a, int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (a.get(row).get(i) == number + 48) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (a.get(i).get(col) == number + 48) {
                return false;
            }
        }

        for (int i = ((row / 3) * 3); i < ((row / 3) + 1) * 3; i++) {
            for (int j = ((col / 3) * 3); j < ((col / 3) + 1) * 3; j++) {
                if (a.get(i).get(j) == number + 48) {
                    return false;
                }
            }
        }

        return true;
    }

    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        boolean[][] board = new boolean[a][a];
        solveNQueens(0, a, board, result);
        return result;
    }

    private void solveNQueens(int row, int N, boolean[][] board, ArrayList<ArrayList<String>> result) {
        if (row == N) {
            ArrayList<String> currentResult = new ArrayList<>();
            for (boolean[] column : board) {
                StringBuilder stringBuilder = new StringBuilder();
                for (boolean ele : column) {
                    if (ele == true) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append(".");
                    }
                }
                currentResult.add(stringBuilder.toString());
            }

            result.add(currentResult);
            return;
        }

        for (int colIndex = 0; colIndex < N; colIndex++) {
            if (!canPlace(row, colIndex, board)) {
                continue;
            }
            board[row][colIndex] = true;
            solveNQueens(row + 1, N, board, result);
            board[row][colIndex] = false;
        }
    }

    public boolean canPlace(int row, int col, boolean[][] board) {
        for (int i = col, j = row; i >= 0 && j >= 0; i--, j--) {
            if (board[j][i] == true) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == true) {
                return false;
            }
        }

        for (int i = row; i >= 0; i--) {
            if (board[i][col] == true) {
                return false;
            }
        }

        return true;
    }

    private static void printCommentedLine(StringBuilder stringBuilder) {
        String result = stringBuilder.toString();
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < result.length(); i++) {
            if (i < result.length() - 1 && (result.charAt(i) == '/' && result.charAt(i + 1) == '*')) {
                output.append(result.charAt(i++));
                output.append(result.charAt(i++));
                while (i < result.length()) {
                    if (count == 0 && result.charAt(i) == '*' && result.charAt(i + 1) == '/') {
                        break;
                    }

                    if (i < result.length() - 1 && (result.charAt(i) == '/' && result.charAt(i + 1) == '*')) {
                        count++;
                    }

                    if (i < result.length() && count != 0 && result.charAt(i) == '*' && result.charAt(i + 1) == '/') {
                        count--;
                    }

                    output.append(result.charAt(i++));
                }

                output.append(result.charAt(i++));
                output.append(result.charAt(i++));
                output.append(result.charAt(i));

            }

            if (i < result.length() && count == 0 && result.charAt(i) == '/' && result.charAt(i + 1) == '/') {
                while (i < result.length() && result.charAt(i) != '\n') {
                    output.append(result.charAt(i++));
                }

                output.append(result.charAt(i));
            }
        }

        System.out.println(output);
    }

    static long strongRelation(int n, int m) {

        int numberOfEndgesNeeded = 0;
        return 0;
    }

    static long kSub(int k, int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((sum + nums[i]) % k == 0) {
                sum += nums[i];
            }
        }
        return 0;
    }

    static int maxDifference(int[] a) {
        long minTillNow = Integer.MAX_VALUE;
        long maxDifference = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < minTillNow) {
                minTillNow = a[i];
            } else if (maxDifference < (a[i] - minTillNow)) {
                maxDifference = a[i] - minTillNow;
            }
        }

        return (int) maxDifference;
    }

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        combine(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void combine(int n, int k, int index, ArrayList<Integer> currentResult, ArrayList<ArrayList<Integer>> result) {
        if (k == 0) {
            result.add((ArrayList<Integer>) currentResult.clone());
            return;
        }

        for (int i = index; i <= n; i++) {
            currentResult.add(i);
            combine(n, k - 1, i + 1, currentResult, result);
            currentResult.remove(currentResult.size() - 1);
        }

    }

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        Collections.sort(a);
        ArrayList<ArrayList<Integer>> finalResult = new ArrayList<>();
        combinationSum(a, 0, b, new ArrayList<Integer>(), finalResult);
        return finalResult;
    }

    private void combinationSum(ArrayList<Integer> a, int index, int b, ArrayList<Integer> sumList, ArrayList<ArrayList<Integer>> result) {
        if (b == 0) {
            result.add((ArrayList<Integer>) sumList.clone());
            return;
        }

        int previous = 0;
        for (int i = index; i < a.size(); i++) {
            if (a.get(i) > b) {
                return;
            }
            if (previous == a.get(i)) {
                continue;
            }
            sumList.add(a.get(i));
            combinationSum(a, i, b - a.get(i), sumList, result);
            sumList.remove(sumList.size() - 1);
            previous = a.get(i);
        }

    }

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
        Collections.sort(a);
        Set<ArrayList<Integer>> powerSet = new HashSet<>();
        subsets(a, 0, new ArrayList<Integer>(), powerSet);
        ArrayList<ArrayList<Integer>> set = new ArrayList<>(powerSet);
        Collections.sort(set, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                    if (o1.get(i) < o2.get(i)) {
                        return -1;
                    } else if (o1.get(i) > o2.get(i)) {
                        return 1;
                    }
                }

                return o1.size() < o2.size()
                        ? -1
                        : 1;
            }

        });

        return set;
    }

    private void subsets(ArrayList<Integer> a, int count, ArrayList<Integer> set, Set<ArrayList<Integer>> powerSet) {
        if (a.size() == count) {
            ArrayList<Integer> clone = (ArrayList<Integer>) set.clone();
            //Collections.sort(clone);
            powerSet.add(clone);
            return;
        }

        subsets(a, count + 1, set, powerSet);
        set.add(a.get(count));
        subsets(a, count + 1, set, powerSet);
        set.remove(set.size() - 1);
    }

    public ArrayList<Integer> grayCode(int a) {
        ArrayList<String> result = new ArrayList<>();
        result.add("0");
        result.add("1");
        for (int i = 2; i <= a; i++) {
            ArrayList<String> clone = (ArrayList<String>) result.clone();
            for (int j = 0; j < result.size(); j++) {
                result.set(j, "0" + result.get(j));
            }

            for (int j = clone.size() - 1; j >= 0; j--) {
                result.add("1" + clone.get(j));
            }
        }

        ArrayList<Integer> resultInNumbers = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            resultInNumbers.add(convertToBinary(result.get(i)));
        }

        return resultInNumbers;
    }

    private Integer convertToBinary(String s) {
        int pow = 1;
        int integer = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            integer += (s.charAt(i) - 48) * pow;
            pow <<= 1;
        }
        return integer;
    }

    public ArrayList<String> generateParenthesis(int a) {
        char[] chars = new char[2 * a];
        ArrayList<String> result = new ArrayList<>();
        generateParenthesis(a, a, chars, 0, result);
        return result;
    }

    private void generateParenthesis(int left, int right, char[] chars, int count, ArrayList<String> result) {
        if (left >= right && left == 0) {
            result.add(String.valueOf(chars));
            return;
        }

        if (left > 0) {
            chars[count] = '(';
            generateParenthesis(left - 1, right, chars, count + 1, result);
        }

        if (left < right) {
            chars[count] = ')';
            generateParenthesis(left, right - 1, chars, count + 1, result);
        }


    }


    private void swap(ArrayList<Integer> a, int i, int l) {
        int temp = a.get(i);
        a.set(i, a.get(l));
        a.set(l, temp);
    }

    public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        partition(a, result, new ArrayList<>());
        return result;
    }

    private void partition(String a, ArrayList<ArrayList<String>> result, ArrayList<String> strings) {
        if (a == null || a.isEmpty()) {
            result.add((ArrayList<String>) strings.clone());
            return;
        }

        for (int j = 1; j <= a.length(); j++) {
            String substring = a.substring(0, j);
            if (isPalindrome(substring)) {
                strings.add(substring);
                partition(a.substring(j, a.length()), result, strings);
                strings.remove(strings.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String substring) {
        if (substring.length() == 1) {
            return true;
        }

        for (int i = 0, j = substring.length() - 1; i < j; i++, j--) {
            if (substring.charAt(i) != substring.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<String> letterCombinations(String a) {
        Map<Integer, String> numberPad = new HashMap<>();
        numberPad.put(0, "0");
        numberPad.put(1, "1");
        numberPad.put(2, "abc");
        numberPad.put(3, "def");
        numberPad.put(4, "ghi");
        numberPad.put(5, "jkl");
        numberPad.put(6, "mno");
        numberPad.put(7, "pqrs");
        numberPad.put(8, "tuv");
        numberPad.put(9, "wxyz");
        ArrayList<String> result = new ArrayList<>();
        letterCombinations(a, result, "", numberPad);
        return result;

    }

    private void letterCombinations(String a, ArrayList<String> result, String currentResult, Map<Integer, String> numberPad) {
        if (a == null || a.isEmpty()) {
            result.add(currentResult);
            return;
        }

        int currentNumber = a.charAt(0) - 48;
        String pad = numberPad.get(currentNumber);

        for (int i = 0; i < pad.length(); i++) {
            currentResult += pad.charAt(i);
            letterCombinations(a.substring(1), result, currentResult, numberPad);
            currentResult = currentResult.substring(0, currentResult.length() - 1);

        }
    }
}
