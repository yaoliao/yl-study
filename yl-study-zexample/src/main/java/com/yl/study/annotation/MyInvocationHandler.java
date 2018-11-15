package com.yl.study.annotation;

import com.yl.study.annotation.demo.DemoService;
import com.yl.study.annotation.demo.DemoServiceB;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yaoliao
 * @date 2018/11/11
 * @time 22:00
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class MyInvocationHandler implements InvocationHandler {

    private Class<?> clazz;

    private MyScan myScan;

    public MyInvocationHandler(Class<?> clazz, MyScan myScan) {
        this.clazz = clazz;
        this.myScan = myScan;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = clazz.newInstance();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(o, args);
        }
        if ("toString".equals(methodName) && parameterTypes.length == 0) {
            return o.toString();
        }
        if ("hashCode".equals(methodName) && parameterTypes.length == 0) {
            return o.hashCode();
        }
        if ("equals".equals(methodName) && parameterTypes.length == 1) {
            return o.equals(args[0]);
        }
        String value = myScan.value();
        String name = method.getName();
        System.out.println(name);
        System.out.println("-------------------------  MyScan:  " + value + "  -----------------------");
        return method.invoke(o, args);
    }

    @SuppressWarnings("unchecked")
    public static Object getProxy(Class<?> clazz, MyScan myScan) {
        Object t = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), clazz.getInterfaces(), new MyInvocationHandler(clazz, myScan));
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
//        DemoServiceB proxy = MyInvocationHandler.getProxy(DemoServiceB.class, myScan);
//        proxy.call();
    }
}
