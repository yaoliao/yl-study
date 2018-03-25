package com.yl.study.example13;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author 小新
 * @date 2018/3/25
 */

public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {

        long sequence = ringBuffer.next();
        try {
            PCData event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }

    }

}
