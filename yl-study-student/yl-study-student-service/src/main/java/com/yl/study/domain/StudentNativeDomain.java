package com.yl.study.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yl.common.utils.BeanUtil;
import com.yl.study.annotation.ToJson;
import com.yl.study.bo.StudentBo;
import com.yl.study.cache.Cache;
import com.yl.study.cache.CacheTemplateService;
import com.yl.study.cache.StudentCache;
import com.yl.study.event.EventConfig;
import com.yl.study.event.rocketmq.processor.MessageContext;
import com.yl.study.event.rocketmq.producer.EventBasicMessageProducer;
import com.yl.study.event.rocketmq.producer.Producer;
import com.yl.study.model.Student;
import com.yl.study.pojo.StudentPojo;
import com.yl.study.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/10/26.
 */
@Service("studentDomain")
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class StudentNativeDomain implements StudentDomain {

    /**
     * 缓存过期时间
     */
    private static final Integer EXPIRATION_TIME = 60 * 60;

    @Resource
    private StudentService studentService;
    @Resource
    private StudentCache studentCache;
    @Resource
    private Producer producer;
    @Resource
    private CacheTemplateService cacheTemplateService;
    @Resource
    private EventBasicMessageProducer eventBasiceMessageProducer;

    @Override
    @ToJson
    public StudentBo getByID(Integer id) {
        System.out.println("method getByID ..........");
        Student sCache = studentCache.getStudent(id);
        if (sCache != null) {
            return BeanUtil.convert(sCache, StudentBo.class);
        }
        Student student = studentService.getByID(id);
        studentCache.setStudent(student);
        return BeanUtil.convert(student, StudentBo.class);
    }

    @Override
    public List<StudentBo> findByAge(Integer age) {
        List<Student> students = studentService.findByAge(age);
        return BeanUtil.convertList(students, StudentBo.class);
    }


    /**
     * 利用缓存模板的查询
     *
     * @param age 年龄
     * @return
     */
    @Override
    public List<StudentBo> findCacheByAge(Integer age) {

        List<Student> students = cacheTemplateService.findDataTemplate("student_age_" + age, EXPIRATION_TIME, new TypeReference<List<Student>>() {
        }, () -> studentService.findByAge(age));


        cacheTemplateService.findDataTemplate1("student_age_" + age, EXPIRATION_TIME, new Cache<List<Student>>() {
            @Override
            public List<Student> load() {
                return studentService.findByAge(age);
            }
        });

        return BeanUtil.convertList(students, StudentBo.class);
    }

    @Override
    public void sendEvent() {
        Integer bizPrimaryId = 2333333;
        Map<String, String> map = new HashMap<>();
        map.put("name", "yl");
        map.put("age", "13");
        String extend = JSON.toJSONString(map);
//        producer.sendTest(EventConfig.GROUP_NAME, EventConfig.TOPIC, EventConfig.TAG, bizPrimaryId, extend);

//        MessageContext messageContext = new MessageContext("student", "student-test", 2233, null, null);

//        producer.send(EventConfig.GROUP_NAME, EventConfig.TOPIC, EventConfig.TAG, messageContext);


        eventBasiceMessageProducer.send(EventConfig.BIZ_DEFINE_CODE, EventConfig.NODE_DEFINE_CODE, bizPrimaryId, extend);
    }

    //==============================================================


    /**
     * mybatis <collection> 标签学习
     *
     * @return
     */
    @Override
    public List<StudentPojo> findStudentPojos() {
        return studentService.findStudentPojos();
    }

    //==========    事务的测试     ===================================================

    /**
     * 事务的测试方法
     */
    @Override
    public void save(Integer i) {
        Student student = new Student();
        student.setName("Jerry_" + i);
        student.setMobile("18357876563");
        student.setDescription("这是Jerry Mouse");
        student.setAge(20);
        studentService.save(student);

    }
}
