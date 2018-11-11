package com.yl.study.annotation.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yaoliao
 * @date 2018/11/11
 * @time 23:56
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class DemoMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        DemoServiceA demoServiceA = context.getBean("demoServiceA", DemoServiceA.class);
        demoServiceA.call();
    }

}
