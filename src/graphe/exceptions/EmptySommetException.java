package src.graphe.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class EmptySommetException extends IllegalArgumentException implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    public EmptySommetException() {
        super("Un arc ne peut pas avoir une source ou une destination vide");
    }
}
