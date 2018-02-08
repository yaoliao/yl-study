package com.yl.study.example9;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by DELL on 2018/1/18.
 */
public class MyCalendar {

    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(date.getTime());
        Date cycleData = getCycleData(date, Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        System.out.println(cycleData.getTime());

    }

    public static Date getCycleData(Date date, int cycleType, int days) {
        if (days <= 0) {
            throw new RuntimeException("天数必须大于0");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(cycleType, days);
        return calendar.getTime();
    }

}
