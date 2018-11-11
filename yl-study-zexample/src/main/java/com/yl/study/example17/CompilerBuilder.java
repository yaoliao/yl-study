package com.yl.study.example17;


import com.alibaba.dubbo.common.compiler.support.JdkCompiler;
import com.yl.study.example17.compiler.JavassistCompiler;

/**
 * @author 小新
 * @date 2018/5/15
 */

public class CompilerBuilder {

    private static final String code = "package com.yl.study.example17;\n" +
            "public class MyTestBean1 implements com.yl.study.example17.MyBeanInterface{\n" +
            "    public void say(String name) {\n" +
            "        System.out.println(\"我会回来的！！！！！！！\");\n" +
            "    }\n" +
            "}\n";

    public Class<?> toBuilder() {
        JavassistCompiler compiler = new JavassistCompiler();
        Class<?> aClass = compiler.compile(code, Thread.currentThread().getContextClassLoader());
        return aClass;
    }

}
