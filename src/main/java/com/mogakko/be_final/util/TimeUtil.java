package com.mogakko.be_final.util;

import java.sql.Time;
import java.time.LocalTime;

public class TimeUtil {
    public static Time addTimes(Time time1, Time time2) {
        LocalTime localTime1 = time1.toLocalTime();
        LocalTime localTime2 = time2.toLocalTime();
        LocalTime sum = localTime1.plusHours(localTime2.getHour())
                .plusMinutes(localTime2.getMinute())
                .plusSeconds(localTime2.getSecond());
        return Time.valueOf(sum);
    }

    public static String changeSecToTime(Long totalTime) {
        long hour, min;

        min = totalTime / 60 % 60;
        hour = totalTime / 3600;

        return String.format("%02dH%02dM", hour, min);
    }
}