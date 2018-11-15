package com.yl.study.annotation;

import com.yl.study.annotation.demo.DemoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yaoliao
 * @date 2018/11/12
 * @time 22:55
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class MyInvocationHandlerV2 implements InvocationHandler {

    private DemoService demoService;

    public MyInvocationHandlerV2(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke  before  ===================================");

        System.out.println(method.getName());
        Object invoke = method.invoke(demoService, args);

        System.out.println("invoke  after  ===================================");
        return invoke;
    }

    public static DemoService getProxy(DemoService demoService) {

        DemoService service = (DemoService) Proxy.newProxyInstance(demoService.getClass().getClassLoader(), new Class[]{DemoService.class}, new MyInvocationHandlerV2(demoService));
        return service;
    }

}
