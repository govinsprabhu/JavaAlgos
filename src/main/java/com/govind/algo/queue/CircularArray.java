package com.govind.algo.queue;

import java.util.Iterator;

/**
 * Created by govindp on 10/26/2015.
 */
public class CircularArray<T> implements Iterable<T>{
    T[] array;
    int head;

    public CircularArray(int size) {
        this.array = (T[]) new Object[size];
        this.head = 0;
    }

    private int convert(int index){
        if (index < 0){
            index += array.length;
        }

        return (head + index) % array.length;
    }


    public void rightShift(int rightShift){
        head = convert(rightShift);
    }


    public T get(int index){
        if (index < 0 && index >= array.length){
            throw  new IndexOutOfBoundsException();
        }

        return array[convert(index)];
    }


    public void set(int index, T value){
        array[convert(index)] = value;
    }

    public static void main(String[] args) {
        CircularArray<Integer> circularArray = new CircularArray<Integer>(3);
        circularArray.set(0,1);
        circularArray.set(1,2);
        circularArray.set(2,3);
        circularArray.rightShift(2);
        for (Integer integer :circularArray ){
            System.out.print(integer);
        }

        String word = " wordk";
        word.toLowerCase();
    }

    @Override
    public Iterator iterator() {
        return new CircularIterator(this);
    }


    private class CircularIterator<T1> implements Iterator<T1>{
        int current;
        T1[] currenArray;

        public CircularIterator(CircularArray<T> array) {
            current = -1;
            this.currenArray = (T1[]) array.array;
        }

        @Override
        public boolean hasNext() {
            return current < array.length;
        }

        @Override
        public T1 next() {
            ++current;
            T1 integer = (T1) currenArray[convert(current)];
            return integer;
        }
    }
}
