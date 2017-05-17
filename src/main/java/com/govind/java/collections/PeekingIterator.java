package com.govind.java.collections;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by govindp on 10/23/2015.
 */

public class PeekingIterator implements Iterator<Integer> {
    boolean hasPeekend;
    int currentValue;
    Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        hasPeekend = false;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasPeekend) {
            hasPeekend = true;
            currentValue = iterator.next();
        }

        return currentValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (hasPeekend) {
            hasPeekend = false;
            return currentValue;
        }

        currentValue = iterator.next();
        return currentValue;
    }

    @Override
    public boolean hasNext() {
        return hasPeekend ||iterator.hasNext() ;
    }
}


