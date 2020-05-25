package org.graphlib.error;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class IllegalEdgeArgumentException extends GraphException {
    public IllegalEdgeArgumentException() {
    }

    public IllegalEdgeArgumentException(String message) {
        super(message);
    }

    public IllegalEdgeArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}