package algorithms.Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
    
    private final List<Vertex> vertices;
    private final List<Edge> edges;
    private Set<Vertex> settledVertices;
    // private Set<Vertex> unsettledVertices;
    private Map<Vertex, Integer> distance;
    private Map<Vertex, Vertex> predessors;
    
    private PriorityQueue<Vertex> pq;

    public Dijkstra(Graph g) {
        vertices = new ArrayList<>(g.vertices);
        edges = new ArrayList<>(g.edges);
    }

    public void execute(Vertex source) {
        settledVertices = new HashSet<>();
        // unsettledVertices = new HashSet<>();
        distance = new HashMap<>();
        predessors = new HashMap<>();
        pq = new PriorityQueue<>((v1, v2)-> Integer.compare(v1.distance, v2.distance));

        //Initialize all distance with Max value
        for (Vertex v : vertices) {
            distance.put(v, Integer.MAX_VALUE);
        }
        distance.put(source, 0);
        source.distance = distance.get(source);
        // unsettledVertices.add(source);
        pq.offer(source);
        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            settledVertices.add(v);
            evaluateNeighbors(v);
        }
    }

    private void evaluateNeighbors(Vertex v) {
        List<Vertex> neighbors = getNeighbors(v);
        for (Vertex neighbor : neighbors) {
            if (!settledVertices.contains(neighbor)) {
                int newDistance = distance.get(v) + getEdgeWeight(v, neighbor);
                if (newDistance < distance.get(neighbor)) {
                    distance.put(neighbor, newDistance);
                    neighbor.distance = distance.get(neighbor);
                    pq.offer(neighbor);
                    predessors.put(neighbor, v);
                }
            } 
        }
    }

    private int getEdgeWeight(Vertex s, Vertex t) {
        for (Edge e : edges) {
            if (e.sourceVertex.equals(s) && e.targeVertex.equals(t))
                return e.weight;
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge e : edges) {
            if (e.sourceVertex.equals(v))
                neighbors.add(e.targeVertex);
        }
        return neighbors;
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        if (predessors.get(target) == null)
            return path;
        path.add(step);
        while(predessors.get(step) != null) {
            step = predessors.get(step);
            path.addFirst(step);
        } 
        return path;
    }

    public static void main(String[] args) {
        int num_of_vertices = 5;
        Graph graph = new Graph();
        
        char label = 'A';
        for (int i=0; i<num_of_vertices; i++) {
            graph.vertices.add(new Vertex(String.valueOf(label++)));
        }

        graph.addEdge(0, 1, 9);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 4);

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.execute(graph.vertices.get(0));
        Vertex target = graph.vertices.get(1);
        LinkedList<Vertex> path = dijkstra.getPath(target);
        System.out.println("The shortest path is via " + path + " and the distance is " + dijkstra.distance.get(target));
    }
}