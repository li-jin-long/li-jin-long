package com.cloudcc.frame.Utils;
import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

        public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String DATE_MILLIS = "yyyy-mm-dd HH:mm:ss.S";
        public static final String DATE_SLASH = "yyy/MM/dd HH:mm:ss";
        public static final String DATE_YEAR_MONTH_DAY = "yyy-MM-dd";
        public static final String DATE_YEAR_MONTH = "yyy-MM";
        public static final String HOUR_MINUTE_SECOND = "HH:mm:ss";
        public static final String DATE_MONTH = "MM";
        private static final String DATE_DAY = "dd";

        /**
         * 获取当前标准格式化日期时间
         *
         * @param
         * @return
         */
        public static String getDateTime() {
            return getDateTime(new Date());
        }

        /**
         * 标准格式化日期时间
         *
         * @param date
         * @return
         */
        public static String getDateTime(Date date) {
            return (new SimpleDateFormat(DATE_FORMAT)).format(date);
        }

        /**
         * 标准格式化日期时间
         *
         * @param date
         * @return
         */
        public static String getDateYmd(Date date) {
            return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).format(date);
        }

        /**
         * String 类型的 Date 转 Date 类型 Date
         * @param date
         * @return
         */
        public static Date getDateTimeYmd(String date) {
            try {
                return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                try {
                    return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).parse(date);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            return new Date();
        }

        /**
         * 日期格式String 转 Date
         *
         * @param date
         * @return
         */

        public static Date getDateTime(String date) {
            try {
                return (new SimpleDateFormat(DATE_FORMAT)).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                try {
                    return (new SimpleDateFormat(DATE_MILLIS)).parse(date);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            return new Date();
        }

        public static String getDate() {
            return getDateTime(new Date());
        }


        public static String getDate(Date date) {
            return (new SimpleDateFormat(DATE_SLASH)).format(date);
        }

        public static Date getDate(String date) {
            try {
                return (new SimpleDateFormat(DATE_SLASH)).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        }

        /**
         * 精确到毫秒
         *
         * @param date
         * @return
         */
        public static String getDateTimeMillis(Date date) {
            return (new SimpleDateFormat(DATE_MILLIS)).format(date);
        }

        public static String addDays(Date time) {
            Calendar date = getInstance();
            date.setTime(time);
            date.set(DATE, date.get(DATE) + 1);
            return (new SimpleDateFormat(DATE_FORMAT)).format(date.getTime());

        }

        public static String addDays(String time,int i) {
            Calendar date = getInstance();
            date.setTime(getDateTimeYmd(time));
            date.set(DATE, date.get(DATE) + i);
            return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).format(date.getTime());
        }


        public static String addDay(String time,int i) {
            Calendar date = getInstance();
            date.setTime(getDateTimeYmd(time));
            date.set(DATE, date.get(DATE) + i);
            return (new SimpleDateFormat(DATE_FORMAT)).format(date.getTime());
        }
        /**
         * 当前日期减几天
         *
         * @param i
         * @return
         */
        public static String subDays(int i) {
            Calendar date = getInstance();
            date.setTime(DateTimeUtils.getDateTime(DateTimeUtils.getDateTime()));
            date.set(DATE, date.get(DATE) - i);
            return (new SimpleDateFormat(DATE_FORMAT)).format(date.getTime());

        }

        public static String conversionTime(String date) {
            String time = addDays(getDateTimeYmd(date));
            return  getBeforeSecond(time,1);
        }

        public static String conversionDate(String date) {
            return getDateTime(getDateTimeYmd(date));
        }

        /**
         * 减去1秒
         * @param second
         * @return
         */
        public static String getBeforeSecond(String date, int second) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDateTimeYmd(date));
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - second);
            return (new SimpleDateFormat(DATE_FORMAT)).format(calendar.getTime());
        }

        /**
         * 减去几秒
         * @param second
         * @return
         */
        public static String BeforeSecond(int second){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - second);
            return (new SimpleDateFormat(DATE_FORMAT)).format(calendar.getTime());
        }

        public static String BeforeSecond(String date,int second){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDateTimeYmd(date));
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - second);
            return (new SimpleDateFormat(DATE_FORMAT)).format(calendar.getTime());
        }
        /**
         * 获取本月第一天
         *
         * @return
         */
        public static String getTimesMonthmorning() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 获取当前月份最后一天
         */

        public static String  getEndMonthDay(){
            Calendar cal = getInstance();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            return (new SimpleDateFormat(DATE_DAY)).format(cal.getTime());
        }
        /**
         * 本周第一天
         *
         * @return
         */
        public static String getTimesWeekmorning() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        public static String thisWeekMonDay() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).format(cal.getTime());
        }
        /**
         * 获取当前月一号
         * @return
         */
        public static String getBeginDayOfYear() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 获取当前月一号
         * @return
         */
        public static String getMonthDay() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
            return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).format(cal.getTime());
        }

        /**
         * 当前月份一号时间加一年
         *
         * @return
         */
        public static String getCurrentYearEndTime() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateTimeUtils.getDateTime(getBeginDayOfYear()));
            cal.add(Calendar.YEAR, 1);
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 当前月份一号减一年
         *
         * @return
         */
        public static String getLastYearStartTime() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateTimeUtils.getDateTime(getBeginDayOfYear()));
            cal.add(Calendar.YEAR, -1);
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 获取当前月份
         */

        public static String getMonth() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateTimeUtils.getDateTime(getBeginDayOfYear()));
            return (new SimpleDateFormat(DATE_MONTH)).format(cal.getTime());
        }
        /**
         * 获取今年第一天
         *
         * @return
         */
        public static String getCurrYearFirst() {
            Calendar currCal = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
            Date time = calendar.getTime();
            return (new SimpleDateFormat(DATE_FORMAT)).format(time);
        }

        public static String getCurrYearFirst(int i) {
            Calendar currCal = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
            calendar.add(MONTH,+i);
            Date time = calendar.getTime();
            return (new SimpleDateFormat(DATE_FORMAT)).format(time);
        }

        /**
         * 今天第一个月 月份格式
         * @return
         */
        public static String TheFirstMonth() {
            Calendar currCal = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
            Date time = calendar.getTime();
            return (new SimpleDateFormat(DATE_YEAR_MONTH)).format(time);
        }

        public static String TheFirstMonth(int i) {
            Calendar currCal = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
            calendar.add(MONTH,+i);
            Date time = calendar.getTime();
            return (new SimpleDateFormat(DATE_YEAR_MONTH)).format(time);
        }



        /**
         * 获取今年的最后一天
         *
         * @return
         */
        public static String getCurrYearLast() {
            Calendar currCal = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
            calendar.roll(Calendar.DAY_OF_YEAR, -1);
            Date time = calendar.getTime();
            return (new SimpleDateFormat(DATE_FORMAT)).format(time);
        }

        /**
         * 今天
         *
         * @return
         */
        public static String todayDate() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 昨天
         *
         * @return
         */
        public static String yesterday() {
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 1, 0, 0, 0);
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 前天
         *
         * @return
         */
        public static String beforeYesterDay() {
            Calendar cal = getInstance();
            cal.set(cal.get(YEAR), cal.get(MONTH), cal.get(DAY_OF_MONTH) - 2, 0, 0, 0);
            return (new SimpleDateFormat(DATE_FORMAT)).format(cal.getTime());
        }

        /**
         * 几个小时前
         *
         * @param ihour
         * @return
         */
        public static String getBeforeHourTime(int ihour) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
            return (new SimpleDateFormat(DATE_FORMAT)).format(calendar.getTime());
        }

        public static String getBeforeHourTime(String date, int ihour) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDateTimeYmd(date));
            calendar.add(Calendar.HOUR,+ihour);
            return (new SimpleDateFormat(DATE_FORMAT)).format(calendar.getTime());
        }

        public static String getDateYearMonthDay(Date date) {
            return (new SimpleDateFormat(DATE_YEAR_MONTH_DAY)).format(date);
        }

        /**
         * 传入两个日期获取它们之间的天数
         * @param before
         * @param after
         * @return
         */
        public static double getDistanceOfTwoDate(Date before, Date after) {
            long beforeTime = before.getTime();
            long afterTime = after.getTime();
            return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
        }

        /**
         * 传入两个日期获取它们之间的月数
         * @param before
         * @param after
         * @return
         */
        public static double getDistanceMonth(Date before, Date after){
            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(before);
            aft.setTime(after);
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
            return Math.abs(month + result);
        }

        public static void main(String[] args) {
            System.out.println("今年这个月一号：" + DateTimeUtils.getBeginDayOfYear());
            System.out.println("明年这个月一号：" + DateTimeUtils.getCurrentYearEndTime());
            System.out.println("去年这个月一号：: " + DateTimeUtils.getLastYearStartTime());
            System.out.println("获取今年开始时间: " + DateTimeUtils.getCurrYearFirst());
            System.out.println("本周第一天: " + DateTimeUtils.getTimesWeekmorning());
            System.out.println("今年年末: " + DateTimeUtils.getCurrYearLast());
            System.out.println("-----------------------------------------");
            System.out.println(DateTimeUtils.getDateTime(DateTimeUtils.getDateTime()));
            System.out.println(DateTimeUtils.getDateTime());
            System.out.println(DateTimeUtils.getDateTimeMillis(DateTimeUtils.getDateTime(DateTimeUtils.getDateTime())));
            System.out.println("-----------------------");
            System.out.println(DateTimeUtils.getDate(DateTimeUtils.getDateTime(DateTimeUtils.getDateTime())));
            System.out.println("今天: " + DateTimeUtils.todayDate());
            System.out.println(DateTimeUtils.yesterday());
            System.out.println(DateTimeUtils.beforeYesterDay());
            System.out.println(DateTimeUtils.getBeforeHourTime(3));
            System.out.println(DateTimeUtils.getDateTime(new Date()));
            String timesMonthmorning = DateTimeUtils.getTimesMonthmorning();
            System.out.println(timesMonthmorning);
            System.out.println("-=======================================================-");

            String date = "2021-2-4";
            Date dateTimeYmd = DateTimeUtils.getDateTimeYmd(date);
            System.out.println(dateTimeYmd);
            String s = DateTimeUtils.addDays(dateTimeYmd);
            System.out.println(s);
            System.out.println(DateTimeUtils.getDateTime());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp nowTime1 = Timestamp.valueOf(df.format(new Date(System.currentTimeMillis())));
            System.out.println(nowTime1);

            System.out.println("------------------------------------------------");

            System.out.println(DateTimeUtils.getDate());
            System.out.println("=================================");
            System.out.println(DateTimeUtils.getTimesWeekmorning());
            ArrayList<String> weekDays = new ArrayList<>();
            String weekMorning = DateTimeUtils.getTimesWeekmorning();
            weekDays.add(weekMorning);
            System.out.println(weekDays.size());
            System.out.println("——===================================---");
            System.out.println("获取今年开始时间: " + DateTimeUtils.TheFirstMonth());
            System.out.println("获取今年开始时间: " + DateTimeUtils.TheFirstMonth(1));
            String s1 = DateTimeUtils.todayDate();
            System.out.println(DateTimeUtils.getBeforeHourTime(s1, 1));
            System.out.println(DateTimeUtils.getBeforeHourTime(s1, 2));
            System.out.println(DateTimeUtils.getMonth());
            System.out.println(DateTimeUtils.getEndMonthDay());
            String  startDate = "2021-04-19";
            String  endDate = "2021-04-22";
            double distanceOfTwoDate = DateTimeUtils.getDistanceOfTwoDate(getDateTimeYmd(startDate), getDateTimeYmd(endDate));
            int a = (int) distanceOfTwoDate;
            System.out.println(distanceOfTwoDate);
            System.out.println(a);
        }
    }
