package src.graphe.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class ArcInexistantException extends IllegalArgumentException implements Serializable{
    @Serial
    private static final long serialVersionUID = 4L;
    public ArcInexistantException() {
        super("Arc inexistant.");
    }
}
