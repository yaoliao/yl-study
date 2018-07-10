package com.yl.study.example17;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2018/5/15 0015.
 */
public class SpringBeanStudy {


    /**
     * 运行时将动态将Class加载为Bean
     *
     * @param args
     */
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationAop.xml");

//        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
//
//        RootBeanDefinition beanDefinition = new RootBeanDefinition();
//        beanDefinition.setBeanClass(MyTestBean.class);
//
//        beanFactory.registerBeanDefinition(MyTestBean.class.getName(), beanDefinition);


        MyTestBean bean = context.getBean("myTestBean", MyTestBean.class);


        /*CompilerBuilder compilerBuilder = new CompilerBuilder();
        MyBeanInterface myBeanInterface = null;
        try {
            myBeanInterface = (MyBeanInterface) compilerBuilder.toBuilder().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        bean.setMyBeanInterface(myBeanInterface);
        bean.say("defg");

        MyBeanInterface myBeanInterface1 = bean.getMyBeanInterface();
        myBeanInterface1.say("");


        MyTestBean bean1 = context.getBean("myTestBean", MyTestBean.class);
        MyBeanInterface myBeanInterface2 = bean.getMyBeanInterface();
        myBeanInterface2.say("");*/
    }

}
