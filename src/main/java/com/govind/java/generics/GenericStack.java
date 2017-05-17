package com.govind.java.generics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 609600403 on 15/11/2016.
 */
public class GenericStack<E> {
    private final int size;
    private E[] array;
    private int top;

    public GenericStack(int size) {
        this.size = size;
        this.array = (E[]) new Object[size];
        top = -1;
    }

    public void addAll(Iterable<E> eles){
        for (E e : eles){
            array[++top] = e;
        }
    }

    public E pop(){
        if (top == -1){
            System.out.println("Stack is empty");
            return null;
        }
        return array[top--];
    }

    public void push(E ele){
        if (top == size - 1){
            System.out.println("Stack is full");
            return;
        }
        array[++top] = ele;
    }

    public static void main(String[] args) {
        GenericStack<Object> genericStack = new GenericStack<>(5);
        genericStack.addAll(Arrays.asList(new Integer[]{1,2,3}));
    }

}
