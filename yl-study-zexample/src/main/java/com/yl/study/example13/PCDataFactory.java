package com.yl.study.example13;

import com.lmax.disruptor.EventFactory;

/**
 * @author 小新
 * @date 2018/3/25
 */

public class PCDataFactory implements EventFactory<PCData> {

    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
