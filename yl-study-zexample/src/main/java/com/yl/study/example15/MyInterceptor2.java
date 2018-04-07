package com.yl.study.example15;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @author 小新
 * @date 2018/4/7
 */
@Aspect
@Order(value = 222)
public class MyInterceptor2 {

    private final String POINT_CUT = "execution(public * com.yl.study.example15.service..*.*(..))";

    @Pointcut("execution(public * com.yl.study.example15.service..*.*(..))")
    public void myPoint() {
    }

    @Before(POINT_CUT)
    public void before() {
        System.out.println("before  2222............");
    }

    @After(POINT_CUT)
    public void after() {
        System.out.println("after  2222..............");
    }

    @AfterReturning(POINT_CUT)
    public void afterReturning() {
        System.out.println("afterReturning  2222 ...........");
    }

    @AfterThrowing("myPoint()")
    public void afterThrowing() {
        System.out.println("AfterThrowing   222...........");
    }

    @Around("myPoint()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("around  before  222 ============");
        Object proceed = jp.proceed();
        System.out.println("around  after  222  ============");

        return proceed;
    }

}
