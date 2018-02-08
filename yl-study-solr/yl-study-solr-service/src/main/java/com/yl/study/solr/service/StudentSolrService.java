package com.yl.study.solr.service;

import com.yl.study.solr.model.Student;
import com.yl.study.solr.repository.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DELL
 * @date 2017/10/27
 */
@Service
public class StudentSolrService {

    @Resource
    private StudentRepository studentRepository;

    /**
     * 保存单个文档
     *
     * @param document 文档
     */
    public void save(Student document) {
        studentRepository.save(document);
    }

    /**
     * 保存批量文档
     *
     * @param documents
     */
    public void save(List<Student> documents) {
        studentRepository.save(documents);
    }

    /**
     * 删除全部数据
     */
    public void deleteAll() {
        this.studentRepository.deleteAll();
    }

    /**
     * 删除单个数据
     *
     * @param document
     */
    public void deleteOne(Student document) {
        this.studentRepository.delete(document);
    }

    public FacetPage<Student> findByDescription(String description, Pageable pageable) {
        return studentRepository.findByDescription(description, pageable);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

}
