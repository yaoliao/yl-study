package com.yl.study.example17;

import org.springframework.beans.PropertyValues;
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

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(MyTestBean.class);

//        beanDefinition.getPropertyValues().addPropertyValue("id","AAAA");
        beanDefinition.getPropertyValues().addPropertyValue("name","Jack");
        beanDefinition.getPropertyValues().addPropertyValue("age","22");

        beanFactory.registerBeanDefinition(MyTestBean.class.getName(), beanDefinition);


        MyTestBean bean = context.getBean(MyTestBean.class.getName(), MyTestBean.class);
        bean.say();
    }

}
