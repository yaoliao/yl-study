package com.yl.study.example15;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @author 小新
 * @date 2018/4/7
 */
@Aspect
@Order(value = 233)
public class MyInterceptor1 {

    private final String POINT_CUT = "execution(public * com.yl.study.example15.service..*.*(..))";

    @Pointcut("execution(public * com.yl.study.example15.service..*.*(..))")
    public void myPoint() {
    }

    @Before(POINT_CUT)
    public void before() {
        System.out.println("before............");
    }

    @After(POINT_CUT)
    public void after() {
        System.out.println("after..............");
    }

    @AfterReturning(POINT_CUT)
    public void afterReturning() {
        System.out.println("afterReturning...........");
    }

    @AfterThrowing("myPoint()")
    public void afterThrowing() {
        System.out.println("AfterThrowing...........");
    }

    @Around("myPoint()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("around  before  ============");
        Object proceed = jp.proceed();
        System.out.println("around  after  ============");

        return proceed;
    }


}
