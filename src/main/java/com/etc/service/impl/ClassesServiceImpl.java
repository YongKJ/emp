package com.etc.service.impl;

import com.etc.domain.Classes;
import com.etc.mapper.ClassesMapper;
import com.etc.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    ClassesMapper classesMapper;

    @Override
    public Classes getClassesById(int id) {
        return classesMapper.getClassesById(id);
    }

    @Override
    public List<Classes> getAllClasses() {
        return classesMapper.getAllClasses();
    }

    @Override
    public void addClasses(Classes classes) {
        classesMapper.addClasses(classes);
    }

    @Override
    public void modClassesById(Classes classes) {
        classesMapper.modClassesById(classes);
    }

    @Override
    public void delClassesById(int id) {
        classesMapper.delClassesById(id);
    }
}
