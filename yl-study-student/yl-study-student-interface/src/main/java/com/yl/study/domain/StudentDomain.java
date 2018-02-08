package com.yl.study.domain;

import com.yl.study.bo.StudentBo;
import com.yl.study.pojo.StudentPojo;

import java.util.List;

/**
 * @author DELL
 * @date 2017/10/26
 */
public interface StudentDomain {

    /**
     * 添加缓存的查询
     *
     * @param id
     * @return
     */
    StudentBo getByID(Integer id);

    /**
     * 普通查询
     *
     * @param age
     * @return
     */
    List<StudentBo> findByAge(Integer age);

    /**
     * 利用缓存模板的查询
     *
     * @param age
     * @return
     */
    List<StudentBo> findCacheByAge(Integer age);

    /**
     * 发送事件方法
     */
    void sendEvent();

    /**
     * mybatis <collection> 标签学习
     *
     * @return
     */
    List<StudentPojo> findStudentPojos();

    public void save(Integer i);

}
