package com.yl.study;

import com.vip.vjtools.vjkit.collection.MoreMaps;
import com.vip.vjtools.vjkit.collection.type.primitive.IntObjectHashMap;
import com.vip.vjtools.vjkit.concurrent.ThreadUtil;
import com.vip.vjtools.vjkit.text.HashUtil;

import java.lang.management.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2018/8/30 0030
 * @time 下午 19:02
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class VipTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.stream(new Integer[]{1, 5, 2, 4, 5}).collect(Collectors.toList());

        System.out.println(list.getClass().isArray());

        List[] array = new ArrayList[5];

        Object[] objects = new Object[5];


        /*IntObjectHashMap<String> map = MoreMaps.createPrimitiveIntKeyMap(8, 0.5f);
        map.put(1, "11");
        map.put(2, "22");

        // 得到 concurrentHashSet
        Map<String, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
        Set<String> strings = Collections.newSetFromMap(concurrentHashMap);


        String locktest = "locktest";
        new Thread(() -> {
            synchronized (locktest) {
                try {
                    locktest.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程123").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        for (ThreadInfo threadInfo : threadMxBean.dumpAllThreads(true, true)) {
            System.out.println(getThreadDumpString(threadInfo));
//            stream.write(getThreadDumpString(threadInfo).getBytes());
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }


    private static String getThreadDumpString(ThreadInfo threadInfo) {
        StringBuilder sb = new StringBuilder("\"" + threadInfo.getThreadName() + "\"" +
                " Id=" + threadInfo.getThreadId() + " " +
                threadInfo.getThreadState());
        if (threadInfo.getLockName() != null) {
            sb.append(" on " + threadInfo.getLockName());
        }
        if (threadInfo.getLockOwnerName() != null) {
            sb.append(" owned by \"" + threadInfo.getLockOwnerName() +
                    "\" Id=" + threadInfo.getLockOwnerId());
        }
        if (threadInfo.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (threadInfo.isInNative()) {
            sb.append(" (in native)");
        }
        sb.append('\n');
        int i = 0;

        StackTraceElement[] stackTrace = threadInfo.getStackTrace();
        MonitorInfo[] lockedMonitors = threadInfo.getLockedMonitors();
        for (; i < stackTrace.length && i < 32; i++) {
            StackTraceElement ste = stackTrace[i];
            sb.append("\tat " + ste.toString());
            sb.append('\n');
            if (i == 0 && threadInfo.getLockInfo() != null) {
                Thread.State ts = threadInfo.getThreadState();
                switch (ts) {
                    case BLOCKED:
                        sb.append("\t-  blocked on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case WAITING:
                        sb.append("\t-  waiting on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case TIMED_WAITING:
                        sb.append("\t-  waiting on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    default:
                }
            }

            for (MonitorInfo mi : lockedMonitors) {
                if (mi.getLockedStackDepth() == i) {
                    sb.append("\t-  locked " + mi);
                    sb.append('\n');
                }
            }
        }
        if (i < stackTrace.length) {
            sb.append("\t...");
            sb.append('\n');
        }

        LockInfo[] locks = threadInfo.getLockedSynchronizers();
        if (locks.length > 0) {
            sb.append("\n\tNumber of locked synchronizers = " + locks.length);
            sb.append('\n');
            for (LockInfo li : locks) {
                sb.append("\t- " + li);
                sb.append('\n');
            }
        }
        sb.append('\n');
        return sb.toString();
    }

}
