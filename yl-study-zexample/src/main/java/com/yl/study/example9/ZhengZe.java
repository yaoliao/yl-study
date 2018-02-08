package com.yl.study.example9;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/1/18.
 */
public class ZhengZe {

    public static void main(String[] args) {

        /*String a = "33unj44啊啊啊";
        String b = "33unj44";

        if(b.matches("([0-9]*[a-zA-Z]+[0-9]*)*")){
            System.out.println("ok==");
        }else {
            System.out.println("GG");
        }*/


        Aa aa = new Aa(65, 1, 15, "宝马", 65, "5系", "华晨宝马 5系");
        Aa aa1 = new Aa(18, 1, 33, "奥迪", 18, "A6L", "一汽-大众奥迪 A6L");
        Aa aa2 = new Aa(692, 1, 33, "奥迪", 692, "A4L", "一汽-大众奥迪 A4L");
        Aa aa3 = new Aa(110, 1, 3, "丰田", 110, "凯美瑞", "广汽丰田 凯美瑞");
        Aa aa4 = new Aa(364, 1, 8, "福特", 364, "福克斯", "长安福特 福克斯");
        Aa aa5 = new Aa(657, 1, 71, "雪佛兰", 657, "科鲁兹", "上汽通用雪佛兰 科鲁兹");
        Aa aa6 = new Aa(1, 0, 1, "大众", null, null, "大众");
        Aa aa7 = new Aa(38, 0, 38, "别克", null, null, "别克");
        Aa aa8 = new Aa(528, 1, 1, "大众", 528, "帕萨特", "上海大众 帕萨特");
        Aa aa9 = new Aa(78, 1, 14, "本田", 78, "雅阁", "广汽本田 雅阁");
        List<Aa> list = new ArrayList<>();
        list.add(aa);
        list.add(aa1);
        list.add(aa2);
        list.add(aa3);
        list.add(aa4);
        list.add(aa5);
        list.add(aa6);
        list.add(aa7);
        list.add(aa8);
        list.add(aa9);
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);

        List<Aa> list1 = JSON.parseObject(jsonString, new TypeReference<List<Aa>>() {
        });
        System.out.println("=============");

        list1.forEach(a -> System.out.println(a.getBrandName()));

    }

}
