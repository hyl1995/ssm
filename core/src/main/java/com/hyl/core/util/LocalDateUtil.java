package com.hyl.core.util;

import cn.hutool.core.date.DateUnit;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @description: com.yhw.api.utils
 * @author: hyl
 * @data: 2019/10/22/022
 */
public class LocalDateUtil {
    public final static String FORMAT_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_PATTERN2 = "yyyyMMddHHmmss";
    public final static String FORMAT_PATTERN3 = "yyyy-MM-dd";
    public final static String FORMAT_PATTERN4 = "yyyyMMdd";

    /**
     * Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     * @param time
     * @return
     */
    public static Date toDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 默认格式的当前时间
     * @return
     */
    public static String now() {
        return localDateTimeFormat(LocalDateTime.now(),LocalDateUtil.FORMAT_PATTERN1);
    }

    /**
     * 自定义格式的当前时间
     * @param pattern
     * @return
     */
    public static String now(String pattern) {
        return localDateTimeFormat(LocalDateTime.now(), pattern);
    }

    /**
     * 变更时区为UTC
     * @param localDateTime
     * @return
     */
    public static Instant convertTimeZone(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field 单位(年月日时分秒)
     * @return
     */
    public static long between(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
    }

    /**
     * 1小时内显示分钟，24小时内显示小时，30天前天，12个月内显示月，超过12个月显示年
     * @param before
     * @return
     */
    public static String transferCN(long before) {
        long current = System.currentTimeMillis();
        long interval = current - before;
        if (interval <= 0) {
            return null;
        }
        if (interval <= DateUnit.MINUTE.getMillis()) {
            return "刚刚";
        } else if (interval <= DateUnit.HOUR.getMillis()) {
            return (interval/DateUnit.MINUTE.getMillis()) + "分钟前";
        } else if (interval <= DateUnit.DAY.getMillis()) {
            return (interval/DateUnit.HOUR.getMillis()) + "小时前";
        } else if (interval <= (DateUnit.DAY.getMillis()) * 30) {
            return (interval/DateUnit.DAY.getMillis()) + "天前";
        } else if (interval <= (DateUnit.DAY.getMillis() * 30 * 12)) {
            return (interval/(DateUnit.DAY.getMillis() * 30)) + "月前";
        } else {
            return (interval/(DateUnit.DAY.getMillis() * 30 * 12)) + "年前";
        }

    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
    }
}
