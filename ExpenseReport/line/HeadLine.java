package expensereport.line;

import java.util.Date;

public class HeadLine implements Line {
    private final Date date;

    public HeadLine(Date date) {
        this.date = date;
    }

    @Override
    public String format() {
        return "Expenses " + date;
    }
}
