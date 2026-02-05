package expensereport.line;

/**
 * An interface representing a line of the expense report. Each line can be formatted for display in the report.
 */
public interface Line {
    /**
     * Formats the line for display in the expense report.
     *
     * @return the formatted line as String
     */
    String format();
}
