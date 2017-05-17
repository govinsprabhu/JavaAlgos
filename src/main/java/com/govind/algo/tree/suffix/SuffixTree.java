package com.govind.algo.tree.suffix;

import java.util.ArrayList;

/**
 * Created by govindp on 12/10/2015.
 */
public class SuffixTree {
    SuffixTreeNode root = new SuffixTreeNode();

    public SuffixTree(String s) {
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            if (i == 3){
                System.out.println();
            }
            root.insertString(suffix, i);
        }
    }

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree("banana");
        System.out.println(suffixTree.search("ana"));
    }

    public ArrayList<Integer> search(String s) {
        return root.search(s);
    }
}