package org.graphlib.error;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class GraphException extends RuntimeException {
    public GraphException() {
    }

    public GraphException(String message) {
        super(message);
    }

    public GraphException(String message, Throwable cause) {
        super(message, cause);
    }
}