package com.yl.study.solr.domain;

import com.yl.study.solr.bo.StudentBo;
import com.yl.study.solr.bo.StudentQueryBo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author DELL
 * @date 2017/10/27
 */
public interface StudentSolrDomain {

    /**
     * 根据名字模糊查询
     *
     * @param name 姓名
     * @return
     */
    List<StudentBo> findByName(String name);

    /**
     * 根据描述查询  使用@Facet
     *
     * @param description 描述
     * @return
     */
    List<StudentBo> findByDescription(String description);


    /**
     * 用queryBo查询
     *
     * @param queryBo  查询条件的BO
     * @param pageNo   当前页数
     * @param pageSize 每页大小
     * @return
     */
    Page<StudentBo> findByQueryBo(StudentQueryBo queryBo, Integer pageNo, Integer pageSize);

    /**
     * 根据age 分组查询
     *
     * @param groupName 分组的字段名
     * @return
     */
    List<StudentBo> findByGroup(String groupName);

}
