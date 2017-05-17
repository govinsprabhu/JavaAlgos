package com.govind.algo.platform.ctc;

/**
 * Created by 609600403 on 20/06/2016.
 */
public class ThreeStack {
    int stackSize ;
    int indexUsed ;
    int[] stackPointer ;
    StackNode[] buffer ;

    ThreeStack(){
         stackSize = 300;
         indexUsed = 0;
         stackPointer = new int [] {-1,-1,-1};
        buffer = new StackNode[stackSize * 3];

    }
    void push(int value, int stackNum) {
        int lastIndex = stackPointer[stackNum];
        stackPointer[stackNum] = indexUsed;
        indexUsed++;
        buffer[stackPointer[stackNum]]=new StackNode(lastIndex,value);
    }
    int pop(int stackNum) {
        int value = buffer[stackPointer[stackNum]].value;
        int lastIndex = stackPointer[stackNum];
        stackPointer[stackNum] = buffer[stackPointer[stackNum]].previous;
        buffer[lastIndex] = null;
        indexUsed--;
        return value;
    }
    int peek(int stack) { return buffer[stackPointer[stack]].value; }
    boolean isEmpty(int stackNum) { return stackPointer[stackNum] == -1; }

    class StackNode {
        public int previous;
        public int value;
        public StackNode(int p, int v){
            value = v;
            previous = p;
        }
    }
    public static void main(String[] args) {
        ThreeStack threeStack = new ThreeStack();
        threeStack.push(1,0);
        threeStack.push(2,1);
        threeStack.push(4,1);
        threeStack.push(6,1);
        threeStack.push(3,2);
        threeStack.push(8,0);
        threeStack.push(19,2);
        threeStack.push(29,2);
        System.out.println(threeStack.pop(1));
        System.out.println(threeStack.pop(1));
        threeStack.push(8,1);
        threeStack.push(12,1);
        System.out.println(threeStack.pop(1));
        System.out.println(threeStack.pop(1));

    }



}
