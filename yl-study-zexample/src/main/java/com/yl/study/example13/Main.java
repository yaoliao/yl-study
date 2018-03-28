package com.yl.study.example13;

/**
 * @author DELL
 * @date 2018/3/28
 */
public class Main {

    public static void main(String[] args) {

        /**
         * 就是在一个Service内部，事务方法之间的嵌套调用，普通方法和事务方法之间的嵌套调用，都不会开启新的事务！
         *
         * 事务嵌套调用解决方法:
         *
         * 1、我们完全可以在抽出一个XxxService，在其内部调用UserService.txMethod()和UserService.txMethod2()方法即可。总而言之，避免在一个Service内部进行事务方法的嵌套调用！（因为动态代理导致这种场景事务失效了。
         * 2、可以通过在方法内部获得代理对象的方式，通过代理对象调用同类的其他方法，这也是Spring的官方文档中给出的方案。 beanFactory.getBean(name);
         *
         */

        UserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);
        UserService instance = userHandler.getInstance();

        System.out.println("---11111---------");
        /**
         * 无事务 的方法内部调用 有事务的方法 ，不开新事务
         */
        instance.method();   // 无事务 的方法内部调用 有事务的方法 ，不开新事务
        System.out.println("----2222--------");

        /**
         * 事务 的方法1 内部调用 有事务2 的方法 ，方法1开启事务， 方法2不开新事务
         */
        instance.txMethod1(); // 事务 的方法1 内部调用 有事务2 的方法 ，方法1开启事务， 方法2不开新事务
        System.out.println("-----3333---------");

        /**
         * 单独执行有事务的方法，开启事务
         */
        instance.txMethod2(); // 单独执行有事务的方法，开启事务
        System.out.println("-----4444---------");


    }

}
