package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class ShortestPath {
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V = 9;
    int minDistance(int[] dist, Boolean[] sptSet)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int[] dist, int n)
    {
        String[] Btype = {"A+", "B+", "O+", "O-", "AB+", "AB-", "A-", "B-", "A+"};
        String[] local = {"Galle", "Matara", "Hambanthota", "Colombo", "Kaluthara", "Kandy", "Trinko", "Jaffna", "Puttalam"};
        String type="O+";
        int min=1000;
        String loca="";
        System.out.format("%-15s %-25s","Vertex","Distance from Source");
        System.out.println();
        for (int j = 0; j < V; j++) {
            if (Btype[j] == type) {
//                 min=dist[j];
//                for (int i = 0; i < V; i++)
                    System.out.format("%-15s %5s", local[j] , dist[j]);
                    System.out.println();
                    if (dist[j]<min){
                        min=dist[j];
                        loca=local[j];
                    }
            }
        }
        System.out.println("*****************************************************************************************************");
        System.out.println("The Nearest Place with Requested blood type(" + type + ") is from :" + loca+" and the distance from source is " + min +"Km");
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int[][] graph, int src)
    {
        int[] dist = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean[] sptSet = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist, V);
    }

    public static void main(String[] args)
    {
        /* Let us create the example graph discussed above */
        int[][] graph = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 5);
//        char arr[]="B-".toCharArray();
//        char arr2[]="A+".toCharArray();
//        if (arr[0]>arr2[0]){
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }
    }
}
