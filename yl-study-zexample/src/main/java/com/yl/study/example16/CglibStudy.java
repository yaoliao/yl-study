package com.yl.study.example16;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 *
 * @author Administrator
 * @date 2018/4/12 0012
 */
public class CglibStudy {

    private static final Callback[] callbacks = new Callback[]{new CglibInterceptor(), new CglibInterceptor1()};

    public static class CglibInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.out.println("before 11111111  ===== " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after 1111111   ===== " + method.getName());
            return result;
        }
    }

    public static class CglibInterceptor1 implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.out.println("before 222222  ===== " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after 222222   ===== " + method.getName());
            return result;
        }
    }

    /**
     * CallbackFilter  CallbackFilter中的accept方法, 根据不同的method返回不同的值i, 这个值是在callbacks中的顺序,
     */
    public static class CallbackFilterImpl implements CallbackFilter {

        @Override
        public int accept(Method method) {

            if (method.getName().startsWith("go")) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(CglibTarget.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilterImpl());

        CglibTarget target = (CglibTarget) enhancer.create();

        String aaaa = target.go("aaaa");
        String bbbb = target.back("bbbb");

    }


}
