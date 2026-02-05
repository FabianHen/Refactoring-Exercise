package expensereport.line;

/**
 * A line of the expense report that contains the total expenses.
 */
public class TotalExpensesLine implements Line {
    private final int expenses;

    /**
    * Creates a new total expenses line with the given total expenses.
    *
    * @param expenses the total expenses
    */
    public TotalExpensesLine(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public String format() {
        return "Total expenses: " + expenses;
    }
}
