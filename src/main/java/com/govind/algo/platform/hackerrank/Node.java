package com.govind.algo.platform.hackerrank;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class Node {
    public  int frequency; // the frequency of this tree
    public  char data;
    public  Node left, right;

    public Node(char data) {
        this.data = data;
    }

    public Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
