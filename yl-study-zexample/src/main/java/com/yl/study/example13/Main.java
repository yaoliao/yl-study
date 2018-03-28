package com.yl.study.example13;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 小新
 * @date 2018/3/25
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        PCDataFactory pcDataFactory = new PCDataFactory();
        int bufferSize = 1024 * 1024;
        //创建disruptor, 泛型参数:传递的事件的类型
        // 第一个参数: 产生Event的工厂类, Event封装生成-消费的数据
        // 第二个参数: RingBuffer的缓冲区大小
        // 第三个参数: 线程池
        // 第四个参数: SINGLE单个生产者, MULTI多个生产者
        // 第五个参数: WaitStrategy 当消费者阻塞在SequenceBarrier上, 消费者如何等待的策略.
        //BlockingWaitStrategy 使用锁和条件变量, 效率较低, 但CPU的消耗最小, 在不同部署环境下性能表现比较一致
        //SleepingWaitStrategy 多次循环尝试不成功后, 让出CPU, 等待下次调度; 多次调度后仍不成功, 睡眠纳秒级别的时间再尝试. 平衡了延迟和CPU资源占用, 但延迟不均匀.
        //YieldingWaitStrategy 多次循环尝试不成功后, 让出CPU, 等待下次调度. 平衡了延迟和CPU资源占用, 延迟也比较均匀.
        //BusySpinWaitStrategy 自旋等待，类似自旋锁. 低延迟但同时对CPU资源的占用也多.
        Disruptor<PCData> disruptor = new Disruptor<>(pcDataFactory, bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer(), new Consumer());
        disruptor.start();

        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (long i = 0L; true; i++) {
            buffer.putLong(0, i);
            producer.pushData(buffer);
            System.out.println("pushData : " + i);
            TimeUnit.MILLISECONDS.sleep(3000);

        }

    }

}
