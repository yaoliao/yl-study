package com.yl.study.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/9/11 0011
 * @time 下午 13:54
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class MyOkHttp {


    public static void main(String[] args) {

        /*Request request = new Request.Builder().url("http://192.168.100.142:9040/car-seller-client-interfaces/m/business/guarantee/cars/1_1_0_0")
                .addHeader("version", "9.0.0.0").build();
        OkHttpUtil.enqueue(request, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("啊呀出错了呀。。。。。。。。。。。。。。。");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("服务器端错误: " + response);
                }
                String string = response.body().string();
                System.out.println(string);
            }
        });*/


        // ==================================================

        String s = "http://testapi.che300.com/service/eval/getUsedCarPriceAnalysis?token=c072fccfda9fb2739b09a5b336fb36c9&modelId=28411&regDate=2018-01&mile=1&zone=281&color=%E9%BB%91%E8%89%B2&interior=%E4%BC%98&surface=%E4%BC%98&work_state=%E4%BC%98";
        int i = s.indexOf('?');
        String substring = s.substring(i+1, s.length());
        System.out.println(substring);


//        String url = "http://api.che300.com/service/eval/getUsedCarPriceAnalysis";
        String url = "http://testapi.che300.com/service/eval/getUsedCarPriceAnalysis";
//        String token = "e1bfeae567314d9a6a0831d2e449b900";
        String token = "c072fccfda9fb2739b09a5b336fb36c9";
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("token", token));
        list.add(new BasicNameValuePair("modelId", "28411"));
        list.add(new BasicNameValuePair("regDate", "2018-01"));
        list.add(new BasicNameValuePair("mile", "1"));
        list.add(new BasicNameValuePair("zone", "281"));
        list.add(new BasicNameValuePair("color", "黑色"));

        list.add(new BasicNameValuePair("interior", "优"));
        list.add(new BasicNameValuePair("surface","优"));
        list.add(new BasicNameValuePair("work_state", "优"));

        String fullUrl = OkHttpUtil.attachHttpGetParams(url, list);

        Request request = new Request.Builder().url(fullUrl).build();



        OkHttpUtil.enqueue(request, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("啊呀出错了呀。。。。。。。。。。。。。。。");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("服务器端错误: " + response);
                }
                String string = response.body().string();
                System.out.println(string+"====================================");
                String string1 = response.request().urlString();
                String string2 = response.request().toString();
                System.out.println(string1);
                System.out.println(string2);
                Che300PreRespon preRespon = JSON.parseObject(string, new TypeReference<Che300PreRespon>() {
                });
                if (preRespon != null && preRespon.getStatus().equals(1)) {
                    EvalPricesResult eval_prices = preRespon.getEval_prices();
                    Double b2b_price = eval_prices.getB2b_price();
                    Double c2b_price = eval_prices.getC2b_price();

                } else {
                    String s = preRespon != null ? preRespon.getError_msg() : "preRespon为空";
                    System.out.println(s);
                }

            }
        });

    }

}
