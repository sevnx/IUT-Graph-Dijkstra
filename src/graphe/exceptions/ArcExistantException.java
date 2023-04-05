package src.graphe.exceptions;

import java.io.Serial;
import java.io.Serializable;

/**
 * Exception thrown when an arc already exists.
 */
public class ArcExistantException extends IllegalArgumentException implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    /**
     * Default constructor with predefined message.
     */
    public ArcExistantException() {
        super("L'arc existe déjà.");
    }
}
