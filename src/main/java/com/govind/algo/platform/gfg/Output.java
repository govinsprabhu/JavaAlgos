package com.govind.algo.platform.gfg;

import com.govind.algo.platform.gfg.graph.Graph;
import com.govind.util.arraylist.ArrayListUtils;

import java.util.*;

/**
 * Created by 609600403 on 15/06/2016.
 */
class newthread implements Runnable {
    Thread t;
    newthread() {
        t = new Thread(this,"Hello");
        t.start();
    }
    public void run() {
        System.out.println(t.getName());
    }
    public static void main(String args[]) {
        int x,y;
        x = 3 & 6;
        y = 3 | 6;
        System.out.println(x + " " + y);
    }
    public void numberToWord(int number) {
        String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] tens = {"", "tens", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] hundreds = {"hundred", "thousands", "million"};
        String numberInWord = "";
        while (number > 0) {
            int num = number;
            int count = 0;
            while (num / 1000 > 0) {
                num /= 1000;
                count++;
            }
            if (count >= 1) {
                numberInWord += hundreds[count];
            }


        }

    }

    public void mergeKList() {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{5, 10, 19, 20});
        ArrayList<Integer> arrayList2 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 4, 17, 25});
        ArrayList<Integer> arrayList3 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 7, 8, 26});
        arrayLists.add(arrayList1);
        arrayLists.add(arrayList2);
        arrayLists.add(arrayList3);
        merge(arrayLists);
    }

    private void merge(ArrayList<ArrayList<Integer>> arrayLists) {
        TreeSet<NumberWithIndex> treeSet = new TreeSet<>((Comparator<NumberWithIndex>) (index1, index2) -> index1.num - index2.num);
        int m = arrayLists.size();
        int n = arrayLists.get(0).size();
        for (int i = 0; i < arrayLists.size(); i++) {
            treeSet.add(new NumberWithIndex(arrayLists.get(i).get(0), i, 0));
        }
        for (int i = m; i < m * n; i++) {
            NumberWithIndex num = treeSet.first();
            System.out.print(num.num + " ");
            treeSet.remove(treeSet.first());
            if (num.index == arrayLists.get(num.row).size() - 1) {
                continue;
            }
            treeSet.add(new NumberWithIndex(arrayLists.get(num.row).get(num.index + 1), num.row, num.index + 1));
        }
    }

    private class NumberWithIndex {
        int num;
        int index;
        int row;

        public NumberWithIndex(int num, int row, int index) {
            this.num = num;
            this.row = row;
            this.index = index;
        }
    }

    private int ropes(List<Integer> ropes) {
        TreeSet<Integer> minHeap = new TreeSet<>();
        for (int i = 0; i < ropes.size(); i++) {
            minHeap.add(ropes.get(i));
        }

        int totalCost = 0;
        while (!minHeap.isEmpty() && minHeap.size() >= 2) {
            int first = minHeap.first();
            minHeap.remove(minHeap.first());
            int second = minHeap.first();
            minHeap.remove(minHeap.first());
            int currentSum = first + second;
            totalCost += currentSum;
            minHeap.add(currentSum);
        }
        return totalCost;
    }

    public ArrayList<Integer> findOrder(String[] array) {
        Graph graph = new Graph(3);
        for (int i = 0; i < array.length - 1; i++) {
            String word1 = array[i];
            String word2 = array[i + 1];

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.addDirectedEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
                }
            }
        }

        return topologicalSort(graph);

    }

    private static ArrayList<Integer> topologicalSort(Graph graph) {
        int vertices = graph.vertex();
        boolean[] isVisited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < vertices; i++) {
            if (!isVisited[i]) {
                visit(graph, i, stack, isVisited);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < graph.vertex(); i++) {
            result.add(stack.pop());
        }

        return result;
    }

    private static void visit(Graph graph, int vertex, Stack<Integer> stack, boolean[] isVisited) {
        if (isVisited[vertex]) {
            return;
        }

        isVisited[vertex] = true;
        for (int i = 0; i < graph.getEdge(vertex).size(); i++) {
            visit(graph, graph.getEdge(vertex).get(i), stack, isVisited);
        }

        stack.push(vertex);
    }
}
