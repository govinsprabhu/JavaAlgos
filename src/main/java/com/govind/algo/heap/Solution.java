package com.govind.algo.heap;

import java.util.*;

/**
 * Created by govindp on 10/23/2015.
 */
public class Solution {


    public static void main(String[] args) {
        String input = "G 2 S 2 6 G 1 S 1 5 S 1 2 G 1 G 2";

        new Solution(4).priorityQueue();
    }


    public void priorityQueue(){
        Queue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        heap.add(6);
        heap.add(4);
        heap.add(7);
        heap.add(1);
        heap.add(2);
        heap.add(5);
        for (int element: heap){
            System.out.print(element);
        }
    }


    public void sortToOneDimentionalArray() {
        int[][] array = {{10, 20, 30, 40},
                {15, 25, 35, 45},
                {24, 29, 37, 48},
                {32, 33, 39, 50}};
        int k = array.length;

        HeapForDataWithIndex heapForDataWithIndex = new HeapForDataWithIndex(array.length);
        for (int i = 0; i < k; i++) {
            heapForDataWithIndex.array[i] = new DataWithIndex(i, 0, array[i][0]);
        }

        heapForDataWithIndex.buildHeap();
        for (int i = k; i < array.length * array.length; i++) {
            DataWithIndex dataWithIndex = heapForDataWithIndex.getTop();
            System.out.println(dataWithIndex.data);
            if (dataWithIndex.j < array.length - 1)
                heapForDataWithIndex.replaceTop(new DataWithIndex(dataWithIndex.i, dataWithIndex.j + 1, array[dataWithIndex.i][dataWithIndex.j + 1]));
            else
                heapForDataWithIndex.array[0] = new DataWithIndex(0, 0, Integer.MAX_VALUE);
        }


    }

    public void kThLargest() {
        int[] array = {4, 5, 1, 7, 0, 8, 9, 2};
        int k = 2;
        Heap heap = new Heap(k);
        for (int i = 0; i < k; i++) {
            heap.array[i] = array[i];
        }

        heap.buildHeap();

        for (int i = k; i < array.length; i++) {
            if (array[i] > heap.getKthMin()) {
                heap.insertToTop(array[i]);
            }
        }
        System.out.println(heap.getKthMin());

    }

    class DataWithIndex {
        int i;
        int j;
        int data;

        public DataWithIndex(int i, int j, int data) {
            this.i = i;
            this.j = j;
            this.data = data;
        }
    }

    class HeapForDataWithIndex {
        int size;
        DataWithIndex[] array;

        public HeapForDataWithIndex(int size) {
            this.size = size;
            this.array = new DataWithIndex[size];
        }

        public void buildHeap() {
            for (int i = size / 2; i >= 0; i--) {
                minHeap(i);
            }
        }

        public void minHeap(int index) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smaller = index;
            if (left < size &&array[smaller].data > array[left].data) {
                smaller = left;
            }

            if (right < size && array[smaller].data > array[right].data) {
                smaller = right;
            }

            if (smaller != index) {
                swap(smaller, index);
                minHeap(smaller);
            }
        }

        private void swap(int smaller, int index) {
            DataWithIndex temp = array[smaller];
            array[smaller] = array[index];
            array[index] = temp;
        }

        public DataWithIndex getTop() {
            return array[0];
        }

        public void replaceTop(DataWithIndex dataWithIndex) {
            array[0] = dataWithIndex;
            minHeap(0);
        }
    }


    class Heap {
        int[] array;
        int size;

        protected int getKthMin() {
            return array[0];
        }

        public Heap(int size) {
            this.size = size;
            array = new int[size];
        }

        public void buildHeap() {
            for (int i = size / 2; i >= 0; i--) {
                minHeap(i);
            }
        }

        public void insertToTop(int value) {
            array[0] = value;
            minHeap(0);
        }

        public void minHeap(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIndex = i;
            if (left < size && array[left] < array[minIndex]) {
                minIndex = left;
            }

            if (right < size && array[right] < array[minIndex]) {
                minIndex = right;
            }

            if (minIndex != i) {
                swap(array, i, minIndex);
                minHeap(minIndex);
            }

        }

        private void swap(int[] array, int i, int minIndex) {
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }


    int capacity;
    Map<Integer, LinkedList> map;
    LinkedList<LinkedList<Integer>> current;
    LinkedList head;

    public Solution(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        LinkedList linkedList = map.get(key);
        current.remove(linkedList);
        current.addFirst(linkedList);
        return (int) current.getFirst().getFirst();
    }

    public void set(int key, int value) {
        if (current == null) {
            current = head = new LinkedList<LinkedList>();
        }
        LinkedList list = new LinkedList();
        if (map.containsKey(key)) {
            current.remove(map.get(key));
            LinkedList list1 = new LinkedList();
            list1.add(value);
            current.addFirst(list1);
            map.put(key, list1);
        } else {
            if (map.size() == capacity) {
                LinkedList<Integer> linkedList = current.removeLast();
                map.values().remove(linkedList);
            }
            list.add(value);
            current.addFirst(list);
            map.put(key, current.getFirst());
        }

    }

    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {
            treeMap.put(A.get(i), treeMap.containsKey(A.get(i))
                    ? treeMap.get(A.get(i)) + 1
                    : 1);
            if (i >= B - 1) {

                arrayList.add(treeMap.size());
                if (i >= B && treeMap.get(A.get(i - B)) > 1) {
                    treeMap.put(A.get(i - B), treeMap.get(A.get(i - B)) - 1);
                } else if (i >= B) {
                    treeMap.remove(A.get(i - B));
                }
            }
        }
        return arrayList;
    }

}

class HeapDupli {
    private int[] array;
    private int index;
    private static int MAX_SIZE = 0;
    private Comparator<Integer> comparator;

    public HeapDupli(int maxSize) {
        MAX_SIZE = maxSize;
        this.array = new int[MAX_SIZE];
        this.index = -1;
    }

    public int[] getArray() {
        return array;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public static int getMaxSize() {
        return MAX_SIZE;
    }

    public static void setMaxSize(int maxSize) {
        MAX_SIZE = maxSize;
    }


    public boolean insert(int data) {
        if (index > MAX_SIZE) {
            return false;
        }
        array[++index] = data;
        hepify(index);
        return true;
    }

    public void hepify(int child) {
        int parent = parent(child);
        if (parent != child && array[parent] < array[child]) {
            swap(array, child, parent);
            hepify(parent);
        }
    }

    private void swap(int[] array, int child, int parent) {
        array[child] = array[child] + array[parent];
        array[parent] = array[child] - array[parent];
        array[child] = array[child] - array[parent];
    }

    private int parent(int index) {
        if (index == 0) {
            return 0;
        }

        return (index - 1) / 2;
    }


    public int getTop() {
        return index < 0
                ? -1
                : array[0];
    }

    public int removeTop() {
        int temp = array[0];
        array[0] = array[index--];
        hepify(index);
        return temp;
    }
}



