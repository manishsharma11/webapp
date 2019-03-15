package com.main.sts.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

public class DateUtil {

    //private static TimeZone timezone = TimeZone.getTimeZone("IST");

    private static DateFormat formatter = null;
    public static Date converStringToDateObject(String date) {

        Date new_date = null;
        try {
            formatter = new SimpleDateFormat(SystemConstants.DATE_FORMAT);
            new_date = formatter.parse(date);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new_date;
    }
    public static Set<Date> dateInterval(Date initial, Date finall) {
        Set<Date> dates = new TreeSet<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initial);

        while (calendar.getTime().before(finall) || calendar.getTime().equals(finall)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }

        return dates;
    }
    public static Date getNewDate() {
        Calendar ISTTime = new GregorianCalendar();
        return ISTTime.getTime();
    }

    
    public static Calendar getCalendar() {
        Calendar cal = Calendar.getInstance();
        return cal;
    }
    
    public static Date getTodayDateWithOnlyDate() {
        return getOnlyTodayDate(new Date());
    }

    public static Date getOnlyTodayDate(Date date) {
        Calendar cal = getOnlyTodayDateCal(date);
        Date d = cal.getTime();
        System.out.println("today date" + d);
        return d;
    }
    
    public static Calendar getOnlyTodayDateCal(Date date) {
        System.out.println("date:"+date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        System.out.println("cal:"+cal.getTime());
        return cal;
    }

    public static DateRange getStartAndEndOfTripDate(Date trip_date) {
        // Date today = date;
        // today.setHours(0);
        // today.setMinutes(0);
        // today.setSeconds(0);

        // 2015-11-19:00:00:00
        Calendar cal = Calendar.getInstance();
        cal.setTime(trip_date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();

        // 2015-11-20:00:00:00
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        Date endOfDay = c.getTime();

        System.out.println("today:" + today + "----tomorrow:" + endOfDay);

        return new DateRange(today, endOfDay);
    }

    public static void main(String[] args) {
        System.out.println(getNewDate());
        
        System.out.println(getOnlyTodayDate(new Date()));
        System.out.println(getOnlyTodayDateCal(new Date()).getTime());

    }

    public static class DateRange {

        private Date startDate;
        private Date endDate;

        public DateRange(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }
        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
        public Date getEndDate() {
            return endDate;
        }
        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

    }

}
