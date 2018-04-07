package com.yl.study.example15;

import com.yl.study.example15.service.AopTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 小新
 * @date 2018/4/7
 */
//@RunWith
public class MainTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationAop.xml");
        AopTest aopTest = context.getBean("aopTest", AopTest.class);
        String yesterday = aopTest.sing("Yesterday");



    }

}
