package org.graphlib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.graphlib.error.IllegalEdgeArgumentException;
import org.graphlib.error.NoPathFoundException;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public abstract class AbstractBaseGraph<T> implements Graph<T>{
    protected List<Vertex<T>> vertexes = new ArrayList<>();
    protected List<Edge<Vertex<T>>> edges = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    public Vertex<T> addVertex(T object) {
        Vertex<T> result = new Vertex<>(object);
        vertexes.add(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Edge<Vertex<T>> addEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo) {
        if (!vertexes.contains(vertexFrom)) {
            throw new IllegalEdgeArgumentException("Unknown vertex from");
        }

        if (!vertexes.contains(vertexTo)) {
            throw new IllegalEdgeArgumentException("Unknown vertex to");
        }

        if (vertexFrom == null || vertexTo == null) {
            throw new IllegalEdgeArgumentException("Unable to add edge with null vertex");
        }

        if (vertexFrom == vertexTo) {
            throw new IllegalEdgeArgumentException("Unable to add edge where both vertexes are the same");
        }

        Edge<Vertex<T>> edge = new Edge<>(vertexFrom, vertexTo);
        edges.add(edge);
        return edge;
    }

    /**
     * {@inheritDoc}
     */
    public abstract List<Edge<Vertex<T>>> getPath(Vertex<T> vertex1, Vertex<T> vertex2);
}