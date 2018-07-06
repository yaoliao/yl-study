package com.yl.study.example17.compiler;

/**
 * @author 小新
 * @date 2018/5/15
 */
public interface Compiler {

    Class<?> compile(String code, ClassLoader classLoader);
}
