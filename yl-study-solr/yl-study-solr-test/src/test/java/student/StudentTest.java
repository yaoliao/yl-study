package student;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.yl.study.solr.bo.StudentBo;
import com.yl.study.solr.bo.StudentQueryBo;
import com.yl.study.solr.domain.StudentSolrDomain;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by DELL on 2017/10/26.
 */
public class StudentTest {

    public <T> T getService(Class<T> clazz) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("studentSolrDomainTest");

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
        StudentSolrDomain studentDomain = getService(StudentSolrDomain.class);
        List<StudentBo> studentBos = studentDomain.findByName("o");
        if (!CollectionUtils.isEmpty(studentBos)) {
            for (StudentBo s : studentBos) {
                System.out.println(s.getName() + " _ " + s.getDescription());
            }
        }
    }

    @Test
    public void findByDescription() {
        StudentSolrDomain studentDomain = getService(StudentSolrDomain.class);
        List<StudentBo> studentBos = studentDomain.findByDescription("李梅");
        if (!CollectionUtils.isEmpty(studentBos)) {
            for (StudentBo s : studentBos) {
                System.out.println(s.getName() + " _ " + s.getDescription());
            }
        }
    }


    @Test
    public void findByQueryBo() {
        StudentSolrDomain studentDomain = getService(StudentSolrDomain.class);
        StudentQueryBo queryBo = new StudentQueryBo();
//        queryBo.setAge(22);
        queryBo.setHasPosition(false);
        Page<StudentBo> page = studentDomain.findByQueryBo(queryBo, 0, 100);
        List<StudentBo> content = page.getContent();
        System.out.println(page.getNumber() + " __ " + page.getSize());
        if (!CollectionUtils.isEmpty(content)) {
            for (StudentBo s : content) {
                System.out.println(s.getName() + " _ " + s.getDescription());
            }
        }

    }

    @Test
    public void findByGroup() {
        StudentSolrDomain studentDomain = getService(StudentSolrDomain.class);
        List<StudentBo> studentBos = studentDomain.findByGroup("age");

    }


}
