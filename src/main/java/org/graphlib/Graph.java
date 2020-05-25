package org.graphlib;

import java.util.List;

import org.graphlib.error.NoPathFoundException;
import org.graphlib.error.IllegalEdgeArgumentException;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public interface Graph<T> {
    /**
     * Add vertex in graph.
     *
     * @param object Inner object to keep in vertex.
     * @return Vertex.
     */
    Vertex<T> addVertex(T object);

    /**
     * Add edge between two vertexes.
     *
     * @param vertexFrom Vertex one.
     * @param vertexTo Vertex two.
     * @throws IllegalEdgeArgumentException if illegal vertex argument used to create edge.
     */
    Edge<Vertex<T>> addEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo);

    /**
     * Find path between to vertexes.
     *
     * @param vertexFrom Start vertex to find path from.
     * @param vertexTo End vertex to find path to.
     * @return List of edges starting from {@code vertex1} till last {@code vertex2}
     * @throws NoPathFoundException if no path can be found between two vertexes.
     */
    List<Edge<Vertex<T>>> getPath(Vertex<T> vertexFrom, Vertex<T> vertexTo);
}