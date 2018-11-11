package com.yl.study.annotation.demo;

import com.yl.study.annotation.MyScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author yaoliao
 * @date 2018/11/11
 * @time 23:50
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class DemoServiceA {

    //@Autowired
    @MyScan
    private DemoServiceB demoServiceB;

    public void call() {
        System.out.println("-----------  DemoServiceA#call -----------------");
        String call = demoServiceB.call();
    }

}
