package org.graphlib;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class Vertex<T> {
    private T object;

    public Vertex(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}