package org.graphlib;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class Edge<R extends Vertex>  {
    public static final int MINIMAL_WEIGHT = 0;

    private R from;
    private R to;
    private int weight = MINIMAL_WEIGHT;

    public Edge(R from, R to) {
        this.from = from;
        this.to = to;
    }

    public R getFrom() {
        return from;
    }

    public R getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
}