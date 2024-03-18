package com;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class TestHash {

  public static Calendar calendar = Calendar.getInstance();

  public static void main(String[] args) {
    Set<String> set = new HashSet<>();
    System.out.println(set.hashCode());

    set.add("ABC");
    System.out.println(set.hashCode());

    set.add("DEF");
    System.out.println(set.hashCode());


    Instant now = Instant.now();
    Timestamp timestamp = Timestamp.from(now);
    Timestamp tenHoursBefore = Timestamp.from(now.minus(10, ChronoUnit.HOURS));
    Timestamp twoHoursBefore = Timestamp.from(now.minus(2, ChronoUnit.HOURS));
    Timestamp twoHoursAfter = Timestamp.from(now.plus(2, ChronoUnit.HOURS));
    int ten = getMinutesSinceStartOfDay(tenHoursBefore);
    int cur = getMinutesSinceStartOfDay(timestamp);
    int start = getMinutesSinceStartOfDay(twoHoursBefore);
    int end = getMinutesSinceStartOfDay(twoHoursAfter);


    boolean isAvailable;
    if (end > start) {
      System.out.println("Greater");
      isAvailable = cur >= start && cur <= end;
    } else {
      System.out.println("Smaller");
      isAvailable = cur <= end || cur >= start;
      System.out.println(ten <= end || ten >= start);
    }
    System.out.println(isAvailable);


  }

  private static int getMinutesSinceStartOfDay(Timestamp timestamp) {
    calendar.setTime(timestamp);
    return calendar.get(Calendar.MINUTE) + (60 * calendar.get(Calendar.HOUR_OF_DAY));
  }
}
