package com.yl.zk;

/**
 * @author Administrator
 * @date 2018/8/29 0029
 * @time 下午 17:12
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface Client {

    void create(String path, boolean ephemeral);

    void delete(String path);

    boolean isConnect();

    void doClose();

    void addListern(String path);

}
