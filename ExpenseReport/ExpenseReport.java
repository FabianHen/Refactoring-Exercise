package expensereport;

import expensereport.line.*;
import expensereport.printer.*;

import java.util.Date;
import java.util.List;

public class ExpenseReport {
    private Printer printer;

    public ExpenseReport() {
        printer = new ConsolePinter();
    }

    public ExpenseReport(Printer printer) {
        this.printer = printer;
    }

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

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
