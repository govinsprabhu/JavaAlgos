package com.govind.algo.platform.gfg.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 609600403 on 15/06/2016.
 */
public class Graph {
    private int vertices;
    private List<List<Integer>> edges;

    public int vertex(){
        return vertices;
    }

    public static Graph getDirectedAcyclicGraph() {
        Graph graph = new Graph(6);
        graph.addDirectedEdge(5, 2);
        graph.addDirectedEdge(2, 3);
        graph.addDirectedEdge(3, 1);
        graph.addDirectedEdge(4, 1);
        graph.addDirectedEdge(4, 0);
        graph.addDirectedEdge(5, 0);
        return graph;
    }

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            edges.add(new ArrayList<>());
        }

    }

    public void addDirectedEdge(int fromVertex, int toVertex) {
        edges.get(fromVertex).add(toVertex);
    }


    public List<Integer> getEdge(int vertex) {
        return edges.get(vertex);
    }
}
