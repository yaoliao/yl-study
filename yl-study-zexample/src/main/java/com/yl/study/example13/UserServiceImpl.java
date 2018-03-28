package com.yl.study.example13;

/**
 * @author DELL
 * @date 2018/3/28
 */
public class UserServiceImpl implements UserService {

    @Override
    public void txMethod1() {  //有事务
        txMethod2();
    }

    @Override
    public void method() {  //无事务
        txMethod2();
    }

    @Override
    public void txMethod2() {  //有事务

    }
}
