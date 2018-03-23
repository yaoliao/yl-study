package com.yl.study.example12;

import java.math.BigDecimal;

/**
 * Created by DELL on 2018/3/23.
 */
public class StripTrailingZeros {

    public static void main(String[] args) {

        /**
         * 小数点末尾去0  23.20 --> 23.2  23.00 --> 23
         */
        String string = new BigDecimal(Double.toString(60.020D)).stripTrailingZeros().toPlainString();
        String string1 = new BigDecimal("60.00").stripTrailingZeros().toPlainString();

        System.out.println(string);
        System.out.println(string1);

        /**
         * 保留两位小数
         */
        String price = String.format("%.2f", 12345678.21D / 10000);
        System.out.println(price);

        Double d = 123.020D;
        Double d1 = 123.000D;
        System.out.println(Double.toString(d));
    }
}
