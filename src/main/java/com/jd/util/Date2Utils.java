package com.jd.util;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author lk
 * @version 1.0
 * @date 2020/10/9 13:21
 * @description: 时间工具类
 */
public class Date2Utils {
    /**
     * 时间对象转字符串
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String dateToStr(Date date) {
      SimpleDateFormat format = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
      return format.format(date);
    }    /**
     * 时间对象转字符串
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String dateToStr(Date date, String pattern) {
      SimpleDateFormat format = new SimpleDateFormat(pattern);
      return format.format(date);
    }    /**
     * 字符串转时间对象
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr) throws ParseException {
      SimpleDateFormat format = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
      Date date = format.parse(dateStr);        return date;
    }    /**
     * 字符串转时间对象
     *
     * @param dateStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr, String pattern) throws ParseException {
      SimpleDateFormat format = new SimpleDateFormat(pattern);
      Date date = format.parse(dateStr);
      return date;
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date    开始时间
     * @param endDate 结束时间
     * @return 返回相差天数
     * @throws ParseException
     */
    public static long getBetweenDay(Date date, Date endDate) throws ParseException {
      SimpleDateFormat dfs = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN);
      String dateStr = dfs.format(date);
      String endDateStr = dfs.format(endDate);
      Date parseDate = dfs.parse(dateStr);
      Date parseEndDate = dfs.parse(endDateStr);
      long day = DateUtil.between(parseDate, parseEndDate, DateUnit.DAY);
      return day;
    }    /**
     * 今天的开始时间
     *
     * @return yyyy-MM-dd 00:00:00
     */
    public static String dayStart() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
      LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
      String dayStartStr = todayStart.format(dateTimeFormatter);
      return dayStartStr;
    }

    /**
     * 今天的结束时间
     *
     * @return yyyy-MM-dd 23:59:59
     */
    public static String dayEnd() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
      LocalDateTime dayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
      String dayEndStr = dayEnd.format(dateTimeFormatter);
      return dayEndStr;
    }

    /**
     * 本周开始时间
     *
     * @return yyyy-MM-dd 00:00:00
     */
    public static String weekStart() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
      LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
      TemporalAdjuster firstWeek = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
      String weekStart = localDateTime.with(firstWeek).format(dateTimeFormatter);
      return weekStart;
    }    /**
     * 本周结束时间
     *
     * @return yyyy-MM-dd 23:59:59
     */
    public static String weekEnd() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
      LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
      TemporalAdjuster lastWeek = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
      String weekEnd = todayEnd.with(lastWeek).format(dateTimeFormatter);
      return weekEnd;
    }

    /**
     * 本月开始时间
     *
     * @return yyyy-MM-dd 00:00:00
     */
    public static String monthStart() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
      LocalDateTime localDateTime = LocalDateTime.now();
      LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), 1);
      String monthStart = LocalDateTime.of(localDate, LocalTime.MIN).format(dateTimeFormatter);
      return monthStart;
    }

    /**
     * 本月结束时间
     *
     * @return yyyy-MM-dd 23:59:59
     */
    public static String monthEnd() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
      LocalDateTime todayEnd = LocalDateTime.now();
      LocalDateTime with = todayEnd.with(TemporalAdjusters.lastDayOfMonth());
      LocalDateTime localDateTime = LocalDateTime.of(with.toLocalDate(), LocalTime.MAX);
      String monthEnd = localDateTime.format(dateTimeFormatter);
      return monthEnd;
    }

    /**
     * 今年开始时间
     *
     * @return yyyy-MM-dd 00:00:00
     */
    public static String yearStart() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);        LocalDateTime localDateTime = LocalDateTime.now();        LocalDate localDate = LocalDate.of(localDateTime.getYear(), 1, 1);
      String yearStart = LocalDateTime.of(localDate, LocalTime.MIN).format(dateTimeFormatter);        return yearStart;
    }    /**
     * 今年结束时间
     *
     * @return yyyy-MM-dd 23:59:59
     */
    public static String yearEnd() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);        LocalDateTime todayEnd = LocalDateTime.now();        LocalDateTime with = todayEnd.with(TemporalAdjusters.lastDayOfYear());        LocalDateTime localDateTime = LocalDateTime.of(with.toLocalDate(), LocalTime.MAX);        String yearEnd = localDateTime.format(dateTimeFormatter);        return yearEnd;
    }    /**
     * 重置秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date resetSecond(Date date, int second) {
      if (second < 0 || second > 59) {
        return date;
      }        LocalDateTime localDate = dateToLocalDateTime(date);        localDate = localDate.withSecond(second);        Date newDate = localDateTimeToDate(localDate);        return newDate;
    }    /**
     * 重置秒
     *
     * @param dateStr
     * @param second
     * @return
     * @throws ParseException
     */
    public static Date resetSecond(String dateStr, int second) throws ParseException {
      if (second < 0 || second > 59) {
        return strToDate(dateStr);
      }        Date date = strToDate(dateStr);        LocalDateTime localDate = dateToLocalDateTime(date);        localDate = localDate.withSecond(second);        Date newDate = localDateTimeToDate(localDate);        return newDate;
    }    public static Date localDateToDate(LocalDate localDate) {
      return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }    public static LocalDate dateToLocalDate(Date date) {
      Instant instant = date.toInstant();        ZoneId zone = ZoneId.systemDefault();        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);        LocalDate localDate = localDateTime.toLocalDate();        return localDate;
    }    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
      ZoneId zoneId = ZoneId.systemDefault();        ZonedDateTime zdt = localDateTime.atZone(zoneId);        Date date = Date.from(zdt.toInstant());        return date;
    }    public static LocalDateTime dateToLocalDateTime(Date date) {
      Instant instant = date.toInstant();        ZoneId zoneId = ZoneId.systemDefault();        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();        return localDateTime;
    }    /**
     * 判断两个时间是否相等
     *
     * @param date
     * @param date2
     * @return
     */
    public static boolean equal(Date date, Date date2) {
      LocalDateTime localDateTime = dateToLocalDateTime(date);        LocalDateTime localDateTime2 = dateToLocalDateTime(date2);        return localDateTime.isEqual(localDateTime2);
    }    /**
     * 判断date 日期是否小于date2
     *
     * @param date
     * @param date2
     * @return
     */
    public static boolean after(Date date, Date date2) {
      LocalDateTime localDateTime = dateToLocalDateTime(date);        LocalDateTime localDateTime2 = dateToLocalDateTime(date2);        return localDateTime.isAfter(localDateTime2);
    }    /**
     * 比较日期相差多少天
     *
     * @param date
     * @param date2
     * @return
     */
    public static int differenceDays(Date date, Date date2) {
      LocalDate localDateTime = dateToLocalDate(date);        LocalDate localDateTime2 = dateToLocalDate(date2);        Period period = Period.between(localDateTime, localDateTime2);        return period.getDays();
    }    public static int differenceDays(LocalDate localDate, LocalDate localDate2) {
      Period period = Period.between(localDate, localDate2);        return period.getDays();
    }
}
