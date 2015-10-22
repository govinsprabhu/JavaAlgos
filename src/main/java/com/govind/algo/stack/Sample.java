package com.govind.algo.stack;

import com.govind.util.ArrayListUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by govindp on 9/30/2015.
 */
public class Sample {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(6);
        arrayList.add(2);
        arrayList.add(32);
        arrayList.add(32);
        arrayList.add(1);
/*
        arrayList.add(28);
        arrayList.add(39);
        arrayList.add(20);
        arrayList.add(28);
*/
        System.out.println(nextGreater(arrayList));
    }

    public static ArrayList<Integer> nextGreater(ArrayList<Integer> a) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(-1);
        if (a.size() == -1){
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(a.get(a.size() - 1));
        for (int i = a.size() -2; i >= 0; i--){
            if (!stack.isEmpty() && stack.peek() > a.get(i)){
                result.add(stack.peek());
            }else {
                while (!stack.isEmpty() && stack.peek() <= a.get(i)){
                    stack.pop();
                }

                if (stack.isEmpty()){
                    result.add(-1);
                }else {
                    result.add(stack.peek());
                }
            }
            stack.push(a.get(i));
        }

        ArrayList<Integer> reverse = new ArrayList<>();
        for (int i = result.size() - 1; i  >= 0; i--){
            reverse.add(result.get(i));
        }
        return reverse;
    }

    public static ArrayList<Integer> slidingMaximum(final List<Integer> a, int b) {
        ArrayList<Integer> resulting = new ArrayList<>();

        LinkedList<Integer> processing = new LinkedList<>();
        if(a.size() < b){
            return resulting;
        }
        for (int i = 0; i < b; i++) {
            while (!processing.isEmpty() && a.get(processing.getLast()) < a.get(i)) {
                processing.removeLast();
            }
            processing.addLast(i);
        }

        int count = 1;
        for (int i = b; i < a.size(); i++) {
            resulting.add(a.get(processing.getFirst()));

            if (processing.get(0) + b <= i) {
                processing.removeFirst();
            }
            while (!processing.isEmpty() && a.get(processing.getLast()) < a.get(i)) {
                processing.removeLast();
            }

            processing.addLast(i);
        }
        resulting.add(a.get(processing.getFirst()));
        return resulting;
    }

    public static int largestRectangleArea(ArrayList<Integer> a) {
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        while (i < a.size()) {
            if (stack.isEmpty() || a.get(stack.peek()) < a.get(i)) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                int area = a.get(top) * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = a.get(top) * (stack.isEmpty() ? i : (i - stack.peek() - 1));
            maxArea = Math.max(maxArea, area);

        }

        return maxArea;
    }

    private static Stack<Integer> stack = new Stack<>();
    private static Stack<Integer> min = new Stack<>();

    public static void push(int x) {
        if (stack.isEmpty()) {
            min.push(x);
        } else {
            int top = min.peek();
            if (x < top) {
                min.push(x);
            } else {
                min.push(min.peek());
            }
        }
        stack.push(x);
    }

    public static void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            min.pop();
        }
    }

    public static int top() {
        return stack.isEmpty()
                ? -1
                : stack.peek();

    }

    public static int getMin() {
        return min.peek();
    }

    public static int trap(final List<Integer> a) {
        int[] left = new int[a.size()];
        int[] right = new int[a.size()];
        left[0] = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            left[i] = Math.max(a.get(i), left[i - 1]);
        }
        right[a.size() - 1] = a.get(a.size() - 1);
        for (int i = a.size() - 2; i >= 0; i--) {
            right[i] = Math.max(a.get(i), right[i + 1]);
        }

        int water = 0;

        for (int i = 0; i < a.size(); i++) {
            water += Math.min(left[i], right[i]) - a.get(i);
        }

        return water;


    }

    public static int evalRPN(ArrayList<String> a) {

        Stack<Integer> stack = new Stack<>();
        for (String string : a) {
            if (string == "+" || string == "-" || string == "*" || string == "/") {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(getResult(first, second, string));
            } else {
                stack.push(Integer.valueOf(string));
            }
        }
        return stack.peek();
    }

    private static int getResult(int operator1, int operator2, String operant) {

        switch (operant) {
            case "+":
                return operator1 + operator2;
            case "-":
                return operator1 - operator2;
            case "*":
                return operator1 * operator2;
            case "/":
                if (operator2 == 0) {
                    return Integer.MAX_VALUE;
                }
                return operator1 / operator2;
        }
        return -1;
    }

    public static int braces(String a) {
        Stack<Character> stack = new Stack<>();

        boolean isOperator = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == ')') {
                isOperator = false;
                while (stack.peek() != '(') {
                    if (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '+' || stack.peek() == '-') {
                        isOperator = true;
                    }
                    stack.pop();
                }
                stack.pop();
                if (isOperator == false) {
                    return 1;
                }
            } else {
                stack.push(a.charAt(i));
            }
        }

        return 0;
    }

    public static void printPar(int l, int r, char[] str, int count) {
        if (l < 0 || r < l) return; // invalid state
        if (l == 0 && r == 0) {
            System.out.println(str); // found one, so print it
        } else {
            if (l > 0) { // try a left paren, if there are some available
                str[count] = '(';
                printPar(l - 1, r, str, count + 1);
            }
            if (r > l) { // try a right paren, if there’s a matching left
                str[count] = ')';
                printPar(l, r - 1, str, count + 1);
            }
        }
    }

    public static void printPar(int l, int r, String result) {
        if (l < 0 || r < l) return; // invalid state
        if (l == 0 && r == 0) {
            System.out.println(result); // found one, so print it
        } else {
            if (l > 0) { // try a left paren, if there are some available
                result += '(';
                printPar(l - 1, r, result);
            }
            if (r > l) { // try a right paren, if there’s a matching left
                result += ')';
                printPar(l, r - 1, result);
            }
        }
    }

    public static void parenthesis(int left, int right, String result) {

        if (left > right || left < 0) {
            return;
        }

        if (left == right && right == 0) {
            System.out.println(result);
            return;
        }

        if (left > 0) {
            result += "(";
            parenthesis(left - 1, right, result);
        }
        if (right > left) {
            result += ")";
            parenthesis(left, right - 1, result);
        }
    }

    public static String simplifyPath(String a) {
        Stack<String> stack = new Stack<>();
        String name = "";
        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) == '/') {
                if (name.isEmpty()) {
                    continue;
                }
                if (name.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (name.equals(".")) {
                    continue;
                } else {
                    stack.push(name);
                }
                name = "";
            } else {
                name = name.concat(String.valueOf(a.charAt(i)));
            }
        }

        if (!name.isEmpty()) {
            stack.push(name);
        }
        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (!stack.isEmpty()) {
            stringBuilder.append("/");
            //stringBuilder.(stack.pop());
        }

        return String.valueOf(stringBuilder);
    }

    public static String reverseString(String a) {


        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            stack.push(a.charAt(i));
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            stringBuilder = stringBuilder.append(stack.pop());
        }
        return new String(stringBuilder);
    }

    public static int isValid(String a) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (; i < a.length(); i++) {
            if (a.charAt(i) == '(' || a.charAt(i) == '[' || a.charAt(i) == '{') {
                stack.push(a.charAt(i));
            } else if (a.charAt(i) == ')' || a.charAt(i) == ']' || a.charAt(i) == '}') {
                if (stack.isEmpty()) {
                    break;
                }
                char top = stack.peek();

                if (a.charAt(i) == ')' && top == '(') {
                    stack.pop();
                } else if (a.charAt(i) == '}' && top == '{') {
                    stack.pop();
                } else if (a.charAt(i) == ']' && top == '[') {
                    stack.pop();
                } else {
                    break;
                }
            }
        }

        if (a.length() == i && stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}
