package com.yl.study.annotation.demo;

import org.springframework.stereotype.Component;

/**
 * @author yaoliao
 * @date 2018/11/11
 * @time 23:50
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
//@Component
public class DemoServiceB implements DemoService {


    @Override
    public String call() {
        System.out.println("-----------   DemoServiceB#Call");
        return "DemoServiceB#call";
    }
}
