package com.yl.study;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.yl.study.bo.EventDefineBo;
import com.yl.study.domain.EventDefineDomain;
import org.junit.Test;

/**
 * Created by DELL on 2017/11/2.
 */
public class EventDefineDomainTest {

    public <T> T getService(Class<T> clazz) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("eventDefineDomainTest");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://116.196.120.187:2181");
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
        EventDefineDomain eventDefineDomain = getService(EventDefineDomain.class);
        EventDefineBo defineBo = eventDefineDomain.getByCode("car-saleAuditPass-event");
        if (defineBo != null) {
            System.out.println(defineBo.toString());
        }
    }

}
