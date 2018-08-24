package com.yl.study.cache;

import com.alibaba.fastjson.TypeReference;

/**
 * @author Administrator
 * @date 2018/8/24 0024
 * @time 下午 16:47
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public abstract class Cache<T> implements CacheLoader<T>{


    public TypeReference<T> getRef() {
        return new TypeReference<T>() {
        };
    }

    //public abstract T load();

}
