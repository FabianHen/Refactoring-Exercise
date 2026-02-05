package expensereport;

/**
 * Represents the type of expense, including its name, limit, and whether it is a meal expense.
 */
public enum ExpenseType {
    DINNER("Dinner", 5000, true),
    BREAKFAST("Breakfast", 1000, true),
    CAR_RENTAL("Car Rental", 0, false),
    LUNCH("Lunch", 2000, true);

    private final String expenseName;
    private final int expenseLimit;
    private final boolean isMealExpense;

    ExpenseType(String expenseName,int expenseLimit, boolean isMealExpense){
        this.expenseName = expenseName;
        this.expenseLimit = expenseLimit;
        this.isMealExpense = isMealExpense;
    }

    /**
     * Returns the name of the expense type.
     *
     * @return the name of the expense type
     */
    public int getExpenseLimit() {
        return expenseLimit;
    }

    /**
     * Returns whether this expense type is a meal expense.
     *
     * @return true if this expense type is a meal expense, false otherwise
     */
    public boolean isMealExpense() {
        return isMealExpense;
    }

    /**
     * Returns the string representation of this expense type, which is its name.
     *
     * @return the name of the expense type
     */
    @Override
    public String toString() {
        return expenseName;
    }
}
