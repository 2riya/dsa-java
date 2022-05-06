package dsa.graph.algo;

import java.util.Arrays;

public class Dijkstra {

    public static void main(String[] args) {
        int[][] adjacencyMatrix =
            new int[][] {{0, 4, 0, 3}, {4, 0, 1, 0}, {0, 1, 0, -10}, {3, 0, -10, 0}};

        boolean[] visited = new boolean[adjacencyMatrix.length];

        // to store result
        int[] dist = new int[adjacencyMatrix.length];

        // distance from source node to all nodes will be infinity initially
        // except for source node itself
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < dist.length; i++) {
            int source = findMin(dist, visited);
            visited[source] = true;
            for (int j = 0; j < dist.length; j++) {
                if (!visited[j] && adjacencyMatrix[source][j] != 0
                    && dist[j] > adjacencyMatrix[source][j] + dist[source]) {
                    dist[j] = adjacencyMatrix[source][j] + dist[source];
                }
            }
        }


        for (int i : dist) {
            System.out.println(i);
        }
    }

    private static int findMin(int[] dist, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE; // required to check condition
        int minIndex = -1; // return value
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < minDistance) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
