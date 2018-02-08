package com.yl.study.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.util.concurrent.Striped;
import com.yl.redis.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

/**
 * V2修改:
 * <p>
 * 因为CacheTemplateService在spring加载后是单例的，即便是不同的key进来都会因 synchronized 而阻塞
 * 所以使用 Striped<Lock> 实现对key的细粒度锁
 * 双重锁检查 + 细粒度锁  完美啊
 *
 * @author DELL
 * @date 2017/11/10
 */
@Service
public class CacheTemplateServiceV2 {

    /**
     * 创建一个弱引用的Striped<Lock>
     */
    private static final Striped<Lock> LOCK_STRIPED = Striped.lazyWeakLock(1024);

    private static final String NULL = "null";

    @Resource
    private RedisUtil redisUtil;

    /**
     * 缓存模板方法
     *
     * @param key         查询的key
     * @param seconds     过期时间
     * @param clazz       返回的数据类型
     * @param cacheLoader 模板类,DB查询的抽象接口
     * @param <T>         返回的数据类型
     * @return
     */
    public <T> T findDataTemplate(String key, Integer seconds, TypeReference<T> clazz, CacheLoader<T> cacheLoader) {

        Lock lock = LOCK_STRIPED.get(key);
        String json = redisUtil.getString(key);

        if (json == null || "".equals(json) || NULL.equalsIgnoreCase(json)) {
            try {
                lock.lock();
                json = redisUtil.getString(key);
                if (json == null || "".equals(json) || NULL.equalsIgnoreCase(json)) {
                    T t = cacheLoader.load();
                    redisUtil.setString(key, JSON.toJSONString(t), seconds);
                    return t;
                }
                return JSON.parseObject(json, clazz);
            } finally {
                lock.unlock();
            }
        }
        return JSON.parseObject(json, clazz);
    }
}
