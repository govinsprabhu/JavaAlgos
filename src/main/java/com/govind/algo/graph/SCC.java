package com.govind.algo.graph;

import com.govind.util.graph.Graph;

import java.util.Stack;

/**
 * Created by govindp on 11/23/2015.
 */
public class SCC {
    public static Graph getGraph() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 1);
        graph.addEdge(2, 3);
        //graph.addEdge(3, 4);
        graph.addEdge(0, 5);
        graph.addEdge(5, 4);
        return graph;

    }

    public static void main(String[] args) {
        new SCC().pringSCC();
    }

    public void pringSCC() {
        Graph graph = getGraph();
        boolean[] visited = new boolean[graph.numberOfVertices()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            if (!visited[i]) {
                DFS(graph, i, visited, stack);
            }
        }

        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        Graph graphTransponse = graphTranspose(graph);
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (!visited[top]) {
                DFS(graphTransponse, top, visited);
            }
            System.out.println();
        }


    }

    private void DFS(Graph graph, int top, boolean[] visited) {
        visited[top] = true;
        System.out.print(top + " -> ");
        for (int adj : graph.getAdjacentVertices(top)) {
            if (!visited[adj])
                DFS(graph, adj, visited);
        }
    }

    public Graph graphTranspose(Graph graph) {

        Graph newGraph = new Graph(graph.numberOfVertices());
        for (int v = 0; v < graph.numberOfVertices(); v++) {
            for (int i = 0; i < graph.getAdjacentVertices(v).size(); i++) {
                newGraph.addEdge(graph.getAdjacentVertices(v).get(i), v);
            }
        }

        return newGraph;
    }

    private void DFS(Graph graph, int index, boolean[] visited, Stack<Integer> stack) {
        visited[index] = true;

        for (int adjacent : graph.getAdjacentVertices(index)) {
            if (!visited[adjacent]) {

                DFS(graph, adjacent, visited, stack);
            }
        }

        stack.add(index);
    }


}
