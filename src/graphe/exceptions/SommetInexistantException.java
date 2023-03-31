package src.graphe.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class SommetInexistantException extends IllegalArgumentException implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;

    public SommetInexistantException() {
        super("Le sommet n'existe pas.");
    }

}
