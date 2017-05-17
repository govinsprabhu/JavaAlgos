package com.govind.algo.hash;


import com.govind.util.arraylist.ArrayListUtils;
import com.govind.util.linkedlist.DoubleLinkedList;
import com.govind.util.linkedlist.RandomListNode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;

/**
 * Created by govindp on 10/7/2015.
 */
public class Solution {
    int capacity;
    LinkedHashMap<Integer, Integer> linkedHashMap;

    public Solution(int capacity) {
        this.capacity = capacity;
        linkedHashMap = new LinkedHashMap(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }


    public static void main(String[] args) {

        /*Solution solution = new Solution(1);
        solution.set(2, 1);
        solution.set(2, 1);
        System.out.println(solution.get(2));
        //System.out.println(solution.get(2));
        solution.set(3, 2);
        System.out.println(solution.get(2));
        System.out.println(solution.get(3));
        *//*solution.set(2,2);
        solution.set(3, 3);
        solution.set(4, 4);
        System.out.println(solution.get(0));*/

        HashMap<Column, Integer> hashMap = new HashMap<>();
        Column column1 = new Column(1, 2);
        Column column2 = new Column(1, 2);
        hashMap.put(column1, 0);
        hashMap.put(column2, 1);
//        System.out.println(hashMap.get(column1));
//        System.out.println(hashMap.get(column2));
        //new Solution(4).nonDuplicateCharacter();
        new Solution(4).longestConsecutiveNumbers();
    }

    public void longestConsecutiveNumbers(){
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{100,4,200,3,2,1});
        HashSet<Integer> set =  new HashSet<>();
        for (int element : arrayList) {
            set.add(element);
        }

        int max = 0;

        for (int element : arrayList){
            int left = element - 1;
            int right = element + 1;
            int count = 1;
            while (set.contains(left)){
                count ++;
                set.remove(left);
                left = left - 1;
            }
            while (set.contains(right)){
                count ++;
                set.remove(right);
                right = right + 1;
            }

            max = Math.max(max, count);
        }

        System.out.println(max);
    }

    public void maxPointsInPlane() {
        ArrayList<Integer> xCoordinate = new ArrayListUtils<Integer>().getArrayList(new Integer[]{6,5,18,2,5,2});
        ArrayList<Integer> yCoordinate = new ArrayListUtils<Integer>().getArrayList(new Integer[]{17,16,17,4,13,20});
        System.out.println(findMaxPoints(xCoordinate, yCoordinate));
    }

    public int findMaxPoints(ArrayList<Integer> xCoordinates, ArrayList<Integer> yCoordinates) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < xCoordinates.size() - 1; i++) {
            map.clear();
            int count = 1;
            int slop = 0;
            for (int j = i + 1; j < xCoordinates.size(); j++) {
                int x1 = xCoordinates.get(i);
                int y1 = yCoordinates.get(i);
                int x2 = xCoordinates.get(j);
                int y2 = yCoordinates.get(j);
                if (x1 == x2 && y1 == y2) {
                    count++;
                    continue;
                }
                int xDif = x1 - x2;
                int yDif = y1 - y2;
                slop = 0;
//                xDif = xDif < 0 ? xDif * -1 : xDif;
                if (xDif != 0) {
                    slop = yDif / xDif;
                }

                map.put(slop, map.containsKey(slop) ? map.get(slop) + 1 : 1);

            }
            max = Math.max(count, max);
            for (int cSlop : map.keySet()){

                max = Math.max(max, map.get(cSlop) + count);
            }
        }

        return max;
    }

    private int gcd1(int x, int y) {
        if (x < y) {
            return gcd1(y, x);
        }

        if (x == 0) {
            return y;
        }

        if (y == 0) {
            return x;
        }

        return gcd1(x % y, y);
    }

    public void findSubString() {
        String a = "aaaaaa";
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("aaa");
        //arrayList.add("aaa");
        System.out.println(findSubstring(a, arrayList));
    }


    public ArrayList<Integer> findSubstring(String a, final List<String> b) {
        int wordLength = b.get(0).length();
        ArrayList<String> copy = new ArrayList<String>(b);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.length() - wordLength + 1; i++) {
            String substring = a.substring(i, i + wordLength);
            if (b.contains(substring)) {
                int j, k;
                for (j = 0, k = 0; k < b.size() && i + j < a.length() - wordLength + 1; j += wordLength, k++) {
                    String currentSubstring = a.substring(i + j, i + j + wordLength);
                    if (copy.contains(currentSubstring)) {
                        copy.remove(currentSubstring);
                    } else {
                        break;
                    }
                }
                if (k == b.size()) {
                    result.add(i);
                }

                copy = new ArrayList<String>(b);
            }
        }

        return result;
    }


    public void printQuadraple() {
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{3, 4, 7, 1, 2, 9, 8});
        HashMap<Integer, Set<Integer>> sums = new HashMap<>();
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = i + 1; j < arrayList.size(); j++) {
                if (arrayList.get(i) == arrayList.get(j)) {
                    continue;
                }
                int sum = arrayList.get(i) + arrayList.get(j);
                if (sums.containsKey(sum)) {
                } else {
                    sums.put(sum, new HashSet<>());
                }
                sums.get(sum).add(j);
                sums.get(sum).add(i);
            }
        }

        for (Integer sum : sums.keySet()) {
            if (sums.get(sum).size() >= 4)
                System.out.println(sum + "  " + sums.get(sum));
        }

    }

    public void printLongestSubStringWithoutRepeatingCharacter() {
        String sentence = "labcpdalbcbbpkjorlb";
        int start = 0;
        int minStart = 0;
        int maxLength = Integer.MIN_VALUE;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            char currentCharacter = sentence.charAt(i);
            if (map.containsKey(currentCharacter)) {
                if (start <= map.get(currentCharacter)) {
                    start = map.get(currentCharacter) + 1;
                }
            }
            if (i - start + 1 > maxLength) {
                minStart = start;
                maxLength = i - start + 1;

            }


            map.put(currentCharacter, i);


        }

        System.out.println(sentence.substring(minStart, minStart + maxLength));
    }

    public void pringShortestSubStringContainAllTheCharacter() {
        String sentence = "this is a test string";
        String substring = "tist";
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < substring.length(); i++) {
            hashMap.put(substring.charAt(i),
                    hashMap.containsKey(substring.charAt(i))
                            ? hashMap.get(substring.charAt(i)) + 1
                            : 1);

        }

        int total = 0;
        int minlength = Integer.MAX_VALUE;
        int start = 0;
        for (int head = 0, tail = 0; tail < sentence.length(); tail++) {
            if (!hashMap.containsKey(sentence.charAt(tail))) {
                continue;
            }

            Character character = sentence.charAt(tail);
            hashMap.put(character, hashMap.get(character) - 1);
            if (hashMap.get(character) >= 0) {
                total++;
            }


            if (total == substring.length()) {

                Character headCharacter = sentence.charAt(head);
                while ((!hashMap.containsKey(headCharacter) || hashMap.get(headCharacter) < 0)) {
                    if (hashMap.containsKey(headCharacter)) {
                        hashMap.put(headCharacter, hashMap.get(headCharacter) + 1);
                    }
                    head++;
                    headCharacter = sentence.charAt(head);
                }

                if (tail - head + 1 < minlength) {
                    minlength = tail - head + 1;
                    start = head;
                }
            }

        }

        System.out.println(sentence.substring(start, start + minlength));
    }

    public void nonDuplicateCharacter() {
        String string = "1123324523845";
        DoubleLinkedList head = null;
        DoubleLinkedList tail = null;
        Map<Character, DoubleLinkedList> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (map.containsKey(character)) {
                DoubleLinkedList toBeRemoved = map.get(character);
                if (toBeRemoved == null) {
                    continue;
                }
                DoubleLinkedList left = toBeRemoved.getPrevious();
                DoubleLinkedList right = toBeRemoved.getNext();
                if (tail == head && head == toBeRemoved) {
                    tail = head = null;
                } else if (head == toBeRemoved) {
                    head = head.getNext();
                    head.setPrevious(null);
                    toBeRemoved.setNext(null);
                } else if (tail == toBeRemoved) {
                    left.setNext(null);
                    tail = left;
                    toBeRemoved.setPrevious(null);
                } else {
                    right.setPrevious(left);
                    left.setNext(right);
                }
                map.put(character, null);

            } else {
                DoubleLinkedList newNode = new DoubleLinkedList(Integer.parseInt(String.valueOf(character)));
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.setNext(newNode);
                    newNode.setPrevious(tail);
                    tail = tail.getNext();
                }
                map.put(character, newNode);
            }
            if (head != null) {
                System.out.print(head.getData() + "->");
            } else {
                System.out.print("No duplicat node");
            }
        }
    }

    public int get(int key) {
        return linkedHashMap.containsKey(key)
                ? linkedHashMap.get(key)
                : -1;
    }

    public void set(int key, int value) {
        linkedHashMap.put(key, value);

    }


    class LRU<K, V> extends LinkedHashMap<K, V> {
        private int cacheSize;

        @Override
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            return super.computeIfPresent(key, remappingFunction);
        }

        public LRU(int cacheSize) {
            //super(16,1,true);
            this.cacheSize = cacheSize;
        }

        @Override
        public V put(K k, V v) {
            return super.put(k, v);
        }

        @Override
        public V get(Object key) {
            return super.containsKey(key)
                    ? super.get(key)
                    : null;
        }


        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() >= cacheSize;
        }
    }

    public void maxPoints() {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        System.out.println(maxPoints(x, y));
    }

    public static int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.isEmpty() || b.isEmpty() || a.size() != b.size()) {
            return 0;
        }
        if (a.size() == 1) {
            return 1;
        }
        HashMap<Solution, Integer> map = new HashMap<>();
        int count = 0;
        int ans = -1;
        for (int i = 0; i < a.size() - 1; i++) {
            count = 1;
            Solution solution = null;
            int max = 0;
            map.clear();
            for (int j = i + 1; j < b.size(); j++) {
                int xDiff = a.get(i) - a.get(j);
                int yDiff = b.get(i) - b.get(j);
                if (xDiff == 0 && yDiff == 0) {
                    count++;
                    continue;
                }

                if (xDiff < 0) {
                    xDiff *= -1;
                    yDiff *= -1;
                }

                int gcd = gcd(xDiff, yDiff);
                solution = new Solution(3);
                max = Math.max(map.containsKey(solution) ? map.get(solution) + 1 : 1, max);
                map.put(solution, max);

            }
            max += count;
            ans = Math.max(ans, max);
        }

        return ans;
    }

    public static int gcd(int a, int b) {
        int c;
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        a = a < 0 ? a * -1 : a;
        b = b < 0 ? b * -1 : b;
        while (a != 0 && b != 0) {
            c = a;
            a = b % a;
            b = c;
        }

        return a == 0 ? b : a;
    }


    public static String minWindow1(String S, String T) {
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
            if (!map.containsKey(S.charAt(tail))) {
                continue;
            }

            map.put(S.charAt(tail), map.get(S.charAt(tail)) - 1);

            if (map.get(S.charAt(tail)) >= 0) {
                length++;
            }

            if (length == T.length()) {
                for (int i = start; i <= tail; i++) {
                    if (!map.containsKey(S.charAt(i)) || map.get(S.charAt(i)) < 0) {
                        if (map.containsKey(S.charAt(i))) {
                            map.put(S.charAt(i), map.get(S.charAt(i)) + 1);
                        }
                        start++;
                    } else {
                        break;
                    }
                }

                if (minLength > (tail - start + 1) || minLength == 0) {
                    minLength = tail - start + 1;
                    startIndex = start;
                }
            }
        }

        if (minLength == S.length() + 1) {
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
                ? result
                : "";
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

    public Column(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Column)) {
            return false;
        }

        Column column = (Column) obj;
        return this.index1 == column.index1
                && this.index2 == column.index2;
    }

    @Override
    public int hashCode() {
        return 31 * index1 + 31 * index2;
    }
}
