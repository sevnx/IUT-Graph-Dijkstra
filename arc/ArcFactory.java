package arc;

/**
 * Factory for creating Arcs (edges) out of a formatted string.
 * Format in which the string should be: "source-destination(valuation)"
 * @author Seweryn CZYKINOWSKI
 */
public class ArcFactory {
    /**
     * Creates an arc (edge) from a formatted string.
     * @param str the string to create the arc from
     * @return the created arc (edge)
     */
    public static Arc createArc(String str){
        if (str.contains(":"))
            return new Arc(str.split(":")[0]);
        String[] parts = str.split("-");
        String source = parts[0];
        String[] parts2 = parts[1].split("\\(");
        String destination = parts2[0];
        String[] parts3 = parts2[1].split("\\)");
        int valuation = Integer.parseInt(parts3[0]);
        return new Arc(source, destination, valuation);
    }
}
