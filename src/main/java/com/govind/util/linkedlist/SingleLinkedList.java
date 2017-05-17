package com.govind.util.linkedlist;

/**
 * Created by govindp on 9/12/2015.
 */
public class SingleLinkedList {
    int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public SingleLinkedList getSingleLinkedList() {
        return singleLinkedList;
    }

    public void setSingleLinkedList(SingleLinkedList singleLinkedList) {
        this.singleLinkedList = singleLinkedList;
    }

    SingleLinkedList singleLinkedList;

    public SingleLinkedList(int data) {
        this.data = data;
    }

    public SingleLinkedList(int data, SingleLinkedList singleLinkedList) {
        this.data = data;
        this.singleLinkedList = singleLinkedList;
    }

    public SingleLinkedList() {
    }

    public static SingleLinkedList getOddLinkedList(){
        SingleLinkedList singleLinkedList1 = new SingleLinkedList(1);
        SingleLinkedList singleLinkedList2 = new SingleLinkedList(2);
        SingleLinkedList singleLinkedList3 = new SingleLinkedList(3);
        SingleLinkedList singleLinkedList4 = new SingleLinkedList(5);
        SingleLinkedList singleLinkedList5 = new SingleLinkedList(7);
        //SingleLinkedList linkedList6 = new SingleLinkedList(1);
        singleLinkedList1.setSingleLinkedList(singleLinkedList2);
        singleLinkedList2.setSingleLinkedList(singleLinkedList3);
        singleLinkedList3.setSingleLinkedList(singleLinkedList4);
        singleLinkedList4.setSingleLinkedList(singleLinkedList5);
        //singleLinkedList1.setSingleLinkedList(singleLinkedList);
        return singleLinkedList1;
    }

    public static SingleLinkedList getEvenLinkedList(){
        SingleLinkedList singleLinkedList1 = new SingleLinkedList(1);
        SingleLinkedList singleLinkedList2 = new SingleLinkedList(2);
        SingleLinkedList singleLinkedList3 = new SingleLinkedList(3);
        SingleLinkedList singleLinkedList4 = new SingleLinkedList(4);
        SingleLinkedList singleLinkedList5 = new SingleLinkedList(5);
        SingleLinkedList singleLinkedList6 = new SingleLinkedList(6);
        singleLinkedList1.setSingleLinkedList(singleLinkedList2);
        singleLinkedList2.setSingleLinkedList(singleLinkedList3);
        singleLinkedList3.setSingleLinkedList(singleLinkedList4);
        singleLinkedList4.setSingleLinkedList(singleLinkedList5);
        //singleLinkedList5.setSingleLinkedList(singleLinkedList6);
        return singleLinkedList1;
    }
    public static void print(SingleLinkedList singleLinkedList){
        while (singleLinkedList  != null){
            System.out.print(singleLinkedList.getData() + " -> ");
            singleLinkedList = singleLinkedList.getSingleLinkedList();
        }
        System.out.println();
    }

}
