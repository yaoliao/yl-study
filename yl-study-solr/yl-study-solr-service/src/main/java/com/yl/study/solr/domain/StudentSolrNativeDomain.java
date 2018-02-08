package com.yl.study.solr.domain;

import com.yl.common.utils.BeanUtil;
import com.yl.study.solr.bo.StudentBo;
import com.yl.study.solr.bo.StudentQueryBo;
import com.yl.study.solr.model.Student;
import com.yl.study.solr.service.StudentSolrService;
import com.yl.study.solr.util.MyPage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DELL
 * @date 2017/10/27
 */
@Service("studentSolrDomain")
@Transactional(rollbackFor = Exception.class)
public class StudentSolrNativeDomain implements StudentSolrDomain {

    @Resource
    private StudentSolrService studentSolrService;

    @Resource
    private SolrTemplate solrTemplate;

    /**
     * 根据名字模糊查询
     *
     * @param name 姓名
     * @return
     */
    @Override
    public List<StudentBo> findByName(String name) {
        List<Student> students = studentSolrService.findByName(name);
        return BeanUtil.convertList(students, StudentBo.class);
    }

    @Override
    public List<StudentBo> findByDescription(String description) {
        Pageable pageable = new PageRequest(0, 10);
        FacetPage<Student> facetPage = studentSolrService.findByDescription(description, pageable);
        Page<FacetFieldEntry> facetResultPage = facetPage.getFacetResultPage("name");

        List<Student> students = facetPage.getContent();

        List<FacetFieldEntry> content = facetResultPage.getContent();
        for (FacetFieldEntry f : content) {
            String value = f.getValue();
            long valueCount = f.getValueCount();
            System.out.println(value + " ______ " + valueCount);
        }

        return BeanUtil.convertList(students, StudentBo.class);
    }

    /**
     * 用queryBo查询
     *
     * @param queryBo  查询条件的BO
     * @param pageNo   当前页数
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public Page<StudentBo> findByQueryBo(StudentQueryBo queryBo, Integer pageNo, Integer pageSize) {

        // 这些查询一般写在service里面.... 以后重构
        solrTemplate.setSolrCore("collection1");
        SolrClient solrClient = solrTemplate.getSolrClient();

        //添加查询条件  *:* 表示全部查询
        SolrQuery query = new SolrQuery("*:*");

        //添加查询过滤条件
        if (queryBo != null && queryBo.getId() != null) {
            query.addFilterQuery("id:" + queryBo.getId());
        }
        if (queryBo != null && queryBo.getAge() != null) {
            query.addFilterQuery("age:" + queryBo.getAge());
        }

        String minAge = "*";
        String maxAge = "*";
        if (queryBo != null && queryBo.getMinAge() != null) {
            minAge = queryBo.getMinAge().toString();
        }
        if (queryBo != null && queryBo.getMaxAge() != null) {
            maxAge = queryBo.getMaxAge().toString();
        }
        query.addFilterQuery("age: [" + minAge + " TO " + maxAge + "]");

        if (queryBo != null && queryBo.getHasPosition() != null) {
            if (queryBo.getHasPosition()) {
                query.addFilterQuery("position:*");
            } else {
                //排除有position字段的列
                query.addFilterQuery("-position:*");
            }
        }

        //下面依次添加过滤条件
        // ......


        query.setStart(pageNo * pageSize);
        query.setRows(pageSize);

        try {
            QueryResponse response = solrClient.query(query);
            List<Student> students = response.getBeans(Student.class);
            Integer numFound = Integer.parseInt(String.valueOf(response.getResults().getNumFound()));
            List<StudentBo> studentBos = BeanUtil.convertList(students, StudentBo.class);

            return new MyPage<>(pageNo, pageSize, studentBos, numFound);

        } catch (SolrServerException | IOException e) {
            throw new RuntimeException("Solr查询出错 : " + e.getMessage());
        }
    }


    /**
     * 根据groupName 分组查询
     * <p>
     * <p>
     * 查询条件 GROUP_LIMIT:10  setRows:10
     * 输出结果
     * NGroups : 3
     * ID: 1 name: Jack description: hello i am jack age: 22
     * ID: 3 name: Tom description: Tom Tom Tom age: 22
     * ID: 4 name: 李梅 description: 我就是大名鼎鼎的李梅 age: 22
     * ID: 2 name: Rose description: i am Rose age: 21
     * ID: 5 name: 韩磊磊 description: 我是韩磊磊，不是李梅 age: 23
     * <p>
     * 查询条件 GROUP_LIMIT:1  setRows:10
     * 输出结果
     * NGroups : 3
     * ID: 1 name: Jack description: hello i am jack age: 22
     * ID: 2 name: Rose description: i am Rose age: 21
     * ID: 5 name: 韩磊磊 description: 我是韩磊磊，不是李梅 age: 23
     * <p>
     * 查询条件 GROUP_LIMIT:10  setRows:1
     * 输出结果
     * NGroups : 3
     * ID: 1 name: Jack description: hello i am jack age: 22
     * ID: 3 name: Tom description: Tom Tom Tom age: 22
     * ID: 4 name: 李梅 description: 我就是大名鼎鼎的李梅 age: 22
     * <p>
     * 查询条件 GROUP_LIMIT:10  setRows:2
     * 输出结果
     * NGroups : 3
     * ID: 1 name: Jack description: hello i am jack age: 22
     * ID: 3 name: Tom description: Tom Tom Tom age: 22
     * ID: 4 name: 李梅 description: 我就是大名鼎鼎的李梅 age: 22
     * ID: 2 name: Rose description: i am Rose age: 21
     *
     * @param groupName
     * @return
     */
    @Override
    public List<StudentBo> findByGroup(String groupName) {

        solrTemplate.setSolrCore("collection1");
        SolrClient solrClient = solrTemplate.getSolrClient();
        SolrQuery solrQuery = new SolrQuery("*:*");

        //分组查询
        //是否分组
        solrQuery.setParam(GroupParams.GROUP, true);

        //分组的域（此处以age进行分组）
        solrQuery.setParam(GroupParams.GROUP_FIELD, groupName);

        //每组显示的个数，默认为1
        solrQuery.setParam(GroupParams.GROUP_LIMIT, "10");

        //是否计算所得分组个数；注意：当每个分组显示数目大于1个时，不能用分组数量来计算总页码
        solrQuery.setParam("group.ngroups", true);

        //solrQuery.setStart(0); //起始索引值

        //显示几条数据(显示几组)
        solrQuery.setRows(10);

        List<StudentBo> studentBos = new ArrayList<>();

        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            if (queryResponse != null) {
                GroupResponse groupResponse = queryResponse.getGroupResponse();
                if (groupResponse != null) {
                    List<GroupCommand> values = groupResponse.getValues();
                    for (GroupCommand groupCommand : values) {
                        System.out.println("NGroups : " + groupCommand.getNGroups().longValue());
                        for (Group group : groupCommand.getValues()) {
                            SolrDocumentList result = group.getResult();
                            for (SolrDocument solrDocument : result) {
                                System.out.println("ID: " + solrDocument.getFieldValue("id")
                                        + " name: " + solrDocument.getFieldValue("name")
                                        + " description: " + solrDocument.getFieldValue("description")
                                        + " age: " + solrDocument.getFieldValue("age"));

                            }
                        }
                    }
                }
            }

        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        return studentBos;
    }
}
