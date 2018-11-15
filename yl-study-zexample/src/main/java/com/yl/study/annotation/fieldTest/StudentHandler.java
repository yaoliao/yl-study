package com.yl.study.annotation.fieldTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yaoliao
 * @date 2018/11/12
 * @time 22:23
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class StudentHandler implements InvocationHandler {

    private MyClass myClass;

    public StudentHandler(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke  before  ===================================");

        Object invoke = method.invoke(myClass, args);

        System.out.println("invoke  after  ===================================");
        return invoke;
    }

    public MyClass getProxy() {
        MyClass o = (MyClass) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{MyClass.class}, this);
        return o;
    }

    public static MyClass get(MyClass myClass) {
        MyClass myClass1 = (MyClass) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{MyClass.class}, new StudentHandler(myClass));
        return myClass1;
    }

}
