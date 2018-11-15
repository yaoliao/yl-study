package com.yl.study.annotation;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yaoliao
 * @date 2018/11/15
 * @time 22:46
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class CglibProxy implements MethodInterceptor {

    private Object object;

    private MyScan myScan;

    public static Object getProxy(Object object, MyScan myScan) {
        CglibProxy cglibProxy = new CglibProxy();
        cglibProxy.object = object;
        cglibProxy.myScan = myScan;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(cglibProxy);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        String value = this.myScan.value();
        System.out.println("-----------Cglib before----------myScan.value：" + value + "--------");
        System.out.println(method.getName());
        Object invoke = method.invoke(object, args);
        System.out.println("-----------Cglib after------------------");
        return invoke;
    }
}
