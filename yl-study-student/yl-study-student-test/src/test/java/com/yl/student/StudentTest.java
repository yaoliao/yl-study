package com.yl.student;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.fastjson.JSON;
import com.yl.study.bo.StudentBo;
import com.yl.study.domain.StudentDomain;
import com.yl.study.pojo.StudentPojo;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by DELL on 2017/10/26.
 */
public class StudentTest {

    public <T> T getService(Class<T> clazz) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("studentDomainTest");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://116.196.100.123:2181");
        ReferenceConfig<T> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能�?�成内存和连接泄�?
        reference.setInterface(clazz);
        reference.setVersion("1.0.0.0");
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setProtocol("dubbo");
        return reference.get();
    }

    @Test
    public void findByID() {
        StudentDomain studentDomain = getService(StudentDomain.class);
        StudentBo studentBo = studentDomain.getByID(1);
        System.out.println(studentBo.getDescription() + "_" + studentBo.getPosition().getContent());
    }

    /**
     * 事件测试类
     */
    @Test
    public void sendEventTest() {
        StudentDomain studentDomain = getService(StudentDomain.class);
        studentDomain.sendEvent();
    }

    @Test
    public void findCacheByAge() {
        StudentDomain studentDomain = getService(StudentDomain.class);
        List<StudentBo> studentBos = studentDomain.findCacheByAge(22);
        if (!CollectionUtils.isEmpty(studentBos)) {
            for (StudentBo s : studentBos) {
                System.out.println(s.toString());
            }
        }
    }

    @Test
    public void findStudentPojos() {
        StudentDomain studentDomain = getService(StudentDomain.class);
        List<StudentPojo> studentPojos = studentDomain.findStudentPojos();
        String string = JSON.toJSONString(studentPojos);
        System.out.println(string);
    }

    @Test
    public void save(){
        StudentDomain studentDomain = getService(StudentDomain.class);
        studentDomain.save(3);

    }


}
