package expensereport.line;

/*
* A line of the expense report that contains the name, amount and marker of an expense.
*/
public class ReportLine implements Line {
    private final String name;
    private final int amount;
    private final String marker;

    /**
     * Creates a new report line with the given name, amount and marker.
     *
     * @param name the name of the expense
     * @param amount the amount of the expense
     * @param marker the marker of the expense (e.g. "X" for expenses above 100)
     */
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
