package com.govind.util.graph;

import java.util.ArrayList;

/**
 * Created by govindp on 12/4/2015.
 */
public class PrimsMinimumSpanningTree {

    public static int[][] getGraph() {
        int[][] graph = {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };;
        return graph;
    }


    public static void main(String[] args) {
        new PrimsMinimumSpanningTree().minimumSpanningTree();
    }

    private void minimumSpanningTree() {
        int[][] graph = getGraph();
        minimumSpanningTree(graph);
    }

    private void minimumSpanningTree(int[][] graph) {
        boolean[] isAdded = new boolean[graph[0].length];
        int[] minimumKey = new int[graph[0].length];
        for (int i = 0; i < minimumKey.length; i++) {
            minimumKey[i] = Integer.MAX_VALUE;
        }
        int[] parent = new int[graph[0].length];
        ArrayList<Integer> edgeLength = new ArrayList<>();
        minimumKey[0] = 0;
        for (int i = 0; i < graph[0].length; i++) {
            int vertex = findMinVertex(minimumKey, isAdded);
            isAdded[vertex] = true;
            edgeLength.add(vertex);

            for (int j = 0; j < graph[vertex].length; j++) {
                if (graph[vertex][j] != 0 && isAdded[j] == false && graph[vertex][j] < minimumKey[j]){
                    parent[j] = vertex;
                    minimumKey[j] = graph[vertex][j];
                }
            }
        }

        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + "  "+ i + " " +graph[i][parent[i]]);
        }


    }

    private int findMinVertex(int[] minimumKey, boolean[] isAdded) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < minimumKey.length; i++) {
            if (!isAdded[i] &&minimumKey[i]  < min){
                min =  minimumKey[i];
                index = i;

            }
        }

        return index;
    }
}
