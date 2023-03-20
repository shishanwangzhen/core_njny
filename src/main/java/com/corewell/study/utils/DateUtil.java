package com.corewell.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式转化工具类
 *
 * @author Administrator
 */
public class DateUtil {
    /**
     * 将Date类型的转换成String类型的
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateStr(Date date, String format) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);

    }

    /**
     * 将String类型的转换成Date类型的
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date strDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getDate() {
        return dateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDay() {
        return dateStr(new Date(), "yyyy-MM-dd");
    }

    public static String getMonth() {
        return dateStr(new Date(), "yyyy-MM");
    }

    public static Date getDayDate() {
        Date date = strDate(getDay(), "yyyy-MM-dd");
        return date;
    }


    public static Date getNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date getNextYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @return Date
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @return Date
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取获取当前日期的前一周
     *
     * @return
     */
    public static Date getBeforeWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.WEEK_OF_YEAR, -1);
        Date dateFrom = cl.getTime();
        return dateFrom;
    }

    /**
     * 获取获取当前日期的前一个月
     *
     * @return
     */
    public static Date getBeforeMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.MONTH, -1);
        Date dateFrom = cl.getTime();
        return dateFrom;
    }

    /**
     * 获取获取当前日期的后参数个月
     *
     * @return
     */
    public static Date getAfterMonth(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.MONTH, month);
        Date dateFrom = cl.getTime();
        return dateFrom;
    }

    /**
     * 获取获取当前日期的后参数年
     *
     * @return
     */
    public static Date getAfterYear(int year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.YEAR, year);
        Date dateFrom = cl.getTime();
        return dateFrom;
    }

    /**
     * 现在时间往前推参数天
     * 格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param day
     * @return
     */
    public static Date getParameterDay(int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, day);
        Date date = c.getTime();
        return date;
    }

    /**
     * 现在时间往前推参数天
     * 格式为 yyyy-MM-dd
     *
     * @return
     * @throws ParseException
     */
    public static Date getParameterDay2(int day) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = sdf.parse(sdf.format(new Date()));   //当前时间
        c.setTime(time);
        c.add(Calendar.DAY_OF_YEAR, day);
        Date date = c.getTime();
        return date;
    }

    /**
     * 现在时间往前推参数小时
     *
     * @return
     */
    public static Date getParameterHour(int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, hour);
        Date date = c.getTime();
        return date;
    }

    /**
     * 将日期格式变成：MM-DD
     *
     * @param date
     * @return
     */
    public static String getMMDD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String strDate = sdf.format(date);
        return strDate;
    }

    /**
     * 现在时间往前推三个月
     *
     * @return
     */
    public static String getMonthBefore3() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -3);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        return time;
    }

    /**
     * 当前时间往后推一天
     *
     * @return
     */
    public static Date getDateToAfterOne(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date dateAfter = calendar.getTime();
        return dateAfter;
    }

    /**
     * 获取当前Date数据的年
     */
    public static String getDateYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String s = cal.get(Calendar.YEAR) + "";
        return s;
    }

    /**
     * 获取当前Date数据的月
     */
    public static String getDateMONTH(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String s = cal.get(Calendar.MONTH) + "";
        return s;
    }

    /**
     * 获取当前Date数据的日
     */
    public static String getDateDATE(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String s = cal.get(Calendar.DATE) + "";
        return s;
    }


    /**
     * 返回的字符串形式是形如：2013-10-20 20:58
     * */
    public static String formatTimeInMillis(long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String fmt = dateFormat.format(date);

        return fmt;
    }

    public static void main(String[] args) {
        System.out.println("RRRR11111::::"+System.currentTimeMillis());
        String s = DateUtil.formatTimeInMillis(1676442724070L);
        System.out.println("RRRR22222::::"+s);

    }
}
