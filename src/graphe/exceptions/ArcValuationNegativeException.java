package src.graphe.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class ArcValuationNegativeException extends IllegalArgumentException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public ArcValuationNegativeException() {
        super("Un arc ne peut pas avoir une valuation négative");
    }
}
