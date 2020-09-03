package algorithms.Dijkstra;

public class Edge {
    Vertex sourceVertex;
    Vertex targeVertex;
    int weight;

    public Edge(Vertex s, Vertex t, int w) {
        sourceVertex = s;
        targeVertex = t;
        weight = w;
    }
}