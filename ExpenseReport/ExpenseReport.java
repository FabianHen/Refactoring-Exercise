package expensereport;

import expensereport.line.*;
import expensereport.printer.*;

import java.util.Date;
import java.util.List;

/**
 * The main class of the expense report, which handles the printing of the report using a printer.
 */
public class ExpenseReport {
    private Printer printer;

    /**
     * Creates a new expense report with a default console printer.
     */
    public ExpenseReport() {
        printer = new ConsolePinter();
    }

    /**
     * Creates a new expense report with the given printer.
     *
     * @param printer the printer to use for printing the report
     */
    public ExpenseReport(Printer printer) {
        this.printer = printer;
    }

    /**
     * Prints the expense report for the given list of expenses.
     *
     * @param expenses the list of expenses to include in the report
     */
    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;


        HeadLine headLine = new HeadLine(new Date());
        printer.printLine(headLine);

        for (Expense expense : expenses) {
            if (expense.type.isMealExpense()) {
                mealExpenses += expense.amount;
            }
            ReportLine currentLine = new ReportLine(
                    expense.type.toString(),
                    expense.amount,
                    expense.getMealOverExpenseMarker()
            );
            printer.printLine(currentLine);

            total += expense.amount;
        }

        MealExpensesLine mealExpensesLine = new MealExpensesLine(mealExpenses);
        printer.printLine(mealExpensesLine);

        TotalExpensesLine totalExpensesLine = new TotalExpensesLine(total);
        printer.printLine(totalExpensesLine);
    }

    /**
     * Sets the printer to use for printing the report.
     *
     * @param printer the printer to set
     */
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
