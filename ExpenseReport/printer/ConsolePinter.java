package expensereport.printer;

import expensereport.line.Line;

public class ConsolePinter implements Printer{

    /**
     * Prints the given line to the console.
     *
     * @param line the line to print
     */
    @Override
    public void printLine(Line line) {
        System.out.println(line.format());
    }
}
