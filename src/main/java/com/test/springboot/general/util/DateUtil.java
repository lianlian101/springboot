package com.test.springboot.general.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

    /** 
     * 日期格式(yyyy-MM-dd HH:mm:ss) 
     */
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /** 
     * 日期格式(yyyy-MM-dd) 
     */
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    /** 
     * 日期格式(HH:mm:ss) 
     */
    public static final String HH_mm_ss = "HH:mm:ss";
    
    private static String verify(String pattern){
        if(StringUtils.isEmpty(pattern))
            return yyyy_MM_dd_HH_mm_ss;
        return pattern;
    }

    /**
     * 获取SimpleDateFormat
     * 
     * @param pattern 格式
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }
    
    /**
     * 获取SimpleDateFormat
     * 
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat();
    }

    /**
     * 将date转换为pattern格式的字符串
     * 
     * @param date 日期
     * @param pattern 格式
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        return DateFormatUtils.format(date, verify(pattern));
    }
    
    /**
     * 将date转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
     * 
     * @param date 日期
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, null);
    }

    /**
     * 将字符串转换为pattern格式后返回date
     * 
     * @param str 日期字符串
     * @param pattern 格式
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str, String pattern) throws ParseException {
        return DateUtils.parseDate(str, verify(pattern));
    }
    
    /**
     * 将字符串转换为"yyyy-MM-dd HH:mm:ss"格式后返回date
     * 
     * @param str 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str) throws ParseException {
        return stringToDate(str, null);
    }

    /**
     * 将long类型的时间转换为pattern格式的字符串
     * 
     * @param time 时间毫秒值
     * @param pattern 格式
     * @return
     */
    public static String longToString(long time, String pattern) {
        return DateFormatUtils.format(time, verify(pattern));
    }
    
    /**
     * 将long类型的时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
     * 
     * @param time 时间毫秒值
     * @return
     */
    public static String longToString(long time) {
        return longToString(time, null);
    }
    
    /**
     * 将string类型的时间转换为pattern格式后返回long类型
     * 
     * @param str 日期字符串
     * @param pattern 格式
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String str, String pattern) throws ParseException{
        return DateUtils.parseDate(str, verify(pattern)).getTime();
    }
    
    /**
     * 将string类型的时间转换为"yyyy-MM-dd HH:mm:ss"格式后返回long类型
     * 
     * @param str 日期字符串
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String str) throws ParseException{
        return stringToLong(str, null);
    }

    /**
     * 将date类型格式化pattern格式后转换为long类型
     * 
     * @param date 日期
     * @param pattern 格式
     * @return
     * @throws ParseException 
     */
    public static long dateToLong(Date date, String pattern) throws ParseException{
        return stringToLong(dateToString(date, verify(pattern)), pattern);
    }
    
    /**
     * 将date类型格式化"yyyy-MM-dd HH:mm:ss"格式后转换为long类型
     * 
     * @param date 日期
     * @return
     * @throws ParseException 
     */
    public static long dateToLong(Date date) throws ParseException {
        return dateToLong(date, null);
    }
    
    /**
     * 将long类型的时间格式化为pattern格式后转换为date
     * 
     * @param time 时间毫秒值
     * @param pattern 格式
     * @return
     * @throws ParseException 
     */
    public static Date longToDate(long time, String pattern) throws ParseException {
        return stringToDate(longToString(time, verify(pattern)), pattern);
    }
    
    /**
     * 将long类型的时间格式化为"yyyy-MM-dd HH:mm:ss"格式后转换为date
     * 
     * @param time 时间毫秒值
     * @return
     * @throws ParseException 
     */
    public static Date longToDate(long time) throws ParseException {
        return longToDate(time, null);
    }
    
    /**
     * 获取当前时间
     * 
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取当前时间的字符串形式
     * 
     * @return
     */
    public static String getCurrentDateString() {
        return dateToString(getCurrentDate());
    }
    
    /**
     * 获取当前时间的pattern格式的字符串形式
     * 
     * @param pattern 格式
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDate(), verify(pattern));
    }

    /**
     * 获取当前时间的毫秒值
     * 
     * @return
     */
    public static long getCurrentDateMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 比较两个时间之间相差的毫秒值（time2-time1）
     * 
     * @param time1 时间1
     * @param time2 时间2
     * @return
     * @throws ParseException
     */
    public static long differMillis(String time1, String time2) throws ParseException {
        return stringToDate(time2).getTime() - stringToDate(time1).getTime();
    }

    /**
     * 比较两个date之间相差的毫秒值（date2-date1）
     * 
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static long differMillis(Date date1, Date date2) {
        return date2.getTime() - date1.getTime();
    }

    /**
     * 比较目标时间是否在两个时间的开始和结束之间
     * 
     * @param targetTime
     *            要比较的时间
     * @param startTime
     *            开始时间
     * @param lastTime
     *            结束时间
     * @return
     * @throws ParseException
     */
    public static boolean isInScope(String targetTime, String startTime, String lastTime) throws ParseException {
        long target = stringToLong(targetTime);
        long start = stringToLong(getFirstTimeOfDate(stringToDate(startTime)));
        long last = stringToLong(getLastTimeOfDate(stringToDate(lastTime)));
        if (target >= start && target <= last) {
            return true;
        }
        return false;
    }
    
    /**
     * 比较目标时间是否在两个时间的开始和结束之间
     * 
     * @param targetTime
     *            要比较的时间
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     * @throws ParseException
     */
    public static boolean isInScope(Date targetTime, Date startTime, Date lastTime) throws ParseException {
        long target = targetTime.getTime();
        long start = stringToLong(getFirstTimeOfDate(startTime));
        long last = stringToLong(getLastTimeOfDate(lastTime));
        if (target >= start && target <= last) {
            return true;
        }
        return false;
    }
    
    /**
     * 获取当前日期，根据日历的规则，获取指定日期后的一个日期字符串
     *   例如：获取当前日期一天后的一个日期字符串，可以通过以下命令来实现
     *      getDateStringForTimeLater(Calendar.DATE, 1, "yyyy-MM-dd HH:mm:ss")
     * 
     * @param field 日历类型（使用Calendar来标注）
     * @param amount 数量
     * @param pattern 格式
     * @return
     */
    public static String getDateStringOfTimeLater(int field, int amount, String pattern){
        Calendar now = Calendar.getInstance();
        now.setTime(getCurrentDate());
        now.add(field, amount);
        return dateToString(now.getTime(), verify(pattern));
    }
    
    /**
     * 获取当前日期，根据日历的规则，获取指定日期后的一个日期字符串
     *   例如：获取当前日期一天后的一个日期字符串，可以通过以下命令来实现
     *      getDateStringForTimeLater(Calendar.DATE, 1)
     *      该命令返回yyyy-MM-dd HH:mm:ss格式的时间字符串
     * 
     * @param field 日历类型（使用Calendar来标注）
     * @param amount 数量
     * @return
     */
    public static String getDateStringOfTimeLater(int field, int amount){
        return getDateStringOfTimeLater(field, amount, null);
    }
    
    /**
     * 获取指定日期，根据日历的规则，获取指定日期后的一个日期字符串
     *  例如：获取2020-3-20一天后的一个日期字符串，可以通过以下命令来实现
     *      getDateStringForTimeLaterByDate("2020-3-20", Calendar.DATE, 1, "yyyy-MM-dd")
     * 
     * @param date 时间字符串
     * @param fidld 日历类型（使用Calendar来标注）
     * @param amount 数量
     * @param pattern 格式
     * @return
     * @throws ParseException
     */
    public static String getDateStringOfTimeLaterByDate(String date, int field, int amount, String pattern) throws ParseException {
        Calendar now = Calendar.getInstance();
        now.setTime(stringToDate(date, verify(pattern)));
        now.add(field, amount);
        return dateToString(now.getTime(), verify(pattern));
    }
    
    /**
     * 获取指定日期，根据日历的规则，获取指定日期后的一个日期字符串
     *  例如：获取2020-3-20一天后的一个日期字符串，可以通过以下命令来实现
     *      getDateStringForTimeLaterByDate("2020-3-20 11:12:13", Calendar.DATE, 1)
     *      该命令返回yyyy-MM-dd HH:mm:ss格式的时间字符串
     * 
     * @param date 时间字符串
     * @param fidld 日历类型（使用Calendar来标注）
     * @param amount 数量
     * @return
     * @throws ParseException
     */
    public static String getDateStringOfTimeLaterByDate(String date, int field, int amount) throws ParseException {
        return getDateStringOfTimeLaterByDate(date, field, amount, null);
    }

    /**
     * 获取指定年月的第一天
     * 
     * @param date 日期
     * @return
     */
    public static String getFirstDayOfMonth(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime firstDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        return DateTimeFormatter.ofPattern(yyyy_MM_dd).format(firstDay);
    }
    
    /**
     * 获取指定年月的最后一天
     * 
     * @param date 日期
     * @return
     */
    public static String getLastDayOfMonth(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime lastDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        return DateTimeFormatter.ofPattern(yyyy_MM_dd).format(lastDay);
    }

    /**
     * 获取日期起始时间
     * 
     * @param date 日期
     * @return
     */
    public static String getFirstTimeOfDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime firstTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss).format(firstTime);
    }

    /**
     * 获取日期结束时间
     * 
     * @param date 日期
     * @return
     */
    public static String getLastTimeOfDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime lastTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss).format(lastTime);
    }
    
}
