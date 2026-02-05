package expensereport.line;

public class TotalExpensesLine implements Line {
    private final int expenses;

    public TotalExpensesLine(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public String format() {
        return "Total expenses: " + expenses;
    }
}
