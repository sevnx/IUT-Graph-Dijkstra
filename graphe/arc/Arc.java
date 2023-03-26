package graphe.arc;

/**
 * Un graphe.arc est un couple (source, destination) avec une valuation.
 * La valuation est un entier positif.
 *
 * @author Seweryn CZYKINOWSKI
 */
public class Arc {
    public static final String EMPTY_EDGE = "";
    private final String source;
    private final String destination;
    private final int valuation;

    public Arc(String source, String destination, int valuation) {
        if (valuation < 0)
            throw new IllegalArgumentException("Valuation ne peut pas être négative");
        if (source == null)
            throw new IllegalArgumentException("Source ne peut pas être null");
        this.source = source;
        this.destination = destination;
        this.valuation = valuation;
    }

    public Arc(String source) {
        this(source, "", 0);
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getValuation() {
        return valuation;
    }

    public String toString() {
        if (destination.equals(EMPTY_EDGE))
            return source + ":";
        return source + "-" + destination + "(" + valuation + ")";
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof Arc))
            return false;
        Arc arc = (Arc) o;
        return source.equals(arc.source) && destination.equals(arc.destination) && valuation == arc.valuation;
    }
}
