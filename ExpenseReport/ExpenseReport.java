package expensereport;

import expensereport.line.*;

import java.util.Date;
import java.util.List;

public class ExpenseReport {

    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        HeadLine headLine = new HeadLine(new Date());
        System.out.println(headLine.format());

        for (Expense expense : expenses) {
            if (expense.type.isMealExpense()) {
                mealExpenses += expense.amount;
            }
            ReportLine currentLine = new ReportLine(
                    expense.type.toString(),
                    expense.amount,
                    expense.getMealOverExpenseMarker()
            );
            System.out.println(currentLine.format());

            total += expense.amount;
        }

        MealExpensesLine mealExpensesLine = new MealExpensesLine(mealExpenses);
        System.out.println(mealExpensesLine.format());

        TotalExpensesLine totalExpensesLine = new TotalExpensesLine(total);
        System.out.println(totalExpensesLine.format());
    }
}
