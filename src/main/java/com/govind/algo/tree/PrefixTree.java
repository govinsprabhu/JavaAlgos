package com.govind.algo.tree;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.ArrayList;

/**
 * Created by govindp on 11/16/2015.
 */
class Trie {
    Trie[] tries;
    int prefix;

    public Trie() {
        tries = new Trie[26];
    }

    public static void main(String[] args) {
        Trie root = new Trie();

    }

    public ArrayList<String> prefix(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            insert(array.get(i), 0);
        }

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            arrayList.add(search(array.get(i), 0));
        }
        return arrayList;
    }

    public void insert(String word, int index) {
        if (index == word.length()){
            return;
        }
        char currentChar = (char) (word.charAt(index) - 'a');
        prefix++;
        if (tries[currentChar] == null) {
            tries[currentChar] = new Trie();
        }

        tries[currentChar].insert(word, index + 1);
    }

    public String search(String word, int index) {
        if (prefix == 1) {
            return word.substring(0, index);
        }
        if (index == word.length()){
            return word.substring(0, word.length());
        }

        return tries[word.charAt(index) - 'a'].search(word, index + 1);
    }

}

public class PrefixTree {
    public static void main(String[] args) {
        System.out.println(new PrefixTree().prefix(new ArrayListUtils<>().getArrayList(new String[]{"zebra", "dove", "dog", "duck"})));

    }

    public ArrayList<String> prefix(ArrayList<String> array) {
        Trie trie = new Trie();
        for (int i = 0; i < array.size(); i++) {
            trie.insert(array.get(i), 0);
        }

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            arrayList.add(trie.search(array.get(i), 0));
        }
        return arrayList;
    }

}
