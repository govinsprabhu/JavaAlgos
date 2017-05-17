package com.govind.algo.platform.gfg.graph;

import java.util.ArrayList;

/**
 * Created by 609600403 on 19/07/2016.
 */
public class GraphWithWeightedEdge {
    private int noOfVertices;
    private ArrayList<ArrayList<Edge>> vertices;
    private static GraphWithWeightedEdge graphWithWeightedEdge = null;

    public GraphWithWeightedEdge(int vertices) {
        this.noOfVertices = vertices;
        this.vertices = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            this.vertices.add(new ArrayList<>());
        }
    }

    public ArrayList<Edge> getEdge(int vertex) {
        return vertices.get(vertex);
    }

    public int getNoOfVertices() {
        return noOfVertices;
    }

    public void addEdge(int fromVertex, int toVertex, int weight) {
        Edge edge = new Edge(fromVertex, toVertex, weight);
        vertices.get(fromVertex).add(edge);
        edge = new Edge(toVertex, fromVertex, weight);
        vertices.get(toVertex).add(edge);
    }

    public static GraphWithWeightedEdge getGraphWithWeightedEdge() {
        if (graphWithWeightedEdge != null) {
            return graphWithWeightedEdge;
        }

        graphWithWeightedEdge = new GraphWithWeightedEdge(5);
        graphWithWeightedEdge.addEdge(0, 1, 5);
        graphWithWeightedEdge.addEdge(2, 1, 3);
        graphWithWeightedEdge.addEdge(3, 2, 4);
        graphWithWeightedEdge.addEdge(4, 0, 9);
        graphWithWeightedEdge.addEdge(2, 4, 5);
        graphWithWeightedEdge.addEdge(3, 0, 6);
        graphWithWeightedEdge.addEdge(3, 4, 1);
        return graphWithWeightedEdge;
    }
}
