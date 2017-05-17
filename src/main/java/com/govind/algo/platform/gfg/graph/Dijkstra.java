package com.govind.algo.platform.gfg.graph;

import com.govind.util.array.ArraysUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 609600403 on 19/07/2016.
 */
public class Dijkstra {
    public static void main(String[] args) {
        GraphWithWeightedEdge graph = GraphWithWeightedEdge.getGraphWithWeightedEdge();
        ArraysUtils.print(dikstra(0, graph));
    }

    private static int[] dikstra(int startVertex, GraphWithWeightedEdge graph) {
        int n = graph.getNoOfVertices();
        boolean[] isVisited = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startVertex] = 0;
        for (int i = 0; i < n; i++) {
            int minDistanceVertex = getMinDistance(dist, graph, isVisited);
            isVisited[minDistanceVertex] = true;
            ArrayList<Edge> edges = graph.getEdge(minDistanceVertex);
            for (int j = 0; j < edges.size(); j++) {
                if (dist[edges.get(j).getToVertex()] > dist[minDistanceVertex] + edges.get(j).getWeight()){
                    dist[edges.get(j).getToVertex()] = dist[minDistanceVertex] + edges.get(j).getWeight();
                }
            }
        }
        return dist;
    }

    private static int getMinDistance(int[] dist, GraphWithWeightedEdge graph, boolean[] isVisited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if (minDistance > dist[i] && !isVisited[i]){
                minDistance = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
