package arc;

/**
 * Un arc est un couple (source, destination) avec une valuation.
 * La valuation est un entier positif.
 * @author Seweryn CZYKINOWSKI
 */
public class Arc {
    public static final String EMPTY_EDGE="";
    private String origin;
    private String destination;
    private int valuation;

    public Arc(String origin, String destination, int valuation) {
        if (isValuationNegative(valuation))
            throw new IllegalArgumentException("Valuation ne peut pas être négative");
        if (origin == null)
            throw new IllegalArgumentException("Source ne peut pas être null");
        this.origin = origin;
        this.destination = destination;
        this.valuation = valuation;
    }

    public Arc(String source) {
        this(source, "", 0);
    }

    public static boolean isValuationNegative(int valuation) {
        return valuation < 0;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getValuation() {
        return valuation;
    }

    public String toString() {
        if (destination.equals(EMPTY_EDGE))
            return origin+":";
        return origin + "-" + destination + "(" + valuation + ")";
    }
}
