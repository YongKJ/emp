package com.etc.service;

import com.etc.domain.Classes;

import java.util.List;

public interface ClassesService {

    Classes getClassesById(int id);

    List<Classes> getAllClasses();

    void addClasses(Classes classes);

    void modClassesById(Classes classes);

    void delClassesById(int id);

}
