package com.yl.study.example14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author DELL
 * @date 2018/3/28
 */
public class UserHandler implements InvocationHandler {

    private UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().startsWith("tx")) {
            System.out.println(method.getName() + " 开启事务");
        }
        Object invoke = method.invoke(userService, args);
        if (method.getName().startsWith("tx")) {
            System.out.println(method.getName() + " 提交");
        }
        return invoke;
    }

    public UserService getInstance() {
        UserService o = (UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserService.class}, this);
        return o;
    }
}
