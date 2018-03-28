##同一类里面事务嵌套调用无效


就是在一个Service内部，事务方法之间的嵌套调用，普通方法和事务方法之间的嵌套调用，都不会开启新的事务！
         
事务嵌套调用解决方法:
         
1、我们完全可以在抽出一个XxxService，在其内部调用UserService.txMethod()和UserService.txMethod2()方法即可。总而言之，避免在一个Service内部进行事务方法的嵌套调用！（因为动态代理导致这种场景事务失效了。
2、可以通过在方法内部获得代理对象的方式，通过代理对象调用同类的其他方法，这也是Spring的官方文档中给出的方案。 beanFactory.getBean(name);
         
         