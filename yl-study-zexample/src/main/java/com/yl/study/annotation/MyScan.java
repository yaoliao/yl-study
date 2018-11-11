package com.yl.study.annotation;

import java.lang.annotation.*;

/**
 * @author yaoliao
 * @date 2018/11/11
 * @time 21:27
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MyScan {

    String value() default "";
}
