package com.chachae.common.core.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 时间工具
 *
 * @author chachae
 * @date 2019/11/14 17:05
 */
public class DateUtil {

  private static final DateTimeFormatter FORMATTER =
      DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  public static String nowStr() {
    LocalDateTime now = LocalDateTime.now();
    return FORMATTER.print(now);
  }

  public static Date nowDateTime() {
    LocalDateTime now = LocalDateTime.now();
    return now.toDate();
  }

  public static Date parse(String date) {
    DateTime dateTime = FORMATTER.parseDateTime(date);
    return dateTime.toDate();
  }
}
