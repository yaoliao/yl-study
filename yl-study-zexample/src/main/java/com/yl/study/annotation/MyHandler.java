package com.yl.study.annotation;

import com.yl.study.annotation.demo.DemoService;
import com.yl.study.annotation.demo.DemoServiceB;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

/**
 * @author yaoliao
 * @date 2018/11/12
 * @time 1:13
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class MyHandler {

    public static  <T> T getProxy(Class clazz, MyScan myScan) {
        T t = (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), clazz.getInterfaces(), new MyInvocationHandler(clazz, myScan));
        return t;
    }

    public static void main(String[] args) {

        MyScan myScan = new MyScan() {
            @Override
            public boolean equals(Object obj) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String value() {
                return "ccccccccc";
            }
        };
        DemoServiceB proxy = MyHandler.getProxy(DemoServiceB.class, myScan);
        proxy.call();
    }

}
