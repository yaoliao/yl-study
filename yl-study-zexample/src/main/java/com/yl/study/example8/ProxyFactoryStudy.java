package com.yl.study.example8;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by DELL on 2018/1/8.
 */
public class ProxyFactoryStudy {

    public static void main(String[] args) {

//        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.addAdvice(new MyMethodInterceptor());
//        proxyFactory.setTarget(new Java8Date());
//        Java8Date proxy = (Java8Date) proxyFactory.getProxy();
//        proxy.say();

//        String a = "欧IV+OBD";
        String a = "国IV(国V)";
//        String a = "国III";
        char[] chars = a.toCharArray();

        String s = a;
        for (int i = 0; i < a.length(); i++) {
            if(!isCnorEn(chars[i])){
                s = a.substring(0,i);
                break;
            }
        }
        System.out.println(s);


    }

//    static String isCnorEn(char c) {
//        if (c >= 0x0391 && c <= 0xFFE5) //中文字符
//            return "中";
//        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) { //英文字符
//            return "英文";
//        }
//        return "其他";
//    }

    static boolean isCnorEn(char c) {
        if (c >= 0x0391 && c <= 0xFFE5) //中文字符
            return true;
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) { //英文字符
            return true;
        }
        return false;
    }

    static class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            Object proceed = methodInvocation.proceed();
            System.out.println("MyMethodInterceptor ......................");
            return proceed;
        }
    }

}
