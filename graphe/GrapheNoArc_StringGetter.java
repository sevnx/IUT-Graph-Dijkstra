package graphe;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for building a string representation of a graph for graphs that do not use the Arc class.
 */
public class GrapheNoArc_StringGetter {
    static String getStringFromStringArcList(List<String> arcs) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(arcs);
        boolean first = true;
        for (String arc : arcs)
            if (first) {
                sb.append(arc);
                first = false;
            } else
                sb.append(", ").append(arc);
        return sb.toString();
    }
}
