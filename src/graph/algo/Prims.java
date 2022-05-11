package graph.algo;

import javafx.util.Pair;

import java.util.*;

public class Prims {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputEdges = sc.nextInt();
        HashMap<String, List<Neighbor>> adjacencyList = new HashMap<>();
        for (int i = 0; i < inputEdges; i++) {
            System.out.println("Enter v1: ");
            String v1 = sc.next();
            System.out.println("Enter v2: ");
            String v2 = sc.next();
            System.out.println("Enter cost: ");
            int c = sc.nextInt();
            addEdge(adjacencyList, v1, v2, c);
            addEdge(adjacencyList, v2, v1, c);
        }
        for (Map.Entry<String, List<Neighbor>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // heap consists of parent (pair.key) and child (pair.value.name) and edge cost (pair.value.cost)
        PriorityQueue<Pair<String, Neighbor>> heap =
            new PriorityQueue<>(Comparator.comparingInt(n -> n.getValue().cost));
        HashSet<String> visited = new HashSet<>();

        // choose any vertex at random
        String key = adjacencyList.keySet().iterator().next();

        // parent will be null for first vertex and cost will be 0
        heap.add(new Pair<>(null, new Neighbor(key, 0)));

        System.out.println("---------------- MST -------------------");

        while (visited.size() != adjacencyList.size()) {
            Pair<String, Neighbor> polled = heap.poll();
            if (visited.contains(polled.getValue().name)) {
                continue;
            }
            visited.add(polled.getValue().name);

            // fetch neighbors of polled vertex
            List<Neighbor> neighborList = adjacencyList.get(polled.getValue().name);
            for (Neighbor neighbor : neighborList) {
                if (!visited.contains(neighbor.name)) {
                    heap.add(new Pair<>(polled.getValue().name, neighbor));
                }
            }

            System.out.println(
                polled.getKey() + " -- " + polled.getValue().name + " : " + polled.getValue().cost);
        }
    }

    private static void addEdge(HashMap<String, List<Neighbor>> adjacencyList, String v1, String v2,
        int c) {
        List<Neighbor> neighborList = new ArrayList<>();
        if (adjacencyList.containsKey(v1)) {
            neighborList = adjacencyList.get(v1);
        }
        neighborList.add(new Neighbor(v2, c));
        adjacencyList.put(v1, neighborList);
    }
}


class Neighbor {
    String name;
    int cost;

    public Neighbor(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Neighbor{" + "name='" + name + '\'' + ", cost=" + cost + '}';
    }
}
