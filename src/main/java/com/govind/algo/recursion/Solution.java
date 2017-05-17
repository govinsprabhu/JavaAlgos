package com.govind.algo.recursion;

import com.govind.algo.backtraking.Sample;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;

/**
 * Created by govindp on 10/26/2015.
 */
public class Solution {
    public static void main(String[] args) {
        String[] strings = {"DAMP", "LAMP", "LIMP", "LIME", "LIKE", "DINK", "DIE", "AMP", "LIEM"};
        Set<String> set = new HashSet<>(Arrays.asList(strings));
        SuffixTree suffixTree = new SuffixTree();
        suffixTree.insert("BIBS");
        System.out.println(suffixTree.search("BS"));
        //System.out.println(new Solution().getChain("DAMP", "LIKE", set));
    }


    public List<String> getChain(String startWord, String endWord, Set<String> dictionary) {
        Queue<String> activeQueue = new LinkedList<>();
        activeQueue.add(startWord);
        List<String> result = new ArrayList<>();
        Map<String, String> map = new TreeMap<>();
        while (!activeQueue.isEmpty()) {
            String first = activeQueue.poll();
            for (String string : getEditedWords(first, dictionary)) {

                if (string.equals(endWord)) {
                    result.add(0, string);
                    map.put(string, first);
                    String start = map.get(string);
                    while (!start.equals(startWord)) {
                        result.add(0, start);
                        start = map.get(start);
                    }
                    result.add(0, startWord);
                    break;
                }
                if (dictionary.contains(string) && !map.containsKey(string)) {

                    map.put(string, first);
                    activeQueue.add(string);
                }
            }
        }

        return result;
    }

    private Set<String> getEditedWords(String first, Set<String> dictionary) {
        char[] charArray = first.toCharArray();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < first.length(); i++) {
            for (int j = 'A'; j <= 'Z'; j++) {
                if (!(first.charAt(i) == j)) {
                    charArray[i] = (char) j;

                }
                if (dictionary.contains(String.valueOf(charArray)))
                    set.add(String.valueOf(charArray));
            }
            charArray[i] = first.charAt(i);
        }

        return set;
    }


    public ArrayList<Integer> getList(String start, String end, ArrayList<String> dictionary) {

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            ArrayList<String> list = getList(word);
        }
        return null;
    }

    private ArrayList<String> getList(String word) {
        char[] chars = word.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 'a'; i <= 'z'; i++) {
                if (chars[i] != i)
                    chars[i] = (char) j;
            }
        }
        return null;
    }
}

class SuffixTree {
    SuffixNode suffixNode;

    public SuffixTree() {
        suffixNode = new SuffixNode();
    }

    public void insert(String string) {
        if (string != null && !string.isEmpty()) {
            for (int i = 0; i < string.length(); i++) {
                String substring = string.substring(i);
                suffixNode.insert(substring, i);
            }
        }
    }

    public ArrayList<Integer> search(String subString){
        return  suffixNode.search(subString);
    }
}

class SuffixNode {
    Map<Character, SuffixNode> map = new HashMap<>();
    ArrayList<Integer> indexex = new ArrayList<>();

    public void insert(String string, int index) {
        indexex.add(index);
        if (string != null && !string.isEmpty()) {
            SuffixNode child;
            char first = string.charAt(0);
            if (map.containsKey(first)) {
                child = map.get(first);
            } else {
                child = new SuffixNode();
                map.put(first, child);
            }
            String substring = string.substring(1);
            child.insert(substring, index);
        }
    }

    public ArrayList<Integer> search(String string) {
        if (string == null || string.isEmpty()) {
            return indexex;
        }

        char first = string.charAt(0);
        if (map.containsKey(first)) {
            String substring = string.substring(1);
            return map.get(first).search(substring);
        }
        return null;
    }
}