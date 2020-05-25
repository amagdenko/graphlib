package org.graphlib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.graphlib.error.NoPathFoundException;

/**
 * <p>
 * Directed graph with all thread-safe methods.
 *
 * @author Aleksandr Magdenko
 */
public class DirectedGraph<T> extends AbstractBaseGraph<T> {
    /**
     * {@inheritDoc}
     */
    public synchronized Vertex<T> addVertex(T object) {
        return super.addVertex(object);
    }

    /**
     * Add adge between two non null vertexes.
     * Allowed to add more than one edge between two vertexes.
     *
     * @param vertex1 Vertex from.
     * @param vertexTo Vertex to.
     */
    public synchronized Edge<Vertex<T>> addEdge(Vertex<T> vertex1, Vertex<T> vertexTo) {
        return super.addEdge(vertex1, vertexTo);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized List<Edge<Vertex<T>>> getPath(Vertex<T> vertex1, Vertex<T> vertex2) {
        LinkedList<Edge<Vertex<T>>> path = new LinkedList<>();
        Vertex<T> vertex = findPath(vertex1, vertex2, new HashSet<>(), path);

        if (vertex == vertex2) {
            return path;
        } else {
            throw new NoPathFoundException("No path between two vertexes");
        }
    }

    /**
     * Find path from selected vertex.
     *
     * @param vertex Current vertex to search path from.
     * @param targetVertex End target vertex to find the path.
     * @param usedVertexes Set for vertexes already used during search.
     * @param path Current path list.
     * @return Vertex
     */
    private Vertex<T> findPath(Vertex<T> vertex, Vertex<T> targetVertex, Set<Vertex<T>> usedVertexes,
                               LinkedList<Edge<Vertex<T>>> path) {
        // Don't search if current vertex already found.
        if (vertex == targetVertex) {
            return vertex;
        }

        // Add itself in Set to prevent usage edges to itself.
        usedVertexes.add(vertex);

        // 1. Find all edges starts from current vertex.
        List<Edge<Vertex<T>>> nextEdges = edges.stream()
                // Begins from current vertex.
                .filter(edge -> edge.getFrom() == vertex)
                // No cycles in graph.
                .filter(edge -> !usedVertexes.contains(edge.getTo()))
                .collect(Collectors.toList());

        if (nextEdges.isEmpty()) {
            usedVertexes.remove(vertex);
            return vertex;
        }

        // 2. Loop over edges and deep for every edge.
        for (Edge<Vertex<T>> nextEdge : nextEdges) {
            path.addLast(nextEdge);

            Vertex<T> found = findPath(nextEdge.getTo(), targetVertex, usedVertexes, path);

            // 3. Check result for every calculated edge.
            if (found == targetVertex) {
                return found;
            } else {
                path.removeLast();
            }
        }

        // No path found.
        usedVertexes.remove(vertex);

        // Return current vertex.
        return vertex;
    }

/*
    public synchronized <R> List<R> traverseAll(Function<T, R> function) {
        return vertexes.stream()
                .map(vertex -> function.apply(vertex.getObject()))
                .collect(Collectors.toList());
    }
*/
}