package expensereport.printer;

import expensereport.line.Line;

import java.util.ArrayList;
import java.util.List;

public class TestPrinter implements Printer {
    private final List<String> lines;

    public TestPrinter() {
        lines = new ArrayList<>();
    }

    @Override
    public void printLine(Line line) {
        lines.add(line.format());
    }

    public List<String> getLines() {
        return lines;
    }
}
