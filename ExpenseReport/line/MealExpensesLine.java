package expensereport.line;

/**
 * A line of the expense report that contains total meal expenses.
 */
public class MealExpensesLine implements Line{
    private final int expenses;

    /**
     * Creates a new meal expenses line with the given total meal expenses.
     *
     * @param expenses the total meal expenses
     */
    public MealExpensesLine(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public String format() {
        return "Meal expenses: " + expenses;
    }
}
