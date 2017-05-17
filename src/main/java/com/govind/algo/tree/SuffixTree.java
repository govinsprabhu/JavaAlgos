package com.govind.algo.tree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by govindp on 12/5/2015.
 */
public class SuffixTree {

    SuffixNode root = new SuffixNode();

    public void insert(String word) {
        for (int i = 0; i < word.length(); i++) {
            root.insertSuffix(word.substring(i), i);
        }
    }


    class LongestCommonString{

        String longest = "";
    }
    public ArrayList<Integer> getIndexes(String word) {
        return root.getIndexes(word);
    }

    public static void main(String[] args) {
        SuffixTree suffixNode = new SuffixTree();
        suffixNode.insert("geeksforgeeksgovindkriandgovindkri");
        System.out.println(suffixNode.findLongestCommonWord());
    }

    public String findLongestCommonWord() {
        LongestCommonString longestCommonString = new LongestCommonString();
        longestSubString(root, longestCommonString, "");
        return longestCommonString.longest;
    }

    public void longestSubString(SuffixNode suffixNode, LongestCommonString longestCommonString, String current) {
        if (suffixNode.indexes.size() >= 2) {
            if (current.length() > longestCommonString.longest.length()) {
                longestCommonString.longest = current;
            }
            //return;
        }
        System.out.println(Integer.MAX_VALUE - 1000);


        HashMap<Character, SuffixNode> map = suffixNode.suffixNodeHashMap;
        for (Character character : map.keySet()) {


            SuffixNode currentSuffixNode = map.get(character);
            longestSubString(currentSuffixNode, longestCommonString, current + character);
        }

    }

}


class SuffixNode {
    HashMap<Character, SuffixNode> suffixNodeHashMap = new HashMap<>();
    ArrayList<Integer> indexes = new ArrayList<>();

    public void insertSuffix(String suffix, int index) {
        indexes.add(index);
        if (0 == suffix.length()) {
            return;
        }
        char character = suffix.charAt(0);
        SuffixNode next;
        if (suffixNodeHashMap.containsKey(character)) {
            next = suffixNodeHashMap.get(character);
        } else {
            next = new SuffixNode();
            suffixNodeHashMap.put(character, next);
        }
        next.insertSuffix(suffix.substring(1), index);
    }

    public ArrayList<Integer> getIndexes(String word) {
        if (word.length() == 0) {
            return indexes;
        }
        char currentChar = word.charAt(0);

        if (!suffixNodeHashMap.containsKey(currentChar)) {
            return null;
        }
        return suffixNodeHashMap.get(currentChar).getIndexes(word.substring(1));
    }

}
