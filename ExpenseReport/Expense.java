package expensereport;

/**
 * Represents an expense with a type and an amount.
 */
public class Expense {
    ExpenseType type;
    int amount;

    /**
     * Returns the marker for meal expenses that exceed their limit. 
     * @return "X" if this expense is a meal expense and its amount exceeds the limit for that type, otherwise returns a single space " ".
     */
    public String getMealOverExpenseMarker() {
        if(type.isMealExpense() && amount > type.getExpenseLimit()){
            return "X";
        }
        return " ";
    }
}
