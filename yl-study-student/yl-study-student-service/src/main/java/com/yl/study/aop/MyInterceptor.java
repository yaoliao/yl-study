package com.yl.study.aop;

import com.alibaba.fastjson.JSON;
import com.yl.study.annotation.ToJson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by DELL on 2018/1/4.
 */
@Aspect
@Component
public class MyInterceptor {

    private final String POINT_CUT = "execution(public * com.yl.study.domain..*.*(..))";

    @Pointcut("execution(public * com.yl.study.domain..*.*(..))")
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
        /*System.out.println("Around  before..............");
        //获取参数
        Object[] args = jp.getArgs();
        args[0] = 2;
        // 以改变后的参数去执行目标方法，并保存目标方法执行后的返回值
        Object proceed = jp.proceed(args);
        String string = JSON.toJSONString(proceed);
        System.out.println(string);
        System.out.println("Around  after..............");*/

        Object proceed = jp.proceed();

        Signature sig = jp.getSignature();
        MethodSignature msig;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = jp.getTarget();
        Method m = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());



        /*Signature s = jp.getSignature();
        MethodSignature ms = (MethodSignature)s;
        Method m = ms.getMethod();*/
        if(m.isAnnotationPresent(ToJson.class)){
            System.out.println(JSON.toJSONString(proceed));
        }

        return proceed;
    }

}
