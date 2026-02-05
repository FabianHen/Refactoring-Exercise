package expensereport.line;

public class ReportLine implements Line {
    private final String name;
    private final int amount;
    private final String marker;

    public ReportLine(String name, int amount, String marker) {
        this.name = name;
        this.amount = amount;
        this.marker = marker;
    }

    @Override
    public String format() {
        return name + "\t" + amount + "\t" + marker;
    }
}
