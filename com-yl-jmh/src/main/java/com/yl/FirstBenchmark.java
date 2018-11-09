package com.yl;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2018/11/5 0005
 * @time 上午 10:03
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class FirstBenchmark {

    @Benchmark
    public List<Integer> testMethod() {
        int cardCount = 54;
        List<Integer> cardList = new ArrayList<>();
        for (int i = 0; i < cardCount; i++) {
            cardList.add(i);
        }
        // 洗牌算法
        Random random = new Random();
        for (int i = 0; i < cardCount; i++) {
            int rand = random.nextInt(cardCount);
            Collections.swap(cardList, i, rand);
        }
        return cardList;
    }

    /*@Benchmark
    public int sleepAWhile() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }
        return 0;
    }*/

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(FirstBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        new Runner(options).run();
    }


}
