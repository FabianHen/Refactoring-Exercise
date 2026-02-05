package expensereport.line;

import java.util.Date;

/**
 * The headline of the expense report, which contains the date of the report.
 */
public class HeadLine implements Line {
    private final Date date;

    /**
     * Creates a new headline with the given date.
     * 
     * @param date the date of the expense report
     */
    public HeadLine(Date date) {
        this.date = date;
    }

    @Override
    public String format() {
        return "Expenses " + date;
    }
}
