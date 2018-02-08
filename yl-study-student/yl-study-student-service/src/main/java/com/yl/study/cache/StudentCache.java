package com.yl.study.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yl.common.utils.JSONUtils;
import com.yl.redis.RedisUtil;
import com.yl.study.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2017/10/26
 */
@Component
public class StudentCache {

    /**
     * key值
     */
    public static final String STUDENT_KEY = "student_";

    /**
     * 失效时间(单位:秒)
     */
    public static final Integer EXPIRE_TIME = 60 * 60 * 24;

    /**
     * 锁
     */
    public static final Object LOCK = new Object();

    @Resource
    private RedisUtil redisUtil;

    public void setStudent(Student student) {
        if (student == null || student.getId() == null) {
            throw new RuntimeException("参数有误");
        }

        Integer id = student.getId();
        String jstr = JSONUtils.toJSONString(student);
        String value = redisUtil.getString(STUDENT_KEY + id);
        if (value == null) {
            synchronized (LOCK) {
                value = redisUtil.getString(STUDENT_KEY + id);
                if (value == null) {
                    redisUtil.setString(STUDENT_KEY + id, jstr, EXPIRE_TIME);
                }
            }
        }
    }

    public Student getStudent(Integer id) {
        if (id == null) {
            throw new RuntimeException("参数有误");
        }
        String value = redisUtil.getString(STUDENT_KEY + id);
        return JSON.parseObject(value, new TypeReference<Student>() {
        });
    }

}
