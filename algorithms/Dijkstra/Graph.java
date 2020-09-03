package algorithms.Dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Vertex> vertices = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    public void addEdge(int s, int t, int w) {
        Edge edge = new Edge(vertices.get(s), vertices.get(t), w);
        edges.add(edge);
    }
}

