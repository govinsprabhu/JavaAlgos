package com.govind.util.graph;

/**
 * Created by govindp on 11/20/2015.
 */
public class Edge {
    public int start;
    public int end;
    public int distance;

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

