package com.yl.study.solr.repository;

import com.yl.study.solr.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * @author DELL
 * @date 2017/10/27
 */
public interface StudentRepository extends SolrCrudRepository<Student, String> {


    /**
     * 根据Description查找
     * <p>
     * 注解Facet作用:对查询出的结果根据指导字段（fields = "name"）进行数量的统计
     *
     * @param description 描述
     * @param pageable    分页
     * @return
     */
    @Query(value = "description:?0")
    @Facet(fields = "name", limit = 100, minCount = 0)
    FacetPage<Student> findByDescription(String description, Pageable pageable);

    /**
     * 根据名字模糊查找
     *
     * @param name 名字
     * @return
     */
    @Query(value = "name:*?0*")
    List<Student> findByName(String name);
}
