package com.govind.algo.stack;

import java.util.Stack;

/**
 * Created by govindp on 11/17/2015.
 */
public class ModifiedStackWithTop {
    Stack<Integer> stack = new Stack<>();
    Integer current;

    public ModifiedStackWithTop() {
        current = null;
    }

    public static void main(String[] args) {
        new ModifiedStackWithTop().getStackTopUsingPopAndPush();
    }

    public void getStackTopUsingPopAndPush() {

        push(1);
        push(2);
        push(3);
        push(4);
        pop();
        getTop();
        getTop();
        pop();
        pop();
        push(5);
        getTop();
        pop();
        push(6);
        pop();
        getTop();

    }

    public void getTop() {
        if (stack.isEmpty()){
            System.out.println("stack is empty");
            return;
        }
        if (current == null){
            current = stack.pop();
        }

        System.out.println("top "+current);
    }

    public void push(int value) {
        stack.push(value);
        current = null;
    }

    public void pop() {
        if (stack.isEmpty()){
            System.out.println("stack is empty");
            return;
        }
        if (current == null) {
            System.out.println("pop "+stack.pop());
        }else {
            System.out.println("pop "+current);
            current = null;
        }
    }
}
