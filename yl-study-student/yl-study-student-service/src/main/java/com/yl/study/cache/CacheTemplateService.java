package com.yl.study.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yl.redis.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * service 缓存模板类
 * <p>
 * http://www.jianshu.com/p/93767dac6b56?utm_source=desktop&utm_medium=timeline
 *
 * <p>
 * 修改:
 * @see CacheTemplateServiceV2
 * 因为CacheTemplateService在spring加载后是单例的，即便是不同的key进来都会因 synchronized 而阻塞
 * 所以使用 Striped<Lock> 实现对key的细粒度锁
 *
 * 新方法：如果要使用的所得个数是确定的 则可以使用:
 * 关键在于 LOCK[i] <========  分段锁(算吗？)
 *
 *  private static final Object[] LOCKS = new Object[]{new Object(), new Object(), new Object()};
 *
 *  synchronized (LOCK[i]) {
 *      json = redisUtil.getString(key);
 *      if (json == null || "".equals(json) || NULL.equalsIgnoreCase(json)) {
 *          T t = cacheLoader.load();
 *          redisUtil.setString(key, JSON.toJSONString(t), seconds);
 *          return t;
 *      }
 *      return JSON.parseObject(json, clazz);
 *  }
 *
 * @author DELL
 * @date 2017/10/31
 */
@Service
    public class CacheTemplateService {

    @Resource
    private RedisUtil redisUtil;

    private static final Object LOCK = new Object();

    private static final String NULL = "null";



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

        String json = redisUtil.getString(key);
        if (json == null || "".equals(json) || NULL.equalsIgnoreCase(json)) {
            synchronized (LOCK) {
                json = redisUtil.getString(key);
                if (json == null || "".equals(json) || NULL.equalsIgnoreCase(json)) {
                    T t = cacheLoader.load();
                    redisUtil.setString(key, JSON.toJSONString(t), seconds);
                    return t;
                }
                return JSON.parseObject(json, clazz);
            }
        }
        return JSON.parseObject(json, clazz);
    }


}
