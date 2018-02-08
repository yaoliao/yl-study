package com.yl.study.event.rocketmq.annotation;

import com.yl.study.event.rocketmq.consumer.ConsumerBuildAdapter;

import javax.annotation.Resource;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DELL on 2017/10/30.
 */
@Resource
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Consumer {

    /**
     * 业务定义Code
     *
     * @return
     */
    String value();

    /**
     * 指定groupName
     *
     * @return
     */
    String groupName() default "";

    /**
     * 指定Topic
     *
     * @return
     */
    String topic() default "";

    /**
     * 指定Tag
     *
     * @return
     */
    String subscribe() default "";


    /**
     * 自定义Cunsumer创建适配
     *
     * @return
     */
    Class<? extends ConsumerBuildAdapter> buildAdapter() default ConsumerBuildAdapter.class;

}
