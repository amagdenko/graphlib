package org.example;

import java.util.List;

import org.graphlib.Edge;
import org.graphlib.Graph;
import org.graphlib.GraphFactory;
import org.graphlib.Vertex;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class ExampleDirectGraphMain {
    /**
     * Example graph
     * 
     * @param args
     */
    public static void main(String[] args) {
        Graph<String> graph = GraphFactory.createGraph(true);

        Vertex<String> v1 = graph.addVertex("v1");
        Vertex<String> v2 = graph.addVertex("v2");
        graph.addEdge(v1, v2);

        Vertex<String> v3 = graph.addVertex("v3");
        graph.addEdge(v2, v3);

        Vertex<String> v6 = graph.addVertex("v6");
        graph.addEdge(v2, v6);
        graph.addEdge(v6, v1);

        Vertex<String> v5 = graph.addVertex("v5");
        graph.addEdge(v1, v5);
        Vertex<String> v7 = graph.addVertex("v7");
        graph.addEdge(v5, v7);
//        graph.addEdge(v7, v5);

        Vertex<String> v4 = graph.addVertex("v4");
        graph.addEdge(v3, v4);
//        graph.addEdge(v7, v4);

        List<Edge<Vertex<String>>> path = graph.getPath(v1, v4);

        for (Edge<Vertex<String>> edge : path) {
            System.out.println("Edge [from=" + edge.getFrom().getObject() + ", to=" + edge.getTo().getObject() + ']');
        }
    }
}