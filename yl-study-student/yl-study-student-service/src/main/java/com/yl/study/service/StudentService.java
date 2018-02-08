package com.yl.study.service;

import com.yl.study.mapper.StudentMapper;
import com.yl.study.model.Student;
import com.yl.study.model.StudentExample;
import com.yl.study.pojo.StudentPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DELL
 * @date 2017/10/26
 */
@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    public Student getByID(Integer ID) {
        return studentMapper.selectByPrimaryKey(ID);
    }

    public List<Student> findByAge(Integer age) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andAgeEqualTo(age);
        return studentMapper.selectByExample(studentExample);
    }


    public List<StudentPojo> findStudentPojos() {
        return studentMapper.findStudentPojos();
    }

    public void save(Student student) {

        studentMapper.insertSelective(student);
    }
}
