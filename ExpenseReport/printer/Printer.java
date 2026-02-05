package expensereport.printer;

import expensereport.line.Line;

/**
 * A printer that handles the printing of lines of the expense report in some way.
 */
public interface Printer {
    /**
     * Prints the given line in some way.
     *
     * @param line the line to print
     */
    void printLine(Line line);
}
