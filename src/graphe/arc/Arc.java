package src.graphe.arc;

import src.graphe.exceptions.ArcValuationNegativeException;
import src.graphe.exceptions.EmptySommetException;

/**
 * Un src.graphe.arc est un couple (source, destination) avec une valuation.
 * La valuation est un entier positif.
 *
 * @author Seweryn CZYKINOWSKI
 */
public class Arc {
    /** Destination string that indicates that an arc does not have any outgoing arc. */
    public static final String EMPTY_EDGE_DESTINATION = "";
    /** Source of the arc. */
    private final String source;
    /** Destination of the arc. */
    private final String destination;
    /** Valuation of the arc. */
    private final int valuation;

    /**
     * Creates a new arc.
     *
     * @param source      Source of the arc.
     * @param destination Destination of the arc.
     * @param valuation   Valuation of the arc.
     * @throws ArcValuationNegativeException If valuation is negative
     * @throws EmptySommetException If source or destination is empty
     */
    public Arc(String source, String destination, int valuation) {
        if (valuation < 0)
            throw new ArcValuationNegativeException();
        if (source.isEmpty() || destination.isEmpty())
            throw new EmptySommetException();
        this.source = source;
        this.destination = destination;
        this.valuation = valuation;
    }

    /**
     * Creates a new arc with no destination of valuation 0.
     * Used to represent a sommet with no outgoing arc.
     *
     * @param source Source of the arc.
     */
    public Arc(String source) {
        this.source = source;
        this.destination = EMPTY_EDGE_DESTINATION;
        this.valuation = 0;
    }

    /**
     * Getter for source.
     * @return Source of the arc.
     */
    public String getSource() {
        return source;
    }

    /**
     * Getter for destination.
     * @return Destination of the arc.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Getter for valuation.
     * @return Valuation of the arc.
     */
    public int getValuation() {
        return valuation;
    }

    /**
     * toString method for the arc.
     * Different representation for arcs with no destination.
     * @return String representation of the arc.
     */
    public String toString() {
        if (destination.equals(EMPTY_EDGE_DESTINATION))
            return source + ":";
        return source + "-" + destination + "(" + valuation + ")";
    }

    /**
     * Equals method for the arc.
     * @param o Object to compare to.
     * @return True if the object is an arc and has the same source, destination and valuation.
     */
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof Arc arc))
            return false;
        return source.equals(arc.source) && destination.equals(arc.destination) && valuation == arc.valuation;
    }
}
