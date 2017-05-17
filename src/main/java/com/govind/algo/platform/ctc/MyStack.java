package com.govind.algo.platform.ctc;


import java.util.*;

/**
 * Created by 609600403 on 19/06/2016.
 */
public class MyStack {
    // Push element x onto stack
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack);
    }
    public void push(int x) {
        queue1.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (!queue2.isEmpty()){
            queue2.poll();
        }
        top();
        queue2.poll();

    }

    // Get the top element.
    public int top() {
        if (!queue2.isEmpty()){
            return queue2.peek();
        }
        while (!queue1.isEmpty()){
            queue2.add(queue1.poll());
        }
        while (queue2.size() != 1){
            queue1.add(queue2.poll());
        }

        return queue2.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}