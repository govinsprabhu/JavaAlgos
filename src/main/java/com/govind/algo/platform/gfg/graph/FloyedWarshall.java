package com.govind.algo.platform.gfg.graph;

import com.govind.util.array.ArraysUtils;

import java.util.Arrays;

/**
 * Created by 609600403 on 02/08/2016.
 */
public class FloyedWarshall {
    private static int inf = Integer.MAX_VALUE / 2;
    public static void main(String[] args) {
        int[][] graph = {{0, 5, inf, inf, 1},{ 5, 0, 3, inf, inf},{inf, 3, 0, inf, 1},{inf, inf, inf, 0, inf},{1, inf, 1, inf, 0}};
        new FloyedWarshall().printFloyedMarshall(graph);
    }

    private void printFloyedMarshall(int[][] graph) {
        int[][] distance = new int[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] != 0){
                    distance[i][j] = graph[i][j];
                }else if (i != j){
                    distance[i][j] = Integer.MAX_VALUE / 2  - 1;
                }
            }
        }

        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int[][] distFromShop = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(distFromShop[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                distFromShop[i][j] = Math.min(distance[i][2] +  distance[2][j], distFromShop[i][j]);
                if (distFromShop[i][j] == Integer.MAX_VALUE / 2){
                    distFromShop[i][j] = 0;
                }
            }
        }



        ArraysUtils.print(distFromShop);
    }
}
