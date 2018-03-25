package com.yl.study.example13;

import com.lmax.disruptor.WorkHandler;

/**
 * @author 小新
 * @date 2018/3/25
 */

public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + " : event : ---" + pcData.getValue());
    }
}
