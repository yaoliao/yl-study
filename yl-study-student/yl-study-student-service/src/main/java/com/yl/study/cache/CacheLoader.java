package com.yl.study.cache;

/**
 * @author DELL
 * @date 2017/10/31
 */
public interface CacheLoader<T> {

    /**
     * 模板类,DB查询的抽象接口
     *
     * @return
     */
    T load();

}
