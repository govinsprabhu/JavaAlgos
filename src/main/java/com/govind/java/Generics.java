package com.govind.java;


import java.util.Iterator;

/**
 * Created by govindp on 9/18/2015.
 */
public class Generics<T> implements Iterable<T>{
    private int head = 0;
    private T[] items;

    public Generics(int size) {
        this.items = (T[]) new Object[size];
    }

    public static void main(String[] args) {

    }

    public int convert(int index){
        if(index < 0){
           index  = index + items.length;
        }

        return (index + head)% items.length;
    }

    public void rotate(int index){
        head = convert(index);
    }

    public T get(int index){
        if(index < 0 || index >= items.length){
            throw new IndexOutOfBoundsException();
        }
        return get(convert(index));
    }

    public void set(int index, T value){
        items[convert(index)] =  value;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericsWithIterator<>(this);
    }

    private class GenericsWithIterator<T> implements Iterator<T>{
        T[] items;
        int current = -1;

        public GenericsWithIterator(Generics<T> ts) {
            items = ts.items;
        }

        @Override
        public boolean hasNext() {
            return current < items.length;
        }

        @Override
        public T next() {
            current++;
            return items[convert(current)];
        }

        @Override
        public void remove() {
            throw  new UnsupportedOperationException();

        }
    }
}
