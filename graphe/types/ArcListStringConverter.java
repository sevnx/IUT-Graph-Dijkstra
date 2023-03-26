package graphe.types;

import java.util.Collections;
import java.util.List;

/**
 * Converts a list of string representing arcs to a string.
 */
abstract class ArcListStringConverter {
    /**
     * Converts a list of string representing arcs to a string.
     *
     * @param arcs the list of string representing arcs
     * @return the string representation of the list of arcs
     */
    static String convertToString(List<String> arcs) {
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
