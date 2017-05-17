package com.govind.util.graph;

/**
 * Created by govindp on 11/20/2015.
 */
public class GraphBuildWithEdgeArray {
    private int V;
    private Edge[] edge;
    int index;


    public GraphBuildWithEdgeArray(int vertex, int edges) {
        this.V = vertex;
        edge = new Edge[edges];
        for (int i = 0; i < edges; i++) {
            edge[i] = new Edge();
        }
        index = 0;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public Edge[] getEdge() {
        return edge;
    }

    public void setEdge(Edge[] edge) {
        this.edge = edge;
    }

    public void addEdge(int u, int v){
        edge[index].start = u;
        edge[index].end = v;
        index++;
    }

    public void addEdge(int u, int v, int value){
        edge[index].start = u;
        edge[index].end = v;
        edge[index].distance = value;
        index ++;
    }
}
