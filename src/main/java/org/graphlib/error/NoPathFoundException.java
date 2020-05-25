package org.graphlib.error;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class NoPathFoundException extends GraphException {
    public NoPathFoundException() {
    }

    public NoPathFoundException(String message) {
        super(message);
    }

    public NoPathFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}