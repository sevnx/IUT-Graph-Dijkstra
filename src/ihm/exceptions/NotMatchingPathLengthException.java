package src.ihm.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class NotMatchingPathLengthException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    public NotMatchingPathLengthException(int expectedLength, int calculatedLength) {
        super(String.format("""
                La distance du chemin calculée n'est pas égale à la longueur du chemin attendue
                Distance attendue : %d
                Distance calculée : %d
                """, expectedLength, calculatedLength));
    }
}
