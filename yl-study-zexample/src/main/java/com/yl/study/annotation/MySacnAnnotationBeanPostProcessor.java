package com.yl.study.annotation;

import com.sun.org.apache.regexp.internal.RE;
import com.yl.study.annotation.demo.DemoServiceB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

/**
 * @author yaoliao
 * @date 2018/11/11
 * @time 21:30
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class MySacnAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
        implements MergedBeanDefinitionPostProcessor, ApplicationContextAware, BeanClassLoaderAware, PriorityOrdered, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySacnAnnotationBeanPostProcessor.class);

    public static final String BEAN_NAME = "mySacnAnnotationBeanPostProcessor";

    private final ConcurrentHashMap<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);

    private ApplicationContext applicationContext;

    private ClassLoader classLoader;


    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        InjectionMetadata myScanMetadata = findMyScanMetadata(beanName, bean.getClass(), pvs);
        try {
            myScanMetadata.inject(bean, beanName, pvs);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return pvs;
    }

    private InjectionMetadata findMyScanMetadata(String beanName, Class<?> clazz, PropertyValues pvs) {
        String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
        InjectionMetadata metadata = injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
            synchronized (injectionMetadataCache) {
                metadata = injectionMetadataCache.get(cacheKey);
                if (InjectionMetadata.needsRefresh(metadata, clazz)) {
                    if (metadata != null) {
                        metadata.clear(pvs);
                    }
                    metadata = InjectionMetadata(clazz);
                    injectionMetadataCache.put(cacheKey, metadata);
                }
            }
        }
        return metadata;
    }

    private InjectionMetadata InjectionMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        List<InjectionMetadata.InjectedElement> fieldScanMetadata = findFieldScanMetadata(beanClass);
        elements.addAll(fieldScanMetadata);
        return new InjectionMetadata(beanClass, elements);
    }

    private List<InjectionMetadata.InjectedElement> findFieldScanMetadata(final Class<?> beanClass) {

        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();

        ReflectionUtils.doWithFields(beanClass, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                MyScan annotation = getAnnotation(field, MyScan.class);
                if (annotation != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (LOGGER.isWarnEnabled()) {
                            LOGGER.warn("@MyScan annotation is not supported on static fields: " + field);
                        }
                        return;
                    }
                    elements.add(new MyScanFieldElement(field, annotation));
                }
            }
        });
        return elements;
    }

    private class MyScanFieldElement extends InjectionMetadata.InjectedElement {

        private final Field field;

        private final MyScan myScan;

        public MyScanFieldElement(Field field, MyScan myScan) {
            super(field, null);
            this.field = field;
            this.myScan = myScan;
        }

        @Override
        protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
            //super.inject(target, requestingBeanName, pvs);
            Class<?> type = field.getType();
            Object proxy = MyInvocationHandler.getProxy(type, myScan);
            ((DemoServiceB)proxy).call();
            System.out.println("=============");
            ReflectionUtils.makeAccessible(field);
            field.set(target, proxy);
        }
    }


    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        if (beanType != null) {
            InjectionMetadata metadata = findMyScanMetadata(beanName, beanType, null);
            metadata.checkConfigMembers(beanDefinition);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void destroy() throws Exception {

    }
}
