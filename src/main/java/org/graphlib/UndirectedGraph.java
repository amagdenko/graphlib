package org.graphlib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.graphlib.error.NoPathFoundException;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class UndirectedGraph<T> extends AbstractBaseGraph<T> {
    /**
     * {@inheritDoc}
     */
    public synchronized Vertex<T> addVertex(T object) {
        return super.addVertex(object);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
                .filter(edge -> (edge.getFrom() == vertex || edge.getTo() == vertex))
                // No cycles in graph.
                .filter(edge -> (edge.getFrom() == vertex && !usedVertexes.contains(edge.getTo())) ||
                        (edge.getTo() == vertex && !usedVertexes.contains(edge.getFrom())))
                .collect(Collectors.toList());

        if (nextEdges.isEmpty()) {
            usedVertexes.remove(vertex);
            return vertex;
        }

        // 2. Loop over edges and deep for every edge.
        for (Edge<Vertex<T>> nextEdge : nextEdges) {
            path.addLast(nextEdge);

            // Get ne vertex for undirected edge.
            Vertex<T> nextVertex = nextEdge.getFrom() == vertex ? nextEdge.getTo() : nextEdge.getFrom();

            Vertex<T> found = findPath(nextVertex, targetVertex, usedVertexes, path);

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
}