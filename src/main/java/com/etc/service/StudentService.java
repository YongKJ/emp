package com.etc.service;

import com.etc.domain.Student;

import java.util.List;

public interface StudentService {

    Student getStudentById(int id);

    List<Student> getAllStudent();

    void addStudent(Student student);

    void modStudentById(Student student);

    void delStudentById(int id);

}
