package com.etc.service.impl;

import com.etc.domain.Emp;
import com.etc.mapper.EmpMapper;
import com.etc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    @Override
    public Emp getEmpById(int id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public Emp getEmpByNoAndPass(String no, String pass) {
        return empMapper.getEmpByNoAndPass(no, pass);
    }

    @Override
    public List<Emp> getAllEmp() {
        return empMapper.getAllEmp();
    }

    @Override
    public void addEmp(Emp emp) {
        empMapper.addEmp(emp);
    }

    @Override
    public void modEmpById(Emp emp) {
        empMapper.modEmpById(emp);
    }

    @Override
    public void delEmpById(int id) {
        empMapper.delEmpById(id);
    }
}
