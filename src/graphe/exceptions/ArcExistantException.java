package src.graphe.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class ArcExistantException extends IllegalArgumentException implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    public ArcExistantException() {
        super("L'arc existe déjà.");
    }
}
