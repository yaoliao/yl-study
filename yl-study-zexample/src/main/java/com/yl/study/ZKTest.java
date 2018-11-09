package com.yl.study;

import org.I0Itec.zkclient.ZkClient;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 17:24
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ZKTest {

    public static void main(String[] args) {

        /*ZkClient client = new ZkClient("116.196.100.123:2181");
        String root = "/test_root";
//        client.createPersistent(root);
//        client.createEphemeral(root + "/child_1");

//        client.createPersistent(root + "/child_2/tom");
//        client.delete(root + "/child_2/tom");
        client.delete(root);*/


        Calendar c1 = Calendar.getInstance(); // 创建日历对象
        Calendar c2 = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date parse = null;
        try {
            parse = format.parse("2015-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c1.setTime(parse);
        c2.setTime(new Date());
        int years = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR); // 计算年度差
        System.out.println(years);


        int yearDiff = getYearDiff(parse, new Date());
        System.out.println(yearDiff);

    }


    public static int getYearDiff(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            if (date1.after(date2)) {
                throw new InvalidParameterException("date1 cannot be after date2!");
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                int year1 = calendar.get(1);
                int month1 = calendar.get(2);
                int day1 = calendar.get(5);
                calendar.setTime(date2);
                int year2 = calendar.get(1);
                int month2 = calendar.get(2);
                int day2 = calendar.get(5);
                int result = year2 - year1;
                if (month2 >= month1) {
                    ++result;
                }

                return result;
            }
        } else {
            throw new InvalidParameterException("date1 and date2 cannot be null!");
        }
    }

}
