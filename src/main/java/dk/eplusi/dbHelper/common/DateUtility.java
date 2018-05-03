package dk.eplusi.dbHelper.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {

    public static Date getThisYear() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get (Calendar.YEAR);
        return new SimpleDateFormat("yyyy-MM-dd").parse(thisYear + "-01-01");
    }

    public static Date getToday() throws ParseException {
        long time = System.currentTimeMillis();
        return new Date(time);
    }

    public static Date parse(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }
}
