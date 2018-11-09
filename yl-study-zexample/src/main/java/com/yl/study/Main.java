package com.yl.study;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by DELL on 2017/11/17.
 */
public class Main {

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=156a171c60eedc7549d3a05575d09ac8d53c6d6025f6ecc0d31a6422c830d999";

    public static void main(String args[]) throws Exception{

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

//        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"@我干啥\"}}";
//        String textMsg = "{\"msgtype\":\"text\",\"text\":{\"content\":\"怕是个智障吧\"},\"at\":{\"atMobiles\":[\"13879828935\"],\"isAtAll\":false}}";
        String textMsg = "{\"msgtype\":\"text\",\"text\":{\"content\":\"测试\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }


        /*
        Thread aa = new Thread(() -> System.out.println("aa"));
        aa.interrupt();

        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.countDown();
        countDownLatch.await(5, TimeUnit.SECONDS);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        cyclicBarrier.await(5, TimeUnit.SECONDS);

        new Thread(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }));
        */


    }

}
