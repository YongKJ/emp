package com.etc.mapper;

import com.etc.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    Student getStudentById(@Param("id") int id);

    List<Student> getAllStudent();

    void addStudent(Student student);

    void modStudentById(Student student);

    void delStudentById(@Param("id") int id);

}
