package algorithms;
import java.util.Arrays;

public class BellmanFord {

    private int nVertices;
    private int nEdges;
    private int[][] graph; //u,v,w
    private int[] distance;

    public BellmanFord(int[][] _graph, int V, int E) {
        this.nVertices = V;
        this.nEdges = E;
        this.graph = _graph;
        this.distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        this.execute();
    }

    public void execute() {
        System.out.println("Executing BellmanFord to find the shortest Path with -ve weights");
        distance[0] = 0;
        //Relax the edges V-1 times
        for (int i=0; i<nVertices-1; i++) {
            for (int j=0; j<nEdges; j++) {
                int u = graph[j][0];
                int v = graph[j][1];
                int weight = graph[j][2];
                if (distance[u] != Integer.MAX_VALUE &&  (distance[v] > distance[u] + weight) ) {
                    distance[v] = distance[u] + weight;
                }
            }
        }
        print();
    }

    public void print() {
        for (int i=0; i<nVertices; i++) {
            System.out.println("Vertix " + i + "\t\t" + distance[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices in graph
        int E = 8; // Number of edges in graph
 
        // Every edge has three values (u, v, w) where
        // the edge is from vertex u to v. And weight
        // of the edge is w.
        int[][] _graph = { { 0, 1, -1 }, { 0, 2, 4 },
                        { 1, 2, 3 }, { 1, 3, 2 },
                        { 1, 4, 2 }, { 3, 2, 5 },
                        { 3, 1, 1 }, { 4, 3, -3 } };
         
        BellmanFord bf = new BellmanFord(_graph, V, E);
    }
    
}