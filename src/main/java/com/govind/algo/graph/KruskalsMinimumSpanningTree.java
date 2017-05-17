package com.govind.algo.graph;

import com.govind.util.graph.Edge;
import com.govind.util.graph.GraphBuildWithEdgeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by govindp on 12/4/2015.
 */
public class KruskalsMinimumSpanningTree {

    public static GraphBuildWithEdgeArray getGraphWithEdgeArray() {
        GraphBuildWithEdgeArray graphBuildWithEdgeArray = new GraphBuildWithEdgeArray(4, 4);
        graphBuildWithEdgeArray.addEdge(0, 1, 5);
        graphBuildWithEdgeArray.addEdge(1, 2, 3);
        graphBuildWithEdgeArray.addEdge(2, 3, 4);
        graphBuildWithEdgeArray.addEdge(3, 0, 1);

        return graphBuildWithEdgeArray;
    }

    public static void main(String[] args) {
        new KruskalsMinimumSpanningTree().findSpanningTree();
    }

    private void findSpanningTree() {
        GraphBuildWithEdgeArray graphBuildWithEdgeArray = getGraphWithEdgeArray();
        findMinimumSpanningTree(graphBuildWithEdgeArray);
    }

    class Subset {
        int rank;
        int parent;
    }

    private void findMinimumSpanningTree(GraphBuildWithEdgeArray graph) {
        Subset[] subsets = new Subset[graph.getEdge().length];
        for (int i = 0; i < graph.getV(); i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        Arrays.sort(graph.getEdge(), new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.distance - o2.distance;
            }
        });


        ArrayList<Edge> edges = new ArrayList<>();
        int count = 0;
        while (edges.size() < graph.getV() - 1) {
            Edge edge = graph.getEdge()[count];
            int parentStart = findParent(graph.getEdge()[count].start, subsets);
            int parentEnd = findParent(graph.getEdge()[count].end, subsets);
            if (parentEnd != parentStart) {
                edges.add(edge);
                union(edge.start, edge.end, subsets);
            }
            count++;

        }
        System.out.println(edges);
    }

    private void union(int start, int end, Subset[] subsets) {
        if (subsets[start].rank < subsets[end].rank) {
            subsets[start].parent = end;

        } else if (subsets[start].rank > subsets[end].rank) {
            subsets[end].parent = start;
        } else {
            subsets[start].parent = end;
            subsets[end].rank++;
        }
    }

    private int findParent(int start, Subset[] subsets) {
        if (start == subsets[start].parent) {
            return start;
        }

        return findParent(subsets[start].parent, subsets);
    }
}
