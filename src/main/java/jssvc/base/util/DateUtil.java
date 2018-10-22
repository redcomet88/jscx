package jssvc.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Description 日期处理工具类
 * 
 * @author 孙小玉 @date 2016-07-11 @reason 新增
 */
public class DateUtil {

    /***************************************************************************
     * @Description 不同格式的时间格式字符串转化为时间
     * @param dateStr日期格式字符串
     * @return Date 日期
     * @author 孙小玉 @date 2016-07-11 @reason 新增
     **************************************************************************/
    public static Date parser(String dateStr) {
        String[] patterns = new String[] { "yyyy年MM月dd日", "MM/dd/yyyy" , "yyyy-MM-dd hh:mm:ss", "yyyy/MM/dd", "yyyy-MM-dd", "yyyy-MM", "kk:mm", "yyyyMMdd", "yyyy" };
        DateFormat dateFormat = null;
        Date date = null;
        for (String pattern : patterns) {
            dateFormat = new SimpleDateFormat(pattern);
            try {
                date = dateFormat.parse(dateStr);
                if ("kk:mm".equals(pattern)) {// set the current date to this
                    // parser result
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    Calendar now = Calendar.getInstance();
                    now.setTime(new Date());
                    c.set(Calendar.YEAR, now.get(Calendar.YEAR));
                    c.set(Calendar.MONTH, now.get(Calendar.MONTH));
                    c.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR));
                    date = c.getTime();
                }
                return date;
            } catch (ParseException e) {
                continue;
            }
        }
        return date;
    }

    /**
     * @Description 日期的格式化，日期的格式化为yyyy年MM月dd日形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016 转化为'2016年07月11日'）
     * @param date日期
     * @return String 字符类型的日期格式
     * @author 孙小玉 @date 2016-07-11 @reason 新增
     * @author 孙小玉 @date 2016-07-11 @reason 修改 日期的格式化
     */
    public static String getChinaDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * @Description 日期的格式化，日期的格式化为yyyy/M/dd形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016 转化为'2016/7/11日'）
     * @param date日期
     * @return String 字符类型的日期格式
     * @author 陈浩 @date 2016-07-11 @reason 新增
     * @author 陈浩 @date 2016-07-11 @reason 修改 日期的格式化
     */
    public static String getSlashDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/M/d", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * @author 孙小玉 @date 2016-07-11 @reason 新增
     * @param date日期
     * @return String 字符类型的日期格式
     * @Description 日期的格式化，日期的格式化为yyyy-MM-dd形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016 转化为'2016-07-11'）
     * @author 孙小玉 @date 2016-07-11 @reason 修改 日期的格式化
     */
    public static String getSimpleDateString(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return dateFormat.format(date);
    }

    /***************************************************************************
     * 描述： 日期的格式化，日期的格式化为yyyy-MM形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016 转化为'2016-07'） 传入参入： date日期 返回结果： String 字符类型的日期格式 作者： 孙小玉 日期： 2016-07-11 事由： 新增
     **************************************************************************/
    public static String getSimpleDateMonthString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * 描述： 日期的格式化，日期的格式化为yyyy-MM-dd kk:mm:ss形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016转化为'2016-07-11 14:59:20'）
     * 
     * 传入参入： date日期 返回结果： String 字符类型的日期格式 作者： 孙小玉 日期： 2016-07-11 事由： 新增 作者： 孙小玉 日期： 2016-07-11 事由： 修改 日期的格式化
     */
    public static String getSimpleDateTimeString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * 作者： 孙小玉 日期： 2016-07-11 事由： 新增 传入参入： date日期 返回结果： String 字符类型的日期格式 描述： 日期的格式化，日期的格式化为yyyyMMdd形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016转化为'20160711'）
     */
    public static String getSimpleDateStringYYYYMMDD(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * 比较两个时间
     * @param 时间戳1
     * @param 时间戳2
     * @return 时间戳1<时间戳2:true,否则：false
     */
    public static boolean isBefore(Date firstDate, Date secondDate) {
        return firstDate.before(secondDate);
    }

    /**
     * 获取当前时间的时分秒为0的时间戳
     * @param 时间戳
     * @return 时分秒为0的时间戳
     */
    public static Date getTheStartTimeOfDate(Date date) {
        String dateStr = getSimpleDateString(date);
        return parser(dateStr);
    }

    /**
     * 获取当前时间的时分秒为23:59:59的时间戳
     * @param 时间戳
     * @return 时分秒为23:59:59的时间戳
     */
    public static Date getTheEndTimeOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, 1);

        Date nextDayStartDate = getTheStartTimeOfDate(c.getTime());
        c.setTime(nextDayStartDate);
        c.add(Calendar.SECOND, -1);

        return c.getTime();
    }

    /**
     * 加日期
     * @param 源日期
     * @param 天数
     * @return 目标日期
     */
    public static Date addDay(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);

        return c.getTime();
    }

    /**
     * 加月
     * @param 源日期
     * @param 月数
     * @return 目标日期
     */
    public static Date addMonth(Date date, int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);

        return c.getTime();
    }

    /**
     * 判断日期是否相等
     * @param 日期1
     * @param 日期2
     * @return true：相等 false：不相等
     */
    public static boolean isEqual(Date firstDate, Date secondDate) {
        return getSimpleDateString(firstDate).equals(getSimpleDateString(secondDate));
    }

    /**
     * @Description 日期的格式化，日期的格式化为yyyyMMddHHmmss形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016转化为'20160711145920'）
     * 
     * @param date 日期
     * @return String 字符类型的日期格式
     * @author 孙小玉 @date 2016-07-11 @reason 新增
     */
    public static String getSimpleDateStringYYYYMMDDHHMM(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * 描述： 日期的格式化，日期的格式化为kk:mm形式 （例：Mon Jul 11 14:59:20 GMT+08:00 2016转化为'14:59'）
     * 
     * 传入参入： date日期 返回结果： String 字符类型的日期格式 作者： 唐振平 日期： 2016-08-23 事由： 新增
     */
    public static String getHourMinuteString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("kk:mm", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * 加小时
     * @param 源日期
     * @param 小时
     * @return 目标日期
     */
    public static Date addHour(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);

        return c.getTime();
    }

    /**
     * 加分钟
     * @param 源日期
     * @param 分钟
     * @return 目标日期
     */
    public static Date addMinute(Date date, int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);

        return c.getTime();
    }

    /**
     * 获取当前时间 时间格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /***************************************************************************
     * @Description 日期类型转换
     * @param date 待转换日期
     * @param type 转换日期格式
     * @return 转换后的日期
     * @author 刘炜 @date 2017-09-21 @reason 新增
     **************************************************************************/
    public static String changeDateType(String date, String type) {
        String newDate = "";
        DateFormat dateFormat = new SimpleDateFormat(type);
        try {
            Date formDate = DateUtil.parser(date);
            newDate = dateFormat.format(formDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 获取月末日期
     * @return 月末日期
     */
    public static Date getLastMontyDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        GregorianCalendar c = new GregorianCalendar();
        String Feb = "";
        if (c.isLeapYear(year)) {
            Feb = "-2-29";
        } else {
            Feb = "-2-28";
        }
        Date result = null;
        switch (month) {
        case 1:
            result = parser(year + "-1-31");
            break;
        case 2:
            result = parser(year + Feb);
            break;
        case 3:
            result = parser(year + "-3-31");
            break;
        case 4:
            result = parser(year + "-4-30");
            break;
        case 5:
            result = parser(year + "-5-31");
            break;
        case 6:
            result = parser(year + "-6-30");
            break;
        case 7:
            result = parser(year + "-7-31");
            break;
        case 8:
            result = parser(year + "-8-31");
            break;
        case 9:
            result = parser(year + "-9-30");
            break;
        case 10:
            result = parser(year + "-10-31");
            break;
        case 11:
            result = parser(year + "-11-30");
            break;
        case 12:
            result = parser(year + "-12-31");
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * 获取月初日期
     * @return 月初日期
     */
    public static Date getFirstMontyDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        return parser(year + "-" + month + "-" + "01");
    }

    /***************************************************************************
     * @Description 获取某个月的全部天数
     * @param month 年月
     * @return 某个月的全部天数
     * @author 刘炜 @date 2017-09-21 @reason 新增
     **************************************************************************/
    @SuppressWarnings("deprecation")
    public static String[] getAllMonthDays(String month) {
        int maxDate = 0;
        Date firstMontyDay = null;
        firstMontyDay = DateUtil.getFirstMontyDay(DateUtil.parser(month));
        Date lastMontyDay = DateUtil.getLastMontyDay(DateUtil.parser(month));
        maxDate = lastMontyDay.getDate();
        Date[] dates = new Date[maxDate];
        String[] datesArr = new String[maxDate];
        for (int i = 1; i <= maxDate; i++) {
            dates[i - 1] = new Date(firstMontyDay.getTime());
            firstMontyDay.setDate(firstMontyDay.getDate() + 1);
            datesArr[i - 1] = DateUtil.getSimpleDateString(dates[i - 1]);
        }
        return datesArr;
    }

    /***************************************************************************
     * @Description 获取指定日期是周几
     * @param 指定日期
     * @return 周一到周日
     * @author 刘炜 @date 2017-09-22 @reason 新增
     **************************************************************************/
    public static String getWeek(Date currentDate) {
        Calendar now = Calendar.getInstance();
        now.setTime(currentDate);
        // 取得指定日期当周位置（周日1、周一到周六2~7）
        int week = now.get(Calendar.DAY_OF_WEEK);
        String result = "";
        switch (week) {
        case 1:
            result = "星期日";
            break;
        case 2:
            result = "星期一";
            break;
        case 3:
            result = "星期二";
            break;
        case 4:
            result = "星期三";
            break;
        case 5:
            result = "星期四";
            break;
        case 6:
            result = "星期五";
            break;
        case 7:
            result = "星期六";
            break;
        default:
            break;
        }
        return result;
    }

    /***************************************************************************
     * @Description 获取int型日期的年月
     * @param date 日期
     * @return int型日期的年月
     * @author 刘炜 @date 2017-09-26 @reason 新增
     **************************************************************************/
    public static int getYearAndMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    /**
     * 转换成时间格式
     * @param 日期
     * @return date
     */
    public static Date getSimpleDateTimeDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        return dateFormat.parse(date.replace("T", " "));
    }

    /**
     * 获取指定日期格式的日期字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getSpecifiedDateFormatString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        return dateFormat.format(date);
    }
}
