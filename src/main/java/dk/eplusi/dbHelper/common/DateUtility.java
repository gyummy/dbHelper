package dk.eplusi.dbHelper.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
    private static final Integer THIS_YEAR = Calendar.getInstance().get (Calendar.YEAR);

    public static Date getThisYear() throws ParseException {
        return new SimpleDateFormat("yyyy").parse(String.valueOf(THIS_YEAR));
    }

    public static Date getNextYear() throws ParseException {
        return new SimpleDateFormat("yyyy").parse(String.valueOf(THIS_YEAR + 1));
    }

    public static Date getThisYearStartDate() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(THIS_YEAR + "-01-01");
    }

    public static Date getThisYearEndDate() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(THIS_YEAR + "-12-31");
    }

    public static Date getToday() {
        long time = System.currentTimeMillis();
        return new Date(time);
    }

    public static Date parse(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }
}
