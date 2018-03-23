package com.yl.study.example10;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by DELL on 2018/2/26.
 */
public class TreeMapStudy {

    public static void main(String[] args) {

        Map<Long, String> map = new TreeMap<>();

        map.put(1L, "1");
        map.put(3L, "3-2");
        map.put(2L, "2");
        map.put(3L, "3-1");
        map.put(0L, "0");

        map.forEach((aLong, s) -> {
            System.out.println(aLong + "  " + s);
        });


        Date now = new Date();
        SimpleDateFormat s = new SimpleDateFormat("YY-MM-dd");
        SimpleDateFormat ss = new SimpleDateFormat("YYYY-MM-dd");
        String format = s.format(now);
        System.out.println(format);
        System.out.println(ss.format(now));

    }

}
