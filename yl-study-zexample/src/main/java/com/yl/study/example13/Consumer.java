package com.yl.study.example13;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 小新
 * @date 2018/3/25
 */

public class Consumer implements WorkHandler<PCData> {

    private String aa;

    public Consumer(String aa) {
        this.aa = aa;
    }

    @Override
    public void onEvent(PCData pcData) throws Exception {
//        int i = new Random().nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(" " + aa + "时间 " + "" + " 线程ID : " + Thread.currentThread().getId() + " : event : ---" + pcData.getValue());
    }
}
