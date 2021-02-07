package com.etc.service.impl;

import com.etc.domain.Student;
import com.etc.mapper.StudentMapper;
import com.etc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student getStudentById(int id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void modStudentById(Student student) {
        studentMapper.modStudentById(student);
    }

    @Override
    public void delStudentById(int id) {
        studentMapper.delStudentById(id);
    }
}
