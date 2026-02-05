package expensereport.printer;

import expensereport.line.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * A test printer that collects the formatted lines in a list for testing purposes.
 */
public class TestPrinter implements Printer {
    private final List<String> lines;
    
    /**
     * Creates a new test printer with an empty list of lines.
     */
    public TestPrinter() {
        lines = new ArrayList<>();
    }

    /**
     * Prints the given line by adding its formatted version to the internal list of lines.
     *
     * @param line the line to print
     */
    @Override
    public void printLine(Line line) {
        lines.add(line.format());
    }

    /**
     * Returns the list of formatted lines that have been printed.
     *
     * @return the list of formatted lines
     */
    public List<String> getLines() {
        return lines;
    }
}
