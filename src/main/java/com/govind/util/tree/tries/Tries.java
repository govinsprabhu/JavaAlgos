package com.govind.util.tree.tries;

/**
 * Created by govindp on 10/22/2015.
 */
public class Tries {
    private int count;
    private Tries[] tries ;
    private int prefix;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Tries getTries(int index) {
        return tries[index];
    }

    public void setTries(Tries tries, int index) {
        this.tries[index] = tries;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public Tries() {
        tries = new Tries[26];
    }
}
