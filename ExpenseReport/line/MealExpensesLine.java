package expensereport.line;

public class MealExpensesLine implements Line{
    private final int expenses;

    public MealExpensesLine(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public String format() {
        return "Meal expenses: " + expenses;
    }
}
