package com.govind.util.graph;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by govindp on 11/22/2015.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int label) {
        this.label = label;
        this.neighbors = new ArrayList<>();
    }

    public  void Bfs(){
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(this);
        Set<UndirectedGraphNode> visited = new HashSet<>();
        visited.add(this);
        while (!queue.isEmpty()){
            UndirectedGraphNode top = queue.poll();
            System.out.print(top.label+" -> ");
            for (int i = 0; i < top.neighbors.size(); i++) {
                if (visited.contains(top.neighbors.get(i))){
                    continue;
                }else {
                    visited.add(top.neighbors.get(i));
                    queue.add(top.neighbors.get(i));
                }
            }
        }
    }
}
