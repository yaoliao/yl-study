package com.yl.study.aop;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

/**
 * @author DELL
 * @date 2018/1/5
 */
public class MyAnnotationAop implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
//        AopUtils.getTargetClass()
        Object proceed = methodInvocation.proceed();
        String jsonString = JSON.toJSONString(proceed);
        System.out.println(jsonString);
        return proceed;
    }
}
