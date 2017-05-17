package com.govind.util.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govindp on 11/5/2015.
 */
public class Graph {
    int n;
    List<Integer>[] adj;

    public Graph(int n) {
        this.n = n;
        adj = (List<Integer>[])new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v1, int v2){
        adj[v1].add(v2);
    }

    public List<Integer> getAdjacentVertices(int vertex){
        return adj[vertex];
    }

    public List<Integer>[] getAdj(){
        return adj;
    }

    public int numberOfVertices(){
        return n;
    }
}
