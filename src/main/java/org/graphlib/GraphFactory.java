package org.graphlib;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class GraphFactory {
    public static <T> Graph<T> createGraph(boolean directed) {
        if (directed) {
            return new DirectedGraph<>();
        } else {
//            throw new UnsupportedOperationException();
            return new UndirectedGraph<>();
        }
    }
}
