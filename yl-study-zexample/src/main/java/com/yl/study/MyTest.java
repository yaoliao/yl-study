package com.yl.study;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/4/25 0025.
 */
public class MyTest {

    static class A {
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    static class B extends A {
        private String b;

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    static class C {
        private A a;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    private static A buidA(A a) {
        a.setA("我是A");
        return a;
    }


    public static String formatAmount(Double doubleVal, int scale, String unit) {
        if (doubleVal == null) {
            return unit;
        } else {
            StringBuilder stringBuilder = new StringBuilder("#.");

            for (int i = 1; i <= scale; ++i) {
                stringBuilder.append("0");
            }

            DecimalFormat decimalFormat = new DecimalFormat(stringBuilder.toString());
            NumberFormat numberFormat = NumberFormat.getInstance();
            return numberFormat.format(new Double(decimalFormat.format(doubleVal))) + unit;
        }
    }

    private static String convertPriceToString(String priceStr) {
        String headStr = priceStr;
        String tailStr = "";
        if (StringUtils.isNotBlank(headStr)) {
            String[] split = null;
            if (priceStr.contains(".")) {
                split = priceStr.split("\\.");
            }
            //拆分成两个部分
            if (split != null && split.length >= 2) {
                headStr = split[0];
                tailStr = "." + split[1];
            }
            if (StringUtils.isNotBlank(headStr) && headStr.length() >= 4) {
                headStr = delimitedAmount(headStr);
                return headStr + tailStr;
            }
        }
        return headStr;
    }

    /**
     * 145444 -> 145,444
     *
     * @param priceStr
     * @return
     */
    public static String delimitedAmount(String priceStr) {
        priceStr = new StringBuilder(priceStr).reverse().toString();     //先将字符串颠倒顺序
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < priceStr.length(); i++) {
            if (i * 3 + 3 > priceStr.length()) {
                str2.append(priceStr.substring(i * 3, priceStr.length()));
                break;
            }
            str2.append(priceStr.substring(i * 3, i * 3 + 3)).append(",");
        }
        if (str2.toString().endsWith(",")) {
            str2 = new StringBuilder(str2.substring(0, str2.length() - 1));
        }
        return new StringBuilder(str2.toString()).reverse().toString();
    }


    public static String double2String(Double d, boolean isGroupByDot) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(isGroupByDot);
        nf.setMaximumFractionDigits(4);
        // 结果未做任何处理
        return nf.format(d);
    }

    public static void main(String[] args) throws InterruptedException {

        /*System.out.println(double2String(2.00D, true));
        System.out.println(double2String(12.01D, true));
        System.out.println(double2String(1232D, true));
        System.out.println(double2String(114232.23667D, true));*/

        /*C c = new C();
        B b =new B();
        b.setB("我是B");
        System.out.println(buidA(b).getClass().getName());
        c.setA(buidA(b));
        System.out.println(JSON.toJSONString(c));*/


        /*int[] ints = new int[]{66,4,5,1,2,4,1,2,1};
        Set<Integer> set = new HashSet<>();

        for (int i : ints) {
            set.add(i);
        }

        Integer[] integers = set.toArray(new Integer[]{});
        for (Integer i : integers) {
            System.out.println(i);
        }*/


        /*System.out.println(null + "aaa");

        String string = new StringJoiner("").add(null).add("aaa").toString();
        System.out.println(string);


        String a = null;
        String b = null;
        String aa = Optional.ofNullable(a).orElse("") + Optional.ofNullable(b).orElse("");
        System.out.println(StringUtils.isEmpty(aa) ? "....." : "aaaaaaa");


        String string1 = new StringBuilder().append(a).append("aa").toString();
        System.out.println(string1);

        String bb = null + "aa";
        String aNull = bb.replace("null", null);
        System.out.println(bb);
        System.out.println(aNull);*/

        ExecutorService pool = Executors.newFixedThreadPool(1);
//        pool.execute();

        long l = System.currentTimeMillis();

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("在回调中执行耗时操作...");
            sleep();
            return 100;
        });
        future = future.thenCompose((e) -> CompletableFuture.supplyAsync(() -> {
            System.out.println("在回调的回调中执行耗时操作...");
            sleep();
            return e + 100;
        }));

        future.whenComplete((result, e) -> System.out.println(result + "............." + Optional.ofNullable(e).map(Throwable::getMessage).orElse("啊啊啊")));
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + " ms");
        new CountDownLatch(1).await();
    }

    private static void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
