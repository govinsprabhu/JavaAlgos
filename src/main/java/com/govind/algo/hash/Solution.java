package com.govind.algo.hash;

import com.govind.util.ArrayListUtils;
import com.govind.util.linkedlist.RandomListNode;

import java.util.*;

/**
 * Created by govindp on 10/7/2015.
 */
public class Solution {
    Integer x;
    Integer y;

    public Solution(int i, int i1) {
        x= i;
        y = i1;
    }

    @Override
    public int hashCode() {
        return 17 * 31 + x + y;
    }

    @Override
    public boolean equals(Object obj) {
        if  (this == obj){
            return true;
        }

        if (!(obj instanceof Solution)){
            return false;
        }
        Solution solution = (Solution) obj;
        return Integer.compare(solution.x,x) == 0 &&
                Integer.compare(solution.y, y)== 0;
    }

    public static boolean canWinNim(int n) {


        return !(n % 4 == 0);
    }

    public static void main(String[] args) {

        System.out.println(maxPoints(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1,2,1,2}),
                new ArrayListUtils<Integer>().getArrayList(new Integer[]{4,5,11,12})));
        System.out.println(canWinNim(5));

    }

    public static int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if(a.isEmpty() || b.isEmpty() || a.size() != b.size()){
            return 0;
        }
        if (a.size() == 1 ){
            return 1;
        }
        HashMap<Solution,Integer> map = new HashMap<>();
        int count =0;
        int ans = -1;
        for (int i = 0; i < a.size() -1; i++) {
            count  = 1;
            Solution solution = null;
            int max = 0;
            map.clear();
            for (int j = i + 1; j < b.size(); j++) {
                int xDiff = a.get(i) - a.get(j);
                int yDiff = b.get(i) - b.get(j);
                if (xDiff == 0 && yDiff == 0){
                    count ++;
                    continue;
                }

                if (xDiff < 0){
                    xDiff *= -1;
                    yDiff *= -1;
                }

                int gcd = gcd(xDiff,yDiff);
                solution = new Solution(xDiff /gcd, yDiff/ gcd);
                max = Math.max(map.containsKey(solution)? map.get(solution) + 1:1, max);
                map.put(solution,max);

            }
            max += count;
            ans= Math.max(ans, max);
        }

        return ans;
    }

    public static int gcd(int a, int b){
        int c;
        if (a== 0){
            return b;
        }
        if (b == 0){
            return a;
        }
        a = a < 0 ? a * -1: a;
        b = b < 0 ? b * -1: b;
        while (a != 0 && b != 0 ){
            c = a;
            a = b % a;
            b = c;
        }

        return a == 0 ? b: a;
    }



    public static String minWindow1(String S, String T){
        HashMap<Character, Integer> map = new HashMap<>();

        if (S.equals(T)) {
            return T;
        }
        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), map.containsKey(T.charAt(i)) ? map.get(T.charAt(i)) + 1 : 1);
        }
        int start = 0;
        int tail = 0;
        int length = 0;


        int startIndex = -1;
        int minLength = 0;
        for (; tail < S.length(); tail++) {
            if (!map.containsKey(S.charAt(tail))){
                continue;
            }

            map.put(S.charAt(tail), map.get(S.charAt(tail)) - 1);

            if (map.get(S.charAt(tail)) >= 0){
                length ++;
            }

            if (length == T.length()){
                for (int i = start; i <= tail; i++) {
                    if (!map.containsKey(S.charAt(i)) || map.get(S.charAt(i)) < 0){
                        if (map.containsKey(S.charAt(i))){
                            map.put(S.charAt(i), map.get(S.charAt(i))+1);
                        }
                        start++;
                    }else {
                        break;
                    }
                }

                if (minLength > (tail - start + 1) || minLength == 0){
                    minLength = tail - start + 1;
                    startIndex = start;
                }
            }
        }

        if (minLength  == S.length() + 1){
            return "Not found";
        }

        return S.substring(startIndex, startIndex + minLength);
    }

    public static String minWindow(String S, String T) {
        HashMap<Character, Integer> map = new HashMap<>();

        if (S.equals(T)) {
            return T;
        }
        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), map.containsKey(T.charAt(i)) ? map.get(T.charAt(i)) + 1 : 1);
        }

        String result = S;
        String currentResult = "";
        int length = 0;
        HashMap<Character, Integer> test = new HashMap<>();
        int i;
        for (i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) {
                break;
            }
        }
        if (i == S.length() || T.length() > S.length()) {
            return "";
        }
        boolean isAtLeastOne = false;
        for (; i < S.length(); i++) {
            currentResult += S.charAt(i);
            if (map.containsKey(S.charAt(i))) {
                test.put(S.charAt(i), test.containsKey(S.charAt(i)) ? test.get(S.charAt(i)) + 1 : 1);
                if (test.get(S.charAt(i)) == map.get(S.charAt(i))) {
                    length++;
                }

                if (length >= map.size()) {
                    isAtLeastOne = true;
                    String backup = currentResult;
                    for (int j = 0; j < currentResult.length(); j++) {
                        if (test.containsKey(currentResult.charAt(j)) && (test.get(currentResult.charAt(j)) > map.get(currentResult.charAt(j)))) {
                            backup = backup.replaceFirst(String.valueOf(currentResult.charAt(j)), "");
                            test.put(currentResult.charAt(j), test.get(currentResult.charAt(j)) - 1);
                            length = test.get(currentResult.charAt(j)) == 0 ? length - 1 : length;
                        } else if (test.containsKey(currentResult.charAt(j)) && (test.get(currentResult.charAt(j)) <= map.get(currentResult.charAt(j)))) {
                            break;
                        } else {
                            backup = backup.replaceFirst(String.valueOf(currentResult.charAt(j)), "");
                        }
                    }
                    currentResult = backup;
                    if (result.length() > currentResult.length()) {
                        result = currentResult;
                    }
                }
            }
        }

        return isAtLeastOne
                ?result
                :"";
    }

    public static int lengthOfLongestSubstring(String a) {

        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = -1;
        int countLength = 0;
        for (int i = 0; i < a.length(); i++) {
            if (!map.containsKey(a.charAt(i)) || i - countLength > map.get(a.charAt(i))) {
                countLength++;
            } else {
                maxLength = Math.max(maxLength, countLength);
                countLength = i - map.get(a.charAt(i));
            }
            map.put(a.charAt(i), i);
        }

        maxLength = Math.max(maxLength, countLength);

        return maxLength;
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode duplicate = null, duplicateHead = null;
        RandomListNode original = head;

        while (head != null) {
            if (duplicate == null) {
                duplicate = new RandomListNode(head.label);
                duplicateHead = duplicate;
            } else {
                duplicate.next = new RandomListNode(head.label);
                duplicate = duplicate.next;
            }

            head = head.next;
        }

        duplicate = duplicateHead;
        head = original;
        while (head != null) {
            duplicate.random = head;
            duplicate = duplicate.next;
            head = head.next;
        }
        duplicate = duplicateHead;
        head = original;
        RandomListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = duplicate;
            head = next;
            duplicate = duplicate.next;
        }
        duplicate = duplicateHead;
        head = original;
        while (duplicate != null) {
            if (duplicate.random.random == null) {
                duplicate.random = null;
            } else {
                duplicate.random = duplicate.random.random.next;
            }
            duplicate = duplicate.next;
        }


        return duplicateHead;
    }

    public static int diffPossible(final List<Integer> a, int b) {
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();

        for (int i = 0; i < a.size(); i++) {
            if (map.containsKey(a.get(i))) {
                return 1;
            }

            if (b > a.get(i)) {
                map.put(b + a.get(i), true);
            } else {
                map.put(a.get(i) - b, true);
            }
        }
        return 0;
    }

    /*public static ArrayList<Integer> equal(ArrayList<Integer> a) {

        HashMap<Integer,ArrayList<ArrayList<Integer>>> map = new HashMap<>();

        Set<ArrayList<Integer>> set = new HashSet<>();
        for (int i = 0; i < a.size() - 2; i++) {
            for (int j = i + 1 ; j  <= a.size() - 1; j ++) {
                int sum = a.get(i) + a.get(j);
                if (map.containsKey(sum) && isValidSum(map,sum)){

                }

            }
        }


    }

    private static boolean isValidSum(HashMap<Integer, ArrayList<ArrayList<Integer>>> map, int sum) {
        ArrayList<ArrayList<Integer>> arrayList = map.get(sum);
        f
    }*/

    public static ArrayList<ArrayList<Integer>> anagrams(final List<String> a) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < a.size(); i++) {
            char[] characters = a.get(i).toCharArray();
            Arrays.sort(characters);
            String newString = String.valueOf(characters);
            ArrayList<Integer> list = new ArrayList<>();
            if (map.containsKey(newString)) {
                list = map.get(newString);
            }
            list.add(i + 1);
            map.put(newString, list);
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, int b) {
        Collections.sort(a);


        Set<ArrayList<Integer>> setResult = new HashSet<>();

        for (int i = 0; i < a.size() - 2; i++) {
            if (a.get(i) > b / 4) {
                continue;
            }
            for (int j = i + 1; j < a.size() - 1; j++) {
                if (a.get(i) + a.get(j) > b / 2) {
                    continue;
                }
                for (int k = j + 1, l = a.size() - 1; k < l; ) {
                    if (a.get(k) + a.get(i) + a.get(j) > b) {
                        k++;
                        l--;
                        continue;
                    }
                    if (a.get(k) + a.get(i) + a.get(j) + a.get(l) == b) {
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(a.get(i));
                        arrayList.add(a.get(j));
                        arrayList.add(a.get(k));
                        arrayList.add(a.get(l));
                        setResult.add(arrayList);
                        k++;
                        l--;
                    } else if (a.get(k) + a.get(i) + a.get(j) + a.get(l) < b) {
                        k++;
                    } else {
                        l--;
                    }

                }
            }
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.addAll(setResult);

        Collections.sort(result, (o1, o2) -> {
            for (int i = 0; i < 4; i++) {
                int comparator = Integer.compare(o1.get(i), o2.get(i));
                if (comparator != 0) {
                    return comparator;
                }
            }
            return 0;
        });

        return result;
    }

    public static ArrayList<Integer> lszero(ArrayList<Integer> a) {
        int sum = 0;
        int[] array = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
            array[i] = sum;
        }
        int maxLength = -1;
        int minIndex = -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < array.length; i++) {
            if ((map.containsKey(array[i]) && maxLength < i - map.get(array[i])) || array[i] == 0) {

                maxLength = i - map.get(array[i]);
                minIndex = map.get(array[i]) + 1;

            } else {
                if (map.containsKey(array[i])) {
                    map.put(array[i], Math.min(map.get(array[i]), i));
                } else {
                    map.put(array[i], i);
                }
            }
        }

        if (minIndex == -1)

        {
            return null;
        }

        return new ArrayList<>(a.subList(minIndex, minIndex + maxLength));

    }

    public static int colorful(int a) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (a > 0) {
            arrayList.add(a % 10);
            a /= 10;
        }

        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < arrayList.size() - 1; i++) {
            if (map.containsKey(arrayList.get(i))) {
                return 0;
            }
            map.put(arrayList.get(i), true);
            int mult = arrayList.get(i);
            for (int j = i + 1; j < arrayList.size(); j++) {
                mult *= arrayList.get(j);
                if (map.containsKey(mult) || map.containsKey(arrayList.get(j))) {
                    return 0;
                }

                map.put(mult, true);
            }
        }
        return 1;
    }

}

class Column {
    int index1;
    int index2;
    public Column(int index1, int index2){
        this.index1 = index1;
        this.index2 = index2;
    }


}
