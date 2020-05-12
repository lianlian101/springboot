package com.test.springboot.demo.test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.test.springboot.general.util.DateUtil;;

public class DateTest {
    
    @Test
    public void dateToString(){
        String str = DateUtil.dateToString(DateUtil.getCurrentDate());
        System.out.println(str);
    }
    
    @Test
    public void stringToDate() throws ParseException{
        Date date = DateUtil.stringToDate("2020-5-5 11:12:22");
        System.out.println(DateUtil.dateToString(date));
    }
    
    @Test
    public void stringToLong() throws ParseException{
        long l = DateUtil.stringToLong("2020-5-5 11:12:22");
        System.out.println(DateUtil.longToString(l));
    }
    
    @Test
    public void dateToLong() throws ParseException{
        long l = DateUtil.dateToLong(new Date());
        System.out.println(DateUtil.longToDate(l));
    }
    
    @Test
    public void getCurrentDate(){
        String str = DateUtil.getCurrentDateString();
        System.out.println(str);
    }
    
    @Test
    public void compareDateMillis() throws ParseException{
        long l = DateUtil.differMillis("2020-5-5 12:12:23", "2020-5-5 12:30:23");
        System.out.println((l/1000/60));
    }

    @Test
    public void inScope() throws ParseException{
        boolean b = DateUtil.isInScope("2020-5-3 23:59:59", "2020-5-4 12:12:23", "2020-5-6 12:12:23");
        System.out.println(b);
    }
    
    @Test
    public void inScope_2() throws ParseException{
        boolean b = DateUtil.isInScope(
                        DateUtil.stringToDate("2020-5-7 00:00:00"), 
                        DateUtil.stringToDate("2020-5-4 12:12:23"), 
                        DateUtil.stringToDate("2020-5-6 12:12:23")
                    );
        System.out.println(b);
    }
    
    @Test
    public void getDateStringOfTimeLater(){
        String str = DateUtil.getDateStringOfTimeLater(Calendar.DATE, 1);
        System.out.println(str);
    }
    
    @Test
    public void getDateStringOfTimeLaterByDate() throws ParseException{
        String str = DateUtil.getDateStringOfTimeLaterByDate("2020-5-5", Calendar.DATE, 1, "yyyy-MM-dd");
        System.out.println(str);
    }
    
    @Test
    public void getFirstDayOfMonth(){
        String str = DateUtil.getFirstDayOfMonth(DateUtil.getCurrentDate());
        System.out.println(str);
    }
    
    @Test
    public void getEndDayOfMonth(){
        String str = DateUtil.getLastDayOfMonth(DateUtil.getCurrentDate());
        System.out.println(str);
    }
    
    @Test
    public void getFirstTimeOfDate(){
        String str = DateUtil.getFirstTimeOfDate(DateUtil.getCurrentDate());
        System.out.println(str);
    }
    
    @Test
    public void getEndTimeOfDate(){
        String str = DateUtil.getLastTimeOfDate(DateUtil.getCurrentDate());
        System.out.println(str);
    }
    
}
