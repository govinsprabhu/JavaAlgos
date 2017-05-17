package com.govind.algo.platform.interviewbit.stackandqueue;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 609600403 on 15/04/2016.
 */
public class Sample {
    public static void main(String[] args) {
        System.out.println(new com.govind.algo.platform.interviewbit.stackandqueue.Sample().largestRectangleArea(new ArrayListUtils<Integer>().getArrayList(new Integer[]{8,7,5,4,3})));
    }

    public int largestRectangleArea(ArrayList<Integer> a) {
        Stack<Integer> stack = new Stack<>();
        int maxRectangle = 0;
        for (int i = 0; i < a.size(); i++) {
            if (stack.isEmpty() || a.get(stack.peek()) <  a.get(i)){
                stack.push(i);
            }else {
                while (!stack.isEmpty() && a.get(stack.peek()) > a.get(i)) {
                    int top = stack.pop();
                    int area = a.get(top) * (i -( stack.isEmpty() ? 0: stack.peek() + 1));
                    if (area > maxRectangle){
                        maxRectangle = area;
                    }
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()){
            int top = stack.pop();
            int area = a.get(top) * (a.size() -( stack.isEmpty() ? 0: stack.peek() + 1));
            if (area > maxRectangle){
                maxRectangle = area;
            }
        }

        return maxRectangle;
    }
    public int trap(final List<Integer> a) {
        int[] left = new int[a.size()];
        left[0] = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            left[i] = Math.max(a.get(i), left[i - 1]);
        }

        int[] right = new int[a.size()];
        right[a.size() - 1] = a.get(a.size() - 1);
        for (int i = a.size() - 2; i >= 0; i--) {
            right[i] = Math.max(a.get(i), right[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < a.size(); i++) {
            water += (Math.min(left[i], right[i]) - a.get(i));
        }

        return water;
    }


    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.pop().compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peek();
    }

    public int getMin() {
        return minStack.isEmpty() ? -1 : minStack.peek();
    }


    public int evalRPN(ArrayList<String> a) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.size(); i++) {
            if (!(a.get(i).equals("+") || a.get(i).equals("-") || a.get(i).equals("*") || a.get(i).equals("/"))) {
                stack.push(Integer.valueOf(a.get(i)));
            } else {
                int second = stack.pop();
                int first = stack.pop();
                int result = result(first, second, a.get(i));
                stack.push(result);
            }
        }

        return stack.pop();
    }

    public int result(int first, int second, String op) {
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;

            case "/":
                return first / second;
        }
        return 0;
    }

    public ArrayList<Integer> slidingMaximum(final List<Integer> a, int b) {
        ArrayList<Integer> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        if (a.size() < b) {
            for (int ele : a) {
                max = Math.max(max, ele);
            }


            result.add(max);
            return result;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < b; i++) {
            if (linkedList.isEmpty()) {
                linkedList.add(i);
            } else {
                while (!linkedList.isEmpty() && a.get(linkedList.getLast()) < a.get(i)) {
                    linkedList.removeLast();
                }
                linkedList.add(i);
            }

        }
        result.add(a.get(linkedList.getFirst()));
        for (int i = b; i < a.size(); i++) {
            if (i >= linkedList.getFirst() + b) {
                linkedList.removeFirst();
            }

            while (!linkedList.isEmpty() && a.get(linkedList.getLast()) < a.get(i)) {
                linkedList.removeLast();
            }
            linkedList.add(i);

            result.add(a.get(linkedList.getFirst()));
        }

        return result;
    }

    public ArrayList<Integer> prevSmaller(ArrayList<Integer> arr) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        stack.push(-1);
        for (int i = 0; i < arr.size(); i++) {
            while (!stack.isEmpty()) {
                if (stack.peek() < arr.get(i)) {
                    arrayList.add(stack.peek());
                    break;
                }
                stack.pop();
            }

            stack.push(arr.get(i));
        }

        return arrayList;
    }

    public int braces(String a) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '(' || a.charAt(i) == '+' || a.charAt(i) == '-' || a.charAt(i) == '*' || a.charAt(i) == '-' || a.charAt(i) == '/') {
                stack.push(a.charAt(i));
            } else if (a.charAt(i) == ')') {
                if (!(stack.peek() == '+' || stack.peek() == '-' || stack.peek() == '*' || stack.peek() == '-' || stack.peek() == '/')) {
                    return 1;
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    return 1;
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() == '(') {
                return 1;
            }
        }

        return 0;
    }

    public String simplifyPath(String a) {
        if (a == null || a.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            String nextWord = "";
            if (a.charAt(i) == '/') {
                continue;
            } else {
                while (i < a.length() && a.charAt(i) != '/') {
                    nextWord += a.charAt(i++);
                }
            }
            if (nextWord.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (nextWord.equals(".") || nextWord.isEmpty()) {
                continue;
            } else {
                stack.push(nextWord);
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }

        return result.isEmpty() ? "/" : result;
    }
}
