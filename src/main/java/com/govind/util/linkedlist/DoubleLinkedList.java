package com.govind.util.linkedlist;

/**
 * Created by govindp on 9/20/2015.
 */
public class DoubleLinkedList {
    int data;
    DoubleLinkedList previous;
    DoubleLinkedList next;

    public int getData() {
        return data;
    }

    public DoubleLinkedList(int data) {
        this.data = data;
    }



    public void setData(int data) {
        this.data = data;
    }

    public DoubleLinkedList getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedList previous) {
        this.previous = previous;
    }

    public DoubleLinkedList getNext() {
        return next;
    }

    public void setNext(DoubleLinkedList next) {
        this.next = next;
    }

    public static void print(DoubleLinkedList doubleLinkedList){
        while (doubleLinkedList.getPrevious() != null){
            System.out.print(doubleLinkedList.getData() + " ");
            doubleLinkedList = doubleLinkedList.getPrevious();
        }
        System.out.println();
    }


    public static DoubleLinkedList getDoubleLinkedList(){
        DoubleLinkedList doubleLinkedList1 = new DoubleLinkedList(1);
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList(2);
        DoubleLinkedList doubleLinkedList3 = new DoubleLinkedList(3);
        DoubleLinkedList doubleLinkedList4 = new DoubleLinkedList(4);
        DoubleLinkedList doubleLinkedList5 = new DoubleLinkedList(5);
        DoubleLinkedList doubleLinkedList6 = new DoubleLinkedList(6);
        DoubleLinkedList doubleLinkedList7 = new DoubleLinkedList(7);
        doubleLinkedList1.setNext(doubleLinkedList2);
        doubleLinkedList2.setPrevious(doubleLinkedList1);
        doubleLinkedList2.setNext(doubleLinkedList3);
        doubleLinkedList3.setPrevious(doubleLinkedList2);
        doubleLinkedList3.setNext(doubleLinkedList4);
        doubleLinkedList4.setPrevious(doubleLinkedList3);
        doubleLinkedList4.setNext(doubleLinkedList5);
        doubleLinkedList5.setPrevious(doubleLinkedList4);
        doubleLinkedList5.setNext(doubleLinkedList6);
        doubleLinkedList6.setPrevious(doubleLinkedList5);
        return doubleLinkedList1;
    }


    public static DoubleLinkedList getDoubleLinkedListWithDownPointer(){
        DoubleLinkedList doubleLinkedList1 = new DoubleLinkedList(5);
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList(10);
        DoubleLinkedList doubleLinkedList3 = new DoubleLinkedList(19);
        DoubleLinkedList doubleLinkedList4 = new DoubleLinkedList(28);
        doubleLinkedList1.setNext(doubleLinkedList2);
        doubleLinkedList2.setNext(doubleLinkedList3);
        doubleLinkedList3.setNext(doubleLinkedList4);
        doubleLinkedList1.setPrevious(new DoubleLinkedList(7));
        doubleLinkedList1.getPrevious().setPrevious(new DoubleLinkedList(8));
        doubleLinkedList1.getPrevious().getPrevious().setPrevious(new DoubleLinkedList(30));
        doubleLinkedList2.setPrevious(new DoubleLinkedList(20));
        doubleLinkedList3.setPrevious(new DoubleLinkedList(22));
        doubleLinkedList3.getPrevious().setPrevious(new DoubleLinkedList(50));
        doubleLinkedList4.setPrevious(new DoubleLinkedList(35));
        doubleLinkedList4.getPrevious().setPrevious(new DoubleLinkedList(40));
        doubleLinkedList4.getPrevious().getPrevious().setPrevious(new DoubleLinkedList(45));
        return doubleLinkedList1;

    }


    public static  int length(DoubleLinkedList doubleLinkedList){
        return length(doubleLinkedList,0);

    }

    private static int length(DoubleLinkedList doubleLinkedList, int length) {
        if (doubleLinkedList == null){
            return length;
        }

        return length(doubleLinkedList.getNext(), length + 1);
    }
}
